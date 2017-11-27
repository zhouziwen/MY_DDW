package com.example.bbacr.ddw.home.shopdetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.home.PortalDetails;
import com.example.bbacr.ddw.utils.LogUtils;

import org.greenrobot.eventbus.Subscribe;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsBottomFragment extends BaseFragment {
    @Bind(R.id.webView)
    WebView mWebView;

    public DetailsBottomFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details_bottom, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

    }
    @Subscribe
    public void eventBus(PortalDetails.DatasBean card) {
        if (card!=null) {
            Document doc= Jsoup.parse(card.getDetailsImg());
            Elements elements=doc.getElementsByTag("img");
            for (Element element : elements) {
                element.attr("style","width:100%").attr("height","auto");
            }
            LogUtils.d("aaaaaaaaaaaaa"+doc.toString());
            mWebView.loadDataWithBaseURL(null,doc.toString(),"text/html","utf-8", null);
        }
    }
    @Override
    protected void setListener() {
        super.setListener();
    }
    @Override
    protected void setData() {
        super.setData();
    }
    @Override
    public void onClick(View v) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
