package com.example.bbacr.ddw.classify.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.RcvCommonAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.adapter.callback.IContentCallBack;
import com.example.bbacr.ddw.adapter.lrecycle.AdapterClassify;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.ClassifyBean;
import com.example.bbacr.ddw.bean.classfy.OneLevelCategory;
import com.example.bbacr.ddw.bean.classfy.TwoLevelCategory;
import com.example.bbacr.ddw.classify.view.init.ClassDetailFragment;
import com.example.bbacr.ddw.classify.view.init.ClassMoreFragment;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 分类
 */
public class ClassifyFragment extends BaseFragment {
    @Bind(R.id.lRecyclerView_left)
    LRecyclerView mLRecyclerViewLeft;
    @Bind(R.id.glide_icon)
    GlideImageView mGlideIcon;
    private LRecyclerViewAdapter mAdapterLeft;
    private RcvCommonAdapter<OneLevelCategory.DatasBean> mRcvAdapterLeft;
    private List<OneLevelCategory.DatasBean> mDatasLeft = new ArrayList<>();
    private int mLeftPosition = 0;
    @Bind(R.id.lRecyclerView_right)
    LRecyclerView mLRecyclerViewRight;
    private LRecyclerViewAdapter mAdapterRight;
    private AdapterClassify mClassify;
    private List<TwoLevelCategory.DatasBean.SecondCategoryBean> mDatasOrder = new ArrayList<>();
    public ClassifyFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classify, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mRcvAdapterLeft = new RcvCommonAdapter<OneLevelCategory.DatasBean>(getContext(), R.layout.item_super_left) {
            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
            }
            @Override
            public void setViewData(SuperViewHolder holder, final OneLevelCategory.DatasBean item, final int position) {
                holder.setText(item.getName(), R.id.tv_super_left).setBackgroupDrawable(getResources()
                        .getDrawable(R.color.white), R.id.tv_super_left)
                        .setTextColor(getResources().getColor(R.color.line_color), R.id.tv_super_left)
                        .itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mLeftPosition = position;
                        notifyDataSetChanged();

                    }
                });
                if (position == mLeftPosition) {
                    twoLevelCategory(item.getId());
                    holder.setBackgroupDrawable(getResources().getDrawable(R.color.white),
                            R.id.tv_super_left).setTextColor(getResources().getColor(R.color.red), R.id.tv_super_left);
                }
            }
            @Override
            public void setListener(SuperViewHolder holder, View view) {
            }
        };
        mAdapterLeft = new LRecyclerViewAdapter(mRcvAdapterLeft);
        mLRecyclerViewLeft.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLRecyclerViewLeft.setAdapter(mAdapterLeft);
        mLRecyclerViewLeft.setPullRefreshEnabled(false);
        mLRecyclerViewLeft.setLoadMoreEnabled(false);
        mClassify = new AdapterClassify(getContext(), null, new IContentCallBack() {
            @Override
            public void back(TwoLevelCategory.DatasBean.SecondCategoryBean.ThirdCategoryBean item) {
                ShowFragmentUtils.showFragment(getActivity(), ClassDetailFragment.class, FragmentTag.CLASSDETAILFRAGMENT,null,true);
            }

            @Override
            public void more(TwoLevelCategory.DatasBean.SecondCategoryBean item) {
                mArguments.putString("parentCategoryId", String.valueOf(item.getId()));
                ShowFragmentUtils.showFragment(getActivity(), ClassMoreFragment.class, FragmentTag.CLASSMOREFRAGMENT, mArguments, true);
            }
        });
        mAdapterRight = new LRecyclerViewAdapter(mClassify);
        mLRecyclerViewRight.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLRecyclerViewRight.setAdapter(mAdapterRight);
        mLRecyclerViewRight.setPullRefreshEnabled(false);
        mLRecyclerViewRight.setLoadMoreEnabled(false);
    }
    @Override
    protected void setListener() {
        super.setListener();
    }
    @Override
    protected void setData() {
        super.setData();
        oneLevelCategory();
    }
    /**
     * 左边的一级分类
     */
    private void oneLevelCategory() {
        HashMap<String, String> hashMap = new HashMap<>();
        HttpHelper.getInstance().post(Url.oneLevelCategory, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                OneLevelCategory category = JsonUtils.parse(response, OneLevelCategory.class);
                if (category.getCode() == 1) {
                    if (mDatasOrder != null) {
                        mDatasOrder.clear();
                    }
                    mDatasLeft.addAll(category.getDatas());
                }
                mRcvAdapterLeft.setDataList(mDatasLeft);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
            }
        });

    }

    /**
     * 右边的二级分类
     */
    private void twoLevelCategory(int parentCategoryId) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("parentCategoryId", String.valueOf(parentCategoryId));
        HttpHelper.getInstance().post(Url.twoLevelCategory, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("twoLevelCategory" + response);
                closeLoadingDialog();
                TwoLevelCategory category = JsonUtils.parse(response, TwoLevelCategory.class);
                if (category.getCode() == 1) {
                    if (mDatasOrder!=null) {
                        mDatasOrder.clear();
                    }
                    mGlideIcon.loadImage(category.getDatas().getImgUrl(), R.mipmap.ic_launcher);
                    mDatasOrder.addAll(category.getDatas().getSecondCategory());
                }
                mClassify.setDataList(mDatasOrder);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                closeLoadingDialog();
                ToastUtil.showShort(error_msg);
            }
        });
    }
    @Override
    public void onClick(View v) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
