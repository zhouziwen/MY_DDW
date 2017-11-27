package com.example.bbacr.ddw.adapter.lrecycle;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.ListBaseAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.adapter.callback.IClassMoreCallBack;
import com.example.bbacr.ddw.adapter.recycle.BaseRecyclerAdapter;
import com.example.bbacr.ddw.adapter.recycle.RecyclerViewHolder;
import com.example.bbacr.ddw.bean.ClassifyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class AdapterPopupWindow extends ListBaseAdapter<ClassifyBean.DatasBean.ZuoDanListBean> {
    private List<ClassifyBean.DatasBean.ZuoDanListBean> mDatas = new ArrayList<>();
    private Context mContext;
    private RecyclerView recyclerview;
    private IClassMoreCallBack mCallBack;
    private boolean isCheck = false;
    private BaseRecyclerAdapter<ClassifyBean.DatasBean.ZuoDanListBean.GoodsOrderListBean> adapter;
    private List<BaseRecyclerAdapter<ClassifyBean.DatasBean.ZuoDanListBean.GoodsOrderListBean>> pAdapterList = new ArrayList<>();


    //    private IOrderCallBack mIOrderCallBack;
    public AdapterPopupWindow(Context context) {
        super(context);
    }
    public AdapterPopupWindow(Context context, List<ClassifyBean.DatasBean.ZuoDanListBean> datas,IClassMoreCallBack iContentCallBack) {
        super(context);
        mDatas = datas;
        mContext = context;
        mCallBack = iContentCallBack;
    }
    @Override
    public void setDataList(List<ClassifyBean.DatasBean.ZuoDanListBean> list) {
        for (int i = 0; i <list.size() ; i++) {
           adapter = new BaseRecyclerAdapter<ClassifyBean.DatasBean.ZuoDanListBean.GoodsOrderListBean>(isCheck,mContext, list.get(i).getGoodsOrderList()) {
                @Override
                public int getItemLayoutId(int viewType) {
                    return R.layout.popwindow_bom_item;
                }
                @Override
                public void bindData(RecyclerViewHolder holder, int position, ClassifyBean.DatasBean.ZuoDanListBean.GoodsOrderListBean item) {
                    holder.setText(R.id.tv1, item.getGoodsName());
                }
            };
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int pos) {
                    mCallBack.Click(pos);
                }
            });
            pAdapterList.add(adapter);
        }
        super.setDataList(list);
    }
    @Override
    public void onClickBack(SuperViewHolder holder, int position, View view) {
    }
    @Override
    public int getLayoutId() {
        return  R.layout.popupwindow_lrecycle_list_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final ClassifyBean.DatasBean.ZuoDanListBean item =  getDataList().get(position);
        TextView textView = holder.getView(R.id.attr_list_name);
        ImageView imageView = holder.getView(R.id.attr_list_img);
        textView.setText(item.getShopName());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        }
        });
        recyclerview = holder.getView(R.id.recyclerview);
        recyclerview.setLayoutManager(new GridLayoutManager(mContext,3));
        recyclerview.setAdapter(pAdapterList.get(position));
    }


}
