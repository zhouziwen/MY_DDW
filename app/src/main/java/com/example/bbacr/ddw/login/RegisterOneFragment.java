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
import com.example.bbacr.ddw.bean.my.UserLoginKey;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.CountDownTimerUtils;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.StringUtils;
import com.example.bbacr.ddw.utils.ToastUtil;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterOneFragment extends BaseFragment {


    @Bind(R.id.phone)
    EditText mPhone;
    @Bind(R.id.next)
    Button mNext;
    @Bind(R.id.deal)
    TextView mDeal;
    private TextView mBack;
    private String phone;
    public RegisterOneFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_one, container, false);
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
        mNext.setOnClickListener(this);
        mDeal.setOnClickListener(this);
    }

    @Override
    protected void setData() {
        super.setData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                if (isPhoneNumber()) {
                    PreferenceManager.instance().savePhoneNum(mPhone.getText().toString());
                    ShowFragmentUtils.showFragment(getActivity(), RegisterTwoFragment.class, FragmentTag.REGISTERTWOFRAGMENT,null,true);
                }
                break;
            case R.id.deal:
                ToastUtil.showShort("协议");
                break;
        }
    }
    private void userLoginKey(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", mPhone.getText().toString());
        HttpHelper.getInstance().post(Url.userLoginKey, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                final UserLoginKey mSendCodeBean= JsonUtils.parse(response, UserLoginKey.class);
                if (mSendCodeBean.getCode()==1) {
                    PreferenceManager.instance().saveKey(mSendCodeBean.getDatas().getUserKey());

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
    private boolean isPhoneNumber(){
        phone = mPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(getActivity(), "手机号不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (StringUtils.isMobileNO(phone)) {
            return true;
        } else {
            Toast.makeText(getActivity(), "手机号不合法!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
