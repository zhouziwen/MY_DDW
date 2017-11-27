package com.example.bbacr.ddw.activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.base.BaseActivity;
import com.example.bbacr.ddw.bean.CodeLogin;
import com.example.bbacr.ddw.bean.GetKeyBean;
import com.example.bbacr.ddw.bean.home.Oath;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBase;
import com.example.bbacr.ddw.login.AccreditFragment;
import com.example.bbacr.ddw.login.ForgetFragment;
import com.example.bbacr.ddw.login.RegisterOneFragment;
import com.example.bbacr.ddw.login.RegisterTwoFragment;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.MD5;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.StringUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
public class LoginActivity extends BaseActivity {
    @Bind(R.id.phone)
    EditText mPhone;
    @Bind(R.id.pwd)
    EditText mPwd;
    @Bind(R.id.login)
    Button mLogin;
    @Bind(R.id.register)
    Button mRegister;
    @Bind(R.id.forget)
    TextView mForget;
    @Bind(R.id.wechat)
    ImageView mWechat;
    private ProgressDialog dialog;
    @Bind(R.id.qq)
    ImageView mQq;
    private String phone;
    private TextView mBack;
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        dialog = new ProgressDialog(this);
        mBack = (TextView) findViewById(R.id.app_title_back);
    }

    @Override
    public void initListener() {
        mQq.setOnClickListener(this);
        mWechat.setOnClickListener(this);
        mForget.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    @Override
    public void initData() {
        if (!TextUtils.isEmpty(PreferenceManager.instance().getPhoneNum())) {
            mPhone.setText(PreferenceManager.instance().getPhoneNum());
        }
        if (!TextUtils.isEmpty(PreferenceManager.instance().getPwd())) {
            mPwd.setText(PreferenceManager.instance().getPwd());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qq:/*qq*/
                UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.QQ, authListener);
                break;
            case R.id.wechat:/*微信*/
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, authListener);
                break;
            case R.id.forget:/*忘记密码*/
//                ToastUtil.showShort("忘记密码");
                ShowFragmentUtils.showFragment(LoginActivity.this, RegisterTwoFragment.class, FragmentTag.REGISTERTWOFRAGMENT, null, true);
                break;
            case R.id.login:
                if (isPhoneNumber()) {
                    getKey();
                }
                break;
            case R.id.register:
                ShowFragmentUtils.showFragment(LoginActivity.this, RegisterOneFragment.class, FragmentTag.REGISTERONEFRAGMENT, null, true);
                break;
            case R.id.app_title_back:
                finish();
                break;
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    /**
     *
     */
    private void getKey() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", mPhone.getText().toString());
        HttpHelper.getInstance().post(Url.GetKey, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("GetKey" + response);
                final GetKeyBean mGetKeyBean = JsonUtils.parse(response, GetKeyBean.class);
                if (mGetKeyBean.getCode() == 1) {
                    PreferenceManager.instance().saveKey(mGetKeyBean.getDatas().getUserKey());
                    login(mGetKeyBean.getDatas().getUserKey());
                } else if (mGetKeyBean.getCode() == 0) {
                    ToastUtil.showShort(mGetKeyBean.getMsg());
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
            }
        });
    }

    //账号登录
    private void login(String key) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userName", mPhone.getText().toString());
        hashMap.put("passWord", MD5.getMD5(new StringBuffer(mPwd.getText().toString()).append(key).toString()));
        HttpHelper.getInstance().post(Url.Login, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("Login" + response);
                final CodeLogin mSendCodeBean = JsonUtils.parse(response, CodeLogin.class);
                if (mSendCodeBean.getCode() == 1) {
                    PreferenceManager.instance().saveUseId(String.valueOf(mSendCodeBean.getDatas().getUserId()));
                    PreferenceManager.instance().saveToken(mSendCodeBean.getDatas().getToken());//token
                    PreferenceManager.instance().savePhoneNum(mPhone.getText().toString());
                    PreferenceManager.instance().savePwd(mPwd.getText().toString());
                    EventBus.getDefault().post(new EventBase());
                    finish();
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

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(dialog);
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            SocializeUtils.safeShowDialog(dialog);
            LogUtils.d("=====" + data.toString());
            if (platform == SHARE_MEDIA.QQ) {
                OAuth(data.get("accessToken"), data.get("openid"),"qq");
            } else if (platform == SHARE_MEDIA.WEIXIN) {
                OAuth(data.get("accessToken"), data.get("openid"),"wx");
            }
//            Toast.makeText(mContext, "成功", Toast.LENGTH_LONG).show();

        }
        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(mContext, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
        }
    };
    /**
     * @param
     * @param
     */
    private void OAuth(String accessToken, String openId,String type) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("accessToken", accessToken);
        hashMap.put("openId", openId);
        hashMap.put("type", type);
        HttpHelper.getInstance().post(Url.OAuth, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("OAuth" + response);
                Oath bean = JsonUtils.parse(response, Oath.class);
                if (bean.getCode() == 1) {
                    if (bean.getDatas().getIsOAuth().equals("1")) {
                        PreferenceManager.instance().saveToken(bean.getDatas().getToken());
                        EventBus.getDefault().post(new EventBase());
                        finish();
                    } else if (bean.getDatas().getIsOAuth().equals("0")) {
                        PreferenceManager.instance().saveAccessToken(bean.getDatas().getAccessToken());
                        PreferenceManager.instance().saveOpenId(bean.getDatas().getOpenId());
                        ShowFragmentUtils.showFragment(LoginActivity.this, AccreditFragment.class,FragmentTag.ACCREDITFRAGMENT,null,true);
                    }
                } else if (bean.getCode() == 0) {
                    ToastUtil.showShort(bean.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}