package com.example.bbacr.ddw.adapter.lrecycle;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.ListBaseAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.adapter.callback.IOrderCallBack;
import com.example.bbacr.ddw.adapter.callback.IOrderTwoCallBack;
import com.example.bbacr.ddw.adapter.recycle.BaseRecyclerAdapter;
import com.example.bbacr.ddw.adapter.recycle.RecyclerViewHolder;
import com.example.bbacr.ddw.bean.my.OrderList;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class AdapterOrderTwo extends ListBaseAdapter<OrderList.PurchaseOrdersBean> {
    private List<OrderList.PurchaseOrdersBean> mDatas = new ArrayList<>();
    private Context mContext;
    private LRecyclerView recyclerview;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private IOrderCallBack mCallBack;
    private List<AdapterOrderThree> pAdapterList = new ArrayList<>();
    //    private IOrderCallBack mIOrderCallBack;
    public AdapterOrderTwo(Context context) {
        super(context);
    }
    public AdapterOrderTwo(Context context, List<OrderList.PurchaseOrdersBean> datas,IOrderCallBack iContentCallBack) {
        super(context);
        mDatas = datas;
        mContext = context;
        mCallBack = iContentCallBack;
    }
    @Override
    public void setDataList(final List<OrderList.PurchaseOrdersBean> list) {
        for (int i = 0; i <list.size() ; i++) {
            AdapterOrderThree adapterOrder = new AdapterOrderThree(mContext, list.get(i).getShopOrders(), new IOrderTwoCallBack() {
                @Override
                public void onReceive(OrderList.PurchaseOrdersBean.ShopOrdersBean item) {
                    mCallBack.onReceive(item);
                }

                @Override
                public void onEvaluate(OrderList.PurchaseOrdersBean.ShopOrdersBean item) {
                    mCallBack.onEvaluate(item);
                }

                @Override
                public void onCancel(OrderList.PurchaseOrdersBean.ShopOrdersBean item) {
                   mCallBack.onCancel2(item);
                }

                @Override
                public void onLook(OrderList.PurchaseOrdersBean.ShopOrdersBean item) {
                    mCallBack.onLook(item);
                }

                @Override
                public void onClick(OrderList.PurchaseOrdersBean.ShopOrdersBean item) {
                    mCallBack.back(item);
                }
            });
            adapterOrder.setDataList(list.get(i).getShopOrders());
            pAdapterList.add(adapterOrder);
        }
        super.setDataList(list);
    }
    @Override
    public void onClickBack(SuperViewHolder holder, int position, View view) {
    }
    @Override
    public int getLayoutId() {
        return  R.layout.item_pending_pay2;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final OrderList.PurchaseOrdersBean item =  getDataList().get(position);
        TextView state = holder.getView(R.id.tv_order_ddmj);/*状态*/
        TextView shoppingNum = holder.getView(R.id.tv_count_all);/*数量*/
        TextView shoppingPrice = holder.getView(R.id.tv_order_hj);/*金钱*/
        TextView shoppingCancel = holder.getView(R.id.order_cancel);/*取消*/
        TextView go_pay = holder.getView(R.id.go_pay);/*去支付*/
        LinearLayout layout = holder.getView(R.id.layout);
        state.setText(item.getCode());
        shoppingNum.setText("共计"+item.getGoodsOrderNum()+"件商品("+item.getServiceNum()+"项增值服务)");
        shoppingPrice.setText("需付款："+item.getPayMoney()+"元");
        if (item.getOrderStateValue()==1) {
            layout.setVisibility(View.VISIBLE);
        } else {
            layout.setVisibility(View.GONE);
        }
        go_pay.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           mCallBack.pay(item);

         }
     });
        shoppingCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onCancel(item);
            }
        });
        recyclerview = holder.getView(R.id.recyclerview);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(pAdapterList.get(position));
        DividerDecoration divider = new DividerDecoration.Builder(mContext)
                .setHeight(R.dimen.a_1dp)
                .setColorResource(R.color.gray_background)
                .build();

        recyclerview.addItemDecoration(divider);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(mLRecyclerViewAdapter);
        recyclerview.setLoadMoreEnabled(false);
        recyclerview.setPullRefreshEnabled(false);
    }
}
