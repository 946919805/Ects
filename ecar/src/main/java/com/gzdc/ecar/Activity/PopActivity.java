package com.gzdc.ecar.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gzdc.ecar.Model.Selectcode;
import com.gzdc.ecar.R;
import com.gzdc.ecar.config.confing;
import com.gzdc.ecar.conmon.BaseActivity;
import com.gzdc.ecar.conmon.OkHttpClientManager;
import com.gzdc.ecar.conmon.spiner.AbstractSpinerAdapter;
import com.gzdc.ecar.conmon.spiner.SpinerPopWindow;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopActivity extends BaseActivity {

    @BindView(R.id.codeone)
    TextView codeone;
    @BindView(R.id.code)
    TextView code;
    @BindView(R.id.areacode)
    TextView areacode;
    @BindView(R.id.input_sure)
    Button inputSure;
    @BindView(R.id.input_cancel)
    Button inputCancel;
    private List<Selectcode.ListBean> selecto, selectt, selects;
    private int age = 1;
    private List listo, listt, lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        ButterKnife.bind(this);
        initdata(1, "");
    }

    @OnClick({R.id.codeone, R.id.code, R.id.areacode, R.id.input_sure, R.id.input_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.codeone:
                if (listo != null) {
                    showSpinWindow();
                }
                break;
            case R.id.code:
                if (listt != null) {
                    showSpinWindowt();
                }

                break;
            case R.id.areacode:
                if (lists != null) {
                    showSpinWindows();
                }
                break;
            case R.id.input_sure:
                Intent i = new Intent();
                int type = 1;
                if (TextUtils.isEmpty(areacode.getText())) {
                    i.putExtra("netcode", code.getTag() + "");
                    i.putExtra("netname", code.getText() + "");
                    type = 1;
                } else {
                    i.putExtra("netcode", areacode.getTag() + "");
                    i.putExtra("netname", areacode.getText() + "");
                    type = 2;
                }
                i.putExtra("nettype", type + "");
                setResult(1, i);
                finish();
                break;
            case R.id.input_cancel:
                finish();
                break;
        }
    }


    private void initdata(int i, String id) {
        Map<String, String> params = new HashMap<>();
        params.put("menustep", i + "");
        params.put("id", id);

        OkHttpClientManager.postAsyn(confing.path + confing.selectcode, params, new OkHttpClientManager.ResultCallback<Selectcode>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Selectcode response) {
                switch (age) {
                    case 1:
                        selecto = response.getList();
                        initspinero();
                        break;
                    case 2:
                        selectt = response.getList();
                        initspinert();
                        break;
                    case 3:
                        selects = response.getList();
                        initspiners();
                        break;

                }

            }
        });

    }

    SpinerPopWindow SpinerPopWindowo, SpinerPopWindowt, SpinerPopWindows;

    private void showSpinWindow() {
        Log.e("", "showSpinWindow");
        SpinerPopWindowo.setWidth(codeone.getWidth());
        SpinerPopWindowo.showAsDropDown(codeone);
    }

    private void showSpinWindowt() {
        Log.e("", "showSpinWindow");
        SpinerPopWindowt.setWidth(code.getWidth());
        SpinerPopWindowt.showAsDropDown(code);
    }

    private void showSpinWindows() {
        Log.e("", "showSpinWindow");
        SpinerPopWindows.setWidth(areacode.getWidth());
        SpinerPopWindows.showAsDropDown(areacode);
    }


    private void initspiners() {
        lists = new ArrayList();
        if (selects != null) {
            for (int i = 0; i < selects.size(); i++) {
                lists.add(selects.get(i).getNetname());

            }
            areacode.setText(selects.get(0).getNetname() + "");
            areacode.setTag(selects.get(0).getNetcode());
        }


        SpinerPopWindows = new SpinerPopWindow(this);
        SpinerPopWindows.refreshData(lists, 0);
        SpinerPopWindows.setItemListener(new AbstractSpinerAdapter.IOnItemSelectListener() {
            @Override
            public void onItemClick(int pos) {
                areacode.setText(selects.get(pos).getNetname() + "");
                areacode.setTag(selects.get(pos).getNetcode());


            }
        });
    }

    private void initspinert() {
        listt = new ArrayList();
        if (selectt != null) {
            for (int i = 0; i < selectt.size(); i++) {
                listt.add(selectt.get(i).getNetname());

            }
            code.setText(selectt.get(0).getNetname() + "");
            code.setTag(selectt.get(0).getNetcode());
            age = 3;
            initdata(3, selectt.get(0).getId());
        }


        SpinerPopWindowt = new SpinerPopWindow(this);
        SpinerPopWindowt.refreshData(listt, 0);
        SpinerPopWindowt.setItemListener(new AbstractSpinerAdapter.IOnItemSelectListener() {
            @Override
            public void onItemClick(int pos) {
                code.setText(selectt.get(pos).getNetname() + "");
                code.setTag(selectt.get(pos).getNetcode());
                age = 3;
                initdata(3, selectt.get(pos).getId());


            }
        });
    }

    private void initspinero() {
        listo = new ArrayList();
        if (selecto != null) {
            for (int i = 0; i < selecto.size(); i++) {
                listo.add(selecto.get(i).getNetname());

            }
            codeone.setText(selecto.get(0).getNetname() + "");
            codeone.setTag(selecto.get(0).getNetcode());
            age = 2;
            initdata(2, selecto.get(0).getId());
        }


        SpinerPopWindowo = new SpinerPopWindow(this);
        SpinerPopWindowo.refreshData(listo, 0);
        SpinerPopWindowo.setItemListener(new AbstractSpinerAdapter.IOnItemSelectListener() {
            @Override
            public void onItemClick(int pos) {
                codeone.setText(selecto.get(pos).getNetname() + "");
                codeone.setTag(selecto.get(pos).getNetcode());
                age = 2;
                initdata(2, selecto.get(pos).getId());
            }
        });
    }


}

