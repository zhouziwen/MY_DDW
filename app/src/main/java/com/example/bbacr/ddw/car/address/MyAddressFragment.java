package com.example.bbacr.ddw.car.address;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.JsonAddress;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.IEvent;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBase;
import com.example.bbacr.ddw.eventbus.EventBean;
import com.example.bbacr.ddw.eventbus.EventLocation;
import com.example.bbacr.ddw.location.LocationFragment;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.CommonUtils;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.widget.CityPickerDialog;
import com.example.bbacr.ddw.widget.ExecutorFactory;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 添加我的收货地址
 */
public class MyAddressFragment extends BaseFragment {
    @Bind(R.id.et_shouhuo_name)
    EditText mEtShouhuoName;
    @Bind(R.id.et_phone_address)
    EditText mEtPhoneAddress;
    @Bind(R.id.et_address_place)
    TextView mEtAddressPlace;
    @Bind(R.id.ll_select_place)
    LinearLayout mLlSelectPlace;
    @Bind(R.id.et_detail_address)
    EditText mEtDetailAddress;
    @Bind(R.id.iv_select_nomal_address)
    CheckBox mIvSelectNomalAddress;
    @Bind(R.id.address)
    TextView mAddress;
    @Bind(R.id.ll_select_editor_address)
    LinearLayout mLlSelectEditorAddress;
    private TextView mBack, mKeep;
    private int ifDefault;
    private String mLocation = "";
    private List<JsonAddress> mAddressList = new ArrayList<>();//地址数据集合

    public MyAddressFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_address, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mKeep = mFindViewUtils.findViewById(R.id.app_title_action);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mBack.setOnClickListener(this);
        mKeep.setOnClickListener(this);
        mLlSelectPlace.setOnClickListener(this);
        mAddress.setOnClickListener(this);
    }

    @Override
    protected void setData() {
        super.setData();
        if (mIvSelectNomalAddress.isChecked()) {
            ifDefault = 1;
        } else {
            ifDefault = 0;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_title_back:
                popSelf();
                break;
            case R.id.app_title_action:
                addUserAddress(mEtAddressPlace.getText().toString(), mLocation, String.valueOf(ifDefault), mEtShouhuoName.getText().toString(), mEtPhoneAddress.getText().toString(), mEtDetailAddress.getText().toString());
                break;
            case R.id.ll_select_place:
                if (mAddressList.size() > 0) {
                    addressPicker();
                } else {
                    addressDatas();
                }
                break;
            case R.id.address:
                ShowFragmentUtils.showFragment(getActivity(), LocationFragment.class, FragmentTag.LOCATIONFRAGMENT, null, true);
                break;
        }
    }

    @Subscribe
    public void location(EventLocation bean) {
        /*刷新数据和ui*/
        LogUtils.d("ddddddddd");
        mLocation = bean.getLocation();
        mEtDetailAddress.setText(bean.getDetail());
    }

    /**
     * 选择地址
     */
    private void addressPicker() {
        new CityPickerDialog(getActivity(), mAddressList, null, null, null,
                new CityPickerDialog.onCityPickedListener() {
                    @Override
                    public void onPicked(JsonAddress selectProvince,
                                         JsonAddress.CityBean selectCity, JsonAddress.CityBean.CountyBean selectCounty) {
                        StringBuilder address = new StringBuilder();
                        address.append(
                                selectProvince != null ? selectProvince
                                        .getName() : "")
                                .append(" ").append(selectCity != null ? selectCity
                                .getName() : "")
                                .append(" ").append(selectCounty != null ? selectCounty
                                .getName() : "");
                        String text = selectCounty != null ? selectCounty
                                .getName() : "";
                        if (selectCounty != null) {
//                            mRegionId = selectCounty.getId();
                        } else {
//                            mRegionId = selectCity.getId();
                        }
                        mEtAddressPlace.setText(address);
//                        mTvAddressPicker.setText(address);
                    }
                }).show();
    }

    /**
     * 添加address数据
     */
    private void addressDatas() {
        Log.d("wyt", "start add address data");
        ExecutorFactory.instance().getExecutorService().submit(new Runnable() {
            @Override
            public void run() {
                String address = CommonUtils.getFromAssets("address.txt", getActivity());
                List<JsonAddress> jsonAddresses = JsonUtils.parseArray(address, new TypeToken<List<JsonAddress>>() {
                });
                if (jsonAddresses != null) {
//                    provinces = province;
                    mAddressList = jsonAddresses;
                }
                if (mAddressList.size() > 0) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            addressPicker();
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "数据初始化失败！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    /**
     * @param city
     * @param location
     * @param ifDefault
     * @param name
     * @param phone
     * @param detail    添加收货地址
     */
    private void addUserAddress(String city, String location, String ifDefault, final String name, String phone, String detail) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("city", city);
        hashMap.put("location", location);
        hashMap.put("ifDefault", ifDefault);
        hashMap.put("name", name);
        hashMap.put("phone", phone);
        hashMap.put("detail", detail);
        HttpHelper.getInstance().post(Url.addUserAddress, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("addUserAddress" + response);
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode() == 1) {
//                    EventBus.getDefault().post(new EventBean(IEvent.getUserAddress));
                    EventBus.getDefault().post(new EventBase());
                    popSelf();
                } else if (bean.getCode() == -1) {
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
