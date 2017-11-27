package com.example.bbacr.ddw.mine;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.RcvCommonAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.SearchService;
import com.example.bbacr.ddw.car.ShopListFragment;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * A simple {@link Fragment} subclass.
 * 我的手机
 */
public class MyPhoneFragment extends BaseFragment {
    private TextView mAction;
    @Bind(R.id.lRecyclerView)
    LRecyclerView mLRecyclerView;
    private TextView mBack;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<SearchService.DatasBean.DataBean> mAdapter;
    private List<SearchService.DatasBean.DataBean> mList = new ArrayList<>();
    public MyPhoneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_phone, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAction = mFindViewUtils.findViewById(R.id.app_title_action);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mAdapter = new RcvCommonAdapter<SearchService.DatasBean.DataBean>(getContext(),R.layout.phone_list_item) {
            @Override
            public void setViewData(SuperViewHolder holder, SearchService.DatasBean.DataBean item, int position) {
                /*.setText(item.getCode(), R.id.txt_6)*/
                holder.setText(item.getImie(), R.id.txt_2).setText(item.getGoodName(), R.id.txt_4).
                        setText(item.getCreateTime()+"至"+item.getEndTime(), R.id.txt_8).setText(item.getNum()+"台", R.id.txt_10).setText(item.getName(), R.id.txt_12)
                .setText(item.getShopName(), R.id.txt_14).setText(item.getId()+"", R.id.txt_16);
                if (item.isState()) {
                    holder.setText("有效", R.id.txt_18);
                } else {
                    holder.setText("无效", R.id.txt_18);
                }
            }
            @Override
            public void setListener(SuperViewHolder holder, View view) {

            }
            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.a_12dp)
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
        mAction.setTextColor(0xFFD33233);
        mAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFragmentUtils.showFragment(getActivity(), ShopListFragment.class,FragmentTag.SHOPLISTFRAGMENT,null,true);
            }
        });
    }
    @Override
    protected void setData() {
        super.setData();
        searchService();
    }
    @Override
    public void onClick(View v) {
    }
    /**
     * 电子保修卡
     */
        private void searchService(){
            startLoadingDialog();
         HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
         HttpHelper.getInstance().post(Url.searchService, hashMap, new RawResponseHandler() {
        @Override
        public void onSuccess(int statusCode, String response) {
            LogUtils.d("searchService"+response);
            closeLoadingDialog();
            SearchService bean = JsonUtils.parse(response, SearchService.class);
            if (bean.getCode()==1) {
                mList.addAll(bean.getDatas().getData());
                mAdapter.setDataList(mList);
            } else if (bean.getCode() == -1) {
                ToastUtil.showShort(bean.getMsg());
                PreferenceManager.instance().removeToken();
                mBaseActivity.showActivity(LoginActivity.class,null);
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
