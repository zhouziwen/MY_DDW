package com.example.bbacr.ddw.mine.myorder;


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

import com.example.bbacr.ddw.adapter.lrecycle.AdapterOrderTwo;
import com.example.bbacr.ddw.adapter.lrecycle.RefundList;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.my.OrderList;
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
 * 退款
 */
public class RefundFragment extends BaseFragment {


    @Bind(R.id.LRecyclerView)
    LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<RefundList.RefundListBean> mAdapterOrder;
    private List<RefundList.RefundListBean> mList = new ArrayList<>();
    private int mPage=1;
    public RefundFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_refund, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);

    }
    @Override
    protected void setListener() {
        super.setListener();
        mLRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                refundList(1);
                mLRecyclerView.refreshComplete(1000);
            }
        });
        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                refundList1(mPage);
                mLRecyclerView.refreshComplete(1000);
            }
        });
    }
    @Override
    protected void setData() {
        super.setData();
        mAdapterOrder = new RcvCommonAdapter<RefundList.RefundListBean>(getContext(),R.layout.refund_item) {
            @Override
            public void setViewData(SuperViewHolder holder, RefundList.RefundListBean item, int position) {
                holder.setText(item.getShopName(), R.id.tv_order_dm).
                        setText(item.getGoodsImg(), R.id.glide_icon).
                        setText(item.getGoodsName(),R.id.shopping_name).
                        setText(item.getSpecificationName(),R.id.specification).
                        setText(item.getRefundType(),R.id.tv_count_all)
                        .setGlideImageView(item.getGoodsImg(),R.id.glide_icon).setText(item.getRefundType(),R.id.tv_order_ddmj);
            }

            @Override
            public void setListener(SuperViewHolder holder, View view) {
                holder.setOnClickListener(R.id.go_pay);
            }

            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
                switch (view.getId()) {
                    case R.id.go_pay:
                        mArguments.putString("goodsOrderId", String.valueOf(mList.get(position).getGoodsOrderId()));
                        ShowFragmentUtils.showFragment(getActivity(),RefundDetailFragment.class,FragmentTag.REFUNDDETAILFRAGMENT,mArguments,true);
                        break;
                }
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapterOrder);
        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.a_5dp)
                .setColorResource(R.color.gray_background)
                .build();
        mLRecyclerView.addItemDecoration(divider);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        refundList(1);
    }
    @Override
    public void onClick(View v) {

    }
    /**
     * @param page
     * 退款列表
     */
    private void refundList(int page){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("pageNum", String.valueOf(page));
        HttpHelper.getInstance().post(Url.refundList, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("refundList"+response);
                RefundList orderList = JsonUtils.parse(response, RefundList.class);
                if (orderList.getCode()==1) {
                    if (mList!=null) {
                        mList.clear();
                    }
                    mList.addAll(orderList.getRefundList());
                    mAdapterOrder.setDataList(mList);
                } else if (orderList.getCode() == -1) {
                    ToastUtil.showShort(orderList.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class,null);
                } else {
                    ToastUtil.showShort(orderList.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
            }
        });
    }
    private void refundList1(int page){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("pageNum", String.valueOf(page));
        HttpHelper.getInstance().post(Url.refundList, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("refundList"+response);
                RefundList orderList = JsonUtils.parse(response, RefundList.class);
                if (orderList.getCode()==1) {
                    mList.addAll(orderList.getRefundList());
                    mAdapterOrder.setDataList(mList);
                } else if (orderList.getCode() == -1) {
                    ToastUtil.showShort(orderList.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class,null);
                } else {
                    ToastUtil.showShort(orderList.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
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
