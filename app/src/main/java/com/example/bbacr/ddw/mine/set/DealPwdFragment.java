package com.example.bbacr.ddw.mine.set;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.CountDownTimerUtils;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.MD5;
import com.example.bbacr.ddw.utils.StringUtils;
import com.example.bbacr.ddw.utils.ToastUtil;

import org.jsoup.helper.StringUtil;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DealPwdFragment extends BaseFragment {


    @Bind(R.id.get_verify_code)
    TextView mGetVerifyCode;
    @Bind(R.id.edit_code)
    EditText mEditCode;
    @Bind(R.id.rl_code)
    RelativeLayout mRlCode;
    @Bind(R.id.eidt_password)
    EditText mEidtPassword;
    @Bind(R.id.password_clear)
    Button mPasswordClear;
    @Bind(R.id.btn_eye)
    CheckBox mBtnEye;
    @Bind(R.id.rl_password)
    RelativeLayout mRlPassword;
    @Bind(R.id.eidt_password_1)
    EditText mEidtPassword1;
    @Bind(R.id.password_clear_1)
    Button mPasswordClear1;
    @Bind(R.id.btn_eye_1)
    CheckBox mBtnEye1;
    @Bind(R.id.rl_password_1)
    RelativeLayout mRlPassword1;
    @Bind(R.id.registerInBtn)
    Button mRegisterInBtn;
    private String mCode,mPwd, mPwdSure;
    public DealPwdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deal_pwd, container, false);
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
        isPasswordShowListener();
        isPasswordShowListenerSure();
        mGetVerifyCode.setOnClickListener(this);
        mRegisterInBtn.setOnClickListener(this);
        mPasswordClear.setOnClickListener(this);
        mPasswordClear1.setOnClickListener(this);
        mEidtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    mPasswordClear.setVisibility(View.VISIBLE);
                } else {
                    mPasswordClear.setVisibility(View.INVISIBLE);
                }
            }
        });
        mEidtPassword1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    mPasswordClear1.setVisibility(View.VISIBLE);
                } else {
                    mPasswordClear1.setVisibility(View.INVISIBLE);
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
        switch (v.getId()) {
            case R.id.get_verify_code:
                sendMobileCode_Vtoken();
                break;
            case R.id.registerInBtn:
                if (isPhoneNumber()) {
                    updatePassword(mCode);
                }
                break;
            case R.id.password_clear:
                mEidtPassword.setText("");
                break;
            case R.id.password_clear_1:
                mEidtPassword.setText("");
                mEidtPassword1.setText("");
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
                    CountDownTimerUtils utils = new CountDownTimerUtils(mGetVerifyCode, 60000, 1000);
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
    //完成更改密码
    private void updatePassword(String code) {
        HashMap<String, String> hash = new HashMap<>();
        hash.put("payPassword", MD5.getMD5(new StringBuffer(mPwd).append(PreferenceManager.instance().getKey()).toString()));
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
    /*显示密码*/
    private void isPasswordShowListener() {
        mBtnEye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int passwordLength = mEidtPassword.getText().length();
                mEidtPassword.setInputType(isChecked ?
                        (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) :
                        (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
                mEidtPassword.setSelection(passwordLength);
            }
        });
    }
    private void isPasswordShowListenerSure() {
        mBtnEye1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int passwordLength = mEidtPassword1.getText().length();
                mEidtPassword1.setInputType(isChecked ?
                        (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) :
                        (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
                mEidtPassword1.setSelection(passwordLength);
            }
        });
    }
    /*判断账号*/
    private boolean isPhoneNumber(){
        mCode = mEditCode.getText().toString().trim();
        mPwd = mEidtPassword.getText().toString().trim();
        mPwdSure = mEidtPassword1.getText().toString().trim();
        if (TextUtils.isEmpty(mCode)) {
            Toast.makeText(getActivity(), "验证码不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(mPwd)) {
            Toast.makeText(getActivity(), "请输入密码!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (TextUtils.isEmpty(mPwdSure)) {
            Toast.makeText(getActivity(), "请再次输入密码!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!mPwdSure.equals(mPwd)) {
            Toast.makeText(getActivity(), "两次输入的密码不一致!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (StringUtils.isPayPwdNO(mPwd)) {
            return true;
        } else {
            ToastUtil.showShort("支付密码需为六位数字");
            return false;
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
