package com.gzdc.ecar.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.gzdc.ecar.Model.RegisterModel;
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

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.custname)
    EditText custname;
    @BindView(R.id.vehicle)
    EditText vehicle;
    @BindView(R.id.vehilen)
    EditText vehilen;
    @BindView(R.id.vehiwht)
    EditText vehiwht;
    @BindView(R.id.driver)
    EditText driver;
    @BindView(R.id.drivertel)
    EditText drivertel;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.sure_password)
    EditText surePassword;
    @BindView(R.id.savebtn)
    Button savebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

    }

    private void sumbit() {
        showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("bookcode", confing.bookcode);
        params.put("password", password.getText() + "");
        params.put("phoneno", drivertel.getText() + "");
        params.put("vehicle", vehicle.getText() + "");
        params.put("custname", custname.getText() + "");
        params.put("username", driver.getText() + "");
        params.put("vehilen", vehilen.getText() + "");
        params.put("vehiwht", vehiwht.getText() + "");
        OkHttpClientManager.postAsyn(confing.path + confing.register, params, new OkHttpClientManager.ResultCallback<RegisterModel>() {
            @Override
            public void onError(Request request, Exception e) {
                dissmissProgress();
Helper.showMsg(getApplication(),"数据异常");
            }

            @Override
            public void onResponse(RegisterModel response) {
                if (response.isSuccess()) {
//                    forwardRight(Login_Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id", response.getId().toString().trim());
                    forwardRight(UplodingcardActivity.class, bundle);
                    finish();
                    dissmissProgress();
                } else {
                    Helper.showMsg(getApplicationContext(), response.getMsg().toString().trim());
                    dissmissProgress();
                }
            }
        });
    }

    @OnClick(R.id.savebtn)
    public void onClick() {
        sumbit();
    }


}


