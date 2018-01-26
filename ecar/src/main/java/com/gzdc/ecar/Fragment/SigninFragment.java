package com.gzdc.ecar.Fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;

import com.gzdc.ecar.Activity.DetailsinActivity;
import com.gzdc.ecar.Activity.Detailsin_twoActivity;
import com.gzdc.ecar.Activity.Login_Activity;
import com.gzdc.ecar.Activity.MapActivity;
import com.gzdc.ecar.Activity.WxDemoActivity;
import com.gzdc.ecar.Adpter.SingforAdpter;
import com.gzdc.ecar.Adpter.SinginchildAdpter;
import com.gzdc.ecar.Model.FileUpload;
import com.gzdc.ecar.Model.Msg;
import com.gzdc.ecar.Model.RunbackModel;
import com.gzdc.ecar.Model.SingInModel;
import com.gzdc.ecar.Model.TaskModel;
import com.gzdc.ecar.R;
import com.gzdc.ecar.Until.GlideImageLoader;
import com.gzdc.ecar.Until.Helper;
import com.gzdc.ecar.Until.PreferencesTools;
import com.gzdc.ecar.config.confing;
import com.gzdc.ecar.conmon.OkHttpClientManager;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;


import static android.graphics.Color.TRANSPARENT;
import static com.gzdc.ecar.Activity.UplodingcardActivity.ID_CARD_FRONT;
import static com.gzdc.ecar.Until.PreferencesTools.CEHICLE;

/**
 * A simple {@link SigninFragment} subclass.
 */
public class SigninFragment extends BaseFragment implements SingforAdpter.Callback, View.OnClickListener {

    @BindView(R.id.singin)
    ListView singin;
    private List<SingInModel.TaskBean> ta, tass;
    private SingforAdpter parentadpter;
    private SinginchildAdpter childadpter;
    private String allid, orderid, inputflag;
    private View view;
    private RunbackModel runbill;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_signin, container, false);
        ButterKnife.bind(this, view);
        inidtata();
        initImagePicker();
        return view;
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
                if (inputflag.equals("2")) {
                    uploadimage("tms_runbill_h", "backimg", images.get(0).path, images.get(0).name);
                } else {
                    uploadimage("tms_sendbill_h", "backimg", images.get(0).path, images.get(0).name);

                }

            }
        }
    }


    private void uploadimage(String tablename, String filefield, String file, String name) {


        Map<String, String> params = new HashMap<>();
        String strbase64 = null;
        try {
            strbase64 = Helper.toBase64(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        params.put("id", allid);
        params.put("tablename", tablename);
        params.put("filefield", filefield);
        params.put("filename", name);
        params.put("filestr", strbase64 + "");
        Log.i("ssssss", "allid" +
                ":" + allid);
        Log.i("ssssss", "tablename:" + tablename);
        Log.i("ssssss", "name:" + name);
        Log.i("ssssss", "click:" + strbase64);
        Log.i("ssssss", "filefield:" + filefield);
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

    private void inidtata() {

        Map<String, String> params = new HashMap<>();
        params.put("id", "ssss");
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_itemScheSign, params, new OkHttpClientManager.ResultCallback<SingInModel>() {
            @Override
            public void onError(Request request, Exception e) {
                forwardRight(Login_Activity.class);

            }

            @Override
            public void onResponse(SingInModel response) {
                if (response != null && response.isIsSuccess()) {
                    ta = response.getTask();
                    PreferencesTools.setStringValue(CEHICLE, ta.get(0).getPlandate());
                    initView();
                } else Helper.showMsg(getApplication(), "暂无数据");

            }
        });
    }

    private void initView() {
        parentadpter = new SingforAdpter(getActivity(), ta, this);
        singin.setAdapter(parentadpter);

    }


    //listview 点击事件回调
    @Override
    public void click(View v) {
        switch (v.getId()) {
            case R.id.sign_for:
                if (v.getTag(R.id.tag_four).equals("1")) {
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("id", v.getTag(R.id.tag_first).toString().trim());
                    bundle1.putString("orderid", v.getTag(R.id.tag_second).toString().trim());
                    bundle1.putString("input_flag", v.getTag(R.id.tag_three).toString().trim());
                    forwardRight(DetailsinActivity.class, bundle1);
                } else {
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("id", v.getTag(R.id.tag_first).toString().trim());
                    bundle1.putString("orderid", v.getTag(R.id.tag_second).toString().trim());
                    bundle1.putString("input_flag", v.getTag(R.id.tag_three).toString().trim());
                    forwardRight(Detailsin_twoActivity.class, bundle1);
                }
                break;
            case R.id.uploding_bill:
                allid = (String) v.getTag(R.id.tag_first);
                inputflag = (String) v.getTag(R.id.tag_second);
                Helper.showMsg(getApplication(), allid);
                Log.i("ssssss", "click:" + allid);
                Intent intent = new Intent(getApplication(), ImageGridActivity.class);
                ImagePicker.getInstance().setSelectLimit(1);
                startActivityForResult(intent, ID_CARD_FRONT);
                break;
            case R.id.back_bill:
                allid = (String) v.getTag(R.id.tag_first);
                Log.i("ssssss", "click:" + allid);
                orderid = (String) v.getTag(R.id.tag_second);
                inputflag = (String) v.getTag(R.id.tag_three);
                getpopdata();
                break;
        }
    }

    private void sig_in(String id) {

        Map<String, String> params = new HashMap<>();
        params.put("id", id + "");

        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_sureLoadBeg, params, new OkHttpClientManager.ResultCallback<Msg>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Msg response) {
                Helper.showMsg(getApplication(), response.getMsg().toString().trim());
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
        imagePicker.setSaveRectangle(false);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素

    }

    private void getpopdata() {
        Map<String, String> params = new HashMap<>();
        params.put("id", allid + "");
        params.put("input_flag", inputflag.toString().trim());
        params.put("orderid", orderid.toString().trim());
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_showSendBack, params, new OkHttpClientManager.ResultCallback<RunbackModel>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(RunbackModel response) {
                if (response != null) {
                    runbill = response;
                    openpopuwindow();

                }
            }
        });
    }

    private Button bt_sure, bt_acncle;
    private ViewGroup menuView;
    private EditText name, ruanbill, revname, revman, backdate, backbillnum;
    private PopupWindow popupWindow;
    private RadioButton backflag;
    private ImageButton date;

    private void openpopuwindow() {
        menuView = (ViewGroup) getActivity().getLayoutInflater().inflate(
                R.layout.runbill_item, null);
//        LayoutInflater mLayoutInflater = LayoutInflater.from(getApplication());
//        menuView = (ViewGroup) getActivity().getLayoutInflater().inflate(
//                R.layout.runbill_item, null, true);
        bt_sure = (Button) menuView.findViewById(R.id.bt_sure);
        bt_acncle = (Button) menuView.findViewById(R.id.bt_cancel);
        name = (EditText) menuView.findViewById(R.id.itemname);
        ruanbill = (EditText) menuView.findViewById(R.id.clientbill);
        revname = (EditText) menuView.findViewById(R.id.revname);
        revman = (EditText) menuView.findViewById(R.id.revman);
        backdate = (EditText) menuView.findViewById(R.id.backdate);
        backbillnum = (EditText) menuView.findViewById(R.id.backbillnum);
        backflag = (RadioButton) menuView.findViewById(R.id.bakbill_flag);
        date = (ImageButton) menuView.findViewById(R.id.backdate_ico);

        name.setText(runbill.getCustname());
        ruanbill.setText(runbill.getClientbill());
        revname.setText(runbill.getRecvname());
        revman.setText(runbill.getRecvman() + "(" + runbill.getRecvtel() + ")");


        bt_sure.setOnClickListener(this);
        bt_acncle.setOnClickListener(this);
        date.setOnClickListener(this);
        //设置popuwindow视图的焦点和触摸
        menuView.setFocusable(true);
        menuView.setFocusableInTouchMode(true);
        //设置back事件
        menuView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        popupWindow.dismiss();
                        return true;
                    }
                }
                return false;
            }
        });
        backdate.setOnClickListener(this);
        popupWindow = new PopupWindow(menuView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAtLocation(view.findViewById(R.id.fragment_signin), Gravity.CENTER, 0, 0);
        //设置popudowin焦点和触摸
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        //设置透明背景
//        backgroundAlpha(0.5f);
//
        popupWindow.update();
//        //popWindow消失监听方法
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//
//            @Override
//            public void onDismiss() {
//                backgroundAlpha(1f);
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_cancel:
                popupWindow.dismiss();
                break;
            case R.id.bt_sure:
                backbillin();
                break;
            case R.id.backdate:
                settime();
                break;
            case R.id.backdate_ico:
                settime();
                break;
        }
    }

    private void backbillin() {
        Map<String, String> params = new HashMap<>();
        String backstr = null;
        if (backflag.isChecked()) {
            backstr = "1";
        } else {
            backstr = "0";
        }
        params.put("id", allid + "");
        params.put("backdate", backdate.getText().toString().trim());
        params.put("backnum", backbillnum.getText().toString().trim());
        params.put("backflag", backstr + "");
        params.put("input_flag", inputflag.toString().trim());
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_saveSendBack, params, new OkHttpClientManager.ResultCallback<RunbackModel>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(RunbackModel response) {
                if (response != null) {
                    runbill = response;
                    popupWindow.dismiss();

                }
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            inidtata();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        inidtata();
    }

    private void settime() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.show();
        DatePicker picker = new DatePicker(getApplication());
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        picker.setDate(year, month);
        picker.setMode(DPMode.SINGLE);
        picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                String dates = Helper.getday(date);
                backdate.setText(dates);
                dialog.dismiss();
            }
        });
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setContentView(picker, params);
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

}
