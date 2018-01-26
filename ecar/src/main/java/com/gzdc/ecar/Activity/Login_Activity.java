package com.gzdc.ecar.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gzdc.ecar.MainActivity;
import com.gzdc.ecar.Model.Msg;
import com.gzdc.ecar.Model.User_modle;
import com.gzdc.ecar.R;
import com.gzdc.ecar.Until.Helper;
import com.gzdc.ecar.Until.PreferencesTools;
import com.gzdc.ecar.config.confing;
import com.gzdc.ecar.conmon.BaseActivity;
import com.gzdc.ecar.conmon.OkHttpClientManager;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.gzdc.ecar.Until.PreferencesTools.IS_LOGIN;
import static com.gzdc.ecar.Until.PreferencesTools.PASSWORD;
import static com.gzdc.ecar.Until.PreferencesTools.USER;
import static com.gzdc.ecar.Until.PreferencesTools.USER_ACCOUNT;
import static com.gzdc.ecar.Until.PreferencesTools.VEHICLE;

public class Login_Activity extends BaseActivity {

    @BindView(R.id.Wear_login_user)
    EditText WearLoginUser;
    @BindView(R.id.Wear_login_pass)
    EditText WearLoginPass;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_register)
    Button btRegister;
    String password, account;
    private Boolean issure = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        WearLoginUser.setText(PreferencesTools.getStringValue(USER,""));
        WearLoginPass.setText(PreferencesTools.getStringValue(PASSWORD,""));

    }

    @OnClick({R.id.bt_login, R.id.bt_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                showProgress();
                login();
                break;
            case R.id.bt_register:
                forwardRight(RegisterActivity.class);
                break;
        }
    }

    private void login() {
        password = WearLoginPass.getText().toString();
        account = WearLoginUser.getText().toString();
        Map<String, String> params = new HashMap<>();
        params.put("usercode", account);
        params.put("password", password);
        OkHttpClientManager.postAsyn(confing.path + confing.login, params, new OkHttpClientManager.ResultCallback<User_modle>() {
            @Override
            public void onError(Request request, Exception e) {
                dissmissProgress();
            }

            @Override
            public void onResponse(User_modle model) {
                //成功登录“

                if (model.isIsSuccess()) {
                    //保存密码及登录状态
                    PreferencesTools.setBooleanValue(IS_LOGIN, true);
                    PreferencesTools.setStringValue(USER_ACCOUNT,model.getUserInfo().getUser_name());
                    PreferencesTools.setStringValue(VEHICLE,model.getUserInfo().getVehicle());
                    PreferencesTools.setStringValue(USER,account);
                    PreferencesTools.setStringValue(PASSWORD,password);
                    dissmissProgress();
                    forwardRight(MainActivity.class);
                    finish();


                }else {
                    Helper.showMsg(getApplication(),model.getMsg().toString());
                    dissmissProgress();
                }
            }


        });

    }
}

