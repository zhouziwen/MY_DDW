package com.example.bbacr.ddw.car;


import android.content.Intent;
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
import com.example.bbacr.ddw.bean.MyFootMark;
import com.example.bbacr.ddw.bean.car.GetPortalShopList;
import com.example.bbacr.ddw.bean.my.MyCollections;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.widget.SingleChoiceDialog;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopListFragment extends BaseFragment {


    @Bind(R.id.list_view)
    LRecyclerView mListView;
    private TextView mBack;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<GetPortalShopList.DatasBean> mAdapter;
    private List<GetPortalShopList.DatasBean> mList = new ArrayList<>();
    private int mPage=1;
    public ShopListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mAdapter = new RcvCommonAdapter<GetPortalShopList.DatasBean>(getContext(),R.layout.shop_list_item) {
            @Override
            public void setViewData(SuperViewHolder holder, GetPortalShopList.DatasBean item, int position) {
                holder.setText(item.getLinkman(),R.id.user_name).
                        setText(item.getContact(),R.id.user_phone).setText(item.getName()+" "+item.getLocation(),R.id.user_address);
            }
            @Override
            public void setListener(SuperViewHolder holder, View view) {
                view.setOnClickListener(holder);
            }

            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
                String[] strs=mList.get(position).getLongitudeAndLatitude().split(",");
                avatar(strs[0],strs[1]);
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
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSelf();
            }
        });
        mListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPortalShopList(1);
            }
        });
        mListView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                getPortalShopList1(mPage);
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        getPortalShopList(mPage);
    }

    @Override
    public void onClick(View v) {

    }
    /**
     * 设置头像
     */
    private void avatar(final String latitude, final String longitude) {
        List<String> list = new ArrayList<>();
        list.add("高德地图");
        list.add("百度地图");
        new SingleChoiceDialog.Builder(getContext()).addList(list)
                .setTitle("请选择")
                .setOnItemClickListener(new SingleChoiceDialog.OnItemClickListener() {
                    @Override
                    public void OnItemClick(String title, int position) {
                        if (position == 0) {//高德地图
                            startGaoDeMap(latitude,longitude);
                        } else if (position == 1) {//百度地图
                            startBaiduMap( latitude,longitude);
                        }
                    }
                }).show();
    }
    //移动APP调起Android高德地图方式
    private void startGaoDeMap(String latitude,String longitude) {
        Intent intent = new Intent("android.intent.action.VIEW",
                android.net.Uri.parse("androidamap://navi?sourceApplication=amap&lat=" + latitude + "&lon=" + longitude + "&dev=1&style=0"));
        intent.setPackage("com.autonavi.minimap");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (isInstallByread("com.autonavi.minimap")) {
            startActivity(intent);
        } else {
            ToastUtil.showShort("没有安装高德地图客户端");
        }
    }
    //移动APP调起Android百度地图方式
    private void startBaiduMap(String latitude,String longitude) {
        Intent intent = null;
        try {
            intent = Intent.getIntent("intent://map/direction?destination=latlng:"+ latitude + "," + longitude + "|name:&origin=" + "我的位置" + "&mode=driving?ion=" + "我的位置2"+ "&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isInstallByread("com.baidu.BaiduMap")) {
            startActivity(intent); // 启动调用
        } else {
            ToastUtil.showShort("没有安装百度地图客户端");
        }
    }
    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName)
                .exists();
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
                if (myCollections.getCode()==1) {
                    if (mList!=null) {
                        mList.clear();
                    }
                    mList.addAll(myCollections.getDatas());
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
                if (myCollections.getCode()==1) {
                    mList.addAll(myCollections.getDatas());
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
