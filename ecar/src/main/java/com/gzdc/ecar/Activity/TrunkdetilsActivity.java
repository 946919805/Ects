package com.gzdc.ecar.Activity;

import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gzdc.ecar.Model.Msg;
import com.gzdc.ecar.Model.ShowSendBill;
import com.gzdc.ecar.Model.TurnkdetailsModel;
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

public class TrunkdetilsActivity extends BaseActivity {

    @BindView(R.id.cast_name)
    TextView castName;
    @BindView(R.id.linkman)
    TextView linkman;
    @BindView(R.id.out_name)
    TextView outName;
    @BindView(R.id.call_name)
    TextView callName;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.standard)
    TextView standard;
    @BindView(R.id.can_num)
    TextView canNum;
    @BindView(R.id.sure_num)
    EditText sureNum;
    @BindView(R.id.bt_login)
    Button btLogin;
    private TurnkdetailsModel tmdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trunkdetils);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        showProgress();
        Intent intent = getIntent();

        Map<String, String> params = new HashMap<>();
        params.put("orderid", intent.getStringExtra("orderid").toString().trim());
        params.put("input_flag", intent.getStringExtra("input_flag").toString().trim());
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_showRunBill1, params, new OkHttpClientManager.ResultCallback<TurnkdetailsModel>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(TurnkdetailsModel response) {

                tmdata = response;
                initview();
                dissmissProgress();
            }
        });

    }

    private void initview() {
        castName.setText(tmdata.getCustname());
        linkman.setText(tmdata.getClientbill() + "");
        outName.setText(tmdata.getRecvman() + "");
        callName.setText(tmdata.getRecvname() + "" + "(" + tmdata.getRecvtel() + "");
        name.setText(tmdata.getItemname() + "");
        standard.setText(tmdata.getRecvman() + "");
        canNum.setText(tmdata.getItemnum() + "");
        sureNum.setText(tmdata.getSignnum() + "");
    }
    @OnClick(R.id.bt_login)
    public void onViewClicked() {
        singin();
    }

    private void singin() {
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
                finish();
            }
        });
    }
}
