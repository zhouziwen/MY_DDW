package com.example.bbacr.ddw.mine.myorder;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.bbacr.ddw.EvaluateActivity;
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.callback.IOrderCallBack;
import com.example.bbacr.ddw.adapter.callback.OnPopWinDisMisBack;
import com.example.bbacr.ddw.adapter.lrecycle.AdapterOrderTwo;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.my.OrderList;
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
import com.example.bbacr.ddw.widget.CustomPopupWindow;
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
 * 待发货
 */
public class DropShippingFragment extends BaseFragment {


    @Bind(R.id.LRecyclerView)
    LRecyclerView mLRecyclerView;
    @Bind(R.id.relative)
    RelativeLayout mRelative;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private AdapterOrderTwo mAdapterOrder;
    private List<OrderList.PurchaseOrdersBean> mList = new ArrayList<>();
    private CustomPopupWindow mPopupWindow;
    private int cancelReasonValue = 1;
    private int mPage;

    public DropShippingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drop_shipping, container, false);
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
                orderList1(1, 3);
                mLRecyclerView.refreshComplete(1000);
            }
        });
        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                orderList(mPage, 3);
                mLRecyclerView.refreshComplete(1000);
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        mAdapterOrder = new AdapterOrderTwo(getContext(), null, new IOrderCallBack() {
            @Override
            public void back(OrderList.PurchaseOrdersBean.ShopOrdersBean item) {
                mArguments.putString("shopOrderId", String.valueOf(item.getId()));
                ShowFragmentUtils.showFragment(getActivity(), OrderDetailFragment.class, FragmentTag.ORDERDETAILFRAGMENT, mArguments, true);
            }

            @Override
            public void onReceive(OrderList.PurchaseOrdersBean.ShopOrdersBean item) {/*确认收货*/
//                confirmReceiptOrder(item.getId());
            }

            @Override
            public void onEvaluate(OrderList.PurchaseOrdersBean.ShopOrdersBean item) {

            }

            @Override
            public void onCancel2(OrderList.PurchaseOrdersBean.ShopOrdersBean item) {/*取消一个*/
                showPop(item.getId());
            }

            @Override
            public void onCancel(OrderList.PurchaseOrdersBean item) {/*取消一级*/

            }


            @Override
            public void onDelete(OrderList.PurchaseOrdersBean.ShopOrdersBean item, int position) {

            }

            @Override
            public void pay(OrderList.PurchaseOrdersBean item) {/*去支付*/
//                mArguments.putString("code",item.getCode());
//                ShowFragmentUtils.showFragment(getActivity(),AlipayFragment.class, FragmentTag.ALIPAYFRAGMENT,mArguments,true);
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
        orderList1(1, 3);
//        orderList(mPage,3);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * @param page 订单列表
     */
    private void orderList(int page, int orderStateValue) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("pageNum", String.valueOf(page));
        hashMap.put("orderStateValue", String.valueOf(orderStateValue));
        HttpHelper.getInstance().post(Url.orderList, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("orderList" + response);
                closeLoadingDialog();
                OrderList orderList = JsonUtils.parse(response, OrderList.class);
                if (orderList.getCode() == 1) {
                    mList.addAll(orderList.getPurchaseOrders());
                    mAdapterOrder.setDataList(mList);
                } else if (orderList.getCode() == -1) {
                    ToastUtil.showShort(orderList.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class, null);
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

    private void orderList1(int page, int orderStateValue) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("pageNum", String.valueOf(page));
        hashMap.put("orderStateValue", String.valueOf(orderStateValue));
        HttpHelper.getInstance().post(Url.orderList, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("orderList" + response);
                closeLoadingDialog();
                OrderList orderList = JsonUtils.parse(response, OrderList.class);
                if (orderList.getCode() == 1) {
                    if (mList != null) {
                        mList.clear();
                    }
                    mList.addAll(orderList.getPurchaseOrders());
                    mAdapterOrder.setDataList(mList);
                } else if (orderList.getCode() == -1) {
                    ToastUtil.showShort(orderList.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class, null);
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

    private void showPop(final int id) {
        mPopupWindow = new CustomPopupWindow(getContext(),
                R.layout.cause_item,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                new OnPopWinDisMisBack() {
                    @Override
                    public void onPopWindowDismiss() {

                    }
                }) {
            @Override
            public void setData(View view) {
                Button button = (Button) view.findViewById(R.id.submit);
                ImageView imageView = (ImageView) view.findViewById(R.id.action_list_bgView);
                ImageView back = (ImageView) view.findViewById(R.id.back_img);
                final RadioButton radioButton1 = (RadioButton) view.findViewById(R.id.btn1);
                final RadioButton radioButton2 = (RadioButton) view.findViewById(R.id.btn2);
                final RadioButton radioButton3 = (RadioButton) view.findViewById(R.id.btn3);
                final RadioButton radioButton4 = (RadioButton) view.findViewById(R.id.btn4);
                final RadioButton radioButton5 = (RadioButton) view.findViewById(R.id.btn5);
                final RadioButton radioButton6 = (RadioButton) view.findViewById(R.id.btn6);
                RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        if (radioButton1.getId() == checkedId) {
                            cancelReasonValue = 1;
                        } else if (radioButton2.getId() == checkedId) {
                            cancelReasonValue = 2;
                        } else if (radioButton3.getId() == checkedId) {
                            cancelReasonValue = 3;
                        } else if (radioButton4.getId() == checkedId) {
                            cancelReasonValue = 4;
                        } else if (radioButton5.getId() == checkedId) {
                            cancelReasonValue = 5;
                        } else if (radioButton6.getId() == checkedId) {
                            cancelReasonValue = 6;
                        }
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stopOrder(id, cancelReasonValue);

                    }
                });
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismissWindow();
                    }
                });
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismissWindow();
                    }
                });

            }
        };
        mPopupWindow.showAsDownWindow(mRelative);
    }

    /**
     * @param shopOrderId
     * @param cancelReasonValue 取消一个订单
     */
    private void stopOrder(int shopOrderId, int cancelReasonValue) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("shopOrderId", String.valueOf(shopOrderId));
        hashMap.put("cancelReasonValue", String.valueOf(cancelReasonValue));
        HttpHelper.getInstance().post(Url.stopOrder, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("stopOrder" + response);
                closeLoadingDialog();
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode() == 1) {
                    ToastUtil.showShort("取消订单");
                    EventBus.getDefault().post(new EventBase());
                    mPopupWindow.dismissWindow();
                } else if (bean.getCode() == -1) {
                    ToastUtil.showShort(bean.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class, null);
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
