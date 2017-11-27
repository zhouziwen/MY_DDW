package com.example.bbacr.ddw.home.evaluate;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.FragAdapter;
import com.example.bbacr.ddw.adapter.FragAdapterTwo;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.home.CommentsSumdatas;
import com.example.bbacr.ddw.bean.my.GetDefault;
import com.example.bbacr.ddw.centre.CenterFragment;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventList;
import com.example.bbacr.ddw.eventbus.EventWay;
import com.example.bbacr.ddw.mine.myorder.DropShippingFragment;
import com.example.bbacr.ddw.mine.myorder.MyAllOrderFragment;
import com.example.bbacr.ddw.mine.myorder.PayWaitOrderFragment;
import com.example.bbacr.ddw.mine.myorder.WaitEvaluateFragment;
import com.example.bbacr.ddw.mine.myorder.WaitForReceivingFragment;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * A simple {@link Fragment} subclass.
 * 评价
 */
public class EvaluateFragment extends BaseFragment {
    @Bind(R.id.tab_title)
    TabLayout mTabTitle;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    private List<CommentsSumdatas.DatasBean> mTitles = new ArrayList<>();//标题集合
    private List<BaseFragment> mfragments = new ArrayList<>();//视图集合
    public EvaluateFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evaluate, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mfragments.add(new AllEvaluateFragment());
        mfragments.add(new GoodEvaluateFragment());
        mfragments.add(new CertreEvaluateFragment());
        mfragments.add(new BadEvaluateFragment());
        mfragments.add(new PictureEvaluateFragment());
    }
    @Override
    protected void setListener() {
        super.setListener();
    }
    @Override
    protected void setData() {
        super.setData();
        commentsSumdatas(PreferenceManager.instance().getGoodsId());
    }
//    @Subscribe
//    public void eventWay(EventList bean) {
//
//    }
    /**
     * @param goodsId
     * 获取评论统计
     */
    private void commentsSumdatas(String goodsId){
        startLoadingDialog("");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("goodsId", goodsId);
        HttpHelper.getInstance().post(Url.commentsSumdatas, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("commentsSumdatas"+response);
                closeLoadingDialog();
                CommentsSumdatas bean = JsonUtils.parse(response, CommentsSumdatas.class);
                mTitles.addAll(bean.getDatas());
                mTabTitle.addTab(mTabTitle.newTab().setText(mTitles.get(0).getCommentsProperty()+"\n"+mTitles.get(0).getCount()));
                //初始化适配器
                FragAdapterTwo adapter = new FragAdapterTwo(getChildFragmentManager(), mfragments,mTitles);
                mViewpager.setOffscreenPageLimit(4);
                mViewpager.setAdapter(adapter);
                mTabTitle.setupWithViewPager(mViewpager);//让TabLayout随着ViewPager的变换而变换
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                closeLoadingDialog();
                ToastUtil.showShort(error_msg);
            }
        });
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
