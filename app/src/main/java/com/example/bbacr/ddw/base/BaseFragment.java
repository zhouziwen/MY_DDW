package com.example.bbacr.ddw.base;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.eventbus.EventBase;
import com.example.bbacr.ddw.eventbus.EventBean;
import com.example.bbacr.ddw.utils.AppMainTabLoading;
import com.example.bbacr.ddw.utils.DialogUtils;
import com.example.bbacr.ddw.utils.FindViewUtils;
import com.example.bbacr.ddw.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
/**
 * des:基类fragment
 * Created by xsf
 * on 2016.07.12:38
 */
public abstract  class BaseFragment extends Fragment implements View.OnClickListener{
    protected BaseActivity mBaseActivity;
    protected Bundle mArguments;
    protected FindViewUtils mFindViewUtils;
    protected InputMethodManager inputMethodManager;
    private Window mWindow;
    protected Handler mHandler = new Handler();
    private AppMainTabLoading mMainTabLoading;
    @Subscribe
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mArguments = getArguments();
        if (mArguments == null) {
            mArguments = new Bundle();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mBaseActivity = (BaseActivity) getActivity();
        view.setClickable(true);
        initView(view);
        setListener();
        setData();
    }
    protected void initView(View view) {
        mWindow = getActivity().getWindow();
        mFindViewUtils = new FindViewUtils(view);
        //在这里给继承BaseFragment的Fragment设置背景色
        int fragmentBg = getResources().getColor(R.color.gray_background);
        view.setBackgroundColor(fragmentBg);
        //点击穿透事件
        view.setClickable(true);
//        mAppTitleBar = (AppTitleBar) view.findViewById(R.id.app_title_bar);
    }
    protected void setListener() {
    }
    @Subscribe
    public void shuaxin(EventBase bean){
        /*刷新数据和ui*/
        setData();
    }
    protected void setData() {
    }
private void Location(){}

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
    /**
     * 关闭Framgment
     */
    public void popSelf() {
        popBackStack();
    }

    /**
     * @param context
     * @param text
     * 复制粘贴
     */
    public  void copyToClipboard(Context context, String text) {
        ClipboardManager systemService = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        systemService.setPrimaryClip(ClipData.newPlainText("text", text));
        ToastUtil.showShort(text+"已复制到剪切板");
    }
    /**
     * @param value
     * @return 线性图
     */
    protected float pxTodp(float value){
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float valueDP= TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,metrics);
        return valueDP;
    }
    private void popBackStack() {
        try {
            if (isDetached() || isRemoving() || getFragmentManager() == null) {
                return;
            }
            if (isResumed()) {
                getFragmentManager().popBackStackImmediate();
            }
        } catch (Exception e) {
        }
    }
    /**
     * 开始加载框
     */
//    public void startLoadingDialog(String message) {
//        DialogUtils.getInstance().createLoadingDialog(getActivity());
//        DialogUtils.getInstance().showDialog(message);
//    }
    public void startLoadingDialog(String textResId) {
        hiddenAppMainTabLoading();
        mBaseActivity.showLoading(textResId);
    }
    public void startLoadingDialog() {
        hiddenAppMainTabLoading();
        mBaseActivity.showLoading();
    }
    /**
     * 关闭加载框
     */
//    public void closeLoadingDialog() {
//        DialogUtils.getInstance().closeDialog();
//    }
    public void closeLoadingDialog() {
        mBaseActivity.hiddenLoading();
    }
    public void hiddenAppMainTabLoading() {
        if (mMainTabLoading != null) {
            mMainTabLoading.close();
        }

    }

    /**
     * 初始化普通弹窗
     */
//    public CommonDialog.Builder startCommonDialog(String title, String message) {
//        return new CommonDialog.Builder(getActivity()).setTitle(title).setMessage(message)
//                .setCanceledOnTouchOutside(false);
//    }
    /**
     * 隐藏键盘
     */
    public void hide_keyboard_from(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    /**
     * 显示键盘
     */
    public void show_keyboard_from(Context context,View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }
    public String getEtText(EditText editText) {
        if (editText != null) {
            return editText.getText().toString().trim();
        }
        return "";
    }
}
