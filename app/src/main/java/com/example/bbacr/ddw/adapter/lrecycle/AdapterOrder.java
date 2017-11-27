package com.example.bbacr.ddw.adapter.lrecycle;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.ListBaseAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.adapter.callback.IContentCallBack;
import com.example.bbacr.ddw.adapter.callback.IOrderBack;
import com.example.bbacr.ddw.adapter.callback.IOrderCallBack;
import com.example.bbacr.ddw.adapter.recycle.BaseRecyclerAdapter;
import com.example.bbacr.ddw.adapter.recycle.RecyclerViewHolder;
import com.example.bbacr.ddw.bean.ClassifyBean;
import com.example.bbacr.ddw.bean.my.OrderDetail;
import com.example.bbacr.ddw.bean.my.OrderList;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class AdapterOrder extends ListBaseAdapter<OrderDetail.ShopOrderBean.GoodsOrdersBean> {
    private List<OrderDetail.ShopOrderBean.GoodsOrdersBean> mDatas = new ArrayList<>();
    private Context mContext;
    private RecyclerView recyclerview;
    private List<BaseRecyclerAdapter<OrderDetail.ShopOrderBean.GoodsOrdersBean.ServiceOrdersBean>> pAdapterList = new ArrayList<>();
        private IOrderBack mIOrderCallBack;
    public AdapterOrder(Context context) {
        super(context);
    }
    public AdapterOrder(Context context, List<OrderDetail.ShopOrderBean.GoodsOrdersBean> datas,IOrderBack iOrderBack) {
        super(context);
        mDatas = datas;
        mContext = context;
        mIOrderCallBack = iOrderBack;
    }
    @Override
    public void setDataList(final List<OrderDetail.ShopOrderBean.GoodsOrdersBean> list) {
        for (int i = 0; i <list.size() ; i++) {
            BaseRecyclerAdapter<OrderDetail.ShopOrderBean.GoodsOrdersBean.ServiceOrdersBean> adapter = new BaseRecyclerAdapter<OrderDetail.ShopOrderBean.GoodsOrdersBean.ServiceOrdersBean>(mContext, list.get(i).getServiceOrders()) {
                @Override
                public int getItemLayoutId(int viewType) {
                    return R.layout.car_confirmorder_recycle_list;
                }
                @Override
                public void bindData(RecyclerViewHolder holder, int position, OrderDetail.ShopOrderBean.GoodsOrdersBean.ServiceOrdersBean item) {
                    holder.setText(R.id.add_service, "【"+item.getName()).setText(R.id.add_service_money,item.getPrice()+"】");
                }
            };
            pAdapterList.add(adapter);
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int pos) {
                }
            });
        }
        super.setDataList(list);
    }
    @Override
    public void onClickBack(SuperViewHolder holder, int position, View view) {
    }
    @Override
    public int getLayoutId() {
        return  R.layout.confirm_order_list_item;
    }
    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final OrderDetail.ShopOrderBean.GoodsOrdersBean item =  getDataList().get(position);
        GlideImageView glide_icon = holder.getView(R.id.glide_icon);
        TextView shopping_name = holder.getView(R.id.shopping_name);
        TextView specification = holder.getView(R.id.specification);
        TextView shopping_money = holder.getView(R.id.shopping_money);
        TextView shopping_num = holder.getView(R.id.shopping_num);
        TextView refund = holder.getView(R.id.refund);
        if (item.getOrderStateValue() == 5) {
            refund.setVisibility(View.VISIBLE);
            refund.setText("退款");
        } else if (item.getOrderStateValue()==10) {
            refund.setVisibility(View.VISIBLE);
            refund.setText("申请售后");
        }
        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIOrderCallBack.Found(item);
            }
        });
        shopping_num.setText("X"+item.getNum());
        shopping_money.setText("￥"+item.getPayMoney());
        shopping_name.setText(item.getGoodsName());
        glide_icon.loadImage(item.getGoodsImg(), R.mipmap.ic_launcher);
        recyclerview = holder.getView(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(pAdapterList.get(position));
    }
}
