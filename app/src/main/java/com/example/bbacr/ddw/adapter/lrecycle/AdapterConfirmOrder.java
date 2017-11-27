package com.example.bbacr.ddw.adapter.lrecycle;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.ListBaseAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.adapter.callback.ICarCallBack;
import com.example.bbacr.ddw.adapter.recycle.BaseRecyclerAdapter;
import com.example.bbacr.ddw.adapter.recycle.RecyclerViewHolder;
import com.example.bbacr.ddw.bean.car.GetUserShoppingCart;

import com.example.bbacr.ddw.bean.home.BuyRightNow;
import com.example.bbacr.ddw.widget.SwipeMenuView;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class AdapterConfirmOrder extends ListBaseAdapter<BuyRightNow.GoodsListBean> {
    private List<BuyRightNow.GoodsListBean> mDatas = new ArrayList<>();
    private Context mContext;
    private RecyclerView recyclerview;
    private List<BaseRecyclerAdapter<BuyRightNow.GoodsListBean.AddServicesBean>> pAdapterList = new ArrayList<>();

    //    private IOrderCallBack mIOrderCallBack;
    public AdapterConfirmOrder(Context context) {
        super(context);
    }
    public AdapterConfirmOrder(Context context, List<BuyRightNow.GoodsListBean> datas) {
        super(context);
        mDatas = datas;
        mContext = context;

    }
    @Override
    public void setDataList(final List<BuyRightNow.GoodsListBean> list) {
        for (int i = 0; i <list.size() ; i++) {
            BaseRecyclerAdapter<BuyRightNow.GoodsListBean.AddServicesBean> adapter = new BaseRecyclerAdapter<BuyRightNow.GoodsListBean.AddServicesBean>(mContext, list.get(i).getAddServices()) {
                @Override
                public int getItemLayoutId(int viewType) {
                    return R.layout.car_confirmorder_recycle_list;
                }
                @Override
                public void bindData(RecyclerViewHolder holder, final int position, final BuyRightNow.GoodsListBean.AddServicesBean item) {
                    holder.setText(R.id.add_service, "【"+item.getName()).setText(R.id.add_service_money,item.getPrice()+"】");
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
        return  R.layout.confirm_order_list_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final BuyRightNow.GoodsListBean item =  getDataList().get(position);
        GlideImageView glide_icon = holder.getView(R.id.glide_icon);
        TextView shopping_name = holder.getView(R.id.shopping_name);
        TextView specification = holder.getView(R.id.specification);
        TextView shopping_money = holder.getView(R.id.shopping_money);
        TextView shopping_num = holder.getView(R.id.shopping_num);
        shopping_num.setText("X"+item.getGood().getNum());
        shopping_money.setText("￥"+item.getGood().getGoodsPrice());
//        specification.setText(item.getGood().());
        shopping_name.setText(item.getGood().getGoodName());
        glide_icon.loadImage(item.getGood().getImgUrl(), R.mipmap.ic_launcher);
        recyclerview = holder.getView(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(pAdapterList.get(position));
    }
}
