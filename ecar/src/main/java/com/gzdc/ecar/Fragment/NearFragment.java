package com.gzdc.ecar.Fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.gzdc.ecar.R;
import com.gzdc.ecar.Until.Callbaciclick;
import com.gzdc.ecar.Until.LocationService;
import com.gzdc.ecar.Until.locationManege;
import com.gzdc.ecar.conmon.EcarApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearFragment extends Fragment {


    @BindView(R.id.mapview)
    TextureMapView mMapView;
    private LocationService locationService;
    boolean isFirstLoc = true;
    BaiduMap mBaiduMap;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;
    private static final int accuracyCircleFillColor = 0xAAFFFF88;
    private static final int accuracyCircleStrokeColor = 0xAA00FF00;
    private int i = 0;

    public NearFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_near, container, false);
        ButterKnife.bind(this, view);
        initview();
        return view;
    }

    private void initview() {
        mBaiduMap = mMapView.getMap();
        //开启定位图层，显示定位点
        mBaiduMap.setMyLocationEnabled(true);
        locationService = ((EcarApplication) getActivity().getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        // mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        //定位监听
        locationService.registerListener(mListener);
        //启动定位服务
        locationService.start();
        mBaiduMap.setMapStatus(locationService.getMapStatusUpdate());//设置地图默认中心点
    }

    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);


            LatLng ll = new LatLng(location.getLatitude(),
                    location.getLongitude());
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(17.0f);
            locationManege.update(ll);
            mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
//                mBaiduMap
//                        .setMyLocationConfigeration(new MyLocationConfiguration(
//                                mCurrentMode, true, mCurrentMarker));
            // 修改为自定义marker
            mCurrentMarker = BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_geo);
            mBaiduMap
                    .setMyLocationConfigeration(new MyLocationConfiguration(
                            mCurrentMode, true, mCurrentMarker,
                            accuracyCircleFillColor, accuracyCircleStrokeColor));
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));


//                Map<String,String> params=new HashMap<>();
//                OkHttpClientManager.postAsyn(confing.path, params, new OkHttpClientManager.ResultCallback() {
//                    @Override
//                    public void onError(Request request, Exception e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(Object response) {
//
//                    }
//                });

        }
    };

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        // 退出时销毁定位
        locationService.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }


}
