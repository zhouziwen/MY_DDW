package com.example.bbacr.ddw.mine.myorder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bbacr.ddw.EvaluateActivity;
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.callback.IOrderCallBack;
import com.example.bbacr.ddw.adapter.lrecycle.AdapterOrderTwo;
import com.example.bbacr.ddw.alipayutils.AlipayFragment;
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
 * 待评价
 */
public class WaitEvaluateFragment extends BaseFragment {

    @Bind(R.id.LRecyclerView)
    LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private AdapterOrderTwo mAdapterOrder;
    private List<OrderList.PurchaseOrdersBean> mList = new ArrayList<>();
    private int mPage=1;
    public WaitEvaluateFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wait_evaluate, container, false);
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
                orderList1(1,"ture");
                mLRecyclerView.refreshComplete(1000);
            }
        });
        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                orderList(mPage,"ture");
                mLRecyclerView.refreshComplete(1000);
            }
        });
    }

    @Override
    protected void setData() {
        super.setData(); mAdapterOrder = new AdapterOrderTwo(getContext(), null, new IOrderCallBack() {
            @Override
            public void back(OrderList.PurchaseOrdersBean.ShopOrdersBean item) {
                mArguments.putString("shopOrderId", String.valueOf(item.getId()));
                ShowFragmentUtils.showFragment(getActivity(),OrderDetailFragment.class,FragmentTag.ORDERDETAILFRAGMENT,mArguments,true);
            }

            @Override
            public void onReceive(OrderList.PurchaseOrdersBean.ShopOrdersBean item) {/*确认收货*/

            }

            @Override
            public void onEvaluate(OrderList.PurchaseOrdersBean.ShopOrdersBean item) {
                PreferenceManager.instance().saveShopOrderId(String.valueOf(item.getId()));
                mBaseActivity.showActivity(EvaluateActivity.class, null);
            }

            @Override
            public void onCancel2(OrderList.PurchaseOrdersBean.ShopOrdersBean item) {/*取消一个*/
                item.getId();
            }

            @Override
            public void onCancel(OrderList.PurchaseOrdersBean item) {/*取消一级*/
                item.getId();
            }


            @Override
            public void onDelete(OrderList.PurchaseOrdersBean.ShopOrdersBean item, int position) {

            }

            @Override
            public void pay(OrderList.PurchaseOrdersBean item) {/*去支付*/

            }

            @Override
            public void onLook(OrderList.PurchaseOrdersBean.ShopOrdersBean item) {

            }
        });
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapterOrder);
        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.a_5dp)
                .setColorResource(R.color.gray_background)
                .build();
        mLRecyclerView.addItemDecoration(divider);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        orderList1(1,"ture");
//        orderList(mPage,"true");
    }
    @Override
    public void onClick(View v) {

    }
    /**
     * @param page
     * 订单列表
     */
    private void orderList(int page,String hasComment){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("pageNum", String.valueOf(page));
        hashMap.put("hasComment", hasComment);
        HttpHelper.getInstance().post(Url.orderList, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("orderList"+response);
                closeLoadingDialog();
                OrderList orderList = JsonUtils.parse(response, OrderList.class);
                if (orderList.getCode()==1) {
                    mList.addAll(orderList.getPurchaseOrders());
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
                closeLoadingDialog();
                ToastUtil.showShort(error_msg);

            }
        });
    }
    /**
     * @param page
     * 订单列表
     */
    private void orderList1(int page,String hasComment){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("pageNum", String.valueOf(page));
        hashMap.put("hasComment", hasComment);
        HttpHelper.getInstance().post(Url.orderList, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                closeLoadingDialog();
                LogUtils.d("orderList"+response);
                OrderList orderList = JsonUtils.parse(response, OrderList.class);
                if (orderList.getCode()==1) {
                    mList.addAll(orderList.getPurchaseOrders());
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
