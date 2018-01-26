package com.gzdc.ecar.conmon;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.gzdc.ecar.R;


/**
 * Created by Administrator on 2016/6/2 0002.
 */
public class ProgressView extends Dialog {

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, int theme) {
        super(context, theme);
    }

    private TextView mTextView;
    private String loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar_view);
        mTextView = (TextView) findViewById(R.id.progress_bar_hint);
        if (!TextUtils.isEmpty(loading)) {
            mTextView.setText(loading);
        }

    }

    public void setLoadingHint(String loading) {
        this.loading = loading;
    }
}
