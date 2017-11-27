package com.example.bbacr.ddw.mine.shareteam;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.RcvCommonAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.my.GetCommission;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareWaterFragment extends BaseFragment {


    @Bind(R.id.lRecyclerView)
    LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<GetCommission.DatasBean.DataBean> mAdapter;
    private List<GetCommission.DatasBean.DataBean> mList = new ArrayList<>();
    private int mPage = 1;
    public ShareWaterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share_water, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAdapter = new RcvCommonAdapter<GetCommission.DatasBean.DataBean>(getContext(),R.layout.order_item) {
            @Override
            public void setViewData(SuperViewHolder holder, GetCommission.DatasBean.DataBean item, int position) {
                holder.getView(R.id.success).setVisibility(View.GONE);
                holder.getView(R.id.check).setVisibility(View.GONE);
                holder.setText(item.getDate(), R.id.txt_time).
                        setText(item.getMoney(), R.id.money).
                        setText(item.getCode(), R.id.order);
            }

            @Override
            public void setListener(SuperViewHolder holder, View view) {
                holder.setOnClickListener(R.id.watch);
            }

            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
                switch (view.getId()) {
                    case R.id.watch:
                        mArguments.putString("code",mList.get(position).getCode());
                        ShowFragmentUtils.showFragment(getActivity(),ShareDetailFragment.class, FragmentTag.SHAREDETAILFRAGMENT,mArguments,true);
                        break;
                }
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.a_5dp)
                .setColorResource(R.color.gray_background)
                .build();
        mLRecyclerView.addItemDecoration(divider);
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mLRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCommission2(1);
            }
        });
        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                getCommission(mPage);
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        getCommission(mPage);
    }

    @Override
    public void onClick(View v) {

    }
    private void getCommission(int page){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("page", String.valueOf(page));
        HttpHelper.getInstance().post(Url.getCommission, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getCommission"+response);
                GetCommission getCommission = JsonUtils.parse(response, GetCommission.class);
                if (getCommission.getCode()==1) {
                    mList.addAll(getCommission.getDatas().getData());
                    mAdapter.setDataList(mList);
                    mLRecyclerView.refreshComplete(1000);
                } else if (getCommission.getCode()==0) {
                    ToastUtil.showShort(getCommission.getMsg());
                } else if (getCommission.getCode()==-1) {
                    ToastUtil.showShort(getCommission.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class,null);
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLRecyclerView.refreshComplete(1000);
                ToastUtil.showShort(error_msg);
            }
        });
    }
    private void getCommission2(int page){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("page", String.valueOf(page));
        HttpHelper.getInstance().post(Url.getCommission, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getCommission"+response);
                GetCommission getCommission = JsonUtils.parse(response, GetCommission.class);
                if (getCommission.getCode()==1) {
                    if (mList!=null) {
                        mList.clear();
                    }
                    mList.addAll(getCommission.getDatas().getData());
                    mAdapter.setDataList(mList);
                    mLRecyclerView.refreshComplete(1000);
                } else if (getCommission.getCode()==0) {
                    ToastUtil.showShort(getCommission.getMsg());
                } else if (getCommission.getCode()==-1) {
                    ToastUtil.showShort(getCommission.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class,null);
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLRecyclerView.refreshComplete(1000);
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
