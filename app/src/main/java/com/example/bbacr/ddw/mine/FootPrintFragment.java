package com.example.bbacr.ddw.mine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.RcvCommonAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.adapter.listview.CommonAdapter;
import com.example.bbacr.ddw.adapter.listview.ViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.ClassificationRightBean;
import com.example.bbacr.ddw.bean.GetKeyBean;
import com.example.bbacr.ddw.bean.MyCollectionsNum;
import com.example.bbacr.ddw.bean.MyFootMark;
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
 * 足迹
 */
public class FootPrintFragment extends BaseFragment {
    private int mPage;
    @Bind(R.id.list_view)
    LRecyclerView mListView;
    private TextView mBack;
    private TextView mClear;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<MyFootMark.DatasBean.FootMarkListBean> mAdapter;
    private List<MyFootMark.DatasBean.FootMarkListBean> mList = new ArrayList<>();
    public FootPrintFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_foot_print, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mClear = mFindViewUtils.findViewById(R.id.app_title_action);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSelf();
            }
        });
        mAdapter = new RcvCommonAdapter<MyFootMark.DatasBean.FootMarkListBean>(getContext(),R.layout.confirm_order_list_item) {
            @Override
            public void setViewData(SuperViewHolder holder, MyFootMark.DatasBean.FootMarkListBean item, int position) {
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
                        delFootMark(String.valueOf(mList.get(position).getId()),position);
                        break;
                }
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.a_5dp)
                .setColorResource(R.color.gray_background)
                .build();
        mListView.addItemDecoration(divider);
        mListView.setAdapter(mLRecyclerViewAdapter);
        mListView.setLoadMoreEnabled(true);
    }
    @Override
    protected void setListener() {
        super.setListener();
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanFootMarK();
            }
        });
        mListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                myFootMark(1);
            }
        });
        mListView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                myLoadMoreFootMark(mPage);
            }
        });
    }
    @Override
    protected void setData() {
        super.setData();
         myFootMark(mPage);
    }
    /**
     * @param page
     * 我的足迹
     *
     */
private void myFootMark(int page){
    startLoadingDialog();
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("token", PreferenceManager.instance().getToken());
    hashMap.put("pageNu", String.valueOf(page));
    HttpHelper.getInstance().post(Url.myFootMark, hashMap, new RawResponseHandler() {
        @Override
        public void onSuccess(int statusCode, String response) {
            LogUtils.d("myFootMark="+response);
            closeLoadingDialog();
            MyFootMark footMark = JsonUtils.parse(response, MyFootMark.class);
            if (footMark.getCode()==1) {
                if (mList!=null) {
                    mList.clear();
                }
                mList.addAll(footMark.getDatas().getFootMarkList());
            }
            mAdapter.setDataList(mList);
            mListView.refreshComplete(1000);
        }

        @Override
        public void onFailure(int statusCode, String error_msg) {
            closeLoadingDialog();
            mListView.refreshComplete(1000);
        }
    });
}

    private void myLoadMoreFootMark(int page){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("pageNu", String.valueOf(page));
        HttpHelper.getInstance().post(Url.myFootMark, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("myFootMark="+response);
                MyFootMark footMark = JsonUtils.parse(response, MyFootMark.class);
                if (footMark.getCode()==1) {
                    mList.addAll(footMark.getDatas().getFootMarkList());
                }
                mAdapter.setDataList(mList);
                mListView.refreshComplete(1000);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mListView.refreshComplete(1000);
            }
        });
    }
    /**
     * 清空足迹
     */
    private void cleanFootMarK(){
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("token", PreferenceManager.instance().getToken());
    HttpHelper.getInstance().post(Url.cleanFootMarK, hashMap, new RawResponseHandler() {
        @Override
        public void onSuccess(int statusCode, String response) {
            BaseBean bean = JsonUtils.parse(response, BaseBean.class);
            if (bean.getCode()==1) {
                mList.clear();
                EventBus.getDefault().post(new EventBean(IEvent.getUserHeadData));
            } else if (bean.getCode()==-1) {
                ToastUtil.showShort(bean.getMsg());
                PreferenceManager.instance().removeToken();
                mBaseActivity.showActivity(LoginActivity.class,null);
            }
        }
        @Override
        public void onFailure(int statusCode, String error_msg) {
            ToastUtil.showShort(error_msg);
        }
    });
}
    /**
     * @param idStr
     * 删除足迹
     */
private void delFootMark(String idStr, final int pos){
    startLoadingDialog();
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("token", PreferenceManager.instance().getToken());
    hashMap.put("idStr",idStr);
    HttpHelper.getInstance().post(Url.delFootMark, hashMap, new RawResponseHandler() {
        @Override
        public void onSuccess(int statusCode, String response) {
            closeLoadingDialog();
            BaseBean bean = JsonUtils.parse(response, BaseBean.class);
            if (bean.getCode()==1) {
                mAdapter.remove(pos);
                EventBus.getDefault().post(new EventBean(IEvent.getUserHeadData));
            } else if (bean.getCode() == 0) {
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
    public void onClick(View v) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
