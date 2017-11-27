package com.example.bbacr.ddw.home.dialogfragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.FragAdapter;
import com.example.bbacr.ddw.base.BaseFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class ChartsDialogFragment extends DialogFragment {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageView mBack;
    private List<String> mTitles = new ArrayList<>();//标题集合

    private List<BaseFragment> mfragments = new ArrayList<>();//视图集合
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.dialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_charts, container);
        mBack = (ImageView) view.findViewById(R.id.back_img);
        initViewPager(view);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChartsDialogFragment.this.dismiss();
            }
        });
        return view;
    }
    public void initViewPager(View view) {

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_title);
        initFragments();
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(0)));
        //初始化适配器
        FragAdapter adapter = new FragAdapter(getChildFragmentManager(), mfragments,mTitles);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);//让TabLayout随着ViewPager的变换而变换
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }
    private void initFragments() {
        mTitles.add("大学生");
        mTitles.add("社会人士");
        mTitles.add("花呗分期");
        mfragments.add(new UniversityFragment());
        mfragments.add(new SocialEliteFragment());
        mfragments.add(new FlowerFragment());
    }

}
