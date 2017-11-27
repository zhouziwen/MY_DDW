package com.example.bbacr.ddw.mine.shareteam;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.my.ShareTeam;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.lixs.charts.PieChartView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 分享团队
 */
public class ShareTeamFragment extends BaseFragment {
    @Bind(R.id.pieView)
    PieChartView mPieView;
    @Bind(R.id.one)
    TextView mOne;
    @Bind(R.id.look_1)
    TextView mLook1;
    @Bind(R.id.two)
    TextView mTwo;
    @Bind(R.id.look_2)
    TextView mLook2;
    @Bind(R.id.three)
    TextView mThree;
    @Bind(R.id.look_3)
    TextView mLook3;
    private List<Float> mRatios = new ArrayList<>();
    List<String> mDescription = new ArrayList<>();
    List<Integer> mArcColors = new ArrayList<>();
    private int yellowColor = Color.argb(255, 253, 197, 53);
    private int greenColor = Color.argb(255, 27, 147, 76);
    private int redColor = Color.argb(255, 211, 57, 53);
    public ShareTeamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share_team, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mLook1.setOnClickListener(this);
        mLook2.setOnClickListener(this);
        mLook3.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.look_1:
                break;
            case R.id.look_2:
                break;
            case R.id.look_3:
                break;
        }
    }

    @Override
    protected void setData() {
        super.setData();
        shareTeam();
    }
    /**
     * 分享团队
     */
    private void shareTeam() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        HttpHelper.getInstance().post(Url.shareTeam, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                ShareTeam bean = JsonUtils.parse(response, ShareTeam.class);
                if (bean.getCode() == 1) {
                    mOne.setText("一级"+bean.getDatas().getOne().getPersons()+"人");
                    mTwo.setText("一级"+bean.getDatas().getTwo().getPersons()+"人");
                    mThree.setText("一级"+bean.getDatas().getThree().getPersons()+"人");
                    if (bean.getDatas().getOne().getPersons() == 0 && bean.getDatas().getTwo().getPersons() == 0 && bean.getDatas().getThree().getPersons() == 0) {
                        mPieView.setVisibility(View.GONE);
                    } else {
                        mPieView.setVisibility(View.VISIBLE);
                        mArcColors.add(greenColor);
                        mArcColors.add(yellowColor);
                        mArcColors.add(redColor);
                        mRatios.add((float) bean.getDatas().getOne().getPercentage());
                        mRatios.add((float) bean.getDatas().getTwo().getPercentage());
                        mRatios.add((float) bean.getDatas().getThree().getPercentage());
                        mDescription.add("一级成员");
                        mDescription.add("二级成员");
                        mDescription.add("三级成员");
                        mPieView.setCanClickAnimation(true);
                        mPieView.setDatas(mRatios, mArcColors, mDescription);
                    }
                } else if (bean.getCode() == -1) {
                    ToastUtil.showShort(bean.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class, null);
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
