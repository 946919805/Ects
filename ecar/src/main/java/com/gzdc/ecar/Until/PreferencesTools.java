package com.gzdc.ecar.Until;

import android.content.Context;
import android.content.SharedPreferences;

import com.gzdc.ecar.conmon.EcarApplication;


/**
 * TODO:SP工具类
 */
public class PreferencesTools {
    public static final String IS_LOGIN = "is_login";
    public static final String SHARED_NAME = "config";
    public static final String IS_FIRST_START = "is_first_start";
    public static final String START_IMAGE_URL = "start_image_url";
    public static final String IS_FIRST_SHOW = "is_first_show";
    public static final String USER_ACCOUNT = "user_account";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String LOCATIONPOINT = "location_point";
    public static  final String VEHICLE="vehicle";
    public static final String CEHICLE = "cehicle";
    public static final String ONE_LAT = "onelat";
    public static final String TWO_LAT = "two;at";
    public final static String SETTING = "Setting";

    public static void setIntValue(String key, int value) {
        SharedPreferences.Editor sp = EcarApplication.getAppContext().getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putInt(key, value);
        sp.commit();
    }

    public static void setBooleanValue(String key, boolean value) {
        SharedPreferences.Editor sp = EcarApplication.getAppContext().getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putBoolean(key, value);
        sp.commit();
    }

    public static void setStringValue(String key, String value) {
        SharedPreferences.Editor sp = EcarApplication.getAppContext().getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putString(key, value);
        sp.commit();
    }

    public static int getIntValue(String key, int defValue) {
        SharedPreferences sp = EcarApplication.getAppContext().getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        int value = sp.getInt(key, defValue);
        return value;
    }

    public static boolean getBooleanValue(String key, boolean defValue) {
        SharedPreferences sp = EcarApplication.getAppContext().getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        boolean value = sp.getBoolean(key, defValue);
        return value;
    }

    public static String getStringValue(String key, String defValue) {
        SharedPreferences sp = EcarApplication.getAppContext().getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String value = sp.getString(key, defValue);
        return value;
    }

    public static void clear() {
        SharedPreferences spf = EcarApplication.getAppContext().getSharedPreferences(SETTING, Context.MODE_APPEND);
        SharedPreferences.Editor editor = spf.edit();
        editor.clear();
        editor.commit();
    }

    /*<!--         -->*/
    public static boolean Isfirst(String key, boolean defValue) {
        SharedPreferences sp = EcarApplication.getAppContext().getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        boolean value = sp.getBoolean(key, defValue);
        return value;
    }

    public static void setIsfirst(String key, boolean value) {
        SharedPreferences.Editor sp = EcarApplication.getAppContext().getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putBoolean(key, value);
        sp.commit();
    }

    public static void setImsgeurl(String key, String value) {
        SharedPreferences.Editor sp = EcarApplication.getAppContext().getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putString(key, value);
        sp.commit();
    }

    public static String getImsgeurl(String key, String defValue) {
        SharedPreferences sp = EcarApplication.getAppContext().getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String value = sp.getString(key, defValue);
        return value;
    }
}
