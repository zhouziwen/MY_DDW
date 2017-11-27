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
 */
public class ForgetFragment extends BaseFragment {


    @Bind(R.id.phone)
    EditText mPhone;
    @Bind(R.id.txt)
    TextView mTxt;
    @Bind(R.id.code)
    EditText mCode;
    @Bind(R.id.get_code)
    TextView mGetCode;
    @Bind(R.id.pwd)
    EditText mPwd;
    @Bind(R.id.login)
    Button mLogin;
    private String phone;
    public ForgetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget, container, false);
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
        mGetCode.setOnClickListener(this);
        mLogin.setOnClickListener(this);
    }

    @Override
    protected void setData() {
        super.setData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_code:
                sendMobileCode_Vtoken();
                break;
            case R.id.login:
                if (isPhoneNumber()) {
                    getKey();
                }
                break;
        }

    }
    //得到验证码
    private void sendMobileCode_Vtoken(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("platform", "android");
        HttpHelper.getInstance().post(Url.sendMobileCode_Vtoken, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                final BaseBean mSendCodeBean= JsonUtils.parse(response, BaseBean.class);
                if (mSendCodeBean.getCode()==1) {
                    Toast.makeText(getContext(),"验证码已发送",Toast.LENGTH_SHORT).show();
                    CountDownTimerUtils utils = new CountDownTimerUtils(mGetCode, 60000, 1000);
                    utils.start();
                } else if (mSendCodeBean.getCode() == -1) {
                    ToastUtil.showShort(mSendCodeBean.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class,null);
                } else {
                    ToastUtil.showShort(mSendCodeBean.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
            }
        });
    }
    private void getKey(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", mPhone.getText().toString());
        HttpHelper.getInstance().post(Url.GetKey, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("GetKey"+response);
                final GetKeyBean mGetKeyBean = JsonUtils.parse(response, GetKeyBean.class);
                if (mGetKeyBean.getCode() == 1) {
                    PreferenceManager.instance().saveKey(mGetKeyBean.getDatas().getUserKey());
                   updatePassword(mCode.getText().toString(),mGetKeyBean.getDatas().getUserKey());
                } else if (mGetKeyBean.getCode()==0) {
                    ToastUtil.showShort(mGetKeyBean.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
            }
        });
    }
    private boolean isPhoneNumber(){
        phone = mPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showShort("手机号不能为空!");
            return false;
        } else if (StringUtils.isMobileNO(phone)) {
            return true;
        } else {
            ToastUtil.showShort("手机号不合法!");
            return false;
        }}

    //完成更改密码
    private void updatePassword(String code,String key) {
        HashMap<String, String> hash = new HashMap<>();
        hash.put("paypassword", MD5.getMD5(new StringBuffer(mPwd.getText().toString()).append(key).toString()));
        hash.put("token", PreferenceManager.instance().getToken());
        hash.put("platform", "android");
        hash.put("mobile_code", code);
        HttpHelper.getInstance().post(Url.updatePassword, hash, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                final BaseBean mCodeLogin = JsonUtils.parse(response, BaseBean.class);
                if (mCodeLogin.getCode() == 1) {
                    Toast.makeText(getContext(), "更改成功", Toast.LENGTH_SHORT).show();
                   popSelf();
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
                Toast.makeText(getContext(), "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
