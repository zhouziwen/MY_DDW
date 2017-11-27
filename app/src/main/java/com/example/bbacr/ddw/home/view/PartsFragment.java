package com.example.bbacr.ddw.home.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * 配件
 *
 */
public class PartsFragment extends BaseFragment {


    public PartsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parts, container, false);
    }

    @Override
    public void onClick(View v) {

    }
}
