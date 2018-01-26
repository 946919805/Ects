package com.gzdc.ecar.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gzdc.ecar.Model.Msg;
import com.gzdc.ecar.Model.ShowSendBill;
import com.gzdc.ecar.R;
import com.gzdc.ecar.Until.Helper;
import com.gzdc.ecar.config.confing;
import com.gzdc.ecar.conmon.BaseActivity;
import com.gzdc.ecar.conmon.OkHttpClientManager;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsinActivity extends BaseActivity {

    @BindView(R.id.cast_name)
    TextView castName;
    @BindView(R.id.linkman)
    TextView linkman;
    @BindView(R.id.out_name)
    TextView outName;
    @BindView(R.id.call_name)
    TextView callName;

    @BindView(R.id.standard)
    TextView standard;
    @BindView(R.id.can_num)
    TextView canNum;
    @BindView(R.id.sure_num)
    EditText sureNum;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.name)
    TextView name;
    private ShowSendBill ssb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsin);
        ButterKnife.bind(this);
        initdata();

    }

    private void initiew()throws NullPointerException {
        castName.setText(ssb.getCustname()+"");
        linkman.setText(ssb.getClientbill()+"");
        outName.setText(ssb.getRecvman()+"");
        callName.setText(ssb.getRecvname() +""+ "(" + ssb.getRecvtel() + "");
        name.setText(ssb.getItemname()+"");
        standard.setText(ssb.getRecvman()+"");
        canNum.setText(ssb.getItemnum()+"");
        sureNum.setText(ssb.getSignnum()+"");

    }

    private void initdata() {
        showProgress();
        Intent intent = getIntent();

        Map<String, String> params = new HashMap<>();
        params.put("orderid", intent.getStringExtra("orderid").toString().trim());
        params.put("input_flag", intent.getStringExtra("input_flag").toString().trim());
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_showSendBill1, params, new OkHttpClientManager.ResultCallback<ShowSendBill>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(ShowSendBill response) {

                ssb = response;
                initiew();
                dissmissProgress();
            }
        });
    }

    @OnClick(R.id.bt_login)
    public void onViewClicked() {
        singin();
    }

    private void singin() {
        showProgress();
        Intent intent = getIntent();
        Map<String, String> params = new HashMap<>();
        params.put("orderid", intent.getStringExtra("orderid").toString().trim());
        params.put("signnum",sureNum.getText().toString().trim());
        params.put("input_flag", intent.getStringExtra("input_flag").toString().trim());
        OkHttpClientManager.postAsyn(confing.path+confing.CarTastAction_sendBillSign1, params, new OkHttpClientManager.ResultCallback<Msg>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Msg response) {
                Helper.showMsg(getApplicationContext(), response.getMsg().toString().trim());
                dissmissProgress();
                finish();

            }
        });
    }
}
