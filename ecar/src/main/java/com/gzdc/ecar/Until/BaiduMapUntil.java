package com.gzdc.ecar.Until;


import android.util.Log;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by pzn on 2017/1/17 0017.
 * 离线地图计算类
 */

public class BaiduMapUntil {
    static BaiduMap map;

    //计算最远坐标
    public static LatLng maolat(List<LatLng> latLngListngs, LatLng po) {
        double maxlat = 0;
        double minlat = 0;
        double maxlong = 0;
        double minlong = 0;

        List<Double> latitude = new ArrayList<>();
        List<Double> longitude = new ArrayList<>();
        for (int i = 0; i < latLngListngs.size(); i++) {

            latitude.add(latLngListngs.get(i).latitude);
            longitude.add(latLngListngs.get(i).longitude);
        }
        for (int i = 0; i < latLngListngs.size(); i++) {
            if (latitude.get(i) > maxlat) {
                maxlat = latitude.get(i);
            }
            if (latitude.get(i) < minlat) {
                minlat = latitude.get(i);
            }
            if (longitude.get(i) > maxlong) {
                maxlong = longitude.get(i);
            }
            if (longitude.get(i) < minlong) {
                minlong = longitude.get(i);
            }
        }
        if (Math.abs(po.latitude - minlat) > Math.abs(po.latitude - maxlat)) {
            maxlat = minlat;
        }
        if (Math.abs(po.longitude - minlong) > Math.abs(po.longitude - maxlong)) {
            maxlong = maxlong;
        }
        LatLng maxlatlng = new LatLng(maxlat, maxlong);

        return maxlatlng;
    }

    public static int mapzoom(LatLng max, LatLng po) {
        int zoomnum = 0, zoomx = 1, zoomy = 2;
        int zoom[] = {500, 1000, 2000, 5000, 10000, 20000, 25000, 50000, 100000, 200000, 500000, 1000000, 2000000, 5000000, 10000000};//级别18到3。
        LatLng maxX = new LatLng(max.latitude, po.longitude);
        double maxXdis = DistanceUtil.getDistance(maxX, po);
        for (int i = 0; i < zoom.length; i++) {

            if ((maxXdis - zoom[i]) > 0) {
                zoomx = 18 - i - 2;
            }
        }
        LatLng y = new LatLng(po.latitude, max.longitude);
        double maxYdis = DistanceUtil.getDistance(po, y);
        for (int i = 0; i < zoom.length; i++) {
            if ((maxYdis - zoom[i]) > 0) {
                zoomy = 18 - i;
            }
        }
        if (zoomx > zoomy) {
            zoomnum = zoomx;
        } else {
            zoomnum = zoomy;
        }
        Log.i("ssss", "zoomx: " + zoomx + "zoomy" + zoomy);
        return zoomnum;
    }

    public MapStatusUpdate getMapStatusUpdate() {
        LatLng cenpt = new LatLng(29.806651, 121.606983);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化


        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        return mMapStatusUpdate;
    }

    public static String formatDataSize(int size) {
        String ret = "";
        if (size < (1024 * 1024)) {
            ret = String.format("%dK", size / 1024);
        } else {
            ret = String.format("%.1fM", size / (1024 * 1024.0));
        }
        return ret;
    }
}
