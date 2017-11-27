package com.example.bbacr.ddw.mine.coupon;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.RcvCommonAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.adapter.recycle.BaseRecyclerAdapter;
import com.example.bbacr.ddw.adapter.recycle.RecyclerViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.home.BuyRightNow;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 不可用
 */
public class UnUseCouponFragment extends BaseFragment {
    @Bind(R.id.lRecyclerView)
    LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<BuyRightNow.UnUseAbleListBean> mAdapter;
    private View mHeaderView;
    private RecyclerView mRecyclerView;
    private BaseRecyclerAdapter<BuyRightNow.TimeOutListBean> mRecyclerAdapter;
    public UnUseCouponFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_un_use_coupon, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.un_use_coupon_head_item, (ViewGroup)getActivity().findViewById(android.R.id.content), false);
        mRecyclerView = (RecyclerView) mHeaderView.findViewById(R.id.recyclerview);
        mRecyclerAdapter = new BaseRecyclerAdapter<BuyRightNow.TimeOutListBean>(getContext(),null) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.coupon_item;
            }

            @Override
            public void bindData(RecyclerViewHolder holder, int position, BuyRightNow.TimeOutListBean item) {
                holder.setText(R.id.money,item.getMoney()).setText( R.id.shop_name,item.getName())
                        .setText( R.id.time1,item.getBeginTime() + item.getEndUseTime());
                holder.setVisible(false, R.id.glide_icon);
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mAdapter = new RcvCommonAdapter<BuyRightNow.UnUseAbleListBean>(getContext(), R.layout.coupon_item) {
            @Override
            public void setViewData(SuperViewHolder holder, BuyRightNow.UnUseAbleListBean item, int position) {
                holder.setText(item.getMoney(), R.id.money).setText(item.getName(), R.id.shop_name)
                        .setText(item.getBeginTime() + item.getEndUseTime(), R.id.time1);
                holder.setVisible(false, R.id.glide_icon);
            }

            @Override
            public void setListener(SuperViewHolder holder, View view) {
            }

            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.a_5dp)
                .setColorResource(R.color.gray_background)
                .build();
        mLRecyclerView.addItemDecoration(divider);
        mLRecyclerViewAdapter.addHeaderView(mHeaderView);
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setPullRefreshEnabled(false);
        mLRecyclerView.setLoadMoreEnabled(false);
    }

    @Override
    protected void setListener() {
        super.setListener();
    }

    @Override
    protected void setData() {
        super.setData();
        if (!PreferenceManager.instance().getResponse().isEmpty()) {
            BuyRightNow rightNow = JsonUtils.parse(PreferenceManager.instance().getResponse(), BuyRightNow.class);
           mRecyclerAdapter.setDataList(rightNow.getTimeOutList());
            mAdapter.setDataList(rightNow.getUnUseAbleList());
        }
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
