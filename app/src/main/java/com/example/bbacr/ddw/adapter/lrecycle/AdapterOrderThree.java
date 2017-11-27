package com.example.bbacr.ddw.adapter.lrecycle;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class AdapterOrderThree extends ListBaseAdapter<OrderList.PurchaseOrdersBean.ShopOrdersBean> {
    private List<OrderList.PurchaseOrdersBean.ShopOrdersBean> mDatas = new ArrayList<>();
    private Context mContext;
    private RecyclerView recyclerview;
    private List<BaseRecyclerAdapter<OrderList.PurchaseOrdersBean.ShopOrdersBean.GoodsOrdersBean>> pAdapterList = new ArrayList<>();
        private IOrderTwoCallBack mIOrderCallBack;
    public AdapterOrderThree(Context context) {
        super(context);
    }
    public AdapterOrderThree(Context context, List<OrderList.PurchaseOrdersBean.ShopOrdersBean> datas,IOrderTwoCallBack iContentCallBack) {
        super(context);
        mDatas = datas;
        mContext = context;
        mIOrderCallBack = iContentCallBack;
    }
    @Override
    public void setDataList(final List<OrderList.PurchaseOrdersBean.ShopOrdersBean> list) {
        for (int i = 0; i <list.size() ; i++) {
            BaseRecyclerAdapter<OrderList.PurchaseOrdersBean.ShopOrdersBean.GoodsOrdersBean> adapter = new BaseRecyclerAdapter<OrderList.PurchaseOrdersBean.ShopOrdersBean.GoodsOrdersBean>(mContext, list.get(i).getGoodsOrders()) {
                @Override
                public int getItemLayoutId(int viewType) {
                    return R.layout.recycle_order_list;
                }
                @Override
                public void bindData(RecyclerViewHolder holder, int position, OrderList.PurchaseOrdersBean.ShopOrdersBean.GoodsOrdersBean item){
                    holder.setText(R.id.shopping_name, item.getGoodsName())
                            .setText(R.id.goods_num,"X"+item.getNum()).setGlideImageView(item.getGoodsImg(),R.id.glide_icon);
                    if (TextUtils.isEmpty(item.getServiceMoney())) {
                        holder.setText(R.id.goods_price, "￥" + item.getUnitPrice());
                    } else {
                        holder.setText(R.id.goods_price, "￥" + item.getUnitPrice() + "(增值服务" + item.getServiceMoney() + "元)");
                    }
                }
            };
            pAdapterList.add(adapter);
        }
        super.setDataList(list);
    }
    @Override
    public void onClickBack(SuperViewHolder holder, int position, View view) {
    }
    @Override
    public int getLayoutId() {
        return  R.layout.item_pending_pay;
    }
    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final OrderList.PurchaseOrdersBean.ShopOrdersBean item =  getDataList().get(position);
        TextView shopName = holder.getView(R.id.tv_order_dm);/*店名*/
        TextView state = holder.getView(R.id.tv_order_ddmj);/*状态*/
        TextView shoppingNum = holder.getView(R.id.tv_count_all);/*数量*/
        TextView shoppingPrice = holder.getView(R.id.tv_order_hj);/*金钱*/
        TextView shoppingSure = holder.getView(R.id.order_sure);/*确认收货*/
        TextView shoppingCancel = holder.getView(R.id.order_cancel);/*取消*/
        TextView check_cancel = holder.getView(R.id.check_cancel);/*查看物流*/
        TextView shoppingEvaluate = holder.getView(R.id.order_evaluate);/*评价*/
        TextView shoppingRefund = holder.getView(R.id.order_refund);/*退款*/
        LinearLayout layout = holder.getView(R.id.layout);
        LinearLayout Layout = holder.getView(R.id.Layout);
        shopName.setText(item.getShopName());
        state.setText(item.getOrderState());
//        shoppingNum.setText("共计"+item.getGoodsOrders().size()+"件商品");
//        shoppingPrice.setText("需付款："+item.getPayMoney()+"元");
        if (item.getOrderStateValue()==3) {
            layout.setVisibility(View.VISIBLE);
            shoppingCancel.setVisibility(View.VISIBLE);
        } else if (item.getOrderStateValue()==4) {
            layout.setVisibility(View.GONE);
        }else if (item.getOrderStateValue()==5) {
            layout.setVisibility(View.VISIBLE);
            check_cancel.setVisibility(View.VISIBLE);
            shoppingSure.setVisibility(View.VISIBLE);
        } else if (item.getOrderStateValue()==10) {
            layout.setVisibility(View.VISIBLE);
            shoppingEvaluate.setVisibility(View.VISIBLE);
        }
        shoppingSure.setOnClickListener(new View.OnClickListener() {/*确认收货*/
            @Override
            public void onClick(View v) {
                mIOrderCallBack.onReceive(item);
            }
        });
        check_cancel.setOnClickListener(new View.OnClickListener() {/*查看物流*/
            @Override
            public void onClick(View v) {
                mIOrderCallBack.onLook(item);
            }
        });
        shoppingCancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mIOrderCallBack.onCancel(item);
    }
});
        shoppingEvaluate.setOnClickListener(new View.OnClickListener() {/*评价*/
            @Override
            public void onClick(View v) {
                mIOrderCallBack.onEvaluate(item);
            }
        });

        recyclerview = holder.getView(R.id.recyclerview);
//        recyclerview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mIOrderCallBack.onClick(item);
//            }
//        });
        recyclerview.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(pAdapterList.get(position));
        pAdapterList.get(position).setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                mIOrderCallBack.onClick(item);
            }
        });

    }

}
