package com.example.bbacr.ddw.home.news;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import com.example.bbacr.ddw.bean.home.GetUserMessages;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.IEvent;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBase;
import com.example.bbacr.ddw.eventbus.EventBean;
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

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyNewsFragment extends BaseFragment {

    private TextView mBack;
    @Bind(R.id.LRecyclerView)
    LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<GetUserMessages.DatasBean.MessagesBean> mAdapter;
    private List<GetUserMessages.DatasBean.MessagesBean> mList = new ArrayList<>();
    private int mPage=1;
    public MyNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_news, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSelf();
            }
        });
        mAdapter = new RcvCommonAdapter<GetUserMessages.DatasBean.MessagesBean>(getContext(),R.layout.new_item) {
            @Override
            public void setViewData(SuperViewHolder holder, GetUserMessages.DatasBean.MessagesBean item, int position) {
                if (item.isIfRead()) {
                    holder.getView(R.id.pic).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.pic).setVisibility(View.VISIBLE);
                }
                holder.setText(item.getTitle(), R.id.integral).setText(item.getContent(), R.id.content).setText(item.getCreateTime(), R.id.time);
            }
            @Override
            public void setListener(SuperViewHolder holder, View view) {
                view.setOnClickListener(holder);
            }
            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
                mArguments.putInt("id", mList.get(position).getId());
                ShowFragmentUtils.showFragment(getActivity(), NewsFragment.class, FragmentTag.NEWSFRAGMENT,mArguments,true);
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.a_1dp)
                .setColorResource(R.color.gray_background)
                .build();
        mLRecyclerView.addItemDecoration(divider);
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);

        mLRecyclerView.setLoadMoreEnabled(true);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mLRecyclerView.setOnRefreshListener(new OnRefreshListener() {
    @Override
    public void onRefresh() {
        getUserMessages(1);
        mLRecyclerView.refreshComplete(1000);
    }
});
        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                getUserMessages(mPage);
                mLRecyclerView.refreshComplete(1000);
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        getUserMessages(mPage);
    }

    @Override
    public void onClick(View v) {

    }
    @Subscribe
    public void getCardList(EventBean card) {
        if (card.getMsg().equals(IEvent.getUserMessages)) {
            getUserMessages(1);
        }
    }
    private void getUserMessages(int page){
        startLoadingDialog();
        HashMap<String, String> parse = new HashMap<>();
        parse.put("token", PreferenceManager.instance().getToken());
        parse.put("pageNo", String.valueOf(page));
        HttpHelper.getInstance().post(Url.getUserMessages, parse, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getUserMessages"+response);
               closeLoadingDialog();
                final GetUserMessages bean = JsonUtils.parse(response, GetUserMessages.class);
                if (bean.getCode() == 1) {
                    if (mList!=null) {
                        mList.clear();
                        mList.addAll(bean.getDatas().getMessages());
                    }
                    mAdapter.setDataList(mList);
                    mLRecyclerViewAdapter.notifyDataSetChanged();
                } else if (bean.getCode() == -1) {
                    ToastUtil.showShort(bean.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class,null);
                } else {
                    ToastUtil.showShort(bean.getMsg());
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
