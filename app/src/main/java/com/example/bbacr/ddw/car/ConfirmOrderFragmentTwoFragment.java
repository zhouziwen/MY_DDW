package com.example.bbacr.ddw.car;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.bbacr.ddw.adapter.lrecycle.AdapterConfirmOrder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.home.BuyRightNow;
import com.example.bbacr.ddw.bean.list.ListBean;
import com.example.bbacr.ddw.car.address.ManageAddressFragment;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.mine.coupon.SelectCouponFragment;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.widget.CommonDialog;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * A simple {@link Fragment} subclass.
 * 购物车去结算界面
 * 已弃用
 */
public class ConfirmOrderFragmentTwoFragment extends BaseFragment {

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
    private BuyRightNow mBuyRightNow;
    private List<BuyRightNow.UseAbleListBean> useAbleList = new ArrayList<>();
    private List<BuyRightNow.UnUseAbleListBean> unUseAbleList = new ArrayList<>();
    private String useAbleNum, unUseAbleNum;
    private TextView mBack;
    public ConfirmOrderFragmentTwoFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm_order_fragment_two, container, false);
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
    }
    @Override
    protected void setData() {
        super.setData();
        cartsBuy();
    }
    /**
     * @param
     *
     */
    private void cartsBuy() {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        HttpHelper.getInstance().post(Url.cartsBuy, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("cartsBuy=" + response);
                closeLoadingDialog();
                if (response.contains("code")) {
                    BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                    if (bean.getCode() == 0) {
                        ToastUtil.showShort(bean.getMsg());
                    } else if (bean.getCode() == -1) {
                        ToastUtil.showShort(bean.getMsg());
                    }
                } else {
                    BuyRightNow bean = JsonUtils.parse(response, BuyRightNow.class);
                    PreferenceManager.instance().saveResponse(response);
                    if (bean.getGoodsList() != null) {
                        mList.addAll(bean.getGoodsList());
                        mAdapter.setDataList(mList);
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
                    mAllMoney.setText("商品：￥" + bean.getAllPrice());
                    mAllDiscounts.setText("优惠：￥" + bean.getCardNumAndMoney().getMoney());
                    mIdTvTotalPrice.setText("合计：￥" + bean.getEndPrice());
                    useAbleNum = String.valueOf(bean.getUseAbleNum());
                    unUseAbleNum = String.valueOf(bean.getUnUseAbleNum());
                    useAbleList = bean.getUseAbleList();
                    unUseAbleList = bean.getUnUseAbleList();
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
        switch (v.getId()) {
            case R.id.relative:/*购买的支付方式*/
                break;
            case R.id.chose_address:/*选择地址*/
                ShowFragmentUtils.showFragment(getActivity(), ManageAddressFragment.class, FragmentTag.MANAGEADDRESSFRAGMENT, null, true);
                break;
            case R.id.submit:
                new CommonDialog.Builder(getActivity()).setTitle("提示")
                        .setCanceledOnTouchOutside(false)
                        .setMessage("你还没有收货地址，先去设置一下吧？", R.color.line_color)
                        .setPositiveButton("去设置", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                        requestDeleteCart(item.getSkuId(),item);
                            }
                        }, R.color.red).setNegativeButton("取消", null).show();
                break;
            case R.id.coupon:
                mArguments.putString("useAbleNum", useAbleNum);
                mArguments.putString("unUseAbleNum", unUseAbleNum);
                ListBean<BuyRightNow.UseAbleListBean> useAbleListBeanListBean = new ListBean<>(useAbleList);
                ListBean<BuyRightNow.UnUseAbleListBean> unUseAbleListBeanListBean = new ListBean<>(unUseAbleList);
                mArguments.putSerializable("useAbleList", useAbleListBeanListBean);
                mArguments.putSerializable("unUseAbleList", unUseAbleListBeanListBean);
                ShowFragmentUtils.showFragment(getActivity(), SelectCouponFragment.class, FragmentTag.SELECTCOUPONFRAGMENT, mArguments, true);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
