package com.example.bbacr.ddw.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.CodeLogin;
import com.example.bbacr.ddw.bean.GetKeyBean;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBase;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.MD5;
import com.example.bbacr.ddw.utils.StringUtils;
import com.example.bbacr.ddw.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 绑定已有账号
 */
public class AccountFragment extends BaseFragment {
    @Bind(R.id.phone)
    EditText mPhone;
    @Bind(R.id.pwd)
    EditText mPwd;
    @Bind(R.id.login)
    Button mLogin;
    private String phone;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
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
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPhoneNumber()) {
                    getKey();
                }
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
    }

    @Override
    public void onClick(View v) {

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
                    OAuthOldRegist(PreferenceManager.instance().getAccessToken(),PreferenceManager.instance().getOpenId(),mGetKeyBean.getDatas().getUserKey(),"101442252");
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

    /**
     * @param accessToken
     * @param openId
     * @param key
     * @param appid
     * 绑定老用户
     */
    private void OAuthOldRegist(String accessToken,String openId,String key,String appid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("accessToken",accessToken);
        hashMap.put("openId", openId);
        hashMap.put("type", "qq");
        hashMap.put("userName", mPhone.getText().toString());
        hashMap.put("passWord", MD5.getMD5(new StringBuffer(mPwd.getText().toString()).append(key).toString()));
        hashMap.put("appid", appid);
        HttpHelper.getInstance().post(Url.OAuthOldRegist, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("OAuthOldRegist" + response);
                final CodeLogin mSendCodeBean = JsonUtils.parse(response, CodeLogin.class);
                if (mSendCodeBean.getCode() == 1) {
                    ToastUtil.showShort("登录成功");
                    mBaseActivity.finish();
                    PreferenceManager.instance().saveToken(mSendCodeBean.getDatas().getToken());
//                    PreferenceManager.instance().saveUseId(String.valueOf(mSendCodeBean.getDatas().getUserId()));
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
