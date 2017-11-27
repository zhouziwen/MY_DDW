package com.example.bbacr.ddw.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.home.CommentsSumdatas;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class FragAdapterTwo extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;
    private List<CommentsSumdatas.DatasBean> titles;
    public FragAdapterTwo(FragmentManager supportFragmentManager, List<BaseFragment> fragments, List<CommentsSumdatas.DatasBean> titles) {
        super(supportFragmentManager);
        this.fragments = fragments;
        this.titles = titles;
    }
    //获取页面内容
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    //返回fragments的个数，即页面数
    @Override
    public int getCount() {
        return fragments.size();
    }

    //获取每个页面的title
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position).getCommentsProperty()+" \n"+titles.get(position).getCount();
    }
}
