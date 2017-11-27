package com.example.bbacr.ddw.adapter.lrecycle;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.ListBaseAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.adapter.callback.IClassMoreCallBack;
import com.example.bbacr.ddw.adapter.recycle.BaseRecyclerAdapter;
import com.example.bbacr.ddw.adapter.recycle.RecyclerViewHolder;
import com.example.bbacr.ddw.bean.ClassifyBean;
import com.example.bbacr.ddw.bean.detail.ShoppingDetailBean;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class AdapterShoppingDetail extends ListBaseAdapter<ShoppingDetailBean.DatasBean.CommentsListBean> {
    private List<ClassifyBean.DatasBean.ZuoDanListBean> mDatas = new ArrayList<>();
    private Context mContext;
    private RecyclerView recyclerview;
    private List<ImageView> starList = new ArrayList<>();
    private IClassMoreCallBack mCallBack;
    private List<BaseRecyclerAdapter<String>> pAdapterList = new ArrayList<>();

    //    private IOrderCallBack mIOrderCallBack;
    public AdapterShoppingDetail(Context context) {
        super(context);
    }
    public AdapterShoppingDetail(Context context, List<ClassifyBean.DatasBean.ZuoDanListBean> datas,IClassMoreCallBack iContentCallBack) {
        super(context);
        mDatas = datas;
        mContext = context;
        mCallBack = iContentCallBack;
    }
    @Override
    public void setDataList(List<ShoppingDetailBean.DatasBean.CommentsListBean> list) {
        for (int i = 0; i <list.size() ; i++) {
            String[] strs=list.get(i).getAvotorr().split(",");
            List list1= Arrays.asList(strs);
            BaseRecyclerAdapter<String> adapter = new BaseRecyclerAdapter<String>(mContext, list1) {
                @Override
                public int getItemLayoutId(int viewType) {
                    return R.layout.classify_grid_big;
                }
                @Override
                public void bindData(RecyclerViewHolder holder, int position, String item) {
                    holder.setGlideImageView(item, R.id.iv_classifcation_img);

                }
            };
            pAdapterList.add(adapter);
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int pos) {
                    mCallBack.Click(pos);
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
        return  R.layout.comment_recycle_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final ShoppingDetailBean.DatasBean.CommentsListBean item =  getDataList().get(position);
        ImageView mIvCommentStar1 = holder.getView(R.id.iv_comment_star_1);
        ImageView mIvCommentStar2 = holder.getView(R.id.iv_comment_star_2);
        ImageView mIvCommentStar3 = holder.getView(R.id.iv_comment_star_3);
        ImageView mIvCommentStar4 = holder.getView(R.id.iv_comment_star_4);
        ImageView mIvCommentStar5 = holder.getView(R.id.iv_comment_star_5);
        if (starList != null) {
            starList.clear();
            starList.add(mIvCommentStar1);
            starList.add(mIvCommentStar2);
            starList.add(mIvCommentStar3);
            starList.add(mIvCommentStar4);
            starList.add(mIvCommentStar5);
        } else {
            starList.add(mIvCommentStar1);
            starList.add(mIvCommentStar2);
            starList.add(mIvCommentStar3);
            starList.add(mIvCommentStar4);
            starList.add(mIvCommentStar5);
        }
        LinearLayout layout = holder.getView(R.id.layout);
        TextView name = holder.getView(R.id.shop_name);
        TextView comment = holder.getView(R.id.content);
        GlideImageView icon = holder.getView(R.id.pic);
        RecyclerView recyclerView = holder.getView(R.id.recyclerview);
        icon.loadCircleImage(item.getAvotorr(), R.mipmap.ic_launcher);
        TextView replay = holder.getView(R.id.reply);
        comment.setText(item.getContent());
        name.setText(item.getUserName());
        if (TextUtils.isEmpty(item.getImgUrl())) {
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(item.getReplyMessage())) {
            layout.setVisibility(View.VISIBLE);
            replay.setText(item.getReplyMessage());
        } else {
            layout.setVisibility(View.GONE);
        }
        for (int i = 0, len = starList.size(); i < len; i++) {
            starList.get(i).setImageResource(i < item.getGradedValue() ? R.mipmap.icon_comment_star_red : R.mipmap.icon_comment_star_gray);
        }
        recyclerview = holder.getView(R.id.recyclerview);
        recyclerview.setLayoutManager(new GridLayoutManager(mContext,3));
        recyclerview.setAdapter(pAdapterList.get(position));
    }
}
