package com.example.bbacr.ddw.home.shopdetail;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.FragAdapter;
import com.example.bbacr.ddw.adapter.callback.OnPopWinDisMisBack;
import com.example.bbacr.ddw.adapter.listview.CommonAdapter;
import com.example.bbacr.ddw.adapter.listview.ViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.car.GetGoodSpecification;
import com.example.bbacr.ddw.bean.car.GetGoodsValueAddedServices;
import com.example.bbacr.ddw.bean.car.GetSpecification;
import com.example.bbacr.ddw.bean.detail.ShoppingDetailBean;
import com.example.bbacr.ddw.bean.home.BuyRightNow;
import com.example.bbacr.ddw.bean.home.PortalDetails;
import com.example.bbacr.ddw.bean.list.ListBean;
import com.example.bbacr.ddw.bean.my.AddCollections;
import com.example.bbacr.ddw.car.ConfirmOrderFragment;
import com.example.bbacr.ddw.car.ShoppingCarTwoFragment;
import com.example.bbacr.ddw.car.address.ManageAddressFragment;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.IEvent;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBean;
import com.example.bbacr.ddw.eventbus.EventList;
import com.example.bbacr.ddw.home.evaluate.EvaluateFragment;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.GlideImageLoader;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.StringUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.web.WebActivity;
import com.example.bbacr.ddw.web.WebFragment;
import com.example.bbacr.ddw.widget.CommonDialog;
import com.example.bbacr.ddw.widget.CustomPopupWindow;
import com.example.bbacr.ddw.widget.MyListView;
import com.sunfusheng.glideimageview.GlideImageView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 我的商品详情
 */
public class MyShoppingDetailFragment extends BaseFragment {
    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.back_img)
    ImageView mBackImg;
    @Bind(R.id.share)
    ImageView mShare;
    @Bind(R.id.pop)
    ImageView mPop;
    @Bind(R.id.focus_btn)
    LinearLayout mFocusBtn;
    @Bind(R.id.shopcar_collect)
    LinearLayout mShopcarCollect;
    @Bind(R.id.shopcar_btn)
    LinearLayout mShopcarBtn;
    @Bind(R.id.put_in)
    TextView mPutIn;
    @Bind(R.id.buy_now)
    TextView mBuyNow;
    @Bind(R.id.layout)
    LinearLayout mLayout;
    @Bind(R.id.collect)
    CheckBox mCollect;
    private ShareAction mShareAction;
    private UMShareListener mShareListener;
    private int goods_nmb = 1;
    private CustomPopupWindow mPopupWindow;
    private List<String> mTitles = new ArrayList<>();//标题集合
    private List<BaseFragment> mfragments = new ArrayList<>();//视图集合
    private CommonAdapter<GetSpecification.DatasBean> mAdapter;
    private CommonAdapter<GetGoodsValueAddedServices.DatasBean> mDetailAdapter;
    private List<GetGoodsValueAddedServices.DatasBean> mDetailList = new ArrayList<>();
    private List<GetSpecification.DatasBean> mList = new ArrayList<>();
    private List<String> valueAddedServicesIds = new ArrayList<>();
    private String specification, specification1, specification2,specification3,specification4,specification5;
    private GlideImageView dialog_img;
    private TextView dialog_goods_name, dialog_goods_price, dialog_goods_nmb, tv_item_minus_comm_detail,
            tv_item_add_comm_detail, tv_item_number_comm_detail, service;
    private LinearLayout dialog_confirm_ll,dialog_confirm_buy;
    private String collectionsId;
    private String mShoppingName, mShoppingPrice,mShoppingPic;
    final public static int REQUEST_CODE_ASK_CALL_PHONE=123;
    public MyShoppingDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_shopping_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mTitles.add("商品");
        mTitles.add("详情");
        mTitles.add("评价");
        mfragments.add(new VesselFragment());
        mfragments.add(new DetailFragment());
        mfragments.add(new EvaluateFragment());
    }

    @Override
    protected void setListener() {
        super.setListener();
        mBackImg.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mPop.setOnClickListener(this);
        mFocusBtn.setOnClickListener(this);
        mShopcarCollect.setOnClickListener(this);
        mShopcarBtn.setOnClickListener(this);
        mPutIn.setOnClickListener(this);
        mBuyNow.setOnClickListener(this);
        mShareListener = new CustomShareListener(this);
        mShareAction = new ShareAction(getActivity()).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.MORE)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (share_media == SHARE_MEDIA.SMS) {
                            new ShareAction(getActivity()).withText("来自分享面板标题")
                                    .setPlatform(share_media)
                                    .setCallback(mShareListener)
                                    .share();
                        } else {
//                            UMWeb web = new UMWeb(Defaultcontent.url);
                            UMWeb web = new UMWeb("http://www.chinaddcat.com/m/phoneHtml/index.html?userId="+PreferenceManager.instance().getUseId());
                            web.setTitle("叮当一响，购物到家");
                            web.setDescription("同城优选，助利商城");
                            web.setThumb(new UMImage(getContext(), R.mipmap.ddw));
                            new ShareAction(getActivity()).withMedia(web)
                                    .setPlatform(share_media)
                                    .setCallback(mShareListener)
                                    .share();
                        }
                    }
                });
    }
    private static class CustomShareListener implements UMShareListener {
        private WeakReference<MyShoppingDetailFragment> mActivity;
        private CustomShareListener(MyShoppingDetailFragment activity) {
            mActivity = new WeakReference(activity);
        }
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }
        @Override
        public void onResult(SHARE_MEDIA platform) {
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(mActivity.get().getContext(), " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                        && platform != SHARE_MEDIA.EMAIL
                        && platform != SHARE_MEDIA.FLICKR
                        && platform != SHARE_MEDIA.FOURSQUARE
                        && platform != SHARE_MEDIA.TUMBLR
                        && platform != SHARE_MEDIA.POCKET
                        && platform != SHARE_MEDIA.PINTEREST
                        && platform != SHARE_MEDIA.INSTAGRAM
                        && platform != SHARE_MEDIA.GOOGLEPLUS
                        && platform != SHARE_MEDIA.YNOTE
                        && platform != SHARE_MEDIA.EVERNOTE) {
                    Toast.makeText(mActivity.get().getContext(), " 分享成功啦", Toast.LENGTH_SHORT).show();
                }
            }
        }
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST
                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
                Toast.makeText(mActivity.get().getContext(), " 分享失败啦", Toast.LENGTH_SHORT).show();
                if (t != null) {
                    Log.d("throw", "throw:" + t.getMessage());
                }
            }
        }
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(mActivity.get().getContext(), " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mShareAction.close();
    }
    @Override
    protected void setData() {
        super.setData();
        EventBus.getDefault().post(new EventList(mArguments.getString("goodsId")));
        ddmGoodsC(mArguments.getString("goodsId"));
        portalDetails2(mArguments.getString("goodsId"),"");
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(0)));
        //初始化适配器
        FragAdapter adapter = new FragAdapter(getChildFragmentManager(), mfragments, mTitles);
        mViewpager.setOffscreenPageLimit(2);
        mViewpager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewpager);//让TabLayout随着ViewPager的变换而变换
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img:
                popSelf();
                break;
            case R.id.share:
                if (!TextUtils.isEmpty(PreferenceManager.instance().getToken())) {
                    mShareAction.open();
                } else {
                    mBaseActivity.showActivity(LoginActivity.class,null);
                }
                break;
            case R.id.pop:
                break;
            case R.id.focus_btn:/*客服*/
                if (Build.VERSION.SDK_INT >= 23) {
                    int checkCallPhonePermission = ContextCompat.checkSelfPermission(getContext(),
                            Manifest.permission.CALL_PHONE);
                    if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[] {
                                Manifest.permission.CALL_PHONE
                        }, REQUEST_CODE_ASK_CALL_PHONE);
                        return;
                    } else {
                        // 上面已经写好的拨号方法 callDirectly(mobile);
                        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + "4006016606"));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                } else {
                    // 上面已经写好的拨号方法 callDirectly(mobile);
                    Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + "4006016606"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
//                if (isQQClientAvailable(getActivity())) {
//                    String url = "mqqwpa://im/chat?chat_type=crm&uin=938193800&version=1&src_type=web&web_src=http:://wpa.b.qq.com";
//                    getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
//                } else {
//                    Toast.makeText(getContext(), "您的手机暂未安装QQ客户端", Toast.LENGTH_SHORT).show();
//                }
                break;
            case R.id.shopcar_collect:/*收藏*/
                addCollections(mArguments.getString("goodsId"));
                break;
            case R.id.shopcar_btn:/*我的购物车*/
                ShowFragmentUtils.showFragment(getActivity(), ShoppingCarTwoFragment.class, FragmentTag.SHOPPINGCARTWOFRAGMENT, null, true);
                break;
            case R.id.put_in:
                getSpecification(mArguments.getString("goodsId"));
                break;
            case R.id.buy_now:
                getSpecification(mArguments.getString("goodsId"));
                break;
            case R.id.collect:
                if (mCollect.isChecked()) {
                    addCollections(mArguments.getString("goodsId"));
                } else {
                    delCollections(collectionsId);
                }
                break;
        }
    }
    @Subscribe
    public void eventBus(EventList bean) {
        if (bean.getResponse().equals("pop")) {
            getSpecification(mArguments.getString("goodsId"));
        }

    }
    /**
     * 判断qq是否可用
     *
     * @param context
     * @return
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
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
                LogUtils.d("delCollections=" + response);
                AddCollections bean = JsonUtils.parse(response, AddCollections.class);
                if (bean.getCode() == 1) {
                    ToastUtil.showShort("已添加到我的收藏");
                    collectionsId =bean.getDatas().getCollectionsId();
                    mCollect.setChecked(true);
                    EventBus.getDefault().post(new EventBean(IEvent.getUserHeadData));
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
                ToastUtil.showShort(error_msg);
            }
        });
    }

    /**
     * @param idStr 删除我的收藏
     */
    private void delCollections(String idStr) {
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
                    mCollect.setChecked(false);
                    EventBus.getDefault().post(new EventBean(IEvent.getUserHeadData));
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
     * @param id 商品详情
     */
    private void ddmGoodsC(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("id", id);
        HttpHelper.getInstance().post(Url.ddmGoodsC, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("ddmGoodsC="+response);
                ShoppingDetailBean bean = JsonUtils.parse(response, ShoppingDetailBean.class);
                if (bean.getCode() == 1) {
                    if (bean.getDatas().isIsCollections()) {
                        collectionsId = bean.getDatas().getCollectionsId();
                        mCollect.setChecked(true);
                    }
                    mShoppingName = bean.getDatas().getName();
                    mShoppingPrice = bean.getDatas().getMemberPrice();
                    mShoppingPic = bean.getDatas().getMainImg();
                    EventBus.getDefault().post(bean);
                } else if (bean.getCode()==0) {
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
     * @param orderInfor
     * 立即购买
     */
    private void buyRightNow(String orderInfor) {
        startLoadingDialog("正在加载");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("orderInfor", orderInfor);
        HttpHelper.getInstance().post(Url.buyRightNow, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("ddmGoodsC="+response);
                closeLoadingDialog();
                PreferenceManager.instance().saveResponse(response);
                if (response.contains("code")) {
                    BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                    if (bean.getCode() == 0) {
                        ToastUtil.showShort(bean.getMsg());
                    } else if (bean.getCode() == -1) {
                        ToastUtil.showShort(bean.getMsg());
                    }
                } else {
                    BuyRightNow bean = JsonUtils.parse(PreferenceManager.instance().getResponse(), BuyRightNow.class);
                    if (bean.getAddressType() == 1) {
                        mPopupWindow.dismissWindow();
                        new CommonDialog.Builder(getActivity()).setTitle("提示")
                                .setCanceledOnTouchOutside(false)
                                .setMessage("你还没有收货地址，先去设置一下吧？", R.color.line_color)
                                .setPositiveButton("去设置", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        ShowFragmentUtils.showFragment(getActivity(), ManageAddressFragment.class, FragmentTag.MANAGEADDRESSFRAGMENT, null, true);
                                    }
                                }, R.color.red).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popSelf();
                            }
                        }).show();
                    } else {
                        mPopupWindow.dismissWindow();
                        mArguments.putString("type","1");
                        mArguments.putString("orderInfor","[{goodId:"+mArguments.getString("goodsId")+ ",num:"+goods_nmb+",goodSpecificationId:"+specification+",addService:\""+StringUtils.arrayToStr(valueAddedServicesIds, ",")+"\"}]");
                        ShowFragmentUtils.showFragment(getActivity(),ConfirmOrderFragment.class, FragmentTag.CONFIRMORDERFRAGMENT,mArguments,true);
                    }

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
     * @param goodsId
     * @param goodSpecificationId
     * 图文详情
     */
    private void portalDetails2(String goodsId,String goodSpecificationId){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", goodsId);
        hashMap.put("goodSpecificationId", goodSpecificationId);
        HttpHelper.getInstance().post(Url.portalDetails2, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("portalDetails2"+response);
                PortalDetails portalDetails = JsonUtils.parse(response, PortalDetails.class);
                if (portalDetails.getCode()==1) {
                    EventBus.getDefault().post(portalDetails.getDatas());
                } else if (portalDetails.getCode()==0) {
                    ToastUtil.showShort(portalDetails.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
            }
        });
    }
    private void popBom(final List<GetSpecification.DatasBean> mList) {
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
                dialog_confirm_buy = (LinearLayout) view.findViewById(R.id.dialog_confirm_buy);
                dialog_goods_name.setText(mShoppingName);
                dialog_goods_price.setText(mShoppingPrice);
                dialog_img.loadImage(mShoppingPic, R.mipmap.ic_launcher);
                dialog_confirm_ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String r = StringUtils.arrayToStr(valueAddedServicesIds, ",");
                        addUserShoppingCart(mArguments.getString("goodsId"), String.valueOf(goods_nmb), specification, r);
                    }
                });
                dialog_confirm_buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*http://localhost/purchaseOrderC/buyRightNow.json?token='sfsfsfsf'&orderInfor=[{goodId:3,num:4,goodSpecificationId:1,addService:"1,2,3,4"}]*/
                        buyRightNow("[{goodId:"+mArguments.getString("goodsId")+ ",num:"+goods_nmb+",goodSpecificationId:"+specification+",addService:\""+StringUtils.arrayToStr(valueAddedServicesIds, ",")+"\"}]");
                        LogUtils.d("接口="+"[{goodId:"+"1"+ ",num:"+goods_nmb+",goodSpecificationId:"+specification+",addService:"+StringUtils.arrayToStr(valueAddedServicesIds, ",")+"}]");
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
                        if (goods_nmb < 1) {
                            Toast.makeText(getActivity(), "已是最低购买量", Toast.LENGTH_SHORT).show();
                        } else {
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
                setAdapter(myListView, mList);
                setDetailAdapter(ListView);
            }
        };
        mPopupWindow.showAsDownWindow(mLayout);
    }

    private void setAdapter(MyListView myListView, List<GetSpecification.DatasBean> mList) {
        mAdapter = new CommonAdapter<GetSpecification.DatasBean>(getContext(), mList, R.layout.specification_item_list) {
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
                                specification2 = item.getId() + ":" + item.getData().get(i).getId();
                                break;
                            case 2:
                                specification3=item.getId() + ":" + item.getData().get(i).getId();
                                break;
                            case 3:
                                specification4=item.getId() + ":" + item.getData().get(i).getId();
                                break;
                            case 4:
                                specification5=item.getId() + ":" + item.getData().get(i).getId();
                                break;
                        }
                        if (specification1==null) {
                            ToastUtil.showShort(specification1);
                            getGoodSpecification(mArguments.getString("goodsId"), "");
                        } else if (!TextUtils.isEmpty(specification1)&&specification2 == null) {
                            ToastUtil.showShort(specification1);
                            getGoodSpecification(mArguments.getString("goodsId"), specification1);
                        } else if (!TextUtils.isEmpty(specification2)&&specification3==null) {
                            ToastUtil.showShort(specification1+","+specification2);
                            getGoodSpecification(mArguments.getString("goodsId"), specification1+","+specification2);
                        } else if (!TextUtils.isEmpty(specification3)&&specification4==null) {
                            ToastUtil.showShort(specification1+","+specification2+","+specification3);
                            getGoodSpecification(mArguments.getString("goodsId"), specification1+","+specification2+","+specification3);
                        } else if (!TextUtils.isEmpty(specification4)&&specification5==null) {
                            getGoodSpecification(mArguments.getString("goodsId"), specification1+","+specification2+","+specification3+","+specification4);
                        } else if (!TextUtils.isEmpty(specification5)) {
                            getGoodSpecification(mArguments.getString("goodsId"), specification1+","+specification2+","+specification3+","+specification4+","+specification5);
                        }
//                        ToastUtil.showShort(specification1 + "," + specification2);
//                        Toast.makeText(getActivity(), item.getData().get(i).getContent(), Toast.LENGTH_SHORT).show();

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
        myListView.setAdapter(mAdapter);
    }
    private void setDetailAdapter(MyListView myListView) {
        mDetailAdapter = new CommonAdapter<GetGoodsValueAddedServices.DatasBean>(getContext(), null, R.layout.specification_grid_two) {
            @Override
            public void setViewData(ViewHolder holder, final GetGoodsValueAddedServices.DatasBean item, int position) {
                holder.setText(item.getName(), R.id.specification);
                CheckBox c = holder.getView(R.id.specification);
                c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            valueAddedServicesIds.add(String.valueOf(item.getId()));
                        } else {
                            valueAddedServicesIds.remove(String.valueOf(item.getId()));
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
//                        ToastUtil.showShort(mDetailList.get(position).getDetailsUrl());
                       PreferenceManager.instance().saveDetail(mDetailList.get(position).getDetailsUrl());
                        mBaseActivity.showActivity(WebActivity.class,null);
//                        ShowFragmentUtils.showFragment(getActivity(), WebFragment.class,FragmentTag.WEBFRAGMENT,mArguments,true);
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
     * @param propertiesid 规格详情
     */
    private void getGoodSpecification(String goodsId, String propertiesid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("goodid", goodsId);
        hashMap.put("propertiesid", propertiesid);
        HttpHelper.getInstance().post(Url.getGoodSpecification, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getGoodSpecification" + response);
                GetGoodSpecification s = JsonUtils.parse(response, GetGoodSpecification.class);
                if (s.getCode() == 1) {
                    dialog_img.loadImage(s.getDatas().getImage(), R.mipmap.ic_launcher);
                    dialog_goods_name.setText(s.getDatas().getGoodName());
                    dialog_goods_price.setText(s.getDatas().getMemberPrice());
                    dialog_goods_nmb.setText(s.getDatas().getPropertiesName());
                    specification = String.valueOf(s.getDatas().getId());
                } else if (s.getCode()==0) {
//                    ToastUtil.showShort(s.getMsg());
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
    private void getSpecification(String goodsId) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("goodid", goodsId);
        HttpHelper.getInstance().post(Url.getSpecification, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getSpecification" + response);
                closeLoadingDialog();
                GetSpecification bean = JsonUtils.parse(response, GetSpecification.class);
                if (bean.getCode() == 1) {
                    if (mList != null) {
                        mList.clear();
                    }
                    if (!bean.getDatas().isEmpty() && bean.getDatas() != null) {
                        mList.addAll(bean.getDatas());
                    }
                    popBom(mList);
                    getGoodsValueAddedServices(mArguments.getString("goodsId"));
                } else if (bean.getCode() == 0) {
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
                LogUtils.d("getGoodsValueAddedServices" + response);
                GetGoodsValueAddedServices bean = JsonUtils.parse(response, GetGoodsValueAddedServices.class);
                if (bean.getCode() == 1) {
                    if (bean.getDatas() != null && !bean.getDatas().isEmpty()) {
                        service.setVisibility(View.VISIBLE);
                        mDetailList.addAll(bean.getDatas());
                        mDetailAdapter.update(mDetailList);
                    } else {
                        service.setVisibility(View.GONE);
                    }
                } else if (bean.getCode()==0) {
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
     * @param goodsId
     * @param count
     * @param specificationId
     * @param valueAddedServicesIds 添加购物车
     */
    private void addUserShoppingCart(String goodsId, String count, String specificationId, String valueAddedServicesIds) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("goodsId", goodsId);
        hashMap.put("count", count);
        hashMap.put("specificationId", specificationId);
        hashMap.put("valueAddedServicesIds", valueAddedServicesIds);
        HttpHelper.getInstance().post(Url.addUserShoppingCart, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("addUserShoppingCart" + response);
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode() == 1) {
                    ToastUtil.showShort("已添加到购物车");
                    mPopupWindow.dismissWindow();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
