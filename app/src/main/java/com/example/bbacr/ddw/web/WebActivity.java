package com.example.bbacr.ddw.web;
import android.view.View;
import android.webkit.WebView;
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.base.BaseActivity;
public class WebActivity extends BaseActivity {
    private WebView mWebView;
    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }
    @Override
    public void initView() {
        mWebView = (WebView) findViewById(R.id.webView);
    }
    @Override
    public void initListener() {
    }
    @Override
    public void initData() {
        mWebView.loadUrl("https://www.baidu.com");
    }
    @Override
    public void onClick(View v) {

    }
}
