package com.example.bbacr.ddw.alipayutils;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaySuccessFragment extends BaseFragment {
    private TextView mAction;

    public PaySuccessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pay_success, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAction = mFindViewUtils.findViewById(R.id.app_title_action);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mAction.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public void onClick(View v) {

    }
}
