package com.example.bbacr.ddw;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bbacr.ddw.base.BaseActivity;
import com.example.bbacr.ddw.bean.home.VersionBean;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.widget.UpdateDialog;

import java.util.HashMap;

public class WelcomeActivity extends BaseActivity {
    boolean isFirstIn;
    private String mVersion;
    public static String sMallId = "";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_welcome);
//    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        getLocalVersion(this);
        // 获取SharedPreferences对象
        SharedPreferences sp = WelcomeActivity.this.getSharedPreferences("SP", MODE_PRIVATE);
        isFirstIn = sp.getBoolean("isFirstIn", true);
        if (isFirstIn) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    goGuide();
                }
            }, 2000);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isFirstIn", false);
            editor.commit();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkVersion();
//                    goHome();
                }
            }, 2000);
        }
    }

    private void goHome() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        WelcomeActivity.this.startActivity(intent);
        WelcomeActivity.this.finish();
    }
    private void goGuide() {
        Intent intent = new Intent(WelcomeActivity.this, GuideViewPagerActivity.class);
        WelcomeActivity.this.startActivity(intent);
        WelcomeActivity.this.finish();
    }
    private void checkVersion(){
        HashMap<String, String> hashMap = new HashMap<>();
        HttpHelper.getInstance().post(Url.VERSION, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("ddddd"+Integer.valueOf(mVersion.replace(".","")));
                LogUtils.d("VERSION="+response);
                final VersionBean bean = JsonUtils.parse(response, VersionBean.class);
                LogUtils.d("ggggggggg="+bean.getDatas().getAndroidVersionCode().replace(".",""));
//                Integer.valueOf(mVersion.replace(".",""))
                if (bean.getCode()==1) {
                    if (Integer.valueOf(bean.getDatas().getAndroidVersionCode().replace(".",""))<=Integer.valueOf(mVersion.replace(".",""))) {
                        goHome();
                    } else {
                        UpdateDialog my = new UpdateDialog(WelcomeActivity.this, bean);
                        my.show();
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }
    private void getLocalVersion(Context context) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            mVersion = "0.0";
        }
        LogUtils.d("版本号"+info.versionName);
        mVersion = info.versionName;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
