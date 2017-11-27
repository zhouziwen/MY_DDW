package com.example.bbacr.ddw.home.shopdetail;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.FragAdapter;
import com.example.bbacr.ddw.adapter.FragAdapterTwo;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.home.CommentsSumdatas;
import com.example.bbacr.ddw.bean.home.PortalDetails;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 图文详情
 */
public class DetailFragment extends BaseFragment {

    @Bind(R.id.tab_title)
    TabLayout mTabTitle;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    private PortalDetails.DatasBean mBean;
    private List<String> mTitles = new ArrayList<>();//标题集合
    private List<BaseFragment> mfragments = new ArrayList<>();//视图集合
    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mTitles.add("详细参数");
        mTitles.add("图文详情");
        mTitles.add("售后保障");
        mfragments.add(new DetailsBottomInfosCommentFragment());
        mfragments.add(new DetailsBottomInfosPicInfoFragment());
        mfragments.add(new DetailsBottomThreeFragment());
    }

    @Override
    protected void setData() {
        super.setData();
        mTabTitle.addTab(mTabTitle.newTab().setText(mTitles.get(0)));
        //初始化适配器
        FragAdapter adapter = new FragAdapter(getChildFragmentManager(), mfragments,mTitles);
        mViewpager.setOffscreenPageLimit(2);
        mViewpager.setAdapter(adapter);
        mTabTitle.setupWithViewPager(mViewpager);//让TabLayout随着ViewPager的变换而变换
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
