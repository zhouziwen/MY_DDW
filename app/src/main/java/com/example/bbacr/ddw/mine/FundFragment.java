package com.example.bbacr.ddw.mine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.RcvCommonAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.my.GetUserFmsFlowV1;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
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
public class FundFragment extends BaseFragment {

    private TextView mBack;
    @Bind(R.id.lRecyclerView)
    LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<GetUserFmsFlowV1.DatasBean> mAdapter;
    private List<GetUserFmsFlowV1.DatasBean> mList = new ArrayList<>();
    private int mPage = 1;
    public FundFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fund, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mAdapter = new RcvCommonAdapter<GetUserFmsFlowV1.DatasBean>(getContext(),R.layout.account_item) {
            @Override
            public void setViewData(SuperViewHolder holder, GetUserFmsFlowV1.DatasBean item, int position) {
                if (position != 0) {
                    String a = (position - 1) >= 0 ? mList.get(position - 1).getCreateDate() : "";
                    if (!a.equals(mList.get(position).getCreateDate())) {
                        holder.getView(R.id.Relative1).setVisibility(View.VISIBLE);
                        holder.setText(item.getCreateDate(), R.id.Relative1);
                    } else {
                        holder.getView(R.id.Relative1).setVisibility(View.GONE);
                    }
                } else {
                    holder.getView(R.id.Relative1).setVisibility(View.VISIBLE);
                    holder.setText(item.getCreateDate(), R.id.Relative1);
                }
                holder.setText(item.getCreateTime(), R.id.txt_time).
                        setText(item.getReferCode(), R.id.content)
                        .setText(item.getChange_num(), R.id.money).setText(item.getSource(), R.id.send);
            }
            @Override
            public void setListener(SuperViewHolder holder, View view) {
            }
            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSelf();
            }
        });
        mLRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUserFmsFlowV1(1);
                mLRecyclerView.refreshComplete(1000);
            }
        });
        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                getUserFmsFlowV2(mPage);
                mLRecyclerView.refreshComplete(1000);
            }
        });
    }
    @Override
    protected void setData() {
        super.setData();
        getUserFmsFlowV1(1);
    }
    @Override
    public void onClick(View v) {
    }
    /**
     * @param page
     * 资金流水
     */
    private void getUserFmsFlowV1(int page){
     startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("page", String.valueOf(page));
        HttpHelper.getInstance().post(Url.getUserFmsFlowV1, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
               closeLoadingDialog();
                final GetUserFmsFlowV1 bean = JsonUtils.parse(response, GetUserFmsFlowV1.class);
                if (bean.getCode()==1) {
                    if (mList!=null) {
                        mList.clear();
                    }
                    mList.addAll(bean.getDatas());
                    mAdapter.setDataList(mList);
                } else if (bean.getCode() == 0) {
                 ToastUtil.showShort(bean.getMsg());
                } else if (bean.getCode()==-1) {
                    ToastUtil.showShort(bean.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class,null);
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
              closeLoadingDialog();
                ToastUtil.showShort(error_msg);
            }
        });
    }
    /**
     * @param page
     * 加载更多
     */
    private void getUserFmsFlowV2(int page){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("page", String.valueOf(page));
        HttpHelper.getInstance().post(Url.getUserFmsFlowV1, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                closeLoadingDialog();
                final GetUserFmsFlowV1 bean = JsonUtils.parse(response, GetUserFmsFlowV1.class);
                if (bean.getCode()==1) {
                    mList.addAll(bean.getDatas());
                    mAdapter.setDataList(mList);
                } else if (bean.getCode() == 0) {
                    ToastUtil.showShort(bean.getMsg());
                } else if (bean.getCode()==-1) {
                    ToastUtil.showShort(bean.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class,null);
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                closeLoadingDialog();
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
