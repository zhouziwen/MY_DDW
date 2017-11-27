package com.example.bbacr.ddw.location;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.listview.RecomandAdapter;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.eventbus.EventLocation;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends BaseFragment implements AMap.OnCameraChangeListener,
        AMap.OnMapLoadedListener, OnLocationGetListener,AdapterView.OnItemClickListener,RouteTask.OnRouteCalculateListener{
    private View mapLayout;
    private MapView mapView;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    private LocationTask mLocationTask;
    private LatLng mStartPosition;
    private Marker mPositionMark;
    private RegeocodeTask mRegeocodeTask;
    private ListView mListView;
    private ListView mListView2;
    private RecomandAdapter mRecomandAdapter;
    private RouteTask mRouteTask;
    private TextView mLocation;
    private EditText mSearch;
    private String location,city, detail;
    public LocationFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mapLayout == null) {
            Log.i("sys", "MF onCreateView() null");
            mapLayout = inflater.inflate(R.layout.activity_location, null);
            mapView = (MapView) mapLayout.findViewById(R.id.map);
            mapView.onCreate(savedInstanceState);
            mLocationTask = LocationTask.getInstance(getContext());
            mLocationTask.setOnLocationGetListener(this);
            mRegeocodeTask = new RegeocodeTask(getContext());
            RouteTask.getInstance(getContext())
                    .addRouteCalculateListener(this);
            if (aMap == null) {
                aMap = mapView.getMap();
                setUpMap();
            }
        }else {
            if (mapLayout.getParent() != null) {
                ((ViewGroup) mapLayout.getParent()).removeView(mapLayout);
            }
        }
        return mapLayout;
    }
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
    @Override
    public void onClick(View v) {
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mListView2 = mFindViewUtils.findViewById(R.id.list_view2);
        mLocation = mFindViewUtils.findViewById(R.id.location);
        mListView = mFindViewUtils.findViewById(R.id.list_view);
        mSearch = mFindViewUtils.findViewById(R.id.search);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mRecomandAdapter=new RecomandAdapter(getContext());
        mListView.setAdapter(mRecomandAdapter);
        mListView.setOnItemClickListener(this);

        mListView2.setAdapter(mRecomandAdapter);
        mListView2.setOnItemClickListener(this);
        mRouteTask=RouteTask.getInstance(getContext());
//        mSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ShowFragmentUtils.showFragment(getActivity(),SearchLocationFragment.class, FragmentTag.SEARCHLOCATIONFRAGMENT,null,true);
//            }
//        });
        mLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventLocation(location, city, detail));
                popSelf();
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
       mSearch.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               mListView2.setVisibility(View.VISIBLE);
               mListView.setVisibility(View.GONE);
               mapView.setVisibility(View.GONE);
               mLocation.setVisibility(View.GONE);
               if(RouteTask.getInstance(getContext()).getStartPoint()==null){
                   Toast.makeText(getContext(), "检查网络，Key等问题", Toast.LENGTH_SHORT).show();
                   return;
               }
               InputTipTask.getInstance( mRecomandAdapter).searchTips(getContext(),s.toString(),
                       RouteTask.getInstance(getContext()).getStartPoint().city);
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        mLocationTask.onDestroy();
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
        mLocation.setText("当前位置"+entity.address);
        detail = entity.address;
        location = entity.longitude + "," + entity.latitue;
        RouteTask.getInstance(getContext()).setStartPoint(entity);
        LogUtils.d("地址"+entity.address);
        mStartPosition = new LatLng(entity.latitue, entity.longitude);
        CameraUpdate cameraUpate = CameraUpdateFactory.newLatLngZoom(
                mStartPosition, aMap.getCameraPosition().zoom);
        aMap.animateCamera(cameraUpate);
    }

    @Override
    public void onRegecodeGet(PositionEntity entity) {
        mLocation.setText("当前位置"+entity.address);
        detail = entity.address;
        location = entity.longitude + "," + entity.latitue;
        entity.latitue = mStartPosition.latitude;
        entity.longitude = mStartPosition.longitude;
        LogUtils.d("==="+entity.address);/*这是动态变化的*/
        RouteTask.getInstance(getContext()).setStartPoint(entity);
        RouteTask.getInstance(getContext()).search();
        InputTipTask.getInstance( mRecomandAdapter).searchTips(getContext(),entity.address,
                RouteTask.getInstance(getContext()).getStartPoint().city);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PositionEntity entity = (PositionEntity) mRecomandAdapter.getItem(position);
        if (entity.latitue == 0 && entity.longitude == 0) {
            PoiSearchTask poiSearchTask=new PoiSearchTask(getContext(), mRecomandAdapter);
            poiSearchTask.search(entity.address,RouteTask.getInstance(getContext()).getStartPoint().city);

        } else {
            mRouteTask.setEndPoint(entity);
            mRouteTask.search();
        }
        popSelf();
    }

    @Override
    public void onRouteCalculate(float cost, float distance, int duration) {
        detail = RouteTask.getInstance(getContext()).getEndPoint().address;
        location = RouteTask.getInstance(getContext()).getEndPoint().longitude + ","+RouteTask.getInstance(getContext()).getEndPoint().latitue;
        city = RouteTask.getInstance(getContext()).getEndPoint().city;
        EventBus.getDefault().post(new EventLocation(location,city,detail));
        LogUtils.d("==="+detail+location+city);

    }
}
