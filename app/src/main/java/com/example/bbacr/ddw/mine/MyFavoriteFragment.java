package com.example.bbacr.ddw.mine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.RcvCommonAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.my.MyCollections;
import com.example.bbacr.ddw.content.IEvent;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBean;
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
 * 我的收藏
 */
public class MyFavoriteFragment extends BaseFragment {
    private int mPage = 1;
    @Bind(R.id.lRecyclerView)
    LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private TextView mClear;
    private TextView mBack;
    private RcvCommonAdapter<MyCollections.DatasBean.CollectionsListBean> mAdapter;
    private List<MyCollections.DatasBean.CollectionsListBean> mList = new ArrayList<>();
    public MyFavoriteFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_favorite, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mClear = mFindViewUtils.findViewById(R.id.app_title_action);
        mAdapter = new RcvCommonAdapter<MyCollections.DatasBean.CollectionsListBean>(getContext(), R.layout.confirm_order_list_item) {
            @Override
            public void setViewData(SuperViewHolder holder, MyCollections.DatasBean.CollectionsListBean item, int position) {
                holder.setText(item.getName(), R.id.shopping_name);
                holder.setGlideImageView(item.getMainImage(), R.id.glide_icon);
                holder.setText("￥"+item.getSalesPrice(), R.id.shopping_money);
                holder.setText("删除", R.id.shopping_num);
                holder.getView(R.id.specification).setVisibility(View.GONE);
            }
            @Override
            public void setListener(SuperViewHolder holder, View view) {
                holder.setOnClickListener(R.id.shopping_num);
            }
            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
                switch (view.getId()) {
                    case R.id.shopping_num:
                        delCollections(String.valueOf(mList.get(position).getId()),position);
                        break;
                }
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
        mClear.setOnClickListener(this);
        mLRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                myCollections(1);
            }
        });
        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                myLoadMoreCollections(mPage);
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSelf();
            }
        });
    }
    @Override
    protected void setData() {
        super.setData();
        myCollections(mPage);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_title_action:
                cleanCollections();
                break;
        }
    }
    /**
     * @param page 我的收藏
     */
    private void myCollections(int page) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("pageNu", String.valueOf(page));
        HttpHelper.getInstance().post(Url.myCollections, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("myCollections=" + response);
                closeLoadingDialog();
                MyCollections myCollections = JsonUtils.parse(response, MyCollections.class);
                if (myCollections.getCode()==1) {
                    if (mList!=null) {
                        mList.clear();
                    }
                    mList.addAll(myCollections.getDatas().getCollectionsList());
                }
                mAdapter.setDataList(mList);
                mLRecyclerView.refreshComplete(1000);
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                closeLoadingDialog();
                mLRecyclerView.refreshComplete(1000);

            }
        });
    }
    /**
     * @param page
     * 加载更多
     */
    private void myLoadMoreCollections(int page) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("pageNu", String.valueOf(page));
        HttpHelper.getInstance().post(Url.myCollections, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("myCollections=" + response);
                MyCollections myCollections = JsonUtils.parse(response, MyCollections.class);
                if (myCollections.getCode()==1) {
                    mList.addAll(myCollections.getDatas().getCollectionsList());
                }
                mAdapter.setDataList(mList);
                mLRecyclerView.refreshComplete(1000);
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
                mLRecyclerView.refreshComplete(1000);
            }
        });
    }
    /**
     * 清空我的收藏
     */
    private void cleanCollections() {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        HttpHelper.getInstance().post(Url.cleanCollections, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("cleanCollections=" + response);
                closeLoadingDialog();
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode()==1) {
                    mAdapter.clear();
                    EventBus.getDefault().post(new EventBean(IEvent.getUserHeadData));
                } else if (bean.getCode() == -1) {
                    ToastUtil.showShort(bean.getMsg());
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
     * @param idStr 删除我的收藏
     */
    private void delCollections(String idStr, final int pos) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("idStr", idStr);
        HttpHelper.getInstance().post(Url.delCollections, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("delCollections=" + response);
                closeLoadingDialog();
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode() == 1) {
                    mAdapter.remove(pos);
                    EventBus.getDefault().post(new EventBean(IEvent.getUserHeadData));
                } else if (bean.getCode() == -1) {
                    ToastUtil.showShort(bean.getMsg());
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
