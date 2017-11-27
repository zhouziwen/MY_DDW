package com.example.bbacr.ddw.mine;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.MyCollectionsNum;
import com.example.bbacr.ddw.bean.my.GetUserHeadData;
import com.example.bbacr.ddw.bean.my.PurchaseOrderNum;
import com.example.bbacr.ddw.car.address.ManageAddressFragment;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.IEvent;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBean;
import com.example.bbacr.ddw.home.InviteCodeFragmentFragment;
import com.example.bbacr.ddw.home.news.MyNewsFragment;
import com.example.bbacr.ddw.home.shopdetail.MyShoppingDetailFragment;
import com.example.bbacr.ddw.mine.coupon.MyCouponFragment;
import com.example.bbacr.ddw.mine.coupon.SelectCouponFragment;
import com.example.bbacr.ddw.mine.myorder.OrderFragment;
import com.example.bbacr.ddw.mine.set.AboutUsFragment;
import com.example.bbacr.ddw.mine.set.BasicsFragment;
import com.example.bbacr.ddw.mine.shareteam.ShareFragment;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.sunfusheng.glideimageview.GlideImageView;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 我的界面
 */
public class MineFragment extends BaseFragment {
    @Bind(R.id.set)
    ImageView mSet;
    @Bind(R.id.news_num)
    ImageView mNewsNum;
    @Bind(R.id.news)
    ImageView mNews;
    @Bind(R.id.iv_photo_person_ziliao)
    GlideImageView mIvPhotoPersonZiliao;
    @Bind(R.id.user_name)
    TextView mUserName;
    @Bind(R.id.please_login)
    RelativeLayout mPleaseLogin;
    @Bind(R.id.my_money)
    TextView mMyMoney;
    @Bind(R.id.rl_1)
    RelativeLayout mRl1;
    @Bind(R.id.my_collect)
    TextView mMyCollect;
    @Bind(R.id.rl_2)
    RelativeLayout mRl2;
    @Bind(R.id.my_footprint)
    TextView mMyFootprint;
    @Bind(R.id.rl_3)
    RelativeLayout mRl3;
    @Bind(R.id.my_order)
    RelativeLayout mMyOrder;
    @Bind(R.id.dfk)
    TextView mDfk;
    @Bind(R.id.num_1)
    TextView mNum1;
    @Bind(R.id.dfh)
    TextView mDfh;
    @Bind(R.id.num_2)
    TextView mNum2;
    @Bind(R.id.dsh)
    TextView mDsh;
    @Bind(R.id.num_3)
    TextView mNum3;
    @Bind(R.id.dpj)
    TextView mDpj;
    @Bind(R.id.num_4)
    TextView mNum4;
    @Bind(R.id.necessary)
    RelativeLayout mNecessary;
    @Bind(R.id.tgm)
    TextView mTgm;
    @Bind(R.id.pot_1)
    TextView mPot1;
    @Bind(R.id.yhq)
    TextView mYhq;
    @Bind(R.id.pot_2)
    TextView mPot2;
    @Bind(R.id.share)
    TextView mShare;
    @Bind(R.id.pot_3)
    TextView mPot3;
    @Bind(R.id.my_phone)
    RelativeLayout mMyPhone;
    @Bind(R.id.my_maintain)
    RelativeLayout mMyMaintain;
    @Bind(R.id.my_address)
    RelativeLayout mMyAddress;
    @Bind(R.id.touch)
    RelativeLayout mTouch;
    @Bind(R.id.about)
    RelativeLayout mAbout;
    @Bind(R.id.num_news)
    TextView mNumNews;
    final public static int REQUEST_CODE_ASK_CALL_PHONE=123;
    //    private TakePhoto mTakePhoto;
//    private InvokeParam invokeParam;
    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Subscribe
    public void getCardList(EventBean card) {
        if (card.getMsg().equals(IEvent.getUserHeadData)) {
            getUserHeadData();
        }
    }
    @Override
    public void onClick(View v) {
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @OnClick({R.id.set, R.id.news, R.id.iv_photo_person_ziliao, R.id.please_login, R.id.my_money, R.id.rl_1, R.id.rl_2, R.id.rl_3, R.id.my_order, R.id.dfk, R.id.dfh, R.id.dsh, R.id.dpj, R.id.necessary, R.id.tgm, R.id.yhq, R.id.share, R.id.my_phone, R.id.my_maintain, R.id.my_address, R.id.touch, R.id.about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set:/*设置*/
                ShowFragmentUtils.showFragment(getActivity(), SetFragment.class, FragmentTag.SETFRAGMENT, null, true);
                break;
            case R.id.news:/*我的消息*/
                ShowFragmentUtils.showFragment(getActivity(), MyNewsFragment.class, FragmentTag.MYNEWSFRAGMENT, null, true);
                break;
            case R.id.iv_photo_person_ziliao:/*个人图像*/
                     ShowFragmentUtils.showFragment(getActivity(), BasicsFragment.class, FragmentTag.BASICSFRAGMENT, null, true);
                break;
            case R.id.please_login:/*请登录*/
                ShowFragmentUtils.showFragment(getActivity(), SetFragment.class, FragmentTag.SETFRAGMENT, null, true);
                break;
//            case R.id.my_money:
////                ShowFragmentUtils.showFragment(getActivity(), ShoppingDetailFragment.class, FragmentTag.SHOPPINGDETAILFRAGMENT,null,true);
//                break;
            case R.id.rl_1:  /*账户余额*/
//                ShowFragmentUtils.showFragment(getActivity(), MyShoppingDetailFragment.class, FragmentTag.MYSHOPPINGDETAILFRAGMENT, null, true);
                break;
            case R.id.rl_2:/*我的收藏*/
                ShowFragmentUtils.showFragment(getActivity(), MyFavoriteFragment.class, FragmentTag.MYFAVORITEFRAGMENT, null, true);
                break;
            case R.id.rl_3:/*我的足迹*/
                ShowFragmentUtils.showFragment(getActivity(), FootPrintFragment.class, FragmentTag.FOOTPRINTFRAGMENT, null, true);
                break;
            case R.id.my_order:/*我的订单*/
//                ShowFragmentUtils.showFragment(getActivity(), ShoppingDetailFragment.class, FragmentTag.SHOPPINGDETAILFRAGMENT,null,true);
                mArguments.putInt("tabId", 0);
                ShowFragmentUtils.showFragment(getActivity(), OrderFragment.class, FragmentTag.ORDERFRAGMENT, mArguments, true);
                break;
            case R.id.dfk:/*代付款*/
//                ShowFragmentUtils.showFragment(getActivity(), ShoppingDetailFragment.class, FragmentTag.SHOPPINGDETAILFRAGMENT,null,true);
                mArguments.putInt("tabId", 1);
                ShowFragmentUtils.showFragment(getActivity(), OrderFragment.class, FragmentTag.ORDERFRAGMENT, mArguments, true);
                break;
            case R.id.dfh:/*待发货*/
//                ShowFragmentUtils.showFragment(getActivity(), ShoppingDetailFragment.class, FragmentTag.SHOPPINGDETAILFRAGMENT,null,true);
                mArguments.putInt("tabId", 2);
                ShowFragmentUtils.showFragment(getActivity(), OrderFragment.class, FragmentTag.ORDERFRAGMENT, mArguments, true);
                break;
            case R.id.dsh:/*待收货*/
//                ShowFragmentUtils.showFragment(getActivity(), ShoppingDetailFragment.class, FragmentTag.SHOPPINGDETAILFRAGMENT,null,true);
                mArguments.putInt("tabId", 3);
                ShowFragmentUtils.showFragment(getActivity(), OrderFragment.class, FragmentTag.ORDERFRAGMENT, mArguments, true);
                break;
            case R.id.dpj:/*待评价*/
//                ShowFragmentUtils.showFragment(getActivity(), ShoppingDetailFragment.class, FragmentTag.SHOPPINGDETAILFRAGMENT,null,true);
                mArguments.putInt("tabId", 4);
                ShowFragmentUtils.showFragment(getActivity(), OrderFragment.class, FragmentTag.ORDERFRAGMENT, mArguments, true);
                break;
            case R.id.necessary:/*必备功能*/
                ShowFragmentUtils.showFragment(getActivity(), MyShoppingDetailFragment.class, FragmentTag.MYSHOPPINGDETAILFRAGMENT, null, true);
                break;
            case R.id.tgm:/*推广码*/
                ShowFragmentUtils.showFragment(getActivity(), InviteCodeFragmentFragment.class, FragmentTag.INVITECODEFRAGMENTFRAGMENT, null, true);
                break;
            case R.id.yhq:/*优惠券*/
                ShowFragmentUtils.showFragment(getActivity(), SelectCouponFragment.class, FragmentTag.SELECTCOUPONFRAGMENT, mArguments, true);
                break;
            case R.id.share:/*分享经济*/
                ShowFragmentUtils.showFragment(getActivity(), ShareFragment.class, FragmentTag.SHAREFRAGMENT, null, true);
                break;
            case R.id.my_phone:/*我的手机*/
            ShowFragmentUtils.showFragment(getActivity(),MyPhoneFragment.class,FragmentTag.MYPHONEFRAGMENT,null,true);
                break;
            case R.id.my_maintain:/*余额兑换*/
                ShowFragmentUtils.showFragment(getActivity(), CreditsExchangeFragment.class, FragmentTag.CREDITSEXCHANGEFRAGMENT, null, true);
                break;
            case R.id.my_address:/*我的信息地址*/
                ShowFragmentUtils.showFragment(getActivity(), SetFragment.class, FragmentTag.SETFRAGMENT, null, true);
                break;
            case R.id.touch:/*联系我们*/

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
            case R.id.about:/*资金流水*/
                ShowFragmentUtils.showFragment(getActivity(), FundFragment.class, FragmentTag.FUNDFRAGMENT, null, true);
                break;
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
    @Override
    protected void setData() {
        super.setData();
        getUserHeadData();
        purchaseOrderNum();
    }
    /**
     * 数量
     */
    private void getUserHeadData() {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        HttpHelper.getInstance().post(Url.getUserHeadData, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                closeLoadingDialog();
                LogUtils.d("getUserHeadData="+response);
                GetUserHeadData num = JsonUtils.parse(response, GetUserHeadData.class);
                if (num.getCode() == 1) {
                    mMyCollect.setText(num.getDatas().getCollectionNum());
                    mMyFootprint.setText(num.getDatas().getFootMarkNum());
                    mMyMoney.setText(num.getDatas().getAccountMoney());
                    mIvPhotoPersonZiliao.loadCircleImage(num.getDatas().getAvotorr(), R.mipmap.ic_launcher);
                    mUserName.setText(num.getDatas().getRealName());
                    if (num.getDatas().getCount() > 0) {
                        mNumNews.setBackgroundResource(R.drawable.bg_corner_red_5);
                        mNumNews.setText(num.getDatas().getCount()+"");
                    }
                }else if (num.getCode() == -1) {
                    ToastUtil.showShort(num.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class,null);
                } else {
                    ToastUtil.showShort(num.getMsg());
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
     * 订单数量
     */
    private void purchaseOrderNum() {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        HttpHelper.getInstance().post(Url.purchaseOrderNum, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                closeLoadingDialog();
                LogUtils.d("purchaseOrderNum="+response);
                PurchaseOrderNum num = JsonUtils.parse(response, PurchaseOrderNum.class);
                if (num.getCode() == 1) {
                    if (num.getCreateNum()>0) {
                        mNum1.setBackgroundResource(R.drawable.bg_corner_red_5);
                        mNum1.setText(num.getCreateNum()+"");
                    }
                    if (num.getPaymentNum()>0) {
                        mNum2.setText(num.getPaymentNum()+"");
                        mNum2.setBackgroundResource(R.drawable.bg_corner_red_5);
                    }
                    if (num.getDeliveriedNum()>0) {
                        mNum3.setText(num.getDeliveriedNum()+"");
                        mNum3.setBackgroundResource(R.drawable.bg_corner_red_5);
                    }
                    if (num.getCommentNum()>0) {
                        mNum4.setText(num.getCommentNum()+"");
                        mNum4.setBackgroundResource(R.drawable.bg_corner_red_5);
                    }
                } else if (num.getCode() == -1) {

                } else {
                    ToastUtil.showShort(num.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                closeLoadingDialog();
                ToastUtil.showShort(error_msg);
            }
        });
    }
}
