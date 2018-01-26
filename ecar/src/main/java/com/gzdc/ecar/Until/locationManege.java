package com.gzdc.ecar.Until;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.baidu.mapapi.model.LatLng;
import com.gzdc.ecar.conmon.EcarApplication;

/**
 * Created by pzn on 2017/1/17 0017.
 */

public class locationManege extends PreferencesTools {
    public static final String LOCATIONPOINT = "location_point";
    public static final String LATLNGLATITUDE = "latitude";
    public static final String LATLNGLONGITUDE = "longitude";
    private static final double VERSION = 1.0;

    public static void update(LatLng la) {
        SharedPreferences spf = EcarApplication.getAppContext().getSharedPreferences(LOCATIONPOINT, Context.MODE_APPEND);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString(LATLNGLATITUDE, la.longitude + "");
        editor.putString(LATLNGLONGITUDE, la.longitude + "");
        editor.commit();
    }

    public static LatLng get() {
        SharedPreferences spf = EcarApplication.getAppContext().getSharedPreferences(LOCATIONPOINT, Context.MODE_APPEND);
        String latitudes = spf.getString(LATLNGLATITUDE, "");
        String longitudes = spf.getString(LATLNGLONGITUDE, "");
        LatLng latlng = null;
        if (!TextUtils.isEmpty(latitudes)) {
            latlng = new LatLng(Double.parseDouble(longitudes), Double.parseDouble(latitudes));
        } else {
            latlng = new LatLng(29.806651, 121.606983);
        }
        return latlng;
    }
}
