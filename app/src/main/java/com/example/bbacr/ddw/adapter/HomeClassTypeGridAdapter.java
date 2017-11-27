package com.example.bbacr.ddw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.callback.IHomeClassTypeGridCallBack;
import com.example.bbacr.ddw.bean.ClassifcationBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 *
 */

public class HomeClassTypeGridAdapter extends RecyclerView.Adapter<HomeClassTypeGridAdapter.ProductViewHolder> {


    private List<ClassifcationBean> classifcationBeanList;
    private Context mContext;
    private IHomeClassTypeGridCallBack mCallBack;

    public HomeClassTypeGridAdapter(IHomeClassTypeGridCallBack callBack) {
        if (callBack != null) {
            mCallBack = callBack;
        }
    }

    public void setData(List<ClassifcationBean> classifcationBeanList){
        this.classifcationBeanList = classifcationBeanList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.classifcation_grid, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        ClassifcationBean classifcationBean = classifcationBeanList.get(position);
        if(classifcationBean!=null){
            holder.iv_classifcation_img.setBackgroundResource(classifcationBean.getClassifcationImg());
            holder.tv_classifcation_text.setText(classifcationBean.getClassifcationName());
            holder.iv_classifcation_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallBack.setOnGridItemListener(position);
                }
            });
            holder.tv_classifcation_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallBack.setOnGridItemListener(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return classifcationBeanList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_classifcation_img;
        TextView tv_classifcation_text;

        ProductViewHolder(View itemView) {
            super(itemView);
            iv_classifcation_img = (ImageView)itemView.findViewById(R.id.iv_classifcation_img);
            tv_classifcation_text = (TextView)itemView.findViewById(R.id.tv_classifcation_text);
        }
    }

}
