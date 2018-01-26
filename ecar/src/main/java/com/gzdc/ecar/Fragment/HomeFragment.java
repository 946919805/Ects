package com.gzdc.ecar.Fragment;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.OrientationListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.gzdc.ecar.Activity.Login_Activity;
import com.gzdc.ecar.Model.DriverUpGis;
import com.gzdc.ecar.Model.FileUpload;
import com.gzdc.ecar.Model.Msg;
import com.gzdc.ecar.Model.NetPointModel;
import com.gzdc.ecar.Model.ShowItemModel;
import com.gzdc.ecar.R;
import com.gzdc.ecar.Until.GlideImageLoader;
import com.gzdc.ecar.Until.Helper;
import com.gzdc.ecar.Until.LocationService;
import com.gzdc.ecar.Until.PreferencesTools;
import com.gzdc.ecar.Until.locationManege;
import com.gzdc.ecar.Until.sendGDS;
import com.gzdc.ecar.config.confing;
import com.gzdc.ecar.conmon.EcarApplication;
import com.gzdc.ecar.conmon.OkHttpClientManager;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.gzdc.ecar.Activity.UplodingcardActivity.ID_CARD_FRONT;
import static com.gzdc.ecar.Until.PreferencesTools.TWO_LAT;

public class HomeFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks {
    @BindView(R.id.ready_car)
    Button readyCar;
    @BindView(R.id.alert)
    Button alert;
    private LocationService locationService;
    boolean isFirstLoc = true;
    BaiduMap mBaiduMap;
    BitmapDescriptor mCurrentMarker;
    private Marker mMarkerD, mMarkerDs;
    private static final int accuracyCircleFillColor = 0xAAFFFF88;
    private static final int accuracyCircleStrokeColor = 0xAA00FF00;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    private List<NetPointModel.NetpointBean> points;
    private Marker mark,marklocatin;
    private InfoWindow mInfoWindow;
    MarkerOptions ooA;
    @BindView(R.id.bmapView)
    MapView mMapView;
    LatLng llb;
    private List<ShowItemModel.List1Bean> list1;
    private List<ShowItemModel.List2Bean> list2;
    private List<LatLng> points1 = new ArrayList<>();
    private String id=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        cameraTask();
//        showProgress();
        initview();
        initImagePicker();
        return view;
    }
    public void cameraTask() {
        if (EasyPermissions.hasPermissions(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Have permission, do the thing!
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, "需要请求定位权限",
                    101, Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    private void initview() {

        //获取百度地图图层
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
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                Bundle bundle=marker.getExtraInfo();
                String s=bundle.getString("flag");

                Log.i("sssssssss", "onMarkerClick: " + marker.getExtraInfo().getString("address"));
                Button button = new Button(getActivity().getApplicationContext());
                button.setBackgroundResource(R.drawable.popup);
                Bundle bundles = marker.getExtraInfo();
                button.setText(bundles.getString("address"));
                InfoWindow.OnInfoWindowClickListener listener = null;
                listener = new InfoWindow.OnInfoWindowClickListener() {
                    public void onInfoWindowClick() {
                        mBaiduMap.hideInfoWindow();
                    }
                };
                LatLng ll = marker.getPosition();
                mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(button), ll, -47, listener);


                if(marker.getExtraInfo().getString("flag").equals("2")){
                    mBaiduMap.showInfoWindow(mInfoWindow);

                }
                return true;
            }
        });
        Intent intent = new Intent(getApplication(), sendGDS.class);
        context.startService(intent);
        PreferencesTools.setBooleanValue(TWO_LAT,true);
    }

    private void getpoints() {
        Map<String, String> params = new HashMap<>();
        params.put("vehicle", "ss");
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_showItemPosition, params, new OkHttpClientManager.ResultCallback<ShowItemModel>() {
            @Override
            public void onError(Request request, Exception e) {
                forwardRight(Login_Activity.class);
            }
            @Override
            public void onResponse(ShowItemModel response) {
                list1 = response.getList1();
                list2 = response.getList2();
//                setmarks();
                dissmissProgress();
            }
        });
    }
    private void setmarks() {
        BitmapDescriptor bdA = BitmapDescriptorFactory
                .fromResource(R.drawable.nocar);
        BitmapDescriptor bdc = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_markb);

        List<LatLng> nolat = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            String po[] = list1.get(i).getGisxy().split(",");
            LatLng la = new LatLng(Double.parseDouble(po[1]), Double.parseDouble(po[0]));
            nolat.add(la);
            MarkerOptions ooA = new MarkerOptions().position(la).icon(bdA)
                    .zIndex(9).draggable(true);
            mark = (Marker) mBaiduMap.addOverlay(ooA);
            Bundle bundle = new Bundle();
            //info必须实现序列化接口
            bundle.putSerializable("address", list1.get(i));
            bundle.putString("flag","2");
            mark.setExtraInfo(bundle);
        }
        for (ShowItemModel.List2Bean ss :
                list2) {
            String po[] = ss.getSendgis().split(",");
            LatLng la = new LatLng(Double.parseDouble(po[1]), Double.parseDouble(po[0]));
            nolat.add(la);
            MarkerOptions ooA = new MarkerOptions().position(la).icon(bdc)
                    .zIndex(9).draggable(true);
            mBaiduMap.addOverlay(ooA);
        }
        dissmissProgress();
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
            llb=ll;
            if (isFirstLoc) {
                isFirstLoc = false;
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll);
                locationManege.update(ll);
                mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
//                mBaiduMap
//                        .setMyLocationConfigeration(new MyLocationConfiguration(
//                                mCurrentMode, true, mCurrentMarker));
                // 修改为自定义marker
//                mCurrentMarker = BitmapDescriptorFactory
//                        .fromResource(R.mipmap.my_car);
//                mBaiduMap
//                        .setMyLocationConfigeration(new MyLocationConfiguration(
//                                mCurrentMode, true, mCurrentMarker
//                                ));

                BitmapDescriptor bdc = BitmapDescriptorFactory
                        .fromResource(R.mipmap.my_car);
                ooA= new MarkerOptions().position(ll).icon(bdc)
                        .zIndex(9).draggable(true);
                marklocatin = (Marker) (mBaiduMap.addOverlay(ooA));

//                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                 MapStatus mMapStatus = new MapStatus.Builder()
                        .target(ll).build();
                MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                mBaiduMap.setMapStatus(mMapStatusUpdate);
                Bundle bundle = new Bundle();
                //info必须实现序列化接口
                bundle.putString("flag","1");
                marklocatin.setExtraInfo(bundle);

            }
            marklocatin.setPosition(ll);
            setscope(ll);
        }
    };
    //划线
    private void setscope(LatLng la) {

//        for (NetPointModel.NetpointBean ss :
//                points) {
//            String po[] = ss.getPointxy().split(",");
//            LatLng la = new LatLng(Double.parseDouble(po[1]), Double.parseDouble(po[0]));

//        OverlayOptions ooPolygon = new PolygonOptions().points(points1)
//                .stroke(new Stroke(5, 0xAA00FF00)).fillColor(0xAAFFFF00);
//        mBaiduMap.addOverlay(ooPolygon);
        if (points1.size()==0||points1==null){
            points1.add(la);
        }
        Log.i("wwwww", "setscope: "+points1.get(points1.size()-1));
        if(DistanceUtil.getDistance(la,points1.get(points1.size()-1))>50){
            points1.add(la);
        }

        if(points1.size()>=2){
            OverlayOptions ooPolyline = new PolylineOptions().width(10)
                    .color(0xAAFF0000).points(points1);
            mBaiduMap.addOverlay(ooPolyline);

        }
//        LatLng llB=points1.get(0);
//        BitmapDescriptor bdB = BitmapDescriptorFactory
//                .fromResource(R.drawable.icon_markb);
//        MarkerOptions ooB = new MarkerOptions().position(llB).icon(bdB)
//                .zIndex(5);
//        mMarkerD = (Marker) (mBaiduMap.addOverlay(ooB));
//        LatLng llBs = new LatLng(113.33241699999998, 23.101758999999998);
//        BitmapDescriptor bdB = BitmapDescriptorFactory
//                .fromResource(R.drawable.icon_markb);
//        MarkerOptions ooB = new MarkerOptions().position(llb).icon(bdB)
//                .zIndex(5);
//        Log.i("x", "setmarks: " + llb.latitude + "sss" + llb.longitude);
//        mMarkerD = (Marker) (mBaiduMap.addOverlay(ooB));
//        LatLng llB = new LatLng(points1.get(0).latitude, points1.get(0).longitude);
//
//        MarkerOptions ooBs = new MarkerOptions().position(llBs).icon(bdB)
//                .zIndex(5);
//        mMarkerDs = (Marker) (mBaiduMap.addOverlay(ooBs));

    }
    public void onReceivePoi(BDLocation poiLocation) {
    }
    @Override
    public void onStart() {
        super.onPause();
//        getpoints();
    }
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
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, "当前App需要申请定位权限,需要打开设置页面么?")
                    .setTitle("权限申请")
                    .setPositiveButton("确认")
                    .setNegativeButton("取消", null /* click listener */)
                    .setRequestCode(101)
                    .build()
                    .show();
        }
    }
    /**
     * EsayPermissions接管权限处理逻辑
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    @OnClick({R.id.ready_car, R.id.alert})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ready_car:
                //报班
                readyCar();

                break;
            case R.id.alert:
//                Helper.showMsg(getApplication(),"报警成功");
                //报警
                alert_car();
                break;
        }
    }
    //报警
    private void alert_car() {
        Map<String, String> params = new HashMap<>();
        params.put("gisx",llb.longitude+"");
        params.put("gisy",llb.latitude+"");
        OkHttpClientManager.postAsyn(confing.path+confing.DriverManageAction_UpWarnGis, params, new OkHttpClientManager.ResultCallback<DriverUpGis>() {
            @Override
            public void onError(Request request, Exception e) {
            }
            @Override
            public void onResponse(DriverUpGis response) {
                if (response.isSuccess()){
                    Intent intent = new Intent(getApplication(), ImageGridActivity.class);
                    id=response.getId();
                    ImagePicker.getInstance().setSelectLimit(1);
                    startActivityForResult(intent, ID_CARD_FRONT);
                }
            }
        });
    }
    //报班
    private void readyCar() {
        Map<String, String> params = new HashMap<>();
        params.put("","");
        OkHttpClientManager.postAsyn(confing.path + confing.DriverManageAction_driverSign, params, new OkHttpClientManager.ResultCallback<Msg>() {
            @Override
            public void onError(Request request, Exception e) {
            }
            @Override
            public void onResponse(Msg response) {
                if (response.isIsSuccess()) {
                    Helper.showMsg(getApplication(), response.getMsg());
                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回REQUEST_CODE_SELECTs
            if (data != null && requestCode == ID_CARD_FRONT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
//                ImagePicker.getInstance().getImageLoader().displayImage(this, images.get(0).path, idCardFront, 0, 0);
//   com.nostra13.universalimageloader.core.ImageLoader im=EcarApplication.getAppContext().getImageLoader();
                uploadimage("tms_carwarn", "warnimg",images.get(0).path,images.get(0).name);

            }

        }

    }
    private void uploadimage(String tablename, String filefield,String  file,String name) {


        Map<String, String> params = new HashMap<>();
        String strbase64=null;
        try {
            strbase64= Helper.toBase64(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        params.put("id", id);
        params.put("tablename", tablename);
        params.put("filefield", filefield);
        params.put("filename", name);
        params.put("filestr",strbase64+"");
        OkHttpClientManager.postAsyn(confing.path + confing.FileUpAction_appUpLoad, params, new OkHttpClientManager.ResultCallback<FileUpload>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(FileUpload response) {
                if (response.isSuccess()){
                    Helper.showMsg(getApplication(), response.getMsg());
                }else {
                    Helper.showMsg(getApplication(), response.getMsg()+"请重新上传");
                }

            }
        });

    }
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {

            }

            @Override
            public void clearMemoryCache() {

            }
        });
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }
}
