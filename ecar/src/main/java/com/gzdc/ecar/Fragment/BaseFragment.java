package com.gzdc.ecar.Fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.gzdc.ecar.R;
import com.gzdc.ecar.conmon.EcarApplication;
import com.gzdc.ecar.conmon.ProgressView;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {
    protected Context context;
    public ViewGroup mContainer;
    private ProgressView mProgressView;
    private EcarApplication hApplication;
    /**
     * 图片下载
     */
    private ImageLoader imageLoader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mContainer = container;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        init();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hApplication = (EcarApplication) getActivity().getApplication();
        imageLoader = hApplication.getImageLoader();
    }

    public ImageLoader getImageLoader() {
        return ImageLoader.getInstance();
    }

    public EcarApplication getApplication() {
        return (EcarApplication) getActivity().getApplication();

    }

    public <T> void forwardRight(Class<T> activity) {
        Intent direct = new Intent(getActivity(), activity);
        direct.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(direct);
        getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public <T> void forwardRight(Class<T> activity, Bundle bundle) {
        Intent direct = new Intent(getActivity(), activity);
        direct.putExtras(bundle);
        startActivity(direct);
        getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }

    public void showProgress() {
if (mProgressView==null) {
    mProgressView = new ProgressView(getActivity(), R.style.ProccessDialogStyle);
    mProgressView.show();
    Log.i("progress", "showProgress: "+getActivity().getComponentName());
}    }

    public void dissmissProgress() {
        if (mProgressView!=null){
        mProgressView.dismiss();}
    }

    protected void init() {

    }

 /*   public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }*/
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        
    }
}
