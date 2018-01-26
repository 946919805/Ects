

package com.gzdc.ecar.Activity;

import android.os.Bundle;

import com.gzdc.ecar.Model.CarTastAction_showUndoneTask;
import com.gzdc.ecar.R;
import com.gzdc.ecar.config.confing;
import com.gzdc.ecar.conmon.BaseActivity;
import com.gzdc.ecar.conmon.OkHttpClientManager;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

public class TaskActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        initview();
        initdata();

    }

    private void initview() {
        showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("", "");

        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_showUndoneTask, params, new OkHttpClientManager.ResultCallback<CarTastAction_showUndoneTask>() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(CarTastAction_showUndoneTask response) {

dissmissProgress();
                    }
                }
        );

    }

    private void initdata() {

    }
}



