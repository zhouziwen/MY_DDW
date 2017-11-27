package com.example.bbacr.ddw.mine.coupon;
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
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.home.BuyRightNow;
import com.example.bbacr.ddw.bean.my.SearchCards;
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
 * 选择优惠券
 */
public class SelectCouponFragment extends BaseFragment {
    @Bind(R.id.lRecyclerView)
    LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<SearchCards.DatasBean.DataBean> mAdapter;
    private List<SearchCards.DatasBean.DataBean> mList = new ArrayList<>();
    private int mPage;
    private TextView mBack,mAction;
    public SelectCouponFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_coupon, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mAction = mFindViewUtils.findViewById(R.id.app_title_action);
        mAdapter = new RcvCommonAdapter<SearchCards.DatasBean.DataBean>(getContext(),R.layout.coupon_cencer_item) {
            @Override
            public void setViewData(SuperViewHolder holder, SearchCards.DatasBean.DataBean item, int position) {
                holder.setText(item.getName(), R.id.shopping_name).setText(item.getMoney(), R.id.shopping_money)
                        .setText("— " + item.getValidDay()+"天 —", R.id.validity_num).setGlideImageView(item.getImg(),R.id.glide_icon);
            }
            @Override
            public void setListener(SuperViewHolder holder, View view) {
                holder.setOnClickListener(R.id.immediately);
            }
            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
                switch (view.getId()) {
                    case R.id.immediately:
                        getCards(mList.get(position).getId());
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
        mLRecyclerView.setPullRefreshEnabled(true);
        mLRecyclerView.setLoadMoreEnabled(true);
    }
    @Override
    protected void setListener() {
        super.setListener();
        mBack.setOnClickListener(this);
        mAction.setOnClickListener(this);
        mLRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchCards1(1);
                mLRecyclerView.refreshComplete(1000);
            }
        });
        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                searchCards(mPage);
                mLRecyclerView.refreshComplete(1000);
            }
        });
    }
    @Override
    protected void setData() {
        super.setData();
        searchCards(1);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_title_back:
                popSelf();
                break;
            case R.id.app_title_action:
                ShowFragmentUtils.showFragment(getActivity(),MyCouponFragment.class,FragmentTag.MYCOUPONFRAGMENT,null,true);
                break;
        }
    }
    /**
     * 领取优惠券
     */
    private void searchCards(int page){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page", String.valueOf(page));
        HttpHelper.getInstance().post(Url.searchCards, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                closeLoadingDialog();
                SearchCards bean = JsonUtils.parse(response, SearchCards.class);
                if (bean.getCode() == 1) {
                    mList.addAll(bean.getDatas().getData());
                    mAdapter.setDataList(mList);
                } else if (bean.getCode()==0) {
                    ToastUtil.showShort(bean.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
                closeLoadingDialog();
            }
        });
    }
    /**
     * 领取优惠券
     */
    private void searchCards1(int page){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page", String.valueOf(page));
        HttpHelper.getInstance().post(Url.searchCards, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                closeLoadingDialog();
                SearchCards bean = JsonUtils.parse(response, SearchCards.class);
                if (bean.getCode() == 1) {
                    if (mList!=null) {
                        mList.clear();
                        mList.addAll(bean.getDatas().getData());
                    }
                    mAdapter.setDataList(bean.getDatas().getData());
                } else if (bean.getCode()==0) {
                    ToastUtil.showShort(bean.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
                closeLoadingDialog();
            }
        });
    }
    /**
     * @param cardId
     */
    private void getCards(int cardId){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("cardsId", String.valueOf(cardId));
        HttpHelper.getInstance().post(Url.getCards, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getCards"+response);
                closeLoadingDialog();
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode() == 1) {
                  ToastUtil.showShort("已添加到我的优惠券");
                } else if (bean.getCode()==0) {
                    ToastUtil.showShort(bean.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
                closeLoadingDialog();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
