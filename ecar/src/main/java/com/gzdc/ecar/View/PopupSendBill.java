package com.gzdc.ecar.View;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

;

import com.gzdc.ecar.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopupSendBill extends PopupWindow{
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    private View conentView;

    public PopupSendBill(final Activity context) {

        conentView =  context.getLayoutInflater().inflate(R.layout.activity_popup, null);
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //conentView = inflater.inflate(R.layout.activity_popup, null);
        //设置popuwindow视图的焦点和触摸
        ButterKnife.bind(this, conentView);
        //设置back事件
        conentView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        PopupSendBill.this.dismiss();
                        return true;
                    }
                }
                return false;
            }
        });

        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
//        this.setFocusable(true);
//        conentView.setFocusableInTouchMode(true);
        // 刷新状态
        this.update();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupSendBill.this.dismiss();
            }
        });
    }



    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.CENTER, 0, 0);
        } else {
            this.dismiss();
        }
    }
}


