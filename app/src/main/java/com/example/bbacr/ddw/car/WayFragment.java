package com.example.bbacr.ddw.car;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.RcvCommonAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.car.GetPortalShopList;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventWay;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 选择支付方式和配送方式
 */
public class WayFragment extends BaseFragment {
    @Bind(R.id.distribution_1)
    RadioButton mDistribution1;
    @Bind(R.id.distribution_2)
    RadioButton mDistribution2;
    @Bind(R.id.pay_1)
    RadioButton mPay1;
    @Bind(R.id.pay_2)
    RadioButton mPay2;
    @Bind(R.id.sure)
    TextView mSure;
    @Bind(R.id.distribution_way)
    RadioGroup mDistributionWay;
    @Bind(R.id.pay_way)
    RadioGroup mPayWay;
    @Bind(R.id.title_back)
    TextView mTitleBack;
    @Bind(R.id.title_text)
    TextView mTitleText;
    @Bind(R.id.title_bar)
    RelativeLayout mTitleBar;
    @Bind(R.id.lRecyclerView)
    LRecyclerView mLRecyclerView;
    @Bind(R.id.Relative_list)
    RelativeLayout mRelativeList;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<GetPortalShopList.DatasBean> mAdapter;
    private List<GetPortalShopList.DatasBean> mList = new ArrayList<>();
    private int mPage = 1;
    @Bind(R.id.relative)
    RelativeLayout mRelative;
    private String mDistribution = "1";
    private String mPay = "1";
    private TextView mBack;

    public WayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_way, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mAdapter = new RcvCommonAdapter<GetPortalShopList.DatasBean>(getContext(), R.layout.shop_list_item) {
            @Override
            public void setViewData(SuperViewHolder holder, GetPortalShopList.DatasBean item, int position) {
                holder.setText(item.getLinkman(), R.id.user_name).
                        setText(item.getContact(), R.id.user_phone).setText(item.getName() + " " + item.getLocation(), R.id.user_address);
            }

            @Override
            public void setListener(SuperViewHolder holder, View view) {
                view.setOnClickListener(holder);
            }

            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
                popSelf();
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.a_5dp)
                .setColorResource(R.color.gray_background)
                .build();
        mLRecyclerView.addItemDecoration(divider);
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setLoadMoreEnabled(true);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSelf();
            }
        });
        mTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRelative.setVisibility(View.VISIBLE);
                mRelativeList.setVisibility(View.GONE);
            }
        });
        mLRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPortalShopList(1);
                mLRecyclerView.refreshComplete(1000);
            }
        });
        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                getPortalShopList1(mPage);
                mLRecyclerView.refreshComplete(1000);
            }
        });
        mDistributionWay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (mDistribution1.getId() == checkedId) {
                    mDistribution = "1";
                    mPay2.setVisibility(View.GONE);
                } else if (mDistribution2.getId() == checkedId) {
                    mDistribution = "2";
                    mRelative.setVisibility(View.GONE);
                    mRelativeList.setVisibility(View.VISIBLE);
                    mPay2.setVisibility(View.VISIBLE);
//                    ShowFragmentUtils.showFragment(getActivity(), ShopListFragment.class, FragmentTag.SHOPLISTFRAGMENT, null, true);
                }
            }
        });
        mPayWay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (mPay1.getId() == checkedId) {
                    mPay = "1";
                    mDistribution1.setVisibility(View.VISIBLE);
                } else if (mPay2.getId() == checkedId) {
                    mPay = "2";
                    mDistribution1.setVisibility(View.GONE);
                }
            }
        });
        mSure.setOnClickListener(this);
    }

    @Override
    protected void setData() {
        super.setData();
        getPortalShopList(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sure:
                EventBus.getDefault().post(new EventWay(mPay, mDistribution));
                popSelf();
                break;
        }

    }

    /**
     * @param page 店铺列表
     */
    private void getPortalShopList(int page) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page", String.valueOf(page));
        HttpHelper.getInstance().post(Url.getPortalShopList, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getPortalShopList=" + response);
                closeLoadingDialog();
                GetPortalShopList myCollections = JsonUtils.parse(response, GetPortalShopList.class);
                if (myCollections.getCode() == 1) {
                    if (mList != null) {
                        mList.clear();
                    }
                    mList.addAll(myCollections.getDatas());
                }
                mAdapter.setDataList(mList);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                closeLoadingDialog();
                ToastUtil.showShort(error_msg);

            }
        });
    }

    /**
     * @param page 店铺列表
     */
    private void getPortalShopList1(int page) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page", String.valueOf(page));
        HttpHelper.getInstance().post(Url.getPortalShopList, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getPortalShopList=" + response);
                closeLoadingDialog();
                GetPortalShopList myCollections = JsonUtils.parse(response, GetPortalShopList.class);
                if (myCollections.getCode() == 1) {
                    mList.addAll(myCollections.getDatas());
                }
                mAdapter.setDataList(mList);

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
