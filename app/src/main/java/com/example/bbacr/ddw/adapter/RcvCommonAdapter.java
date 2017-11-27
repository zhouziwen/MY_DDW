package com.example.bbacr.ddw.adapter;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2017/7/24 0024.
 *
 */

public abstract class RcvCommonAdapter<T> extends ListBaseAdapter<T> {

    private int mLayoutId;
    public RcvCommonAdapter(Context context, int layoutId) {
        super(context);
        mLayoutId = layoutId;
    }

    @Override
    public int getLayoutId() {
        return mLayoutId;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        T item = getDataList().get(position);
        setListener(holder,holder.itemView);
        holder.setPosition(position);
        setViewData(holder, item, position);
    }


    public abstract void setViewData(SuperViewHolder holder, T item, int position);
    public abstract void setListener(SuperViewHolder holder,View view);
}
