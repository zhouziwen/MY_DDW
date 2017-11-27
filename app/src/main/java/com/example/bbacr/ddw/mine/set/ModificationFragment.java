package com.example.bbacr.ddw.mine.set;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBase;
import com.example.bbacr.ddw.home.view.PartsFragment;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 修改基本资料
 */
public class ModificationFragment extends BaseFragment {
    private TextView mBack,mTitle, mKeep;
    @Bind(R.id.content)
    TextView mContent;
    @Bind(R.id.text_card)
    EditText mTextCard;
    @Bind(R.id.account_clear)
    Button mAccountClear;
    public ModificationFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modification, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mTitle = mFindViewUtils.findViewById(R.id.app_title_text);
        mKeep = mFindViewUtils.findViewById(R.id.app_title_action);
    }
    @Override
    protected void setListener() {
        super.setListener();
        mBack.setOnClickListener(this);
        mKeep.setOnClickListener(this);
    }
    @Override
    protected void setData() {
        super.setData();
        mTitle.setText(mArguments.getString("title"));
        mContent.setText(mArguments.getString("name"));
        mTextCard.setHint(mArguments.getString("name_hint"));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_title_back:
                popSelf();
                break;
            case R.id.app_title_action:
                if (mContent.getText().toString().equals("昵称")) {
                    updateRealName();

                } else if (mContent.getText().toString().equals("生日")) {
                    updateBirthday();
                }else if (mContent.getText().toString().equals("手机号")) {

                }else if (mContent.getText().toString().equals("微信")) {
                    updateWeixin();

                }else if (mContent.getText().toString().equals("QQ")) {
                    updateQQ();

                }else if (mContent.getText().toString().equals("邮箱")) {
                    updateEmail();
                }
                break;
        }
    }
    /**
     * 生日
     */
private void updateBirthday(){
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("token", PreferenceManager.instance().getToken());
    hashMap.put("birthday", mTextCard.getText().toString());
    HttpHelper.getInstance().post(Url.updateBirthday, hashMap, new RawResponseHandler() {
        @Override
        public void onSuccess(int statusCode, String response) {
            BaseBean bean = JsonUtils.parse(response, BaseBean.class);
            if (bean.getCode()==1) {
                EventBus.getDefault().post(new EventBase());
                popSelf();
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
            ToastUtil.showShort(error_msg);
        }
    });
}

    /**
     * 昵称
     */
    private void updateRealName(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("realName", mTextCard.getText().toString());
        HttpHelper.getInstance().post(Url.updateRealName, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode()==1) {
                    EventBus.getDefault().post(new EventBase());
                    popSelf();
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
                ToastUtil.showShort(error_msg);
            }
        });
    }
    /**
     * 微信
     */
    private void updateWeixin(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("weixin", mTextCard.getText().toString());
        HttpHelper.getInstance().post(Url.updateWeixin, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode()==1) {
                    EventBus.getDefault().post(new EventBase());
                    popSelf();
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
                ToastUtil.showShort(error_msg);
            }
        });
    }
    /**
     * QQ
     */
    private void updateQQ(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("qq", mTextCard.getText().toString());
        HttpHelper.getInstance().post(Url.updateQQ, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode()==1) {
                    EventBus.getDefault().post(new EventBase());
                    popSelf();
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
                ToastUtil.showShort(error_msg);
            }
        });
    }
    /**
     * 邮箱
     */
    private void updateEmail(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("email", mTextCard.getText().toString());
        HttpHelper.getInstance().post(Url.updateEmail, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode()==1) {
                    EventBus.getDefault().post(new EventBase());
                    popSelf();
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
