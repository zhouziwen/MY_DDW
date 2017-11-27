package com.example.bbacr.ddw.mine.coupon;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.FragAdapter;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.my.SearchUserCardsNum;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 优惠券
 */
public class MyCouponFragment extends BaseFragment {
    @Bind(R.id.tab_title)
    TabLayout mTabTitle;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    private TextView mBack;
    private List<String> mTitles = new ArrayList<>();//标题集合
    private List<BaseFragment> mfragments = new ArrayList<>();//视图集合
    private int Unused=0;
    private int Used=0;
    private int Due=0;


    public MyCouponFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_coupon, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        searchUserCardsNum();
    }

    @Override
    protected void setListener() {
        super.setListener();
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSelf();
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();

    }
    private void searchUserCardsNum() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        HttpHelper.getInstance().post(Url.searchUserCardsNum, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                SearchUserCardsNum bean = JsonUtils.parse(response, SearchUserCardsNum.class);
                if (bean.getCode() == 1) {
                  Unused=  bean.getDatas().getUnused();
                    Used=bean.getDatas().getUsed();
                    Due=bean.getDatas().getDue();
                    mTitles.add("未使用("+Unused+")");
                    mTitles.add("已使用("+Used+")");
                    mTitles.add("已过期("+Due+")");
                    mfragments.add(new NoUseCouponFragment());
                    mfragments.add(new UseCouponFragment());
                    mfragments.add(new StaleCouponFragment());
                    mTabTitle.addTab(mTabTitle.newTab().setText(mTitles.get(0)));
                    //初始化适配器
                    FragAdapter adapter = new FragAdapter(getChildFragmentManager(), mfragments, mTitles);
                    mViewpager.setOffscreenPageLimit(2);
                    mViewpager.setAdapter(adapter);
                    mTabTitle.setupWithViewPager(mViewpager);//让TabLayout随着ViewPager的变换而变换
                } else if (bean.getCode() == -1) {
                    ToastUtil.showShort(bean.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class, null);
                } else {
                    ToastUtil.showShort(bean.getMsg());
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
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
