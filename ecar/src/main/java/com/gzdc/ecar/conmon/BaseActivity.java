package com.gzdc.ecar.conmon;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.gzdc.ecar.R;
import com.nostra13.universalimageloader.core.ImageLoader;


public class BaseActivity extends Activity {
    public ProgressView mProgressView;
//    protected String TAG;
    /**
     * 图片下载
     */
    private ImageLoader imageLoader;

    private EcarApplication mAppliction;


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mAppliction = (EcarApplication) getApplication();
        imageLoader = mAppliction.getImageLoader();
//        TAG=getRunningActivityName();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void showProgress() {
        if (mProgressView == null) {
            mProgressView = new ProgressView(this, R.style.ProccessDialogStyle);
            mProgressView.show();
        }
    }

    public void dissmissProgress() {
        if (mProgressView != null) {
            mProgressView.dismiss();
        }
    }

    public <T> void forwardRight(Class<T> activity) {
        Intent direct = new Intent(this, activity);
        direct.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(direct);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }

    public <T> void forwardRight(Class<T> activity, Bundle bundle) {
        Intent direct = new Intent(this, activity);
        direct.putExtras(bundle);
        startActivity(direct);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }

    public <T> void forwardRight(Class<T> activity, Bundle bundle, int requestCode) {
        Intent direct = new Intent(this, activity);
        direct.putExtras(bundle);
        startActivityForResult(direct, requestCode);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
    private String getRunningActivityName(){
        ActivityManager activityManager=(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity=activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        return runningActivity;
    }
}