package com.example.bbacr.ddw.mine.coupon;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.RcvCommonAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.home.BuyRightNow;
import com.example.bbacr.ddw.content.IEvent;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.eventbus.EventBean;

import com.example.bbacr.ddw.eventbus.EventList;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 可用优惠券
 */
public class UsAbleCouponFragment extends BaseFragment {
    @Bind(R.id.lRecyclerView)
    LRecyclerView mLRecyclerView;
    private CheckBox mCheckBox;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<BuyRightNow.UseAbleListBean> mAdapter;
    private List<BuyRightNow.UseAbleListBean> mList = new ArrayList<>();
    public UsAbleCouponFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_us_able_coupon, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mAdapter = new RcvCommonAdapter<BuyRightNow.UseAbleListBean>(getContext(),R.layout.coupon_item) {
            @Override
            public void setViewData(SuperViewHolder holder, BuyRightNow.UseAbleListBean item, int position) {
                holder.setText(item.getMoney(), R.id.money).setText(item.getName(),R.id.shop_name)
                        .setText(item.getBeginTime()+item.getEndUseTime(),R.id.time1);
                mCheckBox = holder.getView(R.id.glide_icon);
            }
            @Override
            public void setListener(SuperViewHolder holder, View view) {
                holder.setOnClickListener(R.id.glide_icon);
            }
            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
                switch (view.getId()) {
                    case R.id.glide_icon:
//                        CheckBox checkBox = holder.getView(R.id.glide_icon);
                        if (mCheckBox.isChecked()) {
                            ToastUtil.showShort("1");
                            EventBus.getDefault().post(new EventBean(mList.get(position).getMoney()));
                        } else {
                            ToastUtil.showShort("2");
                            EventBus.getDefault().post(new EventList(mList.get(position).getMoney()));
                        }
                        break;
                }
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.a_5dp)
                .setColorResource(R.color.gray_background)
                .build();
        mLRecyclerView.addItemDecoration(divider);
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
            mList.addAll(rightNow.getUseAbleList());
            mAdapter.setDataList(rightNow.getUseAbleList()
            );
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
