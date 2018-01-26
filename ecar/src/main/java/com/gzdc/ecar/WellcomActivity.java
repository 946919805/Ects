package com.gzdc.ecar;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import com.gzdc.ecar.Activity.Login_Activity;
import com.gzdc.ecar.Until.PreferencesTools;
import com.gzdc.ecar.conmon.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.gzdc.ecar.Until.PreferencesTools.IS_LOGIN;

public class WellcomActivity extends BaseActivity {

    @BindView(R.id.in)
    Button in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcom);
        ButterKnife.bind(this);
        handler.postDelayed(delayRun, 2000);


    }

    private Handler handler = new Handler();

    /**
     * 延迟线程，看是否还有下一个字符输入
     */
    private Runnable delayRun = new Runnable() {

        @Override
        public void run() {
            //在这里调用服务器的接口，获取数据
//            getSearchResult(editString, "all", 1, "true");
            queryitem();
        }
    };

    private void queryitem() {
        boolean islogin = PreferencesTools.getBooleanValue(IS_LOGIN, false);
        if (islogin) {
            forwardRight(MainActivity.class);
        } else {
            forwardRight(Login_Activity.class);
        }
        finish();
    }

    @OnClick(R.id.in)
    public void onClick() {
        if (delayRun != null) {
            //每次editText有变化的时候，则移除上次发出的延迟线程
            handler.removeCallbacks(delayRun);
        }
        forwardRight(Login_Activity.class);
        finish();
    }
}