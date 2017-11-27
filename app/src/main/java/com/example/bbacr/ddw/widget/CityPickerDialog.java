package com.example.bbacr.ddw.widget;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.city.AbstractWheelTextAdapter;
import com.example.bbacr.ddw.adapter.city.OnWheelChangedListener;
import com.example.bbacr.ddw.adapter.city.OnWheelClickedListener;
import com.example.bbacr.ddw.bean.JsonAddress;
import com.example.bbacr.ddw.customview.WheelView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class CityPickerDialog extends Dialog {
    private final static int DEFAULT_ITEMS = 5;
    private final static int UPDATE_CITY_WHEEL = 11;
    private final static int UPDATE_COUNTY_WHEEL = 12;

    private Activity mContext;

    private ArrayList<JsonAddress> mProvinces = new ArrayList<>();
    private ArrayList<JsonAddress.CityBean> mCities = new ArrayList<>();
    private ArrayList<JsonAddress.CityBean.CountyBean> mCounties = new ArrayList<>();
    AbstractWheelTextAdapter provinceAdapter;
    AbstractWheelTextAdapter cityAdapter;
    AbstractWheelTextAdapter countyAdapter;
    WheelView provinceWheel;
    WheelView citiesWheel;
    WheelView countiesWheel;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (!isShowing()) {
                return;
            }
            switch (msg.what) {
                case UPDATE_CITY_WHEEL:
                    List<JsonAddress.CityBean> cityBeanList = mProvinces.get(msg.arg1).getCity();
                    if (cityBeanList == null) {
//                        Log.d(IConstant.TAG, "UPDATE_CITY_WHEEL:cityBeanList is null!");
                        mCities = new ArrayList<>();
                    } else {
                        mCities.clear();
                        mCities.addAll(cityBeanList);
                    }
                    citiesWheel.invalidateWheel(true);
                    citiesWheel.setCurrentItem(0, false);

                    List<JsonAddress.CityBean.CountyBean> countyBeenList = mCities.get(0).getCounty();
                    if (countyBeenList == null) {
//                        Log.d(IConstant.TAG, "UPDATE_CITY_WHEEL:countyBeanList is null!");
                        mCounties = new ArrayList<>();
                    } else {
                        mCounties.clear();
                        mCounties.addAll(mCities.get(0).getCounty());
                    }
                    countiesWheel.invalidateWheel(true);
                    countiesWheel.setCurrentItem(0, false);
                    break;
                case UPDATE_COUNTY_WHEEL:
                    List<JsonAddress.CityBean.CountyBean> counties = mCities.get(msg.arg1).getCounty();
                    if (counties == null) {
//                        Log.d(IConstant.TAG, "UPDATE_COUNTY_WHEEL:counties is null");
                        mCounties = new ArrayList<>();
                    } else {
                        mCounties.clear();
                        mCounties.addAll(counties);
                    }
                    countiesWheel.invalidateWheel(true);
                    countiesWheel.setCurrentItem(0, false);
                    break;
                default:
                    break;
            }
        }
    };

    public interface onCityPickedListener {
        void onPicked(JsonAddress selectProvince, JsonAddress.CityBean selectCity,
                      JsonAddress.CityBean.CountyBean selectCounty);
    }

    public CityPickerDialog(Activity context, List<JsonAddress> jsonAddresses,
                            JsonAddress defaultProvince, JsonAddress.CityBean defaultCity, JsonAddress.CityBean.CountyBean defaultCounty,
                            final onCityPickedListener listener) {
        super(context);
        mContext = context;
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#00000000")));
        getWindow().setWindowAnimations(R.style.bottom);
        View rootView = getLayoutInflater().inflate(
                R.layout.dialog_city_picker, null);
        int screenWidth = mContext.getWindowManager().getDefaultDisplay()
                .getWidth();
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(screenWidth,
                ViewGroup.LayoutParams.MATCH_PARENT);
        super.setContentView(rootView, params);

        mProvinces.addAll(jsonAddresses);

        initViews();
        setDefaultArea(defaultProvince, defaultCity, defaultCounty);

        View done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    JsonAddress province = mProvinces.size() > 0 ? mProvinces
                            .get(provinceWheel.getCurrentItem()) : null;
                    JsonAddress.CityBean city = mCities.size() > 0 ? mCities.get(citiesWheel
                            .getCurrentItem()) : null;
                    JsonAddress.CityBean.CountyBean county = mCounties.size() > 0 ? mCounties
                            .get(countiesWheel.getCurrentItem()) : null;
                    listener.onPicked(province, city, county);
                }
                dismiss();
            }
        });

        View cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    private void setDefaultArea(JsonAddress defaultProvince, JsonAddress.CityBean defaultCity,
                                JsonAddress.CityBean.CountyBean defaultCounty) {

        int provinceItem = 0;
        int cityItem = 0;
        int countyItem = 0;

        if (defaultProvince == null) {
            defaultProvince = mProvinces.get(0);
            provinceItem = 0;
        } else {
            for (int i = 0; i < mProvinces.size(); i++) {
                if (mProvinces.get(i).getId()
                        .equals(defaultProvince.getId())) {
                    provinceItem = i;
                    break;
                }
            }
        }
        mCities.clear();
        mCities.addAll(defaultProvince.getCity());
        if (mCities.size() == 0) {
            mCities.add(new JsonAddress.CityBean());
            cityItem = 0;
        } else if (defaultCity == null) {
            defaultCity = mCities.get(0);
            cityItem = 0;
        } else {
            for (int i = 0; i < mCities.size(); i++) {
                if (mCities.get(i).getId().equals(defaultCity.getId())) {
                    cityItem = i;
                    break;
                }
            }
        }

        mCounties.clear();
        mCounties.addAll(defaultCity.getCounty());
        if (mCounties.size() == 0) {
            mCounties.add(new JsonAddress.CityBean.CountyBean());
            countyItem = 0;
        } else if (defaultCounty == null) {
            defaultCounty = mCounties.get(0);
            countyItem = 0;
        } else {
            for (int i = 0; i < mCounties.size(); i++) {
                if (mCounties.get(i).getId()
                        .equals(defaultCounty.getId())) {
                    countyItem = i;
                    break;
                }
            }
        }
        provinceWheel.setCurrentItem(provinceItem, false);
        citiesWheel.setCurrentItem(cityItem, false);
        countiesWheel.setCurrentItem(countyItem, false);

    }

    private void initViews() {

        provinceWheel = (WheelView) findViewById(R.id.provinceWheel);
        citiesWheel = (WheelView) findViewById(R.id.citiesWheel);
        countiesWheel = (WheelView) findViewById(R.id.countiesWheel);


        provinceAdapter = new AbstractWheelTextAdapter(mContext,
                R.layout.wheel_text) {

            @Override
            public int getItemsCount() {

                return mProvinces.size();
            }

            @Override
            protected CharSequence getItemText(int index) {

                return mProvinces.get(index).getName();
            }
        };

        cityAdapter = new AbstractWheelTextAdapter(mContext,
                R.layout.wheel_text) {

            @Override
            public int getItemsCount() {

                return mCities.size();
            }

            @Override
            protected CharSequence getItemText(int index) {

                return mCities.get(index).getName();
            }
        };

        countyAdapter = new AbstractWheelTextAdapter(mContext,
                R.layout.wheel_text) {

            @Override
            public int getItemsCount() {

                return mCounties.size();
            }

            @Override
            protected CharSequence getItemText(int index) {
                return mCounties.get(index).getName();
            }
        };

        provinceWheel.setViewAdapter(provinceAdapter);
        provinceWheel.setCyclic(false);
        provinceWheel.setVisibleItems(DEFAULT_ITEMS);

        citiesWheel.setViewAdapter(cityAdapter);
        citiesWheel.setCyclic(false);
        citiesWheel.setVisibleItems(DEFAULT_ITEMS);

        countiesWheel.setViewAdapter(countyAdapter);
        countiesWheel.setCyclic(false);
        countiesWheel.setVisibleItems(DEFAULT_ITEMS);

        OnWheelClickedListener clickListener = new OnWheelClickedListener() {

            @Override
            public void onItemClicked(WheelView wheel, int itemIndex) {
                if (itemIndex != wheel.getCurrentItem()) {
                    wheel.setCurrentItem(itemIndex, true, 500);
                }
            }
        };

        provinceWheel.addClickingListener(clickListener);
        citiesWheel.addClickingListener(clickListener);
        countiesWheel.addClickingListener(clickListener);

        provinceWheel.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                mHandler.removeMessages(UPDATE_CITY_WHEEL);
                Message msg = Message.obtain();
                msg.what = UPDATE_CITY_WHEEL;
                msg.arg1 = newValue;
                mHandler.sendMessageDelayed(msg, 50);
            }
        });

        citiesWheel.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                mHandler.removeMessages(UPDATE_COUNTY_WHEEL);
                Message msg = Message.obtain();
                msg.what = UPDATE_COUNTY_WHEEL;
                msg.arg1 = newValue;
                mHandler.sendMessageDelayed(msg, 50);

            }
        });

    }
}
