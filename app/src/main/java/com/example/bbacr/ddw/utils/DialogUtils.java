package com.example.bbacr.ddw.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bbacr.ddw.R;

/**
 * Created by Nianner Wang on 2017/9/27.
 * wytaper495@qq.com
 * 类注释：
 */

public class DialogUtils {
    private static DialogUtils dialogUtils;
    private Dialog loadingDialog;
    private TextView tipTextView;

    public static DialogUtils getInstance(){
        if(dialogUtils==null){
            dialogUtils = new DialogUtils();
        }
        return dialogUtils;
    }

    public void createLoadingDialog(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v
                .findViewById(R.id.dialog_loading_view);// 加载布局
        tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字

        loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);

    }

    /**
     * 打开dialog
     */
    public void showDialog(String msg){
        tipTextView.setText(msg);// 设置加载信息
        loadingDialog.show();
    }

    /**
     * 关闭dialog
     * @param
     */
    public  void closeDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}
