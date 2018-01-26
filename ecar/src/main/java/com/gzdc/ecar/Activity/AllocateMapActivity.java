package com.gzdc.ecar.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.gzdc.ecar.Model.FileUpload;
import com.gzdc.ecar.Model.Msg;
import com.gzdc.ecar.Model.WaybillModel;
import com.gzdc.ecar.R;
import com.gzdc.ecar.Until.DrivingRouteOverlay;
import com.gzdc.ecar.Until.GlideImageLoader;
import com.gzdc.ecar.Until.Helper;
import com.gzdc.ecar.Until.LocationService;
import com.gzdc.ecar.Until.OverlayManager;
import com.gzdc.ecar.Until.PreferencesTools;
import com.gzdc.ecar.Until.sendGDS;
import com.gzdc.ecar.config.confing;
import com.gzdc.ecar.conmon.BaseActivity;
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

import static com.gzdc.ecar.Activity.UplodingcardActivity.ID_CARD_FRONT;
import static com.gzdc.ecar.Until.PreferencesTools.TWO_LAT;

public class AllocateMapActivity extends BaseActivity implements BaiduMap.OnMarkerClickListener, BaiduMap.OnMapClickListener, OnGetRoutePlanResultListener, View.OnClickListener {


    @BindView(R.id.baidumap)
    MapView baidumap;
    private BaiduMap mBaiduMap;
    private LocationService locationService;
    private BitmapDescriptor mCurrentMarker;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    private List<WaybillModel.TaskBean> position;
    private Marker mark;
    private boolean isfirst = true;//是否第一次定位
    private boolean isfirstsetzoom = true;//是否经过第一次设置zoom

    RoutePlanSearch mSearch = null;
    OverlayManager routeOverlay = null;
    MarkerOptions ooA;
    Marker marklocatin;


    RouteLine route = null;

    Polyline mPolyline;
    private String pos, billtype;

    /**
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate_map);
        initImagePicker();
        ButterKnife.bind(this);
        initdata();
    }

    //初始化地图并开始定位
    private void initview() {
        Log.i("sss", "initview: ");
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);
        mBaiduMap = baidumap.getMap();
        //获取百度地图图层
        mBaiduMap = baidumap.getMap();
        //开启定位图层，显示定位点
        mBaiduMap.setMyLocationEnabled(true);
        locationService = ((EcarApplication) getApplication()).locationService;
        //获取locationservice实例，启动后每间隔locationservice中设定3000ms回调一次
        // mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        //定位监听
        locationService.registerListener(mListener);
        //启动定位服务
        locationService.start();
        mBaiduMap.setMapStatus(locationService.getMapStatusUpdate());//设置地图默认中心点


        Intent intent = new Intent(AllocateMapActivity.this, sendGDS.class);
        startService(intent);
        PreferencesTools.setBooleanValue(TWO_LAT, true);
    }

    //获取数据并设置mark点
    private void initdata() {
        Intent intent = getIntent();
        Map<String, String> params = new HashMap<>();
        params.put("bill", intent.getStringExtra("bill") + "");
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_showScheTaskItem, params, new OkHttpClientManager.ResultCallback<WaybillModel>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(WaybillModel response) {
                if (response.isIsSuccess()) {
                    position = response.getTask();
                    initview();
                    setMark();


                }
            }
        });

    }

    //设置map marker点
    private void setMark() {
        BitmapDescriptor bdA = BitmapDescriptorFactory
                .fromResource(R.mipmap.goods);//构建图标bitmap
        List<LatLng> nolat = new ArrayList<>();
        for (int i = 0; i < position.size(); i++) {

            String po[] = position.get(i).getGisxy().split(",");
            LatLng la = new LatLng(Double.parseDouble(po[1]), Double.parseDouble(po[0]));
            nolat.add(la);
            MarkerOptions ooA = new MarkerOptions().position(la).icon(bdA)
                    .zIndex(9).draggable(true);
            OverlayOptions option = new MarkerOptions()
                    .position(la)
                    .icon(bdA).zIndex(4);

            mark = (Marker) mBaiduMap.addOverlay(ooA);
            Bundle bundle = new Bundle();
            //info必须实现序列化接口
            bundle.putString("address", position.get(i).getAddress() + "");
            bundle.putString("bill", position.get(i).getBill() + "");
            bundle.putString("clientbill", position.get(i).getClientbill() + "");

            bundle.putString("linkinfo", position.get(i).getLinkman() + "(" + position.get(i).getLinktel() + ")");
            bundle.putString("orderbill", position.get(i).getOrderbill());
            bundle.putString("orderid", position.get(i).getOrderid());
            bundle.putString("flag", "2");
            bundle.putString("id", position.get(i).getId());
            bundle.putString("billtype", position.get(i).getBilltype());
            bundle.putString("formtype", position.get(i).getFormtype());
            bundle.putString("input_flag", position.get(i).getInput_flag());
            mark.setExtraInfo(bundle);

        }

        mBaiduMap.setOnMarkerClickListener(this);
    }

    //定位服务回调
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || mBaiduMap == null) {
                return;
            }

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            LatLng ll = null;
            ll = new LatLng(location.getLatitude(),
                    location.getLongitude());

            if (isfirst) {

                isfirst = false;

                MapStatus.Builder builder = new MapStatus.Builder();


                mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;//设置模式
//                mBaiduMap
//                        .setMyLocationConfigeration(new MyLocationConfiguration(
//                                mCurrentMode, true, mCurrentMarker));
                builder.target(ll).zoom(17.0f);
                // 修改为自定义marker
//                mCurrentMarker = BitmapDescriptorFactory
//                        .fromResource(R.mipmap.my_car);
//                mBaiduMap
//                        .setMyLocationConfigeration(new MyLocationConfiguration(
//                                mCurrentMode, true, mCurrentMarker));
//                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

                BitmapDescriptor bdc = BitmapDescriptorFactory
                        .fromResource(R.mipmap.my_car);
                ooA = new MarkerOptions().position(ll).icon(bdc)
                        .zIndex(9).draggable(true);
                marklocatin = (Marker) (mBaiduMap.addOverlay(ooA));
                Bundle bundle = new Bundle();
                bundle.putString("flag", "1");
                marklocatin.setExtraInfo(bundle);

            }
            marklocatin.setPosition(ll);
            setcenter(ll);
        }


    };

    //mark点的点击事件
    @Override
    public boolean onMarkerClick(final Marker marker) {
        if (marker.getExtraInfo().getString("flag").equals("2")) {
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View view = inflater.inflate(R.layout.baidu_infowindow, null);
            LatLng ll = marker.getPosition();

            InfoWindow infoWindow = new InfoWindow(view, ll, -50);
            TextView name = (TextView) view.findViewById(R.id.name);
            TextView address = (TextView) view.findViewById(R.id.address);
            TextView phone = (TextView) view.findViewById(R.id.phone);
            final Bundle bundle = marker.getExtraInfo();

//        WaybillModel.TaskBean add = (WaybillModel.TaskBean) marker.getExtraInfo().get("");
            name.setText(bundle.get("clientbill") + "");
            address.setText(bundle.get("address") + "");
            phone.setText(bundle.get("linkinfo") + "");

            Button uploding = (Button) view.findViewById(R.id.uploding);
            Button quit = (Button) view.findViewById(R.id.quit);
            Button sure = (Button) view.findViewById(R.id.out);
            sure.setText("确认");
            mBaiduMap.showInfoWindow(infoWindow);

            ///
            //infowindow按钮点击事件
            sure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    surein(bundle.get("id") + "");
                }
            });
            //infowindow按钮点击事件
            uploding.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplication(), ImageGridActivity.class);

                    ImagePicker.getInstance().setSelectLimit(1);
                    startActivityForResult(intent, ID_CARD_FRONT);
                    pos = (String) bundle.get("id");


                }


            });

            quit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBaiduMap.hideInfoWindow();
                }
            });
        }

        return true;
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
                uplodingimage("tms_plancar_d", "loadimg", images.get(0).path, images.get(0).name);

            }

        }

    }

    private void uplodingimage(String tablename, String filefield, String path, String name) {
        Map<String, String> params = new HashMap<>();
        String strbase64 = null;
        try {
            strbase64 = Helper.toBase64(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        params.put("id", pos);
        params.put("tablename", tablename);
        params.put("filefield", filefield);
        params.put("filename", name);
        params.put("filestr", strbase64 + "");
        OkHttpClientManager.postAsyn(confing.path + confing.FileUpAction_appUpLoad, params, new OkHttpClientManager.ResultCallback<FileUpload>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(FileUpload response) {
                if (response.isSuccess()) {
                    Helper.showMsg(getApplication(), response.getMsg());
                } else {
                    Helper.showMsg(getApplication(), response.getMsg() + "请重新上传");
                }

            }
        });
    }


    private void rejection(Marker ma) {
        final Bundle bundle = ma.getExtraInfo();
        Map<String, String> params = new HashMap<>();

        params.put("orderbill", bundle.get("orderbill") + "");
        params.put("orderid", bundle.get("orderid") + "");
        params.put("state", "1");
        params.put("formtype", bundle.get("formtype") + "");
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_insure, params, new OkHttpClientManager.ResultCallback<Msg>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Msg response) {
                if (response.isIsSuccess()) {
                    mBaiduMap.hideInfoWindow();

                }

            }
        });
    }

    private void surein(String id) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id.toString().trim());
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_sureLoadBeg, params, new OkHttpClientManager.ResultCallback<Msg>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Msg response) {


            }
        });
    }

    //更具西北点和东南点设置最佳缩放比例
    private void setcenter(LatLng laa) {
        if (position == null) {
            return;
        }
        if (!isfirstsetzoom) {
            return;
        }
        LatLngBounds.Builder builder2 = new LatLngBounds.Builder();
        for (WaybillModel.TaskBean s : position) {
            String po[] = s.getGisxy().split(",");
            LatLng la = new LatLng(Double.parseDouble(po[1]), Double.parseDouble(po[0]));
            builder2 = builder2.include(la);
        }
        builder2 = builder2.include(laa);
        LatLngBounds latlngBounds = builder2.build();

        latlngBounds.getCenter();
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngBounds(latlngBounds, baidumap.getWidth(), baidumap.getHeight());
        mBaiduMap.animateMapStatus(u);

        setdrive(laa);
        isfirstsetzoom = false;
    }

    //设置driver路线
    private void setdrive(LatLng ll) {
//        PlanNode stMassNode = PlanNode.withCityNameAndPlaceName("北京", "天安门");
        PlanNode stMassNode = PlanNode.withLocation(ll);
//        PlanNode enMassNode = PlanNode.withCityNameAndPlaceName("上海", "东方明珠");
        for (int i = 0; i < position.size(); i++) {
            String po[] = position.get(i).getGisxy().split(",");
            LatLng end = new LatLng(Double.parseDouble(po[1]), Double.parseDouble(po[0]));
            PlanNode enMassNode = PlanNode.withLocation(end);
//        mSearch.drivingSearch(new DrivingRoutePlanOption().from(stMassNode).to(enMassNode));
            List<LatLng> points = new ArrayList<LatLng>();
            points.add(ll);
            points.add(end);
            OverlayOptions ooPolyline = new PolylineOptions().width(10)
                    .color(0xAAFF0000).points(points);
            mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);
        }
//        String po[] = position.get(0).getGisxy().split(",");
//        LatLng end = new LatLng(Double.parseDouble(po[1]), Double.parseDouble(po[0]));
//        PlanNode enMassNode = PlanNode.withLocation(end);
////        mSearch.drivingSearch(new DrivingRoutePlanOption().from(stMassNode).to(enMassNode));
//        List<LatLng> points = new ArrayList<LatLng>();
//        points.add(ll);
//        points.add(end);
//        OverlayOptions ooPolyline = new PolylineOptions().width(10)
//                .color(0xAAFF0000).points(points);
//        mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);
////        routeOverlay.removeFromMap();
//        routeOverlay.addToMap();
    }


    @Override
    public void onMapClick(LatLng latLng) {
        mBaiduMap.hideInfoWindow();
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        mBaiduMap.hideInfoWindow();

        return true;
    }

    @Override
    protected void onResume() {
        baidumap.onResume();
        Log.i("map", "onResume: 3");
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Intent intent = new Intent(MapActivity.this, sendGDS.class);
//        stopService(intent);
        locationService.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        baidumap.onDestroy();
        baidumap = null;
        Log.i("map", "onDestroy: 3");
    }

    @Override
    public void onPause() {
        baidumap.onPause();
        Log.i("map", "onPause:3 ");
        super.onPause();
    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
        route = drivingRouteResult.getRouteLines().get(0);
        DrivingRouteOverlay overlay = new AllocateMapActivity.MyDrivingRouteOverlay(mBaiduMap);
        mBaiduMap.setOnMarkerClickListener(overlay);
        routeOverlay = overlay;
        overlay.setData(drivingRouteResult.getRouteLines().get(0));
        overlay.addToMap();
        overlay.zoomToSpan();
    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }

    @Override
    public void onClick(View v) {

    }

    // 定制RouteOverly
    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {

        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
//            if (useDefaultIcon) {
//                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
//            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
//            if (useDefaultIcon) {
//                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
//            }
            return null;
        }
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
        imagePicker.setSaveRectangle(false);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

}

