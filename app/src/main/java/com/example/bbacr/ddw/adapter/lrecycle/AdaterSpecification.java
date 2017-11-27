package com.example.bbacr.ddw.adapter.lrecycle;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.ListBaseAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.adapter.callback.IContentCallBack;
import com.example.bbacr.ddw.adapter.callback.ISpecificationCallBack;
import com.example.bbacr.ddw.adapter.recycle.BaseRecyclerAdapter;
import com.example.bbacr.ddw.adapter.recycle.RecyclerViewHolder;
import com.example.bbacr.ddw.bean.ClassifyBean;
import com.example.bbacr.ddw.bean.car.GetSpecification;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class AdaterSpecification extends ListBaseAdapter<GetSpecification.DatasBean> {
    private List<GetSpecification.DatasBean> mDatas = new ArrayList<>();
    private Context mContext;
    private int mLeftPosition = 0;
    private RecyclerView recyclerview;
    private ISpecificationCallBack mCallBack;
    private String propertiesid;
    private List<BaseRecyclerAdapter<GetSpecification.DatasBean.DataBean>> pAdapterList = new ArrayList<>();

    //    private IOrderCallBack mIOrderCallBack;
    public AdaterSpecification(Context context) {
        super(context);
    }
    public AdaterSpecification(Context context, List<GetSpecification.DatasBean> datas,ISpecificationCallBack iContentCallBack) {
        super(context);
        mDatas = datas;
        mContext = context;
        mCallBack = iContentCallBack;
    }
    @Override
    public void setDataList(final List<GetSpecification.DatasBean> list) {
        for ( int i = 0; i <list.size() ; i++) {
            list.get(i).getId();
            BaseRecyclerAdapter<GetSpecification.DatasBean.DataBean> adapter = new BaseRecyclerAdapter<GetSpecification.DatasBean.DataBean>(mContext, list.get(i).getData()) {
                @Override
                public int getItemLayoutId(int viewType) {
                    return R.layout.specification_grid;
                }
                @Override
                public void bindData(RecyclerViewHolder holder, final int position, final GetSpecification.DatasBean.DataBean item) {
                    holder.setText(R.id.specification, item.getContent()).setBackgroupDrawable(mContext.getDrawable(R.drawable.def_specification),R.id.specification)
                            .setTextColor(mContext.getColor(R.color.line_color),R.id.specification).setClickListener(R.id.specification, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mLeftPosition = position;
                            notifyDataSetChanged();
                            mCallBack.click(list,item);
                        }
                    });
                    if (position==mLeftPosition) {
                        holder.setText(R.id.specification, item.getContent()).setBackgroupDrawable(mContext.getDrawable(R.drawable.def_specification_check), R.id.specification)
                                .setTextColor(mContext.getColor(R.color.red), R.id.specification);
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
        return  R.layout.specification_lrecycle_list_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final GetSpecification.DatasBean item =  getDataList().get(position);
        TextView textView = holder.getView(R.id.name);
        textView.setText(item.getName());
        item.getId();
        recyclerview = holder.getView(R.id.recyclerview);
        recyclerview.setLayoutManager(new GridLayoutManager(mContext,3));
        recyclerview.setAdapter(pAdapterList.get(position));
    }
}
