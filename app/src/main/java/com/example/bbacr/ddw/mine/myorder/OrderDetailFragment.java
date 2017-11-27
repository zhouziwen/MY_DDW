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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bbacr.ddw.EvaluateActivity;
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.callback.IOrderBack;
import com.example.bbacr.ddw.adapter.callback.OnPopWinDisMisBack;
import com.example.bbacr.ddw.adapter.lrecycle.AdapterOrder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.my.OrderDetail;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBase;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.CountDownTimerUtil;
import com.example.bbacr.ddw.utils.CountDownTimerUtils;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.widget.CustomPopupWindow;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
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
 * 订单详情
 */
public class OrderDetailFragment extends BaseFragment{
    @Bind(R.id.award_num)
    TextView mAwardNum;
    @Bind(R.id.get_award)
    TextView mGetAward;
    @Bind(R.id.copy)
    TextView mCopy;
    @Bind(R.id.location)
    ImageView mLocation;
    @Bind(R.id.user_name)
    TextView mUserName;
    @Bind(R.id.user_phone)
    TextView mUserPhone;
    @Bind(R.id.user_address)
    TextView mUserAddress;
    @Bind(R.id.list_view)
    LRecyclerView mListView;
    @Bind(R.id.relative)
    RelativeLayout mRelative;
    @Bind(R.id.time_difference)
    TextView mTimeDifference;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    @Bind(R.id.state)
    TextView mState;
    @Bind(R.id.layout)
    LinearLayout mLayout;
    private AdapterOrder mAdapter;
    private List<OrderDetail.ShopOrderBean.GoodsOrdersBean> mList = new ArrayList<>();
    @Bind(R.id.pay_way)
    TextView mPayWay;
    @Bind(R.id.pay_num)
    TextView mPayNum;
    @Bind(R.id.copy2)
    TextView mCopy2;
    @Bind(R.id.pay_time)
    TextView mPayTime;
    @Bind(R.id.order_water)
    TextView mOrderWater;
    @Bind(R.id.order_cancel)
    TextView mOrderCancel;
    @Bind(R.id.order_evaluate)
    TextView mOrderEvaluate;
    @Bind(R.id.order_pay)
    TextView mOrderPay;
    @Bind(R.id.ic_normal_dot_left)
    ImageView mIcNormalDotLeft;
    @Bind(R.id.chose_address)
    RelativeLayout mChoseAddress;
    @Bind(R.id.express)
    TextView mExpress;
    private CustomPopupWindow mPopupWindow;
    private int cancelReasonValue = 1;
    private TextView mBack;
    public OrderDetailFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mAdapter = new AdapterOrder(getContext(), null, new IOrderBack() {
            @Override
            public void Found(OrderDetail.ShopOrderBean.GoodsOrdersBean item) {
                mArguments.putString("goodsOrderId", String.valueOf(item.getId()));
               ShowFragmentUtils.showFragment(getActivity(),ApplyRefundFragment.class,FragmentTag.APPLYREFUNDFRAGMENT,null,true);
            }
        });
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.a_5dp)
                .setColorResource(R.color.gray_background)
                .build();
        mListView.addItemDecoration(divider);
        mListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mListView.setAdapter(mLRecyclerViewAdapter);
        mListView.setLoadMoreEnabled(false);
        mListView.setPullRefreshEnabled(false);
    }
    @Override
    protected void setListener() {
        super.setListener();
        mCopy.setOnClickListener(this);
        mCopy2.setOnClickListener(this);
        mOrderWater.setOnClickListener(this);
        mOrderCancel.setOnClickListener(this);
        mOrderEvaluate.setOnClickListener(this);
        mOrderPay.setOnClickListener(this);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSelf();
            }
        });
    }
    @Override
    protected void setData() {
        super.setData();
        orderDetail(mArguments.getString("shopOrderId"));
    }
    /**
     * @param shopOrderId 商品详情
     */
    private void orderDetail(String shopOrderId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("shopOrderId", shopOrderId);
        HttpHelper.getInstance().post(Url.orderDetail, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("orderDetail" + response);
                OrderDetail detail = JsonUtils.parse(response, OrderDetail.class);
                if (detail.getCode() == 1) {
                    if (detail.getShopOrder().getOrderStateValue() == 1) {
                        if (detail.getShopOrder().getTimeDif()>0) {
                            CountDownTimerUtil utils = new CountDownTimerUtil(mTimeDifference, detail.getShopOrder().getTimeDif(), 1000);
                            utils.start();
                        }
                    } else if (detail.getShopOrder().getOrderStateValue() == 3) {
                        mLayout.setVisibility(View.VISIBLE);
                        mOrderCancel.setVisibility(View.VISIBLE);
                    } else if (detail.getShopOrder().getOrderStateValue() == 4) {

                    } else if (detail.getShopOrder().getOrderStateValue() == 5) {
                        mOrderWater.setVisibility(View.VISIBLE);
                        mOrderPay.setVisibility(View.VISIBLE);
                    } else if (detail.getShopOrder().getOrderStateValue() == 10) {
                        mOrderEvaluate.setVisibility(View.VISIBLE);
                    }
                    mList.addAll(detail.getShopOrder().getGoodsOrders());
                    mAdapter.setDataList(mList);
                    mState.setText(detail.getShopOrder().getOrderState());
                    mGetAward.setText(detail.getShopOrder().getCode());
                    mUserName.setText(detail.getDeliveryAddress().getUserName());
                    mUserPhone.setText(detail.getDeliveryAddress().getPhone());
                    mUserAddress.setText(detail.getDeliveryAddress().getAddressDetail());
                    mPayWay.setText(detail.getShopOrder().getPayType());
                    mExpress.setText(detail.getShopOrder().getDeliveryType());
                    mPayNum.setText(detail.getShopOrder().getCode());
                    mPayTime.setText(detail.getShopOrder().getCreateTime());
                } else if (detail.getCode() == -1) {
                    ToastUtil.showShort(detail.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class, null);
                } else {
                    ToastUtil.showShort(detail.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_water:
                ShowFragmentUtils.showFragment(getActivity(), WaterFragment.class, FragmentTag.WATERFRAGMENT, null, true);
                break;
            case R.id.order_cancel:
                showPop(mArguments.getString("shopOrderId"));
                break;
            case R.id.order_evaluate:
                PreferenceManager.instance().saveShopOrderId(mArguments.getString("shopOrderId"));
                mBaseActivity.showActivity(EvaluateActivity.class, null);
                break;
            case R.id.order_pay:
                confirmReceiptOrder(mArguments.getString("shopOrderId"));
                break;
            case R.id.copy:
                copyToClipboard(getContext(), mCopy.getText().toString());
                break;
            case R.id.copy2:
                copyToClipboard(getContext(), mCopy.getText().toString());
                break;
        }
    }
    /**
     * @param shopOrderId 确认收货
     */
    private void confirmReceiptOrder(String shopOrderId) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("shopOrderId", shopOrderId);
        HttpHelper.getInstance().post(Url.confirmReceiptOrder, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                closeLoadingDialog();
                LogUtils.d("confirmReceiptOrder" + response);
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode() == 1) {
                    ToastUtil.showShort(bean.getMsg());
                    EventBus.getDefault().post(new EventBase());
                    popSelf();
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
    /**
     * @param shopOrderId
     * @param cancelReasonValue 取消一个订单
     */
    private void stopOrder(String shopOrderId, int cancelReasonValue) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("shopOrderId", shopOrderId);
        hashMap.put("cancelReasonValue", String.valueOf(cancelReasonValue));
        HttpHelper.getInstance().post(Url.stopOrder, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("stopOrder" + response);
                closeLoadingDialog();
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode() == 1) {
                    ToastUtil.showShort("取消订单");
                    mPopupWindow.dismissWindow();
                    EventBus.getDefault().post(new EventBase());
                    popSelf();
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
    private void showPop(final String id) {
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
