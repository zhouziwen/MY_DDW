package com.example.bbacr.ddw.mine;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.listview.CommonAdapter;
import com.example.bbacr.ddw.adapter.listview.ViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.my.GetCardList;
import com.example.bbacr.ddw.bean.my.GetTiXianFactorage;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBase;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.DisPlayUtils;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.MD5;
import com.example.bbacr.ddw.utils.PackageUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.widget.CommonDialog;
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
 */
public class CreditsExchangeFragment extends BaseFragment {

    private TextView mBack,mAction;
    @Bind(R.id.pic_yhk)
    GlideImageView mPicYhk;
    @Bind(R.id.txt_yhk)
    TextView mTxtYhk;
    @Bind(R.id.my_num)
    TextView mMyNum;
    @Bind(R.id.pic_sp)
    ImageView mPicSp;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.Relative1)
    RelativeLayout mRelative1;
    @Bind(R.id.txt)
    TextView mTxt;
    @Bind(R.id.pic)
    ImageView mPic;
    @Bind(R.id.money)
    EditText mMoney;
    @Bind(R.id.view)
    View mView;
    @Bind(R.id.txt_fw)
    TextView mTxtFw;
    @Bind(R.id.num)
    TextView mNum;
    @Bind(R.id.Relative)
    RelativeLayout mRelative;
    @Bind(R.id.text_pwd)
    EditText mTextPwd;
    @Bind(R.id.registerInBtn)
    Button mRegisterInBtn;
    @Bind(R.id.list_view)
    MyListView mListView;
    private List<String> mList = new ArrayList<>();
    private CommonAdapter<String> mAdapter;
    private String mFee;
    private String Num,mAddress,Money;
    private int mBankId,mId;
    public CreditsExchangeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_credits_exchange, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mAction = mFindViewUtils.findViewById(R.id.app_title_action);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mAdapter = new CommonAdapter<String>(getContext(),null,R.layout.search_item) {
            @Override
            public void setViewData(ViewHolder holder, String item, int position) {
                holder.setText(item,R.id.search_txt);
            }

            @Override
            public void onClickBack(View v, int position, ViewHolder holder) {

            }

            @Override
            public void onBack(String name) {

            }
        };
        mListView.setAdapter(mAdapter);
    }
    @Override
    protected void setListener() {
        super.setListener();
        mRelative1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFragmentUtils.showFragment(getActivity(), SelectCardFragment.class, FragmentTag.SELECTCARDFRAGMENT, null, true);
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSelf();
            }
        });
        mAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFragmentUtils.showFragment(getActivity(),ConvertFragment.class, FragmentTag.CONVERTFRAGMENT,null,true);
            }
        });
        mMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()>0) {
                    Double a = Double.valueOf(mMoney.getText().toString().trim());
                    if (a>Double.valueOf(Money)) {
                        mTxtFw.setText("兑换金额不能超过兑换积分");
                        mNum.setVisibility(View.GONE);
                    } else if (a % 100 == 0) {
                        mTxtFw.setText("服务费");
                        mNum.setVisibility(View.VISIBLE);
                        mNum.setText(a*Double.valueOf(mFee) + "");
                    } else {
                        mTxtFw.setText("兑换金额只能为整百");
                        mNum.setVisibility(View.GONE);
                    }
                }else {
                    mTxtFw.setText("服务费");
                    mNum.setVisibility(View.VISIBLE);
                    mNum.setText("0.00");
                }
            }
        });
        mRegisterInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCard();
            }
        });
    }
    @Override
    protected void setData() {
        super.setData();
        getTiXianFactorage();
        getCardList();
    }
    /**
     * 提现详情
     */
    private void getTiXianFactorage(){
      startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        HttpHelper.getInstance().post(Url.getTiXianFactorage, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getTiXianFactorage"+response);
               closeLoadingDialog();
                final GetTiXianFactorage bean = JsonUtils.parse(response, GetTiXianFactorage.class);
                if (bean.getCode()==1) {
                    mTxt.setText("兑换金额（收取"+Double.valueOf(bean.getServiceCharge())*100+"%服务费)");
                    mFee = bean.getServiceCharge();
                    mList.addAll(bean.getRemark());
                    mAdapter.update(mList);
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
               closeLoadingDialog();
            }
        });
    }
    /**
     * 获取银行卡
     */
    private void getCardList(){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("pageNu", "1");
        HttpHelper.getInstance().post(Url.getCardList, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getCardList"+response);
                closeLoadingDialog();
                final GetCardList bean = JsonUtils.parse(response, GetCardList.class);
               mNum.setText(bean.getDatas().getAccountMoney());
                Money = bean.getDatas().getAccountMoney();
                if (bean.getCode() == 1) {
                    LogUtils.d("aaaaaaaaaa"+bean.getDatas().getRecordsTotal());
                    if (bean.getDatas().getRecordsTotal() == 0) {
                                        new CommonDialog.Builder(getActivity()).setTitle("提示")
                        .setCanceledOnTouchOutside(false)
                        .setMessage("你还没有绑定银行卡，先去设置一下吧？", R.color.line_color)
                        .setPositiveButton("去设置", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                        requestDeleteCart(item.getSkuId(),item);
                            }
                        }, R.color.red).setNegativeButton("取消", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                popSelf();
                                            }
                                        }).show();
                    } else if (bean.getDatas().getRecordsTotal()>0) {
                        for (int i = 0; i <bean.getDatas().getCardList().size() ; i++) {
                            if (bean.getDatas().getCardList().get(i).isIsDefault()) {
                                mPicYhk.loadCircleImage(bean.getDatas().getCardList().get(i).getBankImg(), R.mipmap.ic_launcher);
                                mTxtYhk.setText(bean.getDatas().getCardList().get(i).getBank());
                                mMyNum.setText(bean.getDatas().getCardList().get(i).getCardNumShort());
                                mName.setText(bean.getDatas().getCardList().get(i).getAccountName());
                                Num = bean.getDatas().getCardList().get(i).getCardNum();
                                mBankId = bean.getDatas().getCardList().get(i).getBankId();
                                mId = bean.getDatas().getCardList().get(i).getId();
                                mAddress = bean.getDatas().getCardList().get(i).getKaihuhang();
                            }
                        }
                    }
                } else if (bean.getCode() == 0) {
                    ToastUtil.showShort(bean.getMsg());
                } else {
                    ToastUtil.showShort(bean.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class,null);
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                closeLoadingDialog();
            }
        });
    }
    private void checkCard(){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("pageNu", "1");
        HttpHelper.getInstance().post(Url.getCardList, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getCardList"+response);
                closeLoadingDialog();
                final GetCardList bean = JsonUtils.parse(response, GetCardList.class);
                if (bean.getCode() == 1) {
                    LogUtils.d("aaaaaaaaaa"+bean.getDatas().getRecordsTotal());
                    if (bean.getDatas().getRecordsTotal() == 0) {
                        new CommonDialog.Builder(getActivity()).setTitle("提示")
                                .setCanceledOnTouchOutside(false)
                                .setMessage("你还没有绑定银行卡，先去设置一下吧？", R.color.line_color)
                                .setPositiveButton("去设置", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ShowFragmentUtils.showFragment(getActivity(),BindCardFragment.class, FragmentTag.BINDCARDFRAGMENT,null,true);
                                    }
                                }, R.color.red).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popSelf();
                            }
                        }).show();
                    } else if (bean.getDatas().getRecordsTotal()>0) {
                        addTiXianShenQing(mMoney.getText().toString().trim(),
                                MD5.getMD5(new StringBuffer(mTextPwd.getText().toString().trim()).append(PreferenceManager.instance().getKey()).toString()),
                                mName.getText().toString().trim(),
                                String.valueOf(mBankId),
                                Num,mNum.getText().toString().trim(),
                                mAddress,String.valueOf(mId));
                    }
                } else if (bean.getCode() == 0) {
                    ToastUtil.showShort(bean.getMsg());
                } else {
                    ToastUtil.showShort(bean.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class,null);
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                closeLoadingDialog();
            }
        });
    }

    private void addTiXianShenQing(String money, String pwd, String name, final String bankid, String card,String free,String address,String id){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("money", money);
        hashMap.put("password",pwd);
        hashMap.put("accountName",name);
        hashMap.put("bankId",bankid);
        hashMap.put("cardNum",card);
        hashMap.put("factorage",free);
        hashMap.put("kaihuhang",address);
        hashMap.put("cardId",id);
        HttpHelper.getInstance().post(Url.addTiXianShenQing, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("addTiXianShenQing"+response);
                final BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode()==1) {
                    Toast.makeText(getContext(),"兑换成功", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(new EventBase());

                } else if (bean.getCode() == 0) {
                    Toast.makeText(getContext(), bean.getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), bean.getMsg(), Toast.LENGTH_SHORT).show();
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class,null);
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                Toast.makeText(getContext(),error_msg, Toast.LENGTH_SHORT).show();
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
