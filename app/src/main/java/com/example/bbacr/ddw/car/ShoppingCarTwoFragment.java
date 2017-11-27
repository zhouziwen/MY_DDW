package com.example.bbacr.ddw.car;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.RcvCommonAdapter;
import com.example.bbacr.ddw.adapter.callback.ICarCallBack;
import com.example.bbacr.ddw.adapter.callback.IClassMoreCallBack;
import com.example.bbacr.ddw.adapter.callback.OnPopWinDisMisBack;
import com.example.bbacr.ddw.adapter.listview.CommonAdapter;
import com.example.bbacr.ddw.adapter.listview.ViewHolder;
import com.example.bbacr.ddw.adapter.lrecycle.AdapterCar;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.ShoppingCarBean;
import com.example.bbacr.ddw.bean.car.GetGoodSpecification;
import com.example.bbacr.ddw.bean.car.GetGoodsValueAddedServices;
import com.example.bbacr.ddw.bean.car.GetSpecification;
import com.example.bbacr.ddw.bean.car.GetUserShoppingCart;
import com.example.bbacr.ddw.bean.car.OptionCart;
import com.example.bbacr.ddw.bean.car.OrderInfor;
import com.example.bbacr.ddw.bean.my.GetDefault;
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
import com.example.bbacr.ddw.utils.StringUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.widget.CommonDialog;
import com.example.bbacr.ddw.widget.CustomPopupWindow;
import com.example.bbacr.ddw.widget.MyListView;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.sunfusheng.glideimageview.GlideImageView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;



/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingCarTwoFragment extends BaseFragment {
    private static final int MYLIVE_MODE_CHECK = 0;
    private static final int MYLIVE_MODE_EDIT = 1;
    @Bind(R.id.LRecyclerView)
    LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private AdapterCar mAdapter;
    private List<GetUserShoppingCart.DatasBean> mList=new ArrayList<>();
    @Bind(R.id.id_cb_select_all)
    CheckBox mIdCbSelectAll;
    @Bind(R.id.id_tv_totalPrice)
    TextView mIdTvTotalPrice;
    @Bind(R.id.tvPostPrice)
    TextView mTvPostPrice;
    @Bind(R.id.id_tv_totalCount_jiesuan)
    TextView mIdTvTotalCountJiesuan;
    @Bind(R.id.id_ll_normal_all_state)
    LinearLayout mIdLlNormalAllState;
    @Bind(R.id.id_tv_delete_all)
    TextView mIdTvDeleteAll;
    @Bind(R.id.id_ll_editing_all_state)
    LinearLayout mIdLlEditingAllState;
    @Bind(R.id.car_foot)
    RelativeLayout mCarFoot;
    private int goodsId;
    private TextView mEdit;
    private CustomPopupWindow mPopupWindow;
    private boolean editorStatus = false;
    private boolean isSelectAll = false;
    private int goods_nmb = 1;
    private int mEditMode = MYLIVE_MODE_CHECK;
    List<String> mStrings = new ArrayList<>();
    List<String> mStringid = new ArrayList<>();
    private GlideImageView dialog_img;
    private TextView dialog_goods_name,dialog_goods_price, dialog_goods_nmb,tv_item_minus_comm_detail,
            tv_item_add_comm_detail,tv_item_number_comm_detail,service;
    private LinearLayout dialog_confirm_ll;
    private String specification, specification1, specification2,specification3,specification4,specification5;
    private CommonAdapter<GetSpecification.DatasBean> mListAdapter;
    private List<String> valueAddedServicesIds = new ArrayList<>();
    private CommonAdapter<GetGoodsValueAddedServices.DatasBean> mDetailAdapter;
    private List<GetGoodsValueAddedServices.DatasBean> mDetailList = new ArrayList<>();
    private List<GetSpecification.DatasBean> mlist = new ArrayList<>();
    private List<String> mGoodsId = new ArrayList<>();
    private TextView mClear;
    public ShoppingCarTwoFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_car_two, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mClear = mFindViewUtils.findViewById(R.id.app_title_action);
    }
    @Override
    protected void setListener() {
        super.setListener();
        mClear.setOnClickListener(this);
        mIdCbSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIdCbSelectAll.isChecked()) {
                    allSelect("true");
                } else {
                    allSelect("false");
                }
            }
        });
        mIdTvTotalCountJiesuan.setOnClickListener(this);
    }
    @Override
    protected void setData() {
        super.setData();
        mAdapter = new AdapterCar(getContext(), mList, new ICarCallBack() {
            @Override
            public void ClickTure(int b) {
                mStrings.clear();
                for (int i = 0; i <mList.get(b).getValueAddedServicesList().size() ; i++) {
                    if (mList.get(b).getValueAddedServicesList().get(i).isIsSelect()) {
                        mStrings.add(String.valueOf(mList.get(b).getValueAddedServicesList().get(i).getId()));
                    }
                }
                optionCart(mList.get(b).getCartid(),"true", StringUtils.arrayToStr(mStrings, ","));
                LogUtils.d("1="+StringUtils.arrayToStr(mStrings, ","));
            }
            @Override
            public void ClickFalse(int c) {
                mStrings.clear();
                for (int i = 0; i <mList.get(c).getValueAddedServicesList().size() ; i++) {
                    if (mList.get(c).getValueAddedServicesList().get(i).isIsSelect()) {
                        mStrings.add(String.valueOf(mList.get(c).getValueAddedServicesList().get(i).getId()));
                    }
                }
                optionCart(mList.get(c).getCartid(),"false",StringUtils.arrayToStr(mStrings, ","));
                LogUtils.d("2="+StringUtils.arrayToStr(mStrings, ","));
            }
            @Override
            public void ClickBomTure(boolean isIsSelect,int carId,GetUserShoppingCart.DatasBean item,int pos) {
                mStringid.clear();
                for (int i = 0; i <item.getValueAddedServicesList().size() ; i++) {
                    if (item.getValueAddedServicesList().get(i).isIsSelect()) {
                        mStringid.add(String.valueOf(item.getValueAddedServicesList().get(i).getId()));
                    }
                }
                mStringid.add(String.valueOf(item.getValueAddedServicesList().get(pos).getId()));
                if (isIsSelect) {
                    optionCart(carId, "true",StringUtils.arrayToStr(mStringid, ",") );
                } else {
                    optionCart(carId, "false", StringUtils.arrayToStr(mStringid, ","));
                }
                ToastUtil.showShort(StringUtils.arrayToStr(mStringid, ","));
            }
            @Override
            public void ClickBomFalse(boolean isIsSelect,int carId, GetUserShoppingCart.DatasBean item,int pos) {
                mStringid.clear();
                for (int i = 0; i <item.getValueAddedServicesList().size() ; i++) {
                    if (item.getValueAddedServicesList().get(i).isIsSelect()) {
                        mStringid.add(String.valueOf(item.getValueAddedServicesList().get(i).getId()));
                    }
                }
                mStringid.remove(String.valueOf(item.getValueAddedServicesList().get(pos).getId()));
                if (isIsSelect) {
                    optionCart(carId, "true", StringUtils.arrayToStr(mStringid, ","));
                } else {
                    optionCart(carId, "false", StringUtils.arrayToStr(mStringid, ","));
                }
                ToastUtil.showShort(StringUtils.arrayToStr(mStringid, ","));
            }
            @Override
            public void Count(GetUserShoppingCart.DatasBean item) {
                modifyCount(item.getCartid(),item.getCount());
            }
            @Override
            public void delete(GetUserShoppingCart.DatasBean item,int pos) {
                deleteUserShoppingCart(String.valueOf(item.getCartid()),pos);
            }
            @Override
            public void collect(GetUserShoppingCart.DatasBean item) {
                addCollections(String.valueOf(item.getCartid()));
            }
            @Override
            public void specification(GetUserShoppingCart.DatasBean item, int pos) {
                getSpecification(String.valueOf(item.getGoodsId()),item.getGoodsName(),item.getMemberPrice(),item.getGoodSpecificationName());
            }
        });
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.a_5dp)
                .setColorResource(R.color.gray_background)
                .build();
        mLRecyclerView.addItemDecoration(divider);
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setPullRefreshEnabled(false);
        mLRecyclerView.setLoadMoreEnabled(false);
        getUserShoppingCart();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tv_totalCount_jiesuan:
                cartsBuy();
                break;
            case R.id.app_title_action:
                if (mGoodsId.size() > 0) {
                    new CommonDialog.Builder(getActivity()).setTitle("提示")
                            .setCanceledOnTouchOutside(false)
                            .setMessage("您确定要清空购物车吗", R.color.line_color)
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    clearUserShoppingCart(StringUtils.arrayToStr(mGoodsId, ","));
                                }
                            }, R.color.red).setNegativeButton("取消", null).show();
                } else {
                    ToastUtil.showShort("购物车已被清空");
                }



                break;
        }

    }

    private void clearAll() {
    }
    @Subscribe
    public void getCardList(EventBase card) {
        if (card.getMsg().equals(IEvent.getUserShoppingCart)) {
            getUserShoppingCart1();
        }
    }
    /**
     * 购物车
     */
    private void getUserShoppingCart(){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        HttpHelper.getInstance().post(Url.getUserShoppingCart, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getUserShoppingCart"+response);
                closeLoadingDialog();
                GetUserShoppingCart bean = JsonUtils.parse(response, GetUserShoppingCart.class);
                if (bean.getCode()==1) {
                    for (int i = 0; i <bean.getDatas().size() ; i++) {
                        mGoodsId.add(String.valueOf(bean.getDatas().get(i).getCartid()));
                    }
                    mIdTvTotalPrice.setText("合计：￥"+bean.getTotalMoney());
                    mTvPostPrice.setText("总共："+bean.getRowCount()+"件");
                    if (mList!=null) {
                        mList.clear();
                    }
                    mList.addAll(bean.getDatas());
                    if (mList.size() == bean.getRowCount()) {
                        mIdCbSelectAll.setChecked(true);
                    } else {
                        mIdCbSelectAll.setChecked(false);
                    }
                    mAdapter.setDataList(mList);
                    mLRecyclerViewAdapter.notifyDataSetChanged();
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
            }
        });
    }
    private void getUserShoppingCart1(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        HttpHelper.getInstance().post(Url.getUserShoppingCart, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getUserShoppingCart"+response);
                GetUserShoppingCart bean = JsonUtils.parse(response, GetUserShoppingCart.class);
                if (bean.getCode()==1) {
                    mIdTvTotalPrice.setText("合计：￥"+bean.getTotalMoney());
                    mTvPostPrice.setText("总共："+bean.getRowCount()+"件");
                    if (mList!=null) {
                        mList.clear();
                        mList.addAll(bean.getDatas());
                    }
                    if (mList.size() == bean.getRowCount()) {
                        mIdCbSelectAll.isChecked();
                    } else {
                        mIdCbSelectAll.isChecked();
                    }
                    mAdapter.setDataList(mList);
//                    mLRecyclerViewAdapter.notifyDataSetChanged();
                } else if (bean.getCode() == 0) {
                    ToastUtil.showShort(bean.getMsg());
                } else {
                    ToastUtil.showShort(bean.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
            }
        });
    }


    /**
     * 去结算
     */
    private void cartsBuy() {
        startLoadingDialog("正在加载");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        HttpHelper.getInstance().post(Url.cartsBuy, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                closeLoadingDialog();
                LogUtils.d("cartsBuy="+response);
                PreferenceManager.instance().saveResponse(response);
                if (response.contains("code")) {
                    BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                    if (bean.getCode() == 0) {
                        ToastUtil.showShort(bean.getMsg());
                    } else if (bean.getCode() == -1) {
                        ToastUtil.showShort(bean.getMsg());
                    }
                } else {
                    mArguments.putString("type","2");
                    ShowFragmentUtils.showFragment(getActivity(),ConfirmOrderFragment.class, FragmentTag.CONFIRMORDERFRAGMENT,mArguments,true);
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
     * @param id
     * 删除
     */
    private void deleteUserShoppingCart(String id, final int pos){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("id", id);
        HttpHelper.getInstance().post(Url.deleteUserShoppingCart, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("deleteUserShoppingCart"+response);
                closeLoadingDialog();
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode()==1) {
                  mAdapter.remove(pos);
                    EventBus.getDefault().post(new EventBase() );
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

    /**
     * @param id
     * 清空购物车
     */
    private void clearUserShoppingCart(String id){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("id", id);
        HttpHelper.getInstance().post(Url.deleteUserShoppingCart, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("deleteUserShoppingCart"+response);
                closeLoadingDialog();
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode()==1) {
                   mAdapter.clear();
                    EventBus.getDefault().post(new EventBase() );
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
    /**
     * @param id
     * @param option
     * @param valueAddedServicesIds
     * 勾选购物车
     */
    private void optionCart(int id, String option, String valueAddedServicesIds) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("id", String.valueOf(id));
        hashMap.put("option", option);
        hashMap.put("valueAddedServicesIds", valueAddedServicesIds);
        HttpHelper.getInstance().post(Url.optionCart, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("optionCart" + response);
                closeLoadingDialog();
                OptionCart bean = JsonUtils.parse(response, OptionCart.class);
                if (bean.getCode() == 1) {
                    if (mList.size() == bean.getDatas().getRowCount()) {
                        mIdCbSelectAll.setChecked(true);
                    } else {
                        mIdCbSelectAll.setChecked(false);
                    }
                    EventBus.getDefault().post(new EventBase());
                } else {
                    ToastUtil.showShort(bean.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                closeLoadingDialog();
            }
        });
    }
    /**
     * @param goodsId 添加我的收藏
     */
    private void addCollections(String goodsId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("goodsId", goodsId);
        HttpHelper.getInstance().post(Url.addCollections, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode() == 1) {
                    ToastUtil.showShort("添加到我的收藏");
                } else if (bean.getCode() == 0) {
                    ToastUtil.showShort(bean.getMsg());
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
    /**
     * @param id
     * @param count
     * 编辑购物车数量
     */
    private void modifyCount(int id,int count) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("id", String.valueOf(id));
        hashMap.put("count", String.valueOf(count));
        HttpHelper.getInstance().post(Url.modifyCount, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("modifyCount"+response);
                closeLoadingDialog();
                OptionCart bean = JsonUtils.parse(response, OptionCart.class);
                if (bean.getCode() == 1) {
                    if (mList.size() == bean.getDatas().getRowCount()) {
                        mIdCbSelectAll.setChecked(true);
                    } else {
                        mIdCbSelectAll.setChecked(false);
                    }
                    mIdTvTotalPrice.setText("合计：￥"+bean.getDatas().getTotalMoney());
                    mTvPostPrice.setText("总共："+bean.getDatas().getRowCount()+"件");
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
    /**
     * @param isSelect
     * 全选
     */
    private void allSelect(String isSelect) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("isSelect",isSelect);
        HttpHelper.getInstance().post(Url.allSelect, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("allSelect"+response);
                closeLoadingDialog();
                OptionCart bean = JsonUtils.parse(response, OptionCart.class);
                if (bean.getCode() == 1) {
                    mIdTvTotalPrice.setText("合计：￥"+bean.getDatas().getTotalMoney());
                    mTvPostPrice.setText("总共："+bean.getDatas().getRowCount()+"件");
                    EventBus.getDefault().post(new EventBase());
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
    /**
     * @param modifydata
     * 编辑购物车
     */
    private void modifyUserShoppingCart(String modifydata) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("modifydata",modifydata);
        HttpHelper.getInstance().post(Url.modifyUserShoppingCart, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("modifyUserShoppingCart"+response);
                closeLoadingDialog();
                OptionCart bean = JsonUtils.parse(response, OptionCart.class);
                if (bean.getCode() == 1) {
                    mAdapter.clear();
                    mPopupWindow.dismissWindow();
                    EventBus.getDefault().post(new EventBase());

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
    private void popBom(final List<GetSpecification.DatasBean> mList, final String goodsId, final String name, final String price, final String spec) {
        mPopupWindow = new CustomPopupWindow(
                getContext(),
                R.layout.popup_car,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, new OnPopWinDisMisBack() {
            @Override
            public void onPopWindowDismiss() {

            }
        }
        ) {
            @Override
            public void setData(View view) {
                MyListView myListView = (MyListView) view.findViewById(R.id.dialog_listView);
                MyListView ListView = (MyListView) view.findViewById(R.id.lRecyclerView_bom);
                dialog_img = (GlideImageView) view.findViewById(R.id.dialog_img);
                dialog_goods_name = (TextView) view.findViewById(R.id.dialog_goods_name);
                dialog_goods_price = (TextView) view.findViewById(R.id.dialog_goods_price);
                dialog_goods_nmb = (TextView) view.findViewById(R.id.dialog_goods_nmb);
                dialog_confirm_ll = (LinearLayout) view.findViewById(R.id.dialog_confirm_ll);
                Button button = (Button) view.findViewById(R.id.sure);
                button.setVisibility(View.VISIBLE);
                dialog_goods_name.setText(name);
                dialog_goods_price.setText(price);
                dialog_goods_nmb.setText(spec);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String r=StringUtils.arrayToStr(valueAddedServicesIds, ",");
                        modifyUserShoppingCart("id="+goodsId+":count="+
                                String.valueOf(goods_nmb)+":specificationId="+specification+":valueAddedServicesIds="+r);
                    }
                });
                service = (TextView) view.findViewById(R.id.service);
                RelativeLayout custom_dialog_close = (RelativeLayout) view.findViewById(R.id.custom_dialog_close);
                custom_dialog_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismissWindow();
                    }
                });
                tv_item_minus_comm_detail = (TextView) view.findViewById(R.id.tv_item_minus_comm_detail);
                tv_item_minus_comm_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goods_nmb--;
                        if (goods_nmb<1){
                            Toast.makeText(getActivity(),"已是最低购买量",Toast.LENGTH_SHORT).show();
                        }else {
                            tv_item_number_comm_detail.setText(String.valueOf(goods_nmb));
                        }
                    }
                });
                tv_item_number_comm_detail = (TextView) view.findViewById(R.id.tv_item_number_comm_detail);
                tv_item_add_comm_detail = (TextView) view.findViewById(R.id.tv_item_add_comm_detail);
                tv_item_add_comm_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goods_nmb++;
                        tv_item_number_comm_detail.setText(String.valueOf(goods_nmb));
                    }
                });
                setAdapter(goodsId,myListView, mList);
                setDetailAdapter(ListView);
            }
        };
        mPopupWindow.showAsDownWindow(mCarFoot);
    }

    private void setAdapter(final String goodsId, MyListView myListView, List<GetSpecification.DatasBean> mList) {
        mListAdapter = new CommonAdapter<GetSpecification.DatasBean>(getContext(), mList, R.layout.specification_item_list) {
            @Override
            public void setViewData(ViewHolder holder, final GetSpecification.DatasBean item, final int position) {
                final TagFlowLayout flowLayout = holder.getView(R.id.flower);
                holder.setText(item.getName(), R.id.specification);
                flowLayout.setMaxSelectCount(1);
                flowLayout.setAdapter(new TagAdapter<GetSpecification.DatasBean.DataBean>(item.getData()) {
                    @Override
                    public View getView(FlowLayout parent, int position, GetSpecification.DatasBean.DataBean o) {
                        final LayoutInflater mInflater = LayoutInflater.from(getActivity());
                        TextView tv = (TextView) mInflater.inflate(R.layout.shopping_specification_layout_item,
                                flowLayout, false);
                        tv.setText(o.getContent());
                        return tv;
                    }
                });
                flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int i, FlowLayout parent) {
                        switch (position) {
                            case 0:
                                specification1 = item.getId() + ":" + item.getData().get(i).getId();
                                break;
                            case 1:
                                specification2= item.getId() + ":" + item.getData().get(i).getId();
                                break;
                        }
                        getGoodSpecification(goodsId, specification1 + "," + specification2);
//
                        ToastUtil.showShort(specification1+","+specification2);
                        if (specification1==null) {
                            ToastUtil.showShort(specification1);
                            getGoodSpecification(goodsId, "");
                        } else if (!TextUtils.isEmpty(specification1)&&specification2 == null) {
                            ToastUtil.showShort(specification1);
                            getGoodSpecification(goodsId, specification1);
                        } else if (!TextUtils.isEmpty(specification2)&&specification3==null) {
                            ToastUtil.showShort(specification1+","+specification2);
                            getGoodSpecification(goodsId, specification1+","+specification2);
                        } else if (!TextUtils.isEmpty(specification3)&&specification4==null) {
                            ToastUtil.showShort(specification1+","+specification2+","+specification3);
                            getGoodSpecification(goodsId, specification1+","+specification2+","+specification3);
                        } else if (!TextUtils.isEmpty(specification4)&&specification5==null) {
                            getGoodSpecification(goodsId, specification1+","+specification2+","+specification3+","+specification4);
                        } else if (!TextUtils.isEmpty(specification5)) {
                            getGoodSpecification(goodsId, specification1+","+specification2+","+specification3+","+specification4+","+specification5);
                        }

                        return true;
                    }
                });
            }
            @Override
            public void onClickBack(View v, int position, ViewHolder holder) {

            }

            @Override
            public void onBack(String name) {

            }
        };
        myListView.setAdapter(mListAdapter);
    }
    private void setDetailAdapter(MyListView myListView){
        mDetailAdapter = new CommonAdapter<GetGoodsValueAddedServices.DatasBean>(getContext(),null,R.layout.specification_grid_two) {
            @Override
            public void setViewData(ViewHolder holder, final GetGoodsValueAddedServices.DatasBean item, int position) {
                holder.setText(item.getName(), R.id.specification);
                CheckBox c = holder.getView(R.id.specification);
                c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            valueAddedServicesIds.add(item.getId() + "");
                        } else {
                            valueAddedServicesIds.remove(item.getId() + "");
                        }
                    }
                });
            }
            @Override
            protected void setEvent(ViewHolder holder, View convertView, int position) {
                holder.setOnclickListener(R.id.look);
                holder.setOnclickListener(R.id.specification);
            }
            @Override
            public void onClickBack(View v, int position, ViewHolder holder) {
                switch (v.getId()) {
                    case R.id.look:
                        ToastUtil.showShort("详情");
                        break;
                    case R.id.specification:

                        break;
                }
            }

            @Override
            public void onBack(String name) {

            }
        };
        myListView.setAdapter(mDetailAdapter);
    }
    /**
     * @param goodsId
     * @param propertiesid
     * 规格详情
     */
    private void getGoodSpecification(String goodsId,String propertiesid){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("goodid",goodsId);
        hashMap.put("propertiesid",propertiesid);
        HttpHelper.getInstance().post(Url.getGoodSpecification, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getGoodSpecification"+response);
                GetGoodSpecification s = JsonUtils.parse(response, GetGoodSpecification.class);
                if (s.getCode()==1) {
                    dialog_img.loadImage(s.getDatas().getImage(), R.mipmap.ic_launcher);
                    dialog_goods_name.setText(s.getDatas().getGoodName());
                    dialog_goods_price.setText(s.getDatas().getMemberPrice());
                    dialog_goods_nmb.setText(s.getDatas().getPropertiesName());
                    specification = String.valueOf(s.getDatas().getId());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
            }
        });
    }
    /**
     * @param goodsId 规格
     */
    private void getSpecification(final String goodsId, final String name, final String price, final String spec) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("goodid", goodsId);
        HttpHelper.getInstance().post(Url.getSpecification, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getSpecification"+response);
                closeLoadingDialog();
                GetSpecification bean = JsonUtils.parse(response, GetSpecification.class);
                if (bean.getCode() == 1) {
                    if (mlist != null) {
                        mlist.clear();
                    }
                    if (!bean.getDatas().isEmpty()&&bean.getDatas()!=null) {
                        mlist.addAll(bean.getDatas());
                    }
                    popBom(mlist,goodsId,name,price,spec);
                    getGoodsValueAddedServices(goodsId);
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
    /**
     * @param goodsId 增值详情
     */
    private void getGoodsValueAddedServices(String goodsId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", goodsId);
        HttpHelper.getInstance().post(Url.getGoodsValueAddedServices, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getGoodsValueAddedServices"+response);
                GetGoodsValueAddedServices bean = JsonUtils.parse(response, GetGoodsValueAddedServices.class);
                if (bean.getCode()==1) {
                    if (bean.getDatas() != null&&!bean.getDatas().isEmpty()) {
                        service.setVisibility(View.VISIBLE);
                        mDetailList.addAll(bean.getDatas());
                        mDetailAdapter.update(mDetailList);
                    } else {
                        service.setVisibility(View.GONE);
                    }
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
