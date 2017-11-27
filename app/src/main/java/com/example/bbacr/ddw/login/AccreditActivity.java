package com.example.bbacr.ddw.login;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.FragAdapter;
import com.example.bbacr.ddw.base.BaseActivity;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.content.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
public class AccreditActivity extends BaseActivity {
    private TextView mBack;
    @Bind(R.id.tab_title)
    TabLayout mTabTitle;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    private List<String> mTitles = new ArrayList<>();//标题集合
    private List<BaseFragment> mfragments = new ArrayList<>();//视图集合
    @Override
    public int getLayoutId() {
        return R.layout.activity_accredit;
    }

    @Override
    public void initView() {
        mBack = (TextView) findViewById(R.id.app_title_back);
        mTitles.add("绑定已有账号");
        mTitles.add("账号授权");
        mfragments.add(new AccountFragment());
        mfragments.add(new AccountExecFragment());
    }

    @Override
    public void initListener() {
    mBack.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});
    }

    @Override
    public void initData() {
        mTabTitle.addTab(mTabTitle.newTab().setText(mTitles.get(0)));
        //初始化适配器
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), mfragments,mTitles);
        mViewpager.setAdapter(adapter);
        mTabTitle.setupWithViewPager(mViewpager);//让TabLayout随着ViewPager的变换而变换
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
