package com.example.bbacr.ddw.car;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.FragAdapter;
import com.example.bbacr.ddw.adapter.lrecycle.AdapterConfirmOrder;
import com.example.bbacr.ddw.alipayutils.AlipayFragment;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.car.SubmitOrder;
import com.example.bbacr.ddw.bean.home.BuyRightNow;
import com.example.bbacr.ddw.bean.my.GetDefault;
import com.example.bbacr.ddw.car.address.ManageAddressFragment;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.IEvent;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBase;
import com.example.bbacr.ddw.eventbus.EventBean;
import com.example.bbacr.ddw.eventbus.EventList;
import com.example.bbacr.ddw.eventbus.EventWay;
import com.example.bbacr.ddw.mine.coupon.UnUseCouponFragment;
import com.example.bbacr.ddw.mine.coupon.UsAbleCouponFragment;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.StringUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.widget.CommonDialog;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * 确认订单
 */
public class ConfirmOrderFragment extends BaseFragment {
    @Bind(R.id.pay_way)
    TextView mPayWay;
    @Bind(R.id.relative)
    RelativeLayout mRelative;
    @Bind(R.id.no_address)
    TextView mNoAddress;
    @Bind(R.id.user_name)
    TextView mUserName;
    @Bind(R.id.user_phone)
    TextView mUserPhone;
    @Bind(R.id.user_address)
    TextView mUserAddress;
    @Bind(R.id.list_view)
    LRecyclerView mListView;
    @Bind(R.id.ic_normal_dot_left)
    ImageView mIcNormalDotLeft;
    @Bind(R.id.remark)
    EditText mRemark;
    @Bind(R.id.discounts_num)
    TextView mDiscountsNum;
    @Bind(R.id.discounts)
    TextView mDiscounts;
    @Bind(R.id.discounts_money)
    TextView mDiscountsMoney;
    @Bind(R.id.coupon)
    RelativeLayout mCoupon;
    @Bind(R.id.all_money)
    TextView mAllMoney;
    @Bind(R.id.all_discounts)
    TextView mAllDiscounts;
    @Bind(R.id.freight)
    TextView mFreight;
    @Bind(R.id.layout)
    LinearLayout mLayout;
    @Bind(R.id.use_discounts)
    TextView mUseDiscounts;
    @Bind(R.id.tab_title)
    TabLayout mTabTitle;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.select_card)
    RelativeLayout mSelectCard;
    @Bind(R.id.confirmOrder)
    RelativeLayout mConfirmOrder;
    private List<String> mTitles = new ArrayList<>();//标题集合
    private List<BaseFragment> mfragments = new ArrayList<>();//视图集合
    @Bind(R.id.un_use_card)
    TextView mUnUseCard;
    @Bind(R.id.use_card)
    TextView mUseCard;
    @Bind(R.id.location)
    ImageView mLocation;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    @Bind(R.id.chose_address)
    RelativeLayout mChoseAddress;
    @Bind(R.id.id_tv_totalPrice)
    TextView mIdTvTotalPrice;
    @Bind(R.id.piece)
    TextView mPiece;
    @Bind(R.id.submit)
    TextView mSubmit;
    private AdapterConfirmOrder mAdapter;
    private List<BuyRightNow.GoodsListBean> mList = new ArrayList<>();
    private List<Integer> mId = new ArrayList<>();
    private String useAbleNum, unUseAbleNum;
    private TextView mBack;
    private String mAll;
    private float mFloat, mEndFloat;
    private String mAddressId;
    private String payType = "1";
    private String deliveryType = "1";
    public ConfirmOrderFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirm_order, container, false);
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
        mAdapter = new AdapterConfirmOrder(getContext(), mList);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mListView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.a_5dp)
                .setColorResource(R.color.gray_background)
                .build();
        mListView.addItemDecoration(divider);
        mListView.setAdapter(mLRecyclerViewAdapter);
        mListView.setPullRefreshEnabled(false);
        mListView.setLoadMoreEnabled(false);
    }
    @Override
    protected void setListener() {
        super.setListener();
        mRelative.setOnClickListener(this);
        mChoseAddress.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
        mCoupon.setOnClickListener(this);
        mUnUseCard.setOnClickListener(this);
        mUseCard.setOnClickListener(this);
        buyRightNow();
    }
    @Override
    protected void setData() {
        super.setData();
        getDefault();}
    /**
     * 立即购买数据处理
     */
    private void buyRightNow() {
        BuyRightNow bean = JsonUtils.parse(PreferenceManager.instance().getResponse(), BuyRightNow.class);
        if (bean.getGoodsList() != null) {
            mList.addAll(bean.getGoodsList());
            mAdapter.setDataList(mList);
            mLRecyclerViewAdapter.notifyDataSetChanged();
        }
        if (bean.getAddressType() == 2) {
            mUserName.setText(bean.getAddress().getName());
            mUserPhone.setText(bean.getAddress().getPhone());
            mUserAddress.setText(bean.getAddress().getCity());
        } else {
            mLayout.setVisibility(View.GONE);
            mUserAddress.setText(bean.getAddressContent());
        }
        mDiscountsNum.setText(bean.getCardNumAndMoney().getNum() + "张可用");
        mDiscountsMoney.setText(bean.getCardNumAndMoney().getMoney());
        mAll = bean.getAllPrice();
        mAllMoney.setText("商品：￥" + bean.getAllPrice());
        mAllDiscounts.setText("优惠：￥" + bean.getCardNumAndMoney().getMoney());
        mEndFloat = Float.parseFloat(bean.getEndPrice());
        mIdTvTotalPrice.setText("合计：￥" + bean.getEndPrice());
        mPiece.setText("共计"+bean.getGoodsList().size()+"件商品");
        useAbleNum = String.valueOf(bean.getUseAbleNum());
        unUseAbleNum = String.valueOf(bean.getUnUseAbleNum());
        mFloat = Float.parseFloat(bean.getCardNumAndMoney().getMoney());
        add();
    }
    /**
     * 获取默认地址
     */
private void getDefault(){
    startLoadingDialog();
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("token", PreferenceManager.instance().getToken());
    HttpHelper.getInstance().post(Url.getDefault, hashMap, new RawResponseHandler() {
        @Override
        public void onSuccess(int statusCode, String response) {
            LogUtils.d("getDefault"+response);
            closeLoadingDialog();
            GetDefault bean = JsonUtils.parse(response, GetDefault.class);
            if (bean.getCode()==1) {
                mLayout.setVisibility(View.VISIBLE);
                mUserName.setText(bean.getDatas().getName());
                mUserPhone.setText(bean.getDatas().getPhone());
                mUserAddress.setText(bean.getDatas().getDetail());
                mAddressId = String.valueOf(bean.getDatas().getId());
            } else if (bean.getCode() == -1) {
                ToastUtil.showShort(bean.getMsg());
                PreferenceManager.instance().removeToken();
                mBaseActivity.showActivity(LoginActivity.class,null);
            } else if (bean.getCode() == 0) {
                ToastUtil.showShort(bean.getMsg());
                                new CommonDialog.Builder(getActivity()).setTitle("提示")
                        .setCanceledOnTouchOutside(false)
                        .setMessage("你还没有收货地址，先去设置一下吧？", R.color.line_color)
                        .setPositiveButton("去设置", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ShowFragmentUtils.showFragment(getActivity(), ManageAddressFragment.class, FragmentTag.MANAGEADDRESSFRAGMENT, null, true);
                            }
                        }, R.color.red).setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        popSelf();
                                    }
                                }).show();
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
     * @param type
     * @param addressId
     * @param payType
     * @param deliveryType
     * @param userCardsId
     * 购物车结算
     */
    private void submitOrder(String type,String addressId,String payType,String deliveryType,String userCardsId,String memo){
        startLoadingDialog("正在提交订单");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("type", type);
        hashMap.put("addressId", addressId);
        hashMap.put("payType", payType);
        hashMap.put("deliveryType", deliveryType);
        hashMap.put("userCardsId", userCardsId);
        hashMap.put("memo", memo);
        HttpHelper.getInstance().post(Url.submitOrder, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("submitOrder"+response);
                closeLoadingDialog();
                SubmitOrder bean = JsonUtils.parse(response, SubmitOrder.class);
                if (bean.getCode()==1) {
                    EventBus.getDefault().post(new EventBase());
                    mArguments.putString("code", bean.getPCode());
                    mArguments.putString("money",bean.getPayMoney());
                    ShowFragmentUtils.showFragment(getActivity(), AlipayFragment.class,FragmentTag.ALIPAYFRAGMENT,mArguments,true);
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
    /**
     * @param type
     * @param addressId
     * @param orderInfor
     * @param deliveryType
     * @param userCardsId
     * 立即购买
     * http://localhost/purchaseOrderC/submitOrder.json?
     * token=A3E1D1A18943E77285A323E400E163FB&type=1&orderInfor=[{goodId:3,num:1,goodSpecificationId:10,addService:"4,6,8,9"}]&addressId=2&userCardsId=6,7,8,9,10,11&deliveryType=1
     */
    private void submitOrder2(String type,String addressId,String orderInfor,String deliveryType,String userCardsId,String payType,String memo){
        startLoadingDialog("正在提交订单");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("type", type);
        hashMap.put("addressId", addressId);
        hashMap.put("orderInfor", orderInfor);
        hashMap.put("deliveryType", deliveryType);
        hashMap.put("userCardsId", userCardsId);
        hashMap.put("payType",payType);
        hashMap.put("memo", memo);
        HttpHelper.getInstance().post(Url.submitOrder, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("submitOrder"+response);
                closeLoadingDialog();
                SubmitOrder bean = JsonUtils.parse(response, SubmitOrder.class);
                if (bean.getCode()==1) {
                    EventBus.getDefault().post(new EventBase());
                    mArguments.putString("code", bean.getPCode());
                    mArguments.putString("money",bean.getPayMoney());
                    ShowFragmentUtils.showFragment(getActivity(), AlipayFragment.class,FragmentTag.ALIPAYFRAGMENT,mArguments,true);
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

    private void add() {
        mTitles.add("可用优惠券" + "(" + useAbleNum + ")");
        mTitles.add("不可用优惠券" + "(" + unUseAbleNum + ")");
        mfragments.add(new UsAbleCouponFragment());
        mfragments.add(new UnUseCouponFragment());
        mTabTitle.addTab(mTabTitle.newTab().setText(mTitles.get(0)));
        //初始化适配器
        FragAdapter adapter = new FragAdapter(getChildFragmentManager(), mfragments, mTitles);
        mViewpager.setAdapter(adapter);
        mTabTitle.setupWithViewPager(mViewpager);//让TabLayout随着ViewPager的变换而变换
    }
    @Subscribe
    public void eventBus(EventBean bean) {
        if (bean.getMsg().equals(IEvent.upDateConfirmOrder)) {
            upDateConfirmOrder();
        }
        mFloat = mFloat + Float.parseFloat(bean.getMsg());
        mEndFloat = mEndFloat - Float.parseFloat(bean.getMsg());
        mDiscountsMoney.setText("" + mFloat);
        mAllDiscounts.setText("优惠：￥" + mFloat);
        mIdTvTotalPrice.setText("合计：￥" + mEndFloat);
    }
    @Subscribe
    public void eventWay(EventWay bean) {
        payType = bean.getMsg();
        deliveryType = bean.getWay();
    }
    @Subscribe
    public void event(EventList bean) {/*减*/
        mFloat = mFloat - Float.parseFloat(bean.getResponse());
        mEndFloat = mEndFloat + Float.parseFloat(bean.getResponse());
        mDiscountsMoney.setText("" + mFloat);
        mAllDiscounts.setText("优惠：￥" + mFloat);
        mIdTvTotalPrice.setText("合计：￥" + mEndFloat);
    }
    private void upDateConfirmOrder() {
        mDiscountsMoney.setText("0");
        mAllDiscounts.setText("优惠：￥0");
        mIdTvTotalPrice.setText("合计：￥" + mAll);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relative:/*购买的支付方式*/
            ShowFragmentUtils.showFragment(getActivity(),WayFragment.class,FragmentTag.WAYFRAGMENT,null,true);
                break;
            case R.id.chose_address:/*选择地址*/
                ShowFragmentUtils.showFragment(getActivity(), ManageAddressFragment.class, FragmentTag.MANAGEADDRESSFRAGMENT, null, true);
                break;
            case R.id.submit:

                if (mArguments.getString("type").equals("1")) {
                    submitOrder2("1",mAddressId,mArguments.getString("orderInfor"),deliveryType,"",payType,mRemark.getText().toString());
                } else if (mArguments.getString("type").equals("2")) {
                    submitOrder("2", mAddressId, payType, deliveryType, "",mRemark.getText().toString());
                }
                break;
            case R.id.coupon:
                mDiscountsMoney.setText("" + mFloat);
                mAllDiscounts.setText("优惠：￥" + mFloat);
                mIdTvTotalPrice.setText("合计：￥" + mEndFloat);
                mSelectCard.setVisibility(View.VISIBLE);
                mConfirmOrder.setVisibility(View.GONE);
                break;
            case R.id.un_use_card: /*不使用优惠券*/
//                EventBus.getDefault().post(new EventBean(IEvent.upDateConfirmOrder));
                buyRightNow();
                mSelectCard.setVisibility(View.GONE);
                mConfirmOrder.setVisibility(View.VISIBLE);
                break;
            case R.id.use_card:
                mSelectCard.setVisibility(View.GONE);
                mConfirmOrder.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
