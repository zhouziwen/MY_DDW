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
import com.example.bbacr.ddw.adapter.recycle.BaseRecyclerAdapter;
import com.example.bbacr.ddw.adapter.recycle.RecyclerViewHolder;
import com.example.bbacr.ddw.bean.ClassifyBean;
import com.example.bbacr.ddw.bean.classfy.TwoLevelCategory;
import com.example.bbacr.ddw.utils.GlideImageLoader;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */
public class AdapterClassify extends ListBaseAdapter<TwoLevelCategory.DatasBean.SecondCategoryBean>{
    private List<TwoLevelCategory.DatasBean.SecondCategoryBean> mDatas = new ArrayList<>();
    private Context mContext;
    private RecyclerView recyclerview;
    private IContentCallBack mCallBack;
    private List<BaseRecyclerAdapter<TwoLevelCategory.DatasBean.SecondCategoryBean.ThirdCategoryBean>> pAdapterList = new ArrayList<>();

//    private IOrderCallBack mIOrderCallBack;
    public AdapterClassify(Context context) {
        super(context);
    }
    public AdapterClassify(Context context, List<TwoLevelCategory.DatasBean.SecondCategoryBean> datas,IContentCallBack iContentCallBack) {
        super(context);
        mDatas = datas;
        mContext = context;
        mCallBack = iContentCallBack;
    }
    @Override
    public void setDataList(List<TwoLevelCategory.DatasBean.SecondCategoryBean> list) {
        for (int i = 0; i <list.size() ; i++) {
            BaseRecyclerAdapter<TwoLevelCategory.DatasBean.SecondCategoryBean.ThirdCategoryBean> adapter = new BaseRecyclerAdapter<TwoLevelCategory.DatasBean.SecondCategoryBean.ThirdCategoryBean>(mContext, list.get(i).getThirdCategory()) {
                @Override
                public int getItemLayoutId(int viewType) {
                    return R.layout.classify_grid;
                }
                @Override
                public void bindData(RecyclerViewHolder holder, final int position, final TwoLevelCategory.DatasBean.SecondCategoryBean.ThirdCategoryBean item) {
                    holder.setText(R.id.tv_classifcation_text, item.getName()).setGlideImageView(item.getImgUrl(),R.id.iv_classifcation_img).setClickListener(R.id.iv_classifcation_img, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mCallBack.back(item);
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
        return  R.layout.classify_lrecycle_list_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final TwoLevelCategory.DatasBean.SecondCategoryBean item =  getDataList().get(position);
        TextView textView = holder.getView(R.id.content);
        TextView more = holder.getView(R.id.more);
        if (position == 0) {
            more.setText("更多品牌");
        } else {
            more.setVisibility(View.GONE);
        }
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.more(item);
            }
        });
        textView.setText(item.getName());
       recyclerview = holder.getView(R.id.recyclerview);
        recyclerview.setLayoutManager(new GridLayoutManager(mContext,3));
        recyclerview.setAdapter(pAdapterList.get(position));
    }
}
