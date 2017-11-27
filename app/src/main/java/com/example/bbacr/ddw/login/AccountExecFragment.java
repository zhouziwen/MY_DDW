package com.example.bbacr.ddw.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.CodeLogin;
import com.example.bbacr.ddw.bean.GetKeyBean;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.CountDownTimerUtils;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.MD5;
import com.example.bbacr.ddw.utils.StringUtils;
import com.example.bbacr.ddw.utils.ToastUtil;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 授权登录并绑定
 */
public class AccountExecFragment extends BaseFragment {

    @Bind(R.id.phone)
    EditText mPhone;
    @Bind(R.id.txt)
    TextView mTxt;
    @Bind(R.id.code)
    EditText mCode;
    @Bind(R.id.get_code)
    TextView mGetCode;
    @Bind(R.id.login)
    Button mLogin;
    private String phone;
    public AccountExecFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_exec, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPhoneNumber()) {
                    OAuthNewRegist(PreferenceManager.instance().getAccessToken(),PreferenceManager.instance().getOpenId(),"101442252");
                }
            }
        });
        mGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPhoneNumber()) {
                    sendMobileCode_v2();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
    }
    //得到验证码
    private void sendMobileCode_v2(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("mobile", mPhone.getText().toString());
        hashMap.put("platform", "android");
        HttpHelper.getInstance().post(Url.sendMobileCode_v2, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                final BaseBean mSendCodeBean= JsonUtils.parse(response, BaseBean.class);
                if (mSendCodeBean.getCode()==1) {
                    Toast.makeText(getContext(),"验证码已发送",Toast.LENGTH_SHORT).show();
                    CountDownTimerUtils utils = new CountDownTimerUtils(mGetCode, 60000, 1000);
                    utils.start();
                }else {
                    ToastUtil.showShort(mSendCodeBean.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
            }
        });
    }
    private boolean isPhoneNumber() {
        phone = mPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showShort("手机号不能为空!");
            return false;
        } else if (StringUtils.isMobileNO(phone)) {
            return true;
        } else {
            ToastUtil.showShort("手机号不合法!");
            return false;
        }
    }
    private void OAuthNewRegist(String accessToken,String openId,String appid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("accessToken",accessToken);
        hashMap.put("openId", openId);
        hashMap.put("type", "qq");
        hashMap.put("phone", mPhone.getText().toString());
        hashMap.put("code", mCode.getText().toString());
        hashMap.put("appid", appid);
        HttpHelper.getInstance().post(Url.OAuthNewRegist, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("OAuthNewRegist" + response);
                final CodeLogin mSendCodeBean = JsonUtils.parse(response, CodeLogin.class);
                if (mSendCodeBean.getCode() == 1) {
                    ToastUtil.showShort("登录成功");
                    mBaseActivity.finish();
                    PreferenceManager.instance().saveToken(mSendCodeBean.getDatas().getToken());
                    PreferenceManager.instance().saveUseId(String.valueOf(mSendCodeBean.getDatas().getUserId()));
                }
                if (mSendCodeBean.getCode() == 0) {
                    ToastUtil.showShort(mSendCodeBean.getMsg());
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
