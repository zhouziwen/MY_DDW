package com.example.bbacr.ddw.home.dialogfragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.FragAdapter;
import com.example.bbacr.ddw.base.BaseFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class SpecificationFragmentDialog extends DialogFragment {
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;
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
        View view = inflater.inflate(R.layout.specification_item, container);
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.custom_dialog_close);
        initViewPager(view);
        mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpecificationFragmentDialog.this.dismiss();
            }
        });
        return view;
    }
    public void initViewPager(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        initFragments();
        FragAdapter adapter = new FragAdapter(getChildFragmentManager(), mfragments,null);
        mViewPager.setAdapter(adapter);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }
    private void initFragments() {
        mfragments.add(new SpecificationFragment());
    }

}
