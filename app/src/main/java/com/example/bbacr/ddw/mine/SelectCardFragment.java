package com.example.bbacr.ddw.mine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.RcvCommonAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.my.GetCardList;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBase;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.widget.CommonDialog;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectCardFragment extends BaseFragment {


    @Bind(R.id.lRecyclerView)
    LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<GetCardList.DatasBean.CardListBean> mAdapter;
    private List<GetCardList.DatasBean.CardListBean> mList = new ArrayList<>();
    private int mPage=1;
    private TextView mBack,mAction;
    public SelectCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_card, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAction = mFindViewUtils.findViewById(R.id.app_title_action);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mAdapter = new RcvCommonAdapter<GetCardList.DatasBean.CardListBean>(getContext(), R.layout.card_list_item) {
            @Override
            public void setViewData(SuperViewHolder holder, GetCardList.DatasBean.CardListBean item, int position) {
                holder.setText(item.getAccountName(), R.id.integral).
                        setText("尾号"+item.getCardNumShort(), R.id.content).setGlideImageView(item.getBankImg(),R.id.pic);
                if (item.isIsDefault()) {
                    holder.getView(R.id.time).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void setListener(SuperViewHolder holder, View view) {
                view.setOnClickListener(holder);
            }

            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
                toBeDefault(mList.get(position).getId());
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
        mAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFragmentUtils.showFragment(getActivity(),BindCardFragment.class, FragmentTag.BINDCARDFRAGMENT,null,true);
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSelf();
            }
        });
        mLRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCardList(1);
                mLRecyclerView.refreshComplete(1000);
            }
        });
        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                getCardList1(mPage);
                mLRecyclerView.refreshComplete(1000);
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        getCardList(mPage);
    }

    /**
     * @param page
     */
    private void getCardList(int page){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("pageNu", String.valueOf(page));
        HttpHelper.getInstance().post(Url.getCardList, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getCardList"+response);
                closeLoadingDialog();
                final GetCardList bean = JsonUtils.parse(response, GetCardList.class);
                if (bean.getCode() == 1) {
                    if (mList!=null) {
                        mList.clear();
                    }
                    mList.addAll(bean.getDatas().getCardList());
                    mAdapter.setDataList(mList);
                } else if (bean.getCode() == 0) {
                    ToastUtil.showShort(bean.getMsg());
                } else {
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
     */
    private void getCardList1(int page){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("pageNu", String.valueOf(page));
        HttpHelper.getInstance().post(Url.getCardList, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getCardList"+response);
                closeLoadingDialog();
                final GetCardList bean = JsonUtils.parse(response, GetCardList.class);
                if (bean.getCode() == 1) {
                    mList.addAll(bean.getDatas().getCardList());
                    mAdapter.setDataList(mList);
                } else if (bean.getCode() == 0) {
                    ToastUtil.showShort(bean.getMsg());
                } else {
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
    private void toBeDefault(int cardId){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("cardId", String.valueOf(cardId));
        HttpHelper.getInstance().post(Url.toBeDefault, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode()==1) {
                    EventBus.getDefault().post(new EventBase());
                    popSelf();
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
