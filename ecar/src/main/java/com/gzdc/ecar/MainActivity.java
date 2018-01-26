package com.gzdc.ecar;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.gzdc.ecar.Fragment.HomeFragment;
import com.gzdc.ecar.Fragment.MyFragment;
import com.gzdc.ecar.Fragment.MytaskFragment;
import com.gzdc.ecar.Fragment.NearFragment;
import com.gzdc.ecar.Fragment.SigninFragment;
import com.gzdc.ecar.Until.GPSService;
import com.gzdc.ecar.conmon.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_menu)
    RadioGroup mRadioGroup;
    private HomeFragment homefra;
    private MyFragment myfra;
    private MytaskFragment mytastfra;
    private NearFragment nearfra;
    private SigninFragment siginfra;
    private static Boolean isExit = false;
    private GPSService service;
    private int currentTab;
    private int index = 0;
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    @BindView(R.id.index_tab_content)
    FrameLayout indexTabContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getFragmentManager();
        initView();
    }

    private void initView() {
//        service=new GPSService();
//        service.startService();
        setTabSelection(index);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("sssss", "setTabSelection:2 " + checkedId + "");
                for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
                    if (mRadioGroup.getChildAt(i).getId() == checkedId) {
                        setTabSelection(i);
                    }
                }
            }
        });
    }

    public void setTabSelection(int i) {
        currentTab = i;
        transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        ((RadioButton) mRadioGroup.getChildAt(currentTab)).setChecked(true);
        switch (currentTab) {
            case 0:
                if (homefra == null) {
                    homefra = new HomeFragment();
                    transaction.add(R.id.index_tab_content, homefra);
                } else {
                    transaction.show(homefra);
                }
                break;
            case 1:
                if (mytastfra == null) {
                    mytastfra = new MytaskFragment();
                    transaction.add(R.id.index_tab_content, mytastfra);
                } else {
                    transaction.show(mytastfra);
                }
                break;
            case 2:

                if (nearfra == null) {
                    nearfra = new NearFragment();
                    transaction.add(R.id.index_tab_content, nearfra);
                } else {
                    transaction.show(nearfra);
                }
                break;
            case 3:
                if (siginfra == null) {
                    siginfra = new SigninFragment();
                    transaction.add(R.id.index_tab_content, siginfra);
                } else {
                    transaction.show(siginfra);
                }
                break;
            case 4:
                Log.i("sssss", "setTabSelection:4 ");
                if (myfra == null) {
                    myfra = new MyFragment();
                    transaction.add(R.id.index_tab_content, myfra);
                } else {
                    transaction.show(myfra);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 将所有的Fragment都设置隐藏状态
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (myfra != null) {
            transaction.hide(myfra);
        }
        if (mytastfra != null) {
            transaction.hide(mytastfra);
        }
        if (nearfra != null) {
            transaction.hide(nearfra);
        }
        if (homefra != null) {
            transaction.hide(homefra);
        }
        if (siginfra != null) {
            transaction.hide(siginfra);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            esitBy2Click();
        }
        return false;
    }

    private void esitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true;//准备退出
            Toast.makeText(getApplication(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; //取消退出
                }
            }, 2000);//如果2秒钟内没有按下返回键，则启动定时器取消掉刚才的操作
        } else {
            finish();
            System.exit(0);
        }
    }
}
