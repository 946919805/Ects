package com.gzdc.ecar.Until;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.gzdc.ecar.Model.Msg;
import com.gzdc.ecar.config.confing;
import com.gzdc.ecar.conmon.EcarApplication;
import com.gzdc.ecar.conmon.OkHttpClientManager;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

import static com.gzdc.ecar.Until.PreferencesTools.CEHICLE;
import static com.gzdc.ecar.Until.PreferencesTools.ONE_LAT;
import static com.gzdc.ecar.Until.PreferencesTools.TWO_LAT;

/**
 * Created by dianch3 on 2017/2/6.
 */
public class sendGDS extends Service {
    private int i = 0;
    private int TIME = 3000;
    @Nullable
    private static final String TAG = "TestTag";
    private LocationService locationService;
    private LatLng lat,oldlat;
    private String Vehicle;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        super.onCreate();
        locationService = ((EcarApplication) getApplication()).locationService;
        //定位监听
        locationService.registerListener(mListener);
        //启动定位服务
        locationService.start();

    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.i(TAG, "onStart");
        super.onStart(intent, startId);
    }

    private void sendlocation() {
        Map<String, String> params = new HashMap<>();

        if(PreferencesTools.getBooleanValue(ONE_LAT, false)){



        params.put("gisx", lat.longitude + "");
        params.put("gisy", lat.latitude + "");
        params.put("vehicle", PreferencesTools.getStringValue(CEHICLE, ""));
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_uploadGis, params, new OkHttpClientManager.ResultCallback<Msg>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(Msg response) {
                Log.i(TAG, "onResponse: sss+2");
            }
        });
        }
        if(PreferencesTools.getBooleanValue(TWO_LAT, false)){
            Log.i(TAG, "onResponse: sss+1");
        params.put("gisx", lat.longitude + "");
        params.put("gisy", lat.latitude + "");
        params.put("vehicle", PreferencesTools.getStringValue(CEHICLE, ""));
        OkHttpClientManager.postAsyn(confing.path + confing.DriverManageAction_UpCarGis, params, new OkHttpClientManager.ResultCallback<Msg>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(Msg response) {
                Log.i(TAG, "onResponse: sss+1"+lat.longitude+""+PreferencesTools.getStringValue(CEHICLE, "")+"");
            }
        });}
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        PreferencesTools.setBooleanValue(ONE_LAT,false);
        PreferencesTools.setBooleanValue(TWO_LAT,false);
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    //定位服务回调
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {


                lat = new LatLng(location.getLatitude(), location.getLongitude());
            if(oldlat==null){
                oldlat=lat;
            }
            Log.i(TAG, "onReceiveLocation: "+DistanceUtil.getDistance(lat,oldlat));
//            Helper.showMsg(getApplication(),DistanceUtil.getDistance(lat,oldlat)+"");
            if (DistanceUtil.getDistance(lat,oldlat)>50){
                oldlat=lat;
                sendlocation();
            }
        }


    };
}
