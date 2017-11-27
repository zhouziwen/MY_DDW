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
import com.example.bbacr.ddw.bean.my.UserGetTiXianShenQingList;
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
public class ConvertFragment extends BaseFragment {
    @Bind(R.id.lRecyclerView)
    LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<UserGetTiXianShenQingList.DatasBean> mAdapter;
    private TextView mBack;
    private int mPage = 1;
    private List<UserGetTiXianShenQingList.DatasBean> mList = new ArrayList<>();

    public ConvertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_convert, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mAdapter = new RcvCommonAdapter<UserGetTiXianShenQingList.DatasBean>(getContext(),R.layout.change_pay_item) {
            @Override
            public void setViewData(SuperViewHolder holder, UserGetTiXianShenQingList.DatasBean item, int position) {
                holder.setText("订单号："+item.getCode(), R.id.integral).
                        setText("-"+item.getMoney(), R.id.check).
                        setText(item.getCreateTime(),R.id.content).setText(item.getState(),R.id.money);
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
                userGetTiXianShenQingList(1);
                mLRecyclerView.refreshComplete(1000);
            }
        });
     mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
         @Override
         public void onLoadMore() {
             mPage++;
             userGetTiXianShenQingList1(mPage);
             mLRecyclerView.refreshComplete(1000);
         }
     });

    }

    @Override
    protected void setData() {
        super.setData();
        userGetTiXianShenQingList(1);
    }

    private void userGetTiXianShenQingList(int page) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("page", String.valueOf(page));
        HttpHelper.getInstance().post(Url.userGetTiXianShenQingList, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                closeLoadingDialog();
                final UserGetTiXianShenQingList bean = JsonUtils.parse(response, UserGetTiXianShenQingList.class);
                if (bean.getCode() == 1) {
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
    private void userGetTiXianShenQingList1(int page) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("page", String.valueOf(page));
        HttpHelper.getInstance().post(Url.userGetTiXianShenQingList, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                closeLoadingDialog();
                final UserGetTiXianShenQingList bean = JsonUtils.parse(response, UserGetTiXianShenQingList.class);
                if (bean.getCode() == 1) {
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
    public void onClick(View v) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
