package com.example.bbacr.ddw.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.home.GetUserId;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 推广码
 */
public class InviteCodeFragmentFragment extends BaseFragment {
    @Bind(R.id.glide_icon)
    GlideImageView mGlideIcon;
    private TextView mBack;
    public InviteCodeFragmentFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invite_code, container, false);
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
    }
    @Override
    protected void setData() {
        super.setData();
        getUserId();
    }
    @Override
    public void onClick(View v) {
    }
    private void getUserId() {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        HttpHelper.getInstance().post(Url.getUserId, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getUserId"+response);
                closeLoadingDialog();
                final GetUserId bean = JsonUtils.parse(response, GetUserId.class);
                if (bean.getCode() == 1) {
                    mGlideIcon.loadImage(Url.QRCODEC+String.valueOf(bean.getDatas().getUserId())+".jpg",R.mipmap.ic_launcher);
                } else if (bean.getCode() == 0) {
                    ToastUtil.showShort(bean.getMsg());
                } else if (bean.getCode() == -1) {
                    ToastUtil.showShort(bean.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class, null);
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
