package com.example.bbacr.ddw.car.address;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.RcvCommonAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.ClassificationRightBean;
import com.example.bbacr.ddw.bean.my.GetUserAddress;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.IEvent;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBase;
import com.example.bbacr.ddw.eventbus.EventBean;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.bbacr.ddw.content.Url.userAddressToBeDefault;

/**
 * A simple {@link Fragment} subclass.
 * 管理收货地址
 */
public class ManageAddressFragment extends BaseFragment {

    @Bind(R.id.list_view)
    LRecyclerView mListView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<GetUserAddress.DatasBean.AddressLestBean> mAdapter;
    private List<GetUserAddress.DatasBean.AddressLestBean> mList = new ArrayList<>();
    @Bind(R.id.btn_add_address)
    Button mBtnAddAddress;
    private TextView mBack;
    public ManageAddressFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_address, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSelf();
            }
        });
        mAdapter = new RcvCommonAdapter<GetUserAddress.DatasBean.AddressLestBean>(getContext(),R.layout.item_listview_shouhuo_address) {
            @Override
            public void setViewData(SuperViewHolder holder, GetUserAddress.DatasBean.AddressLestBean item, int position) {
                holder.setText(item.getName(), R.id.tv_shouhuo_address_name).setText(item.getPhone(),R.id.tv_shouhuo_address_phone).setText(item.getDetail(),R.id.tv_detail_address);
                if (item.isIfDefault()) {
                    holder.setImgRes(R.mipmap.dh, R.id.iv_item_select_default_address);
                }
            }
            @Override
            public void setListener(SuperViewHolder holder, View view) {
                holder.setOnClickListener(R.id.layout);
                holder.setOnClickListener(R.id.ll_select_nomal_address);
                holder.setOnClickListener(R.id.ll_delete_address);/*删除*/
                holder.setOnClickListener(R.id.ll_editor_address);/*编辑*/
            }
            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
                switch (view.getId()) {
                    case R.id.ll_select_nomal_address:
                        userAddressToBeDefault(mList.get(position).getId());
                        break;
                    case R.id.ll_delete_address:
                        deleteUserAddress(mList.get(position).getId());
                        break;
                    case R.id.ll_editor_address:
                        mArguments.putString("name",mList.get(position).getName());
                        mArguments.putString("phone",mList.get(position).getPhone());
                        mArguments.putString("city",mList.get(position).getCity());
                        mArguments.putString("detail",mList.get(position).getDetail());
                        mArguments.putString("location",mList.get(position).getLocation());
                        mArguments.putString("addressId", String.valueOf(mList.get(position).getId()));
                        ShowFragmentUtils.showFragment(getActivity(),EditAddressFragment.class,FragmentTag.EDITADDRESSFRAGMENT,mArguments,true);
                        break;
                    case R.id.layout:
                        userAddressToBeDefault(mList.get(position).getId());
                        break;
                }
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mListView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.a_5dp)
                .setColorResource(R.color.gray_background)
                .build();
        mListView.addItemDecoration(divider);
        mListView.setAdapter(mLRecyclerViewAdapter);
        mListView.setLoadMoreEnabled(false);

    }
    @Override
    protected void setListener() {
        super.setListener();
        mBtnAddAddress.setOnClickListener(this);
        mListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUserAddress();
                mListView.refreshComplete(1000);
                mLRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
       getUserAddress();
    }
//    @Subscribe
//    public void getCardList(EventBean card) {
//        if (card.getMsg().equals(IEvent.getUserAddress)) {
//           getUserAddress();
//        }
//    }
    /**
     * 用户地址列表
     */
    private void getUserAddress(){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        HttpHelper.getInstance().post(Url.getUserAddress, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getUserAddress"+response);
                GetUserAddress bean = JsonUtils.parse(response, GetUserAddress.class);
                if (bean.getCode() == 1) {
                    closeLoadingDialog();
                    if (mList!=null) {
                        mList.clear();
                        mList.addAll(bean.getDatas().getAddressLest());
                    }
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
    /**
     * @param addressId
     * 指定默认收货地址
     */
    private void userAddressToBeDefault(int addressId){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("addressId", String.valueOf(addressId));
        HttpHelper.getInstance().post(Url.userAddressToBeDefault, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("userAddressToBeDefault"+response);
                closeLoadingDialog();
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode()==1) {
//                    EventBus.getDefault().post(new EventBean(IEvent.getDefault));
                    EventBus.getDefault().post(new EventBase());
                    popSelf();
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
    /**
     * @param addressId
     * 删除收货地址
     */
    private void deleteUserAddress(int addressId){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("addressId", String.valueOf(addressId));
        HttpHelper.getInstance().post(Url.deleteUserAddress, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("deleteUserAddress"+response);
                closeLoadingDialog();
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode()==1) {
                    EventBus.getDefault().post(new EventBase());
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_address:
                ShowFragmentUtils.showFragment(getActivity(),MyAddressFragment.class, FragmentTag.MYADDRESSFRAGMENT,null,true);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
