package com.gzdc.ecar.conmon;

import android.app.Application;
import android.app.Service;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Vibrator;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.gzdc.ecar.Until.LocationService;
import com.gzdc.ecar.Until.PreferencesTools;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import com.squareup.okhttp.OkHttpClient;

import java.net.CookieManager;
import java.net.CookiePolicy;

import static com.gzdc.ecar.Until.PreferencesTools.ONE_LAT;
import static com.gzdc.ecar.Until.PreferencesTools.TWO_LAT;


/**
 * Created by mancos on 15/11/16.
 * 下午3:58
 * ToDo:application 配置
 * <p/>
 * ━━━━━━神兽出没━━━━━━
 * 　　 ┏┓   ┏┓
 * 　　┏┛┻━━━┛┻━━┓
 * 　　┃　　      ┃
 * 　　┃　　 ━     ┃
 * 　　┃　┳┛　┗┳　  ┃
 * 　　┃　　　      ┃
 * 　　┃　　┻       ┃
 * 　　┃　　　      ┃
 * 　　┗━┓　　　  ┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━┓
 * 　　　　┃　      ┣┓
 * 　　　　┃　      ┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　 ┃┫┫ ┃┫┫
 * 　　　　 ┗┻┛ ┗┻┛
 * <p/>
 * <p/>
 * ━━━━━━感觉萌萌哒━━━━━━
 * Code is far away from bug with the animal protecting
 * 　　　　　　　　　神兽保佑,代码无bug
 * <p/>
 */
public class EcarApplication extends Application {
    private static EcarApplication instance;
    /**
     * 图片下载类
     */
    private ImageLoader imageLoader;
    /**
     * 图片下载配置
     */
    private DisplayImageOptions options;
    /**
     * 图片下载配置
     */
    private ImageLoaderConfiguration config;

    private boolean isLogin;
    private boolean islocation;

    public boolean islocation() {
        return islocation;
    }

    public void setIslocation(boolean islocation) {
        this.islocation = islocation;
    }
    /**
     * 保存用户登录信息
     **/
    public LocationService locationService;
    public Vibrator mVibrator;

    public static EcarApplication getAppContext() {
        return instance;
    }
    int i=1;
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        //imageLoad 配置
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .build();
        config = new ImageLoaderConfiguration.Builder(getApplicationContext()).threadPoolSize(4)
                .threadPriority(Thread.NORM_PRIORITY - 1)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache())
                /*.memoryCache(new LRULimitedMemoryCache(8 * 1024 * 1024))*/
                .memoryCacheSize(8 * 1024 * 1024)
                .writeDebugLogs()
                .defaultDisplayImageOptions(options).build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
        /**
         * 百度地图初始化
         */

        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(this);
        OkHttpClient client = new OkHttpClient();
        client.setCookieHandler(new CookieManager(
                new PersistentCookieStore(getApplicationContext()),
                CookiePolicy.ACCEPT_ALL));
        String processName = getPackageName();
        if (processName!= null) {
            if(processName.equals("com.gzdc.ecar")){
                //初始化com.soubw.prodemo以包名为进程名，项目默认的进程
                PreferencesTools.setBooleanValue(TWO_LAT,false);
                PreferencesTools.setBooleanValue(ONE_LAT,false);
                Log.i("TestTag", "sendlocation: 11"+PreferencesTools.getBooleanValue(ONE_LAT,false));
                Log.i("TestTag", "sendlocation: "+PreferencesTools.getBooleanValue(TWO_LAT,false)+""+i++);
            }else if(processName.equals("com.gzdc.ecar:MapActivity")){
                //初始化com.soubw.prodemo:login
                Log.i("TestTag","sss");
            }
        }


    }
    public boolean isLogin() {
        return isLogin;
    }
    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }
    public ImageLoader getImageLoader() {
        return imageLoader;
    }
    public DisplayImageOptions getOptions() {
        return options;
    }
    public ImageLoaderConfiguration getConfig() {
        return config;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }
}
