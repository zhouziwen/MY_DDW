package com.example.bbacr.ddw.alipayutils;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.alipayutils.utils.AuthResult;
import com.example.bbacr.ddw.alipayutils.utils.PayResult;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.my.PayApp;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBase;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.MD5;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 支付
 */
public class AlipayFragment extends BaseFragment {
    @Bind(R.id.pay)
    TextView mPay;
    @Bind(R.id.iv_recharge_balance_icon)
    ImageView mIvRechargeBalanceIcon;
    @Bind(R.id.iv_balance_pay)
    ImageView mIvBalancePay;
    @Bind(R.id.iv_recharge_wechat_icon)
    ImageView mIvRechargeWechatIcon;
    @Bind(R.id.iv_recharge_pay)
    ImageView mIvRechargePay;
    @Bind(R.id.iv_recharge_alipay_icon)
    ImageView mIvRechargeAlipayIcon;
    @Bind(R.id.iv_alipay_pay)
    ImageView mIvAlipayPay;
    @Bind(R.id.rl_recharge_balance)
    RelativeLayout mRlRechargeBalance;
    @Bind(R.id.rl_recharge_alipay)
    RelativeLayout mRlRechargeAlipay;
    @Bind(R.id.rl_recharge_wechat)
    RelativeLayout mRlRechargeWechat;
    @Bind(R.id.iv_recharge_yin_icon)
    ImageView mIvRechargeYinIcon;
    @Bind(R.id.iv_recharge_pay_yin)
    ImageView mIvRechargePayYin;
    @Bind(R.id.rl_recharge_yin)
    RelativeLayout mRlRechargeYin;
    @Bind(R.id.all_money)
    TextView mAllMoney;
    @Bind(R.id.tv_recharge_balance_name)
    TextView mTvRechargeBalanceName;
    private TextView mBack;
    private String mOrderInfo, mBtnInput;
    private EditText mTvOne;
    private EditText mTvTwo;
    private EditText mTvThree;
    private EditText mTvFour;
    private EditText mTvFive;
    private EditText mTvSix;

    private int payPostion = 0;//0是余额，2是微信，1是支付宝
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")

                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        EventBus.getDefault().post(new EventBase());
                        ShowFragmentUtils.showFragment(getActivity(), PaySuccessFragment.class, FragmentTag.PAYSUCCESSFRAGMENT, null, true);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ShowFragmentUtils.showFragment(getActivity(), PayFailFragment.class, FragmentTag.PAYFAILFRAGMENT, null, true);
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(getContext(),
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(getContext(),
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
    public AlipayFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alipay, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
    }
    @Override
    protected void setListener() {
        super.setListener();
        mPay.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mRlRechargeAlipay.setOnClickListener(this);
        mRlRechargeBalance.setOnClickListener(this);
        mRlRechargeWechat.setOnClickListener(this);
        mRlRechargeYin.setOnClickListener(this);
    }
    @Override
    protected void setData() {
        super.setData();
        mAllMoney.setText(mArguments.getString("money"));
        mPay.setText("余额需支付"+mArguments.getString("money")+"元");
    }

    /**
     * 支付宝支付业务
     *
     * @param
     */
    public void payV2(final String orderInfo) {
        LogUtils.d("执行1");
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
//                Map<String, String> result = alipay.payV2(orderInfo, true);
//                LogUtils.d("支付"+mOrderInfo);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                LogUtils.d("支付" + result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                LogUtils.d("执行2");
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * @param code 支付宝支付
     */
    private void payApp(String code) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("code", code);
        hashMap.put("mark", "12");
        HttpHelper.getInstance().post(Url.payApp, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("payApp" + response);
                PayApp payApp = JsonUtils.parse(response, PayApp.class);
                if (payApp.getCode() == 1) {
                    payV2(payApp.getDatas());
                } else if (payApp.getCode() == -1) {
                    ToastUtil.showShort(payApp.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class, null);
                } else {
                    ToastUtil.showShort(payApp.getMsg());
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
            }
        });
    }

    /**
     * @param code 余额支付
     */
    private void pay(String code, String payPassword, final Dialog dialog) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("code", code);
        hashMap.put("payPassword", MD5.getMD5(new StringBuffer(payPassword).append(PreferenceManager.instance().getKey()).toString()));
        hashMap.put("payChannelValue", "5");
        HttpHelper.getInstance().post(Url.pay, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("payApp" + response);
                PayApp payApp = JsonUtils.parse(response, PayApp.class);
                if (payApp.getCode() == 1) {
                    ToastUtil.showShort("支付成功");
                    EventBus.getDefault().post(new EventBase());
                    dialog.dismiss();
                } else if (payApp.getCode() == -1) {
                    ToastUtil.showShort(payApp.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class, null);
                } else {
                    ToastUtil.showShort(payApp.getMsg());
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
            case R.id.pay:
                if (payPostion == 0) {
                    showDialog();
                } else if (payPostion == 1) {
                    payApp(mArguments.getString("code"));
                } else if (payPostion == 2) {

                } else if (payPostion == 3) {

                }

                break;
            case R.id.app_title_back:
                popSelf();
                break;
            case R.id.rl_recharge_balance:/*余额*/
                payPostion = 0;
                mPay.setText("余额需支付"+mArguments.getString("money")+"元");
                mIvBalancePay.setImageResource(R.mipmap.ic_checked);
                mIvAlipayPay.setImageResource(R.mipmap.ic_uncheck);
                mIvRechargePay.setImageResource(R.mipmap.ic_uncheck);
                mIvRechargePayYin.setImageResource(R.mipmap.ic_uncheck);
                break;
            case R.id.rl_recharge_wechat:/*微信*/
                payPostion = 2;
                mPay.setText("微信需支付"+mArguments.getString("money")+"元");
                mIvBalancePay.setImageResource(R.mipmap.ic_uncheck);
                mIvAlipayPay.setImageResource(R.mipmap.ic_uncheck);
                mIvRechargePay.setImageResource(R.mipmap.ic_checked);
                mIvRechargePayYin.setImageResource(R.mipmap.ic_uncheck);
                break;
            case R.id.rl_recharge_alipay:/*支付宝*/
                payPostion = 1;
                mPay.setText("支付宝需支付"+mArguments.getString("money")+"元");
                mIvBalancePay.setImageResource(R.mipmap.ic_uncheck);
                mIvAlipayPay.setImageResource(R.mipmap.ic_checked);
                mIvRechargePay.setImageResource(R.mipmap.ic_uncheck);
                mIvRechargePayYin.setImageResource(R.mipmap.ic_uncheck);
                break;
            case R.id.rl_recharge_yin:/*银联*/
                payPostion = 3;
                mPay.setText("银联需支付"+mArguments.getString("money")+"元");
                mIvBalancePay.setImageResource(R.mipmap.ic_uncheck);
                mIvAlipayPay.setImageResource(R.mipmap.ic_uncheck);
                mIvRechargePay.setImageResource(R.mipmap.ic_uncheck);
                mIvRechargePayYin.setImageResource(R.mipmap.ic_checked);
                break;
        }
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(getContext(), R.style.EasyDialogStyle);
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_pay_pwd, null);
        dialog.setContentView(contentView);
        mTvOne = (EditText) contentView.findViewById(R.id.tv_pwd_one);
        mTvTwo = (EditText) contentView.findViewById(R.id.tv_pwd_two);
        mTvThree = (EditText) contentView.findViewById(R.id.tv_pwd_three);
        mTvFour = (EditText) contentView.findViewById(R.id.tv_pwd_four);
        mTvFive = (EditText) contentView.findViewById(R.id.tv_pwd_five);
        mTvSix = (EditText) contentView.findViewById(R.id.tv_pwd_six);
        Button btnOne = (Button) contentView.findViewById(R.id.tv_key_one);
        Button btnTwo = (Button) contentView.findViewById(R.id.tv_key_two);
        Button btnThree = (Button) contentView.findViewById(R.id.tv_key_three);
        Button btnFour = (Button) contentView.findViewById(R.id.tv_key_four);
        Button btnFive = (Button) contentView.findViewById(R.id.tv_key_five);
        Button btnSix = (Button) contentView.findViewById(R.id.tv_key_six);
        Button btnSeven = (Button) contentView.findViewById(R.id.tv_key_seven);
        Button btnEight = (Button) contentView.findViewById(R.id.tv_key_eight);
        Button btnNine = (Button) contentView.findViewById(R.id.tv_key_nine);
        Button btnNull = (Button) contentView.findViewById(R.id.tv_key_null);
        Button btnZero = (Button) contentView.findViewById(R.id.tv_key_zero);
        Button btnBack = (Button) contentView.findViewById(R.id.tv_key_back);
        ImageView ivDismiss = (ImageView) contentView.findViewById(R.id.iv_dismiss);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_key_one:
                        setKeyText("1", dialog);
                        break;
                    case R.id.tv_key_two:
                        setKeyText("2", dialog);
                        break;
                    case R.id.tv_key_three:
                        setKeyText("3", dialog);
                        break;
                    case R.id.tv_key_four:
                        setKeyText("4", dialog);
                        break;
                    case R.id.tv_key_five:
                        setKeyText("5", dialog);
                        break;
                    case R.id.tv_key_six:
                        setKeyText("6", dialog);
                        break;
                    case R.id.tv_key_seven:
                        setKeyText("7", dialog);
                        break;
                    case R.id.tv_key_eight:
                        setKeyText("8", dialog);
                        break;
                    case R.id.tv_key_nine:
                        setKeyText("9", dialog);
                        break;
                    case R.id.tv_key_null:
                        setKeyBack();
                        break;
                    case R.id.tv_key_zero:
                        setKeyText("0", dialog);
                        break;
                    case R.id.tv_key_back:
                        pay(mArguments.getString("code"), mBtnInput, dialog);
                        break;
                    case R.id.iv_dismiss:
                        dialog.dismiss();
                        break;
                }
            }
        };
        btnOne.setOnClickListener(listener);
        btnTwo.setOnClickListener(listener);
        btnThree.setOnClickListener(listener);
        btnFour.setOnClickListener(listener);
        btnFive.setOnClickListener(listener);
        btnSix.setOnClickListener(listener);
        btnSeven.setOnClickListener(listener);
        btnEight.setOnClickListener(listener);
        btnNine.setOnClickListener(listener);
        btnNull.setOnClickListener(listener);
        btnZero.setOnClickListener(listener);
        btnBack.setOnClickListener(listener);
        ivDismiss.setOnClickListener(listener);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
        dialog.show();
    }

    private void setKeyBack() {
        if (!mTvSix.getText().toString().isEmpty()) {
            mTvSix.setText("");
        } else if (!mTvFive.getText().toString().isEmpty()) {
            mTvFive.setText("");
        } else if (!mTvFour.getText().toString().isEmpty()) {
            mTvFour.setText("");
        } else if (!mTvThree.getText().toString().isEmpty()) {
            mTvThree.setText("");
        } else if (!mTvTwo.getText().toString().isEmpty()) {
            mTvTwo.setText("");
        } else if (!mTvOne.getText().toString().isEmpty()) {
            mTvOne.setText("");
//            ToastUtil.showShort("当前没有输入任何密码");
        }
    }

    public void setKeyText(String keyText, Dialog dialog) {
        if (mTvOne.getText().toString().isEmpty()) {
            mTvOne.setText(keyText);
        } else if (mTvTwo.getText().toString().isEmpty()) {
            mTvTwo.setText(keyText);
        } else if (mTvThree.getText().toString().isEmpty()) {
            mTvThree.setText(keyText);
        } else if (mTvFour.getText().toString().isEmpty()) {
            mTvFour.setText(keyText);
        } else if (mTvFive.getText().toString().isEmpty()) {
            mTvFive.setText(keyText);
        } else if (mTvSix.getText().toString().isEmpty()) {
            mTvSix.setText(keyText);
//            dialog.dismiss();
            mBtnInput = mTvOne.getText().toString() + mTvTwo.getText().toString()
                    + mTvThree.getText().toString() + mTvFour.getText().toString()
                    + mTvFive.getText().toString() + mTvSix.getText().toString();

                  /*  ("您的支付密码是：" + mTvOne.getText().toString() +
                    mTvTwo.getText().toString() + mTvThree.getText().toString() +
                    mTvFour.getText().toString() + mTvFive.getText().toString() + mTvSix.getText().toString());*/
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
