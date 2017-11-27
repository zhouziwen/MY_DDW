package com.example.bbacr.ddw.home.shopdetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.FragAdapterTwo;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.home.CommentsSumdatas;
import com.example.bbacr.ddw.bean.home.PortalDetails;
import com.example.bbacr.ddw.content.IEvent;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBean;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 详细参数
 */
public class DetailsBottomInfosCommentFragment extends BaseFragment {


    @Bind(R.id.webView)
    WebView mWebView;

    public DetailsBottomInfosCommentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details_bottom_infos_comment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    public void onClick(View v) {

    }
    @Subscribe
    public void eventBus(PortalDetails.DatasBean card) {
        if (card!=null) {
            Document doc= Jsoup.parse(card.getParameter());
            Elements elements=doc.getElementsByTag("img");
            for (Element element : elements) {
                element.attr("style","width:100%").attr("height","auto");
            }
            LogUtils.d("aaaaaaaaaaaaa"+doc.toString());
            mWebView.loadDataWithBaseURL(null,doc.toString(),"text/html","utf-8", null);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
