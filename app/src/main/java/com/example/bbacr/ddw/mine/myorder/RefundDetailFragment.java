package com.example.bbacr.ddw.mine.myorder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.listview.CommonAdapter;
import com.example.bbacr.ddw.adapter.listview.ViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.my.PayApp;
import com.example.bbacr.ddw.bean.my.RefundDetail;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBase;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.widget.MyListView;
import com.sunfusheng.glideimageview.GlideImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 退款详情
 */
public class RefundDetailFragment extends BaseFragment {


    @Bind(R.id.state)
    TextView mState;
    @Bind(R.id.award_num)
    TextView mAwardNum;
    @Bind(R.id.user_address)
    TextView mUserAddress;
    @Bind(R.id.chose_address)
    RelativeLayout mChoseAddress;
    @Bind(R.id.glide_icon)
    GlideImageView mGlideIcon;
    @Bind(R.id.shopping_name)
    TextView mShoppingName;
    @Bind(R.id.specification)
    TextView mSpecification;
    @Bind(R.id.shopping_money)
    TextView mShoppingMoney;
    @Bind(R.id.shopping_num)
    TextView mShoppingNum;
    @Bind(R.id.list_view)
    MyListView mListView;
    @Bind(R.id.pay_way)
    TextView mPayWay;
    @Bind(R.id.express)
    TextView mExpress;
    @Bind(R.id.pay_time)
    TextView mPayTime;
    @Bind(R.id.order_water)
    TextView mOrderWater;
    @Bind(R.id.layout)
    LinearLayout mLayout;
    @Bind(R.id.relative)
    RelativeLayout mRelative;
    private MyListView mMyListView;
    private int refundId;
    private CommonAdapter<RefundDetail.DatasBean.ServiceOrderBean> mAdapter;
    private List<RefundDetail.DatasBean.ServiceOrderBean> mList = new ArrayList<>();
    public RefundDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_refund_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mMyListView = mFindViewUtils.findViewById(R.id.list_view);
        mAdapter = new CommonAdapter<RefundDetail.DatasBean.ServiceOrderBean>(getContext(),null,R.layout.car_confirmorder_recycle_list) {
            @Override
            public void setViewData(ViewHolder holder, RefundDetail.DatasBean.ServiceOrderBean item, int position) {
                holder.setText(R.id.add_service, "【"+item.getName()).setText(R.id.add_service_money,item.getPrice()+"】");
            }

            @Override
            protected void setEvent(ViewHolder holder, View convertView, int position) {
                super.setEvent(holder, convertView, position);
                holder.setOnclickListener(R.id.detail);
            }

            @Override
            public void onClickBack(View v, int position, ViewHolder holder) {
                switch (v.getId()) {
                    case R.id.detail:

                        break;
                }
            }

            @Override
            public void onBack(String name) {

            }
        };
        mMyListView.setAdapter(mAdapter);

    }

    @Override
    protected void setListener() {
        super.setListener();
        mOrderWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelRefund(String.valueOf(refundId));
            }
        });

    }

    @Override
    protected void setData() {
        super.setData();
        refundDetail(mArguments.getString("goodsOrderId"));
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * @param goodsOrderId
     * 退款详情界面
     */
private void refundDetail(String goodsOrderId){
    startLoadingDialog();
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("token", PreferenceManager.instance().getToken());
    hashMap.put("goodsOrderId", goodsOrderId);
    HttpHelper.getInstance().post(Url.refundDetail, hashMap, new RawResponseHandler() {

        @Override
        public void onSuccess(int statusCode, String response) {
            LogUtils.d("refundDetail"+response);
            closeLoadingDialog();
            RefundDetail bean = JsonUtils.parse(response, RefundDetail.class);
            if (bean.getCode()==1) {
                refundId = bean.getDatas().getRefundLog().getId();
                mGlideIcon.loadImage(bean.getDatas().getGoods().getGoodsImg(), R.id.glide_icon);
                mShoppingName.setText(bean.getDatas().getGoods().getGoodsName());
                mSpecification.setText(bean.getDatas().getGoods().getSpecificationName());
                mShoppingMoney.setText(bean.getDatas().getGoods().getUnitPrice());
                mShoppingNum.setText("X"+bean.getDatas().getGoods().getGoodsNum());
                mPayWay.setText(bean.getDatas().getRefundLog().getRefundReason());
                mExpress.setText(bean.getDatas().getRefundLog().getRefundMoney());
                mPayTime.setText(bean.getDatas().getRefundLog().getNum()+"件");
                mState.setText(bean.getDatas().getRefundLog().getRefundType());
                mList.addAll(bean.getDatas().getServiceOrder());
               mAdapter.update(mList);
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
            closeLoadingDialog();
            ToastUtil.showShort(error_msg);
        }
    });
}

    /**
     * @param refundId
     * 撤销退款
     */
    private void cancelRefund(String refundId){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("refundId", refundId);
        HttpHelper.getInstance().post(Url.cancelRefund, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("cancelRefund"+response);
                closeLoadingDialog();
                RefundDetail bean = JsonUtils.parse(response, RefundDetail.class);
                if (bean.getCode()==1) {
                    ToastUtil.showShort("撤销退款");
//                    EventBus.getDefault().post(new EventBase());
                    popSelf();
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
