package com.example.bbacr.ddw;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.bbacr.ddw.base.BaseActivity;
import com.example.bbacr.ddw.location.LocationTask;
import com.example.bbacr.ddw.location.OnLocationGetListener;
import com.example.bbacr.ddw.location.PositionEntity;
import com.example.bbacr.ddw.location.RegeocodeTask;
import com.example.bbacr.ddw.utils.LogUtils;

public class LocationActivity extends BaseActivity implements AMap.OnCameraChangeListener,
        AMap.OnMapLoadedListener, OnLocationGetListener{
    private AMap aMap;
    private MapView mapView;
    private MyLocationStyle myLocationStyle;
    private ImageView mBack;
    private EditText mEditText;
    private Marker mPositionMark;
    private LatLng mStartPosition;
    private LocationTask mLocationTask;
    private RegeocodeTask mRegeocodeTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView();
        mEditText = (EditText) findViewById(R.id.search);
        mBack = (ImageView) findViewById(R.id.back_img);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        mLocationTask = LocationTask.getInstance(getApplicationContext());
        mLocationTask.setOnLocationGetListener(this);
        mRegeocodeTask = new RegeocodeTask(getApplicationContext());
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
//            aMap = mapView.getMap();

        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_location;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
//    mBack.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        finish();
//    }
//    });
//        mEditText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public void initData() {

    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        // 如果要设置定位的默认状态，可以在此处进行设置

        myLocationStyle = new MyLocationStyle();
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.setOnMapLoadedListener(this);
        aMap.setOnCameraChangeListener(this);
//        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW));
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        mLocationTask.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        mStartPosition = cameraPosition.target;
        mRegeocodeTask.setOnLocationGetListener(this);
        mRegeocodeTask
                .search(mStartPosition.latitude, mStartPosition.longitude);
//        if (mIsFirst) {
//            Utils.addEmulateData(mAmap, mStartPosition);
//            if (mPositionMark != null) {
//                mPositionMark.setToTop();
//            }
//            mIsFirst = false;
//        }
    }

    @Override
    public void onMapLoaded() {

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.setFlat(true);
        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.position(new LatLng(0, 0));
        markerOptions
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(),
                                R.drawable.ic_loading_rotate)));
        mPositionMark = aMap.addMarker(markerOptions);

        mPositionMark.setPositionByPixels(mapView.getWidth() / 2,
                mapView.getHeight() / 2);
        mLocationTask.startSingleLocate();
    }

    @Override
    public void onLocationGet(PositionEntity entity) {
//        mAddressTextView.setText(entity.address);
        mStartPosition = new LatLng(entity.latitue, entity.longitude);
        CameraUpdate cameraUpate = CameraUpdateFactory.newLatLngZoom(
                mStartPosition, aMap.getCameraPosition().zoom);
        aMap.animateCamera(cameraUpate);
    }

    @Override
    public void onRegecodeGet(PositionEntity entity) {
//        mAddressTextView.setText(entity.address);
        entity.latitue = mStartPosition.latitude;
        entity.longitude = mStartPosition.longitude;
    }
}
