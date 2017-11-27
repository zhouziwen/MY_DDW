package com.example.bbacr.ddw.classify.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * 品牌
 */
public class BrandFragment extends BaseFragment {
    public BrandFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_brand, container, false);
    }
    @Override
    public void onClick(View v) {
    }
}
