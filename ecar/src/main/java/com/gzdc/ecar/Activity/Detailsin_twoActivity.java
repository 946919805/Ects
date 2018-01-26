package com.gzdc.ecar.Activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.gzdc.ecar.Adpter.DetailsAdpter;
import com.gzdc.ecar.Model.CarTastAction_showSendBill2;
import com.gzdc.ecar.Model.FileUpload;
import com.gzdc.ecar.Model.Msg;
import com.gzdc.ecar.R;
import com.gzdc.ecar.Until.GlideImageLoader;
import com.gzdc.ecar.Until.Helper;
import com.gzdc.ecar.config.confing;
import com.gzdc.ecar.conmon.BaseActivity;
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
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gzdc.ecar.Activity.UplodingcardActivity.ID_CARD_FRONT;

public class Detailsin_twoActivity extends BaseActivity implements OnClickListener {

    @BindView(R.id.custname)
    TextView custname;
    @BindView(R.id.clientbill)
    TextView clientbill;
    @BindView(R.id.recvman)
    TextView recvman;
    @BindView(R.id.recvname)
    TextView recvname;
    @BindView(R.id.goosd_item)
    ListView goosdItem;
    private CarTastAction_showSendBill2 bill;
    private DetailsAdpter madpter;
    private int itempos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datails_two);
        ButterKnife.bind(this);
        initdata();


    }


    private void initdata() {
        showProgress();
        bill = new CarTastAction_showSendBill2();
        Map<String, String> params = new HashMap<>();
        params.put("input_flag", getIntent().getStringExtra("input_flag"));
        params.put("orderid", getIntent().getStringExtra("orderid"));
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_showSendBill2, params, new OkHttpClientManager.ResultCallback<CarTastAction_showSendBill2>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(CarTastAction_showSendBill2 response) {
                if (response != null) {
                    bill = response;
                    initview();
                    dissmissProgress();
                }
            }
        });
    }

    private void initview() {
        custname.setText(bill.getCustname().toString().trim());
        clientbill.setText(bill.getClientbill().toString().trim());
        recvman.setText(bill.getRecvman().toString().trim());
        recvname.setText(bill.getCustname().toString().trim() + "(" + bill.getRecvtel().toString().trim() + ")");
        madpter = new DetailsAdpter(getApplicationContext(), bill.getItemlist());
        goosdItem.setAdapter(madpter);
        goosdItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itempos=position;
                openpopuwindow(position);
            }
        });
    }

    private Button bt_sure, bt_cancle;
    private ViewGroup menuView;
    private EditText itemname, standard, itemnum, signnum;
    private PopupWindow popupWindow;

    private void openpopuwindow(int pos) {
        LayoutInflater mLayoutInflater = LayoutInflater.from(getApplicationContext());
        menuView = (ViewGroup) mLayoutInflater.inflate(
                R.layout.activity_addres, null, true);
        bt_sure = (Button) menuView.findViewById(R.id.bt_sure);
        bt_cancle = (Button) menuView.findViewById(R.id.bt_cancel);
        itemname = (EditText) menuView.findViewById(R.id.itemname);
        standard = (EditText) menuView.findViewById(R.id.standard);
        itemnum = (EditText) menuView.findViewById(R.id.itemnum);
        signnum = (EditText) menuView.findViewById(R.id.signnum);
        bt_sure.setOnClickListener(this);
        bt_cancle.setOnClickListener(this);
        //设置popuwindow视图的焦点和触摸
        menuView.setFocusable(true);
        menuView.setFocusableInTouchMode(true);
        itemname.setText(bill.getItemlist().get(pos).getItemname());
        standard.setText(bill.getItemlist().get(pos).getStandard());
        itemnum.setText(bill.getItemlist().get(pos).getItemnum());
        signnum.setText(bill.getItemlist().get(pos).getSignnum());
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
        popupWindow = new PopupWindow(menuView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAtLocation(Detailsin_twoActivity.this.findViewById(R.id.activity_datails_two), Gravity.CENTER, 0, 0);
        //设置popudowin焦点和触摸
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);

        //设置透明背景
        backgroundAlpha(0.3f);
        popupWindow.update();
        //popWindow消失监听方法
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_sure:
                upin();
                break;
            case R.id.bt_cancel:
                popupWindow.dismiss();
                break;


        }
    }

    private void upin() {showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("orderid",bill.getItemlist().get(itempos).getId());
        params.put("signnum", signnum.getText().toString().trim());
        params.put("input_flag", getIntent().getStringExtra("input_flag"));
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_sendBillSign2, params, new OkHttpClientManager.ResultCallback<Msg>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Msg response) {
                if (response.isIsSuccess()) {
                    initdata();
                    Helper.showMsg(getApplicationContext(), response.getMsg().toString().trim());
                    popupWindow.dismiss();
                    dissmissProgress();
                }
            }
        });
    }



}
