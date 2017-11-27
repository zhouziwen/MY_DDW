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
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBase;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.CountDownTimerUtils;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.MD5;
import com.example.bbacr.ddw.utils.StringUtils;
import com.example.bbacr.ddw.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterTwoFragment extends BaseFragment {


    @Bind(R.id.phone)
    EditText mPhone;
    @Bind(R.id.get_code)
    TextView mGetCode;
    @Bind(R.id.pwd)
    EditText mPwd;
    @Bind(R.id.login)
    Button mLogin;
    private TextView mBack;
    private String pwd;
    public RegisterTwoFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_two, container, false);
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
    }

    @Override
    protected void setListener() {
        super.setListener();
        mLogin.setOnClickListener(this);
        mGetCode.setOnClickListener(this);
    }

    @Override
    protected void setData() {
        super.setData();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                if (isPhoneNumber()) {
                    checkCode_register(mPhone.getText().toString());
                }

                break;
            case R.id.get_code:
               sendMobileCode_v2();

                break;
        }
    }

    //得到验证码
    private void sendMobileCode_v2(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("mobile", PreferenceManager.instance().getPhoneNum());
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
        pwd = mPhone.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            ToastUtil.showShort("密码不能为空!");
            return false;
        }
        return true;
    }
    private void checkCode_register(String code) {
        HashMap<String, String> hash = new HashMap<>();
        hash.put("password", mPwd.getText().toString());
        hash.put("mobile", PreferenceManager.instance().getPhoneNum());
        hash.put("platform", "android");
        hash.put("mobile_code", code);
        HttpHelper.getInstance().post(Url.checkCode_register, hash, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                final CodeLogin mCodeLogin = JsonUtils.parse(response, CodeLogin.class);
                if (mCodeLogin.getCode() == 1) {
                    ToastUtil.showShort("注册成功");
                    PreferenceManager.instance().saveToken(mCodeLogin.getDatas().getToken());
                    PreferenceManager.instance().saveUseId(String.valueOf(mCodeLogin.getDatas().getUserId()));
                    EventBus.getDefault().post(new EventBase());
                    mBaseActivity.finish();
                }
                if (mCodeLogin.getCode() == 0) {
                    ToastUtil.showShort(mCodeLogin.getMsg());
                }
                if (mCodeLogin.getCode() == -1) {
                    ToastUtil.showShort(mCodeLogin.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class, null);
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
