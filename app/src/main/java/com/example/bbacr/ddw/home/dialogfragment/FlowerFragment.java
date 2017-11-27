package com.example.bbacr.ddw.home.dialogfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.RcvCommonAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.HomeBomBean;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 花呗分期
 */
public class FlowerFragment extends BaseFragment {
    @Bind(R.id.lRecyclerView)
    LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<HomeBomBean> mCommonAdapter;
    private List<HomeBomBean> mHomeBomBeen = new ArrayList<>();
    @Bind(R.id.flower)
    TextView mManage;
    public FlowerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flower, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mCommonAdapter = new RcvCommonAdapter<HomeBomBean>(getContext(),R.layout.check_item) {
            @Override
            public void setViewData(SuperViewHolder holder, HomeBomBean item, int position) {
                holder.setText(item.getTitle(), R.id.title).setText(item.getContent(),R.id.content);
            }

            @Override
            public void setListener(SuperViewHolder holder, View view) {

            }

            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {

            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mCommonAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setPullRefreshEnabled(false);
        mLRecyclerView.setLoadMoreEnabled(false);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mManage.setOnClickListener(this);

    }

    @Override
    protected void setData() {
        super.setData();
        for (int i = 0; i <8 ; i++) {
            mHomeBomBeen.add(new HomeBomBean("这是标题"+i, "这是内容"+i));
        }
        mCommonAdapter.setDataList(mHomeBomBeen);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.flower:
                ToastUtil.showShort("我被点击啦");
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
