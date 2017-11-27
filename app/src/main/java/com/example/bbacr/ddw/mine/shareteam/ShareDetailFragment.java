package com.example.bbacr.ddw.mine.shareteam;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.my.GetDetailCommission;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ToastUtil;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareDetailFragment extends BaseFragment {


    @Bind(R.id.txt_1)
    TextView mTxt1;
    @Bind(R.id.txt_2)
    TextView mTxt2;
    @Bind(R.id.txt_3)
    TextView mTxt3;
    @Bind(R.id.txt_4)
    TextView mTxt4;
    @Bind(R.id.txt_5)
    TextView mTxt5;
    @Bind(R.id.txt_6)
    TextView mTxt6;
    @Bind(R.id.layout1)
    LinearLayout mLayout1;
    @Bind(R.id.layout2)
    LinearLayout mLayout2;
    @Bind(R.id.layout3)
    LinearLayout mLayout3;
    @Bind(R.id.layout4)
    LinearLayout mLayout4;
    @Bind(R.id.layout5)
    LinearLayout mLayout5;
    @Bind(R.id.layout6)
    LinearLayout mLayout6;

    public ShareDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share_detail, container, false);
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
    }

    @Override
    protected void setData() {
        super.setData();
        getDetailCommission(mArguments.getString("code"));
    }

    @Override
    public void onClick(View v) {

    }

    private void getDetailCommission(String code) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("code", code);
        HttpHelper.getInstance().post(Url.getDetailCommission, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                closeLoadingDialog();
                LogUtils.d("集合" + response);
                GetDetailCommission getDetailCommission = JsonUtils.parse(response, GetDetailCommission.class);
                if (getDetailCommission.getCode() == 1) {
                    if (getDetailCommission.getDatas().size() == 3) {
                        mTxt1.setText(getDetailCommission.getDatas().get(0).getName());
                        mTxt2.setText(getDetailCommission.getDatas().get(0).getMoney());
                        mTxt3.setText(getDetailCommission.getDatas().get(1).getName());
                        mTxt4.setText(getDetailCommission.getDatas().get(1).getMoney());
                        mTxt5.setText(getDetailCommission.getDatas().get(2).getName());
                        mTxt6.setText(getDetailCommission.getDatas().get(2).getMoney());
                    } else if (getDetailCommission.getDatas().size()==2) {
                        mTxt1.setText(getDetailCommission.getDatas().get(0).getName());
                        mTxt2.setText(getDetailCommission.getDatas().get(0).getMoney());
                        mTxt3.setText(getDetailCommission.getDatas().get(1).getName());
                        mTxt4.setText(getDetailCommission.getDatas().get(1).getMoney());
                        mLayout5.setVisibility(View.GONE);
                        mLayout6.setVisibility(View.GONE);
                    } else if (getDetailCommission.getDatas().size()==1) {
                        mTxt1.setText(getDetailCommission.getDatas().get(0).getName());
                        mTxt2.setText(getDetailCommission.getDatas().get(0).getMoney());
                        mLayout3.setVisibility(View.GONE);
                        mLayout4.setVisibility(View.GONE);
                        mLayout5.setVisibility(View.GONE);
                        mLayout6.setVisibility(View.GONE);
                    }
                } else {
                    ToastUtil.showShort(getDetailCommission.getMsg());
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
