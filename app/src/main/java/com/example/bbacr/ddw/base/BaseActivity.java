package com.example.bbacr.ddw.base;
/**
 * 基类
 */
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.utils.AppLoading;
import com.example.bbacr.ddw.utils.AppManager;
import com.example.bbacr.ddw.utils.DialogUtils;
import com.example.bbacr.ddw.utils.StatusBarCompat;
import butterknife.ButterKnife;
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    public Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetContentView();
        setContentView(getLayoutId());
        if (getSupportActionBar() != null) {//隐藏titleBar
            getSupportActionBar().hide();
        }
        mContext = this;
        ButterKnife.bind(this);
        DialogUtils.getInstance().createLoadingDialog(this);
        this.initView();
        this.initListener();
        this.initData();
    }
    public abstract int getLayoutId();
    public abstract void initView();
    public abstract void initListener();
    public abstract void initData();
    /**
     * 设置layout前配置
     */
    private void doBeforeSetContentView() {
//         把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 设置竖屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 默认着色状态栏
        SetStatusBarColor(R.color.line_color);
    }
    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color) {
        StatusBarCompat.compat(this, getResources().getColor(color));
    }
//    /**
//     * 沉浸状态栏（4.4以上系统有效）
//     */
//    protected void SetTranslanteBar() {
//        StatusBarCompat.translucentStatusBar(this);
//    }
    /**
     * @param activity : 将要被显示的Activity
     * @param data     ： extraData
     */
    public void showActivity(Class<? extends BaseActivity> activity, Bundle data) {
        Intent intent = new Intent(this, activity);
        if (data != null) {
            intent.putExtras(data);
        }
        startActivity(intent);
    }

    private AppLoading mAppLoading;
    public void showLoading() {
        if (mAppLoading == null) {
            mAppLoading = new AppLoading(this);
        }
        mAppLoading.open();
    }
    public void showLoading(int textResId) {
        if (mAppLoading == null) {
            mAppLoading = new AppLoading(this);
        }
        mAppLoading.open(textResId);
    }public void showLoading(String textResId) {
        if (mAppLoading == null) {
            mAppLoading = new AppLoading(this);
        }
        mAppLoading.open(textResId);
    }
    public void hiddenLoading() {
        if (mAppLoading != null) {
            mAppLoading.close();
        }
    }
    /**
     * 开始加载框
     */
    public void startLoadingDialog(String message) {
        DialogUtils.getInstance().showDialog(message);
    }
    /**
     * 关闭加载框
     */
    public void closeLoadingDialog() {
        DialogUtils.getInstance().closeDialog();
    }
//    /**
//     * 初始化普通弹窗
//     */
//    public CommonDialog.Builder startCommonDialog(String title, String message) {
//        return new CommonDialog.Builder(this).setTitle(title).setMessage(message)
//                .setCanceledOnTouchOutside(false);
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
