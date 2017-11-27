package com.example.bbacr.ddw.mine.shareteam;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.FragAdapter;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.classify.view.init.ShoppingDetailFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 分享
 */
public class ShareFragment extends BaseFragment {


    @Bind(R.id.back_img)
    ImageView mBackImg;
    @Bind(R.id.tab_title)
    TabLayout mTabTitle;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    private List<String> mTitles = new ArrayList<>();//标题集合
    private List<BaseFragment> mfragments = new ArrayList<>();//视图集合
    public ShareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mTitles.add("分享团队");
        mTitles.add("分享流水");
        mfragments.add(new ShareTeamFragment());
        mfragments.add(new ShareWaterFragment());
    }

    @Override
    protected void setListener() {
        super.setListener();
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSelf();
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        mTabTitle.addTab(mTabTitle.newTab().setText(mTitles.get(0)));
        //初始化适配器
        FragAdapter adapter = new FragAdapter(getChildFragmentManager(), mfragments,mTitles);
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
