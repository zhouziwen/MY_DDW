package com.example.bbacr.ddw.adapter.lrecycle;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.ListBaseAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.adapter.callback.IClassMoreCallBack;
import com.example.bbacr.ddw.adapter.recycle.BaseRecyclerAdapter;
import com.example.bbacr.ddw.adapter.recycle.RecyclerViewHolder;
import com.example.bbacr.ddw.bean.classfy.ThreeLevelCategory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class AdapterMoreClass extends ListBaseAdapter<ThreeLevelCategory.DatasBean> {
    private List<ThreeLevelCategory.DatasBean> mDatas = new ArrayList<>();
    private Context mContext;
    private RecyclerView recyclerview;
    private IClassMoreCallBack mCallBack;
    private List<BaseRecyclerAdapter<ThreeLevelCategory.DatasBean.ThirdCategoryBean>> pAdapterList = new ArrayList<>();

    //    private IOrderCallBack mIOrderCallBack;
    public AdapterMoreClass(Context context) {
        super(context);
    }
    public AdapterMoreClass(Context context, List<ThreeLevelCategory.DatasBean> datas,IClassMoreCallBack iContentCallBack) {
        super(context);
        mDatas = datas;
        mContext = context;
        mCallBack = iContentCallBack;
    }
    @Override
    public void setDataList(List<ThreeLevelCategory.DatasBean> list) {
        for (int i = 0; i <list.size() ; i++) {
            BaseRecyclerAdapter<ThreeLevelCategory.DatasBean.ThirdCategoryBean> adapter = new BaseRecyclerAdapter<ThreeLevelCategory.DatasBean.ThirdCategoryBean>(mContext, list.get(i).getThirdCategory()) {
                @Override
                public int getItemLayoutId(int viewType) {
                    return R.layout.classify_grid;
                }
                @Override
                public void bindData(RecyclerViewHolder holder, final int position, ThreeLevelCategory.DatasBean.ThirdCategoryBean item) {
                    holder.setText(R.id.tv_classifcation_text, item.getName()).setGlideImageView(item.getImgUrl(),R.id.iv_classifcation_img).setClickListener(R.id.iv_classifcation_img, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mCallBack.Click(position);
                        }
                    });
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
        return  R.layout.classify_recycle_list_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final ThreeLevelCategory.DatasBean item =  getDataList().get(position);
        TextView textView = holder.getView(R.id.content);
        textView.setText(item.getName());
        recyclerview = holder.getView(R.id.recyclerview);
        recyclerview.setLayoutManager(new GridLayoutManager(mContext,4));
        recyclerview.setAdapter(pAdapterList.get(position));
    }

}
