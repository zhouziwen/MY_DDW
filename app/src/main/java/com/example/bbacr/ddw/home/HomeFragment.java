package com.example.bbacr.ddw.home;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.FragAdapter;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.my.GetUserHeadData;
import com.example.bbacr.ddw.classify.view.init.ClassDetailFragment;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.home.news.MyNewsFragment;
import com.example.bbacr.ddw.home.search.SearchFragment;
import com.example.bbacr.ddw.home.view.RecommendFragment;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 首页
 */
public class HomeFragment extends BaseFragment {
    @Bind(R.id.news)
    ImageView mNews;
    @Bind(R.id.tab_title)
    TabLayout mTabTitle;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.search)
    EditText mSearch;
    @Bind(R.id.num_news)
    TextView mNumNews;
    @Bind(R.id.tj)
    TextView mTj;
    @Bind(R.id.sj)
    TextView mSj;
    @Bind(R.id.dn)
    TextView mDn;
    @Bind(R.id.sm)
    TextView mSm;
    @Bind(R.id.pj)
    TextView mPj;
    private List<BaseFragment> mfragments = new ArrayList<>();//视图集合
    private List<String> mList = new ArrayList<>();//类型的集合

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mList.add("推荐");
        mList.add("手机");
        mList.add("电脑");
        mList.add("数码");
        mList.add("配件");
        mfragments.add(new RecommendFragment());
//        mfragments.add(new ClassDetailFragment());
//        mfragments.add(new ClassDetailFragment());
//        mfragments.add(new ClassDetailFragment());
//        mfragments.add(new ClassDetailFragment());
    }
    @Override
    protected void setListener() {
        super.setListener();
        mSj.setOnClickListener(this);
        mSm.setOnClickListener(this);
        mPj.setOnClickListener(this);
        mDn.setOnClickListener(this);
        mNews.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mTabTitle.addTab(mTabTitle.newTab().setText(mList.get(0)));
        FragAdapter adapter = new FragAdapter(getChildFragmentManager(), mfragments, mList);
        mViewpager.setAdapter(adapter);
//        mViewpager.setOffscreenPageLimit(4);
        mTabTitle.setupWithViewPager(mViewpager);//让TabLayout随着ViewPager的变换而变换
        getUserHeadData();
    }

    @Override
    protected void setData() {
        super.setData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news:
                ShowFragmentUtils.showFragment(getActivity(), MyNewsFragment.class, FragmentTag.MYNEWSFRAGMENT, null, true);
                break;
            case R.id.search:
                ShowFragmentUtils.showFragment(getActivity(), SearchFragment.class, FragmentTag.SEARCHFRAGMENT, mArguments, true);
                break;
            case R.id.sj:
                ShowFragmentUtils.showFragment(getActivity(), ClassDetailFragment.class, FragmentTag.CLASSDETAILFRAGMENT, mArguments, true);
                break;
            case R.id.dn:
                ShowFragmentUtils.showFragment(getActivity(), ClassDetailFragment.class, FragmentTag.CLASSDETAILFRAGMENT, mArguments, true);
                break;
            case R.id.sm:
                ShowFragmentUtils.showFragment(getActivity(), ClassDetailFragment.class, FragmentTag.CLASSDETAILFRAGMENT, mArguments, true);
                break;
            case R.id.pj:
                ShowFragmentUtils.showFragment(getActivity(), ClassDetailFragment.class, FragmentTag.CLASSDETAILFRAGMENT, mArguments, true);
                break;
        }
    }

    /**
     * 数量
     */
    private void getUserHeadData() {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        HttpHelper.getInstance().post(Url.getUserHeadData, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                closeLoadingDialog();
                LogUtils.d("getUserHeadData=" + response);
                GetUserHeadData num = JsonUtils.parse(response, GetUserHeadData.class);
                if (num.getCode() == 1) {
                    if (num.getDatas().getCount() > 0) {
                        mNumNews.setBackgroundResource(R.drawable.bg_corner_red_5);
                        mNumNews.setText(num.getDatas().getCount() + "");
                    } else if (num.getCode() == -1) {
                        ToastUtil.showShort(num.getMsg());
                        PreferenceManager.instance().removeToken();
                        mBaseActivity.showActivity(LoginActivity.class, null);
                    } else {
                        ToastUtil.showShort(num.getMsg());
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                closeLoadingDialog();
                ToastUtil.showShort(error_msg);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
