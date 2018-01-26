package com.gzdc.ecar.Fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gzdc.ecar.Activity.Login_Activity;
import com.gzdc.ecar.Activity.OffLineActivity;
import com.gzdc.ecar.Activity.TaskActivity;
import com.gzdc.ecar.Activity.UplodingcardActivity;
import com.gzdc.ecar.R;
import com.gzdc.ecar.Until.PreferencesTools;
import com.gzdc.ecar.View.PopupSendBill;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.gzdc.ecar.Until.PreferencesTools.IS_LOGIN;
import static com.gzdc.ecar.Until.PreferencesTools.USER_ACCOUNT;
import static com.gzdc.ecar.Until.PreferencesTools.VEHICLE;


public class MyFragment extends BaseFragment {

    @BindView(R.id.offlinemap)
    RelativeLayout offlinmap;
    @BindView(R.id.untask)
    RelativeLayout untask;
    @BindView(R.id.task)
    RelativeLayout task;
    @BindView(R.id.completetask)
    RelativeLayout completetask;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.logingout)
    Button logingout;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        initview();
        return view;
    }

    private void initview() {
        name.setText(PreferencesTools.getStringValue(USER_ACCOUNT, ""));
        phone.setText(PreferencesTools.getStringValue(VEHICLE, ""));
    }


    @OnClick({R.id.untask, R.id.task, R.id.completetask, R.id.offlinemap,R.id.logingout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.untask:
                forwardRight(TaskActivity.class);
                break;
            case R.id.task:
                forwardRight(UplodingcardActivity.class);
                break;
            case R.id.completetask:

                openpop();
                break;
            case R.id.offlinemap:
                forwardRight(OffLineActivity.class);
                break;
            case R.id.logingout:
                PreferencesTools.setBooleanValue(IS_LOGIN,false);
                forwardRight(Login_Activity.class);
                break;
        }
    }

    private void openpop() {
        PopupSendBill popupSendbill = new PopupSendBill(getActivity());
        /*popupSendbill.showAtLocation(view, Gravity.CENTER, 0, 0);
        //设置popupwin焦点和触摸
        popupSendbill.setFocusable(true);
        popupSendbill.setTouchable(true);
        popupSendbill.update();*/
        popupSendbill.showPopupWindow(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


}
