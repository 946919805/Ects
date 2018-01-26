package com.gzdc.ecar.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.gzdc.ecar.Model.FileUpload;
import com.gzdc.ecar.Model.Msg;
import com.gzdc.ecar.R;
import com.gzdc.ecar.Until.GlideImageLoader;
import com.gzdc.ecar.Until.Helper;
import com.gzdc.ecar.config.confing;
import com.gzdc.ecar.conmon.BaseActivity;
import com.gzdc.ecar.conmon.OkHttpClientManager;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.imagepicker.view.CropImageView;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.gzdc.ecar.Activity.UplodingcardActivity.ID_CARD_FRONT;

/**
 * billtype为1
 */
public class DetailsActivity extends BaseActivity {


    @BindView(R.id.sendname)
    EditText sendname;
    @BindView(R.id.linkman)
    EditText linkman;
    @BindView(R.id.linktel)
    EditText linktel;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.recvname)
    EditText recvname;
    @BindView(R.id.recvman)
    EditText recvman;
    @BindView(R.id.recvtel)
    EditText recvtel;
    @BindView(R.id.recvaddr)
    EditText recvaddr;
    @BindView(R.id.itemname)
    EditText itemname;
    @BindView(R.id.itemnum)
    EditText itemnum;
    @BindView(R.id.runfee)
    EditText runfee;
    @BindView(R.id.sure_bill)
    Button sureBill;
    @BindView(R.id.cancel_bill)
    Button cancelBill;
    @BindView(R.id.custname)
    EditText custname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        initview();
        initImagePicker();
    }

    private void initview() {
        custname.setText(getIntent().getStringExtra("custname"));
        sendname.setText(getIntent().getStringExtra("sendname"));
        linkman.setText(getIntent().getStringExtra("linkman"));
        linktel.setText(getIntent().getStringExtra("linktel"));
        address.setText(getIntent().getStringExtra("address"));
    }

    private void Sure_Bill() {
        Map<String, String> params = new HashMap<>();
        params.put("sendname", sendname.getText().toString().trim());
        params.put("linkman", linkman.getText().toString().trim());
        params.put("linktel", linktel.getText().toString().trim());
        params.put("address", address.getText().toString().trim());
        params.put("recvname", recvname.getText().toString().trim());
        params.put("recvman", recvman.getText().toString().trim());
        params.put("recvtel", recvtel.getText().toString().trim());
        params.put("recvaddr", recvaddr.getText().toString().trim());
        params.put("itemname", itemname.getText().toString().trim());
        params.put("itemnum", itemnum.getText().toString().trim());
        params.put("runfee", runfee.getText().toString().trim());
        params.put("id", getIntent().getStringExtra("id"));
        params.put("orderid", getIntent().getStringExtra("orderid"));
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_saveOrderBill, params, new OkHttpClientManager.ResultCallback<Msg>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Msg response) {
                if (response.isIsSuccess()) {
//                    Intent intent = new Intent(getApplication(), ImageGridActivity.class);
//
//                    ImagePicker.getInstance().setSelectLimit(1);
//                    startActivityForResult(intent, ID_CARD_FRONT);
                    Helper.showMsg(getApplicationContext(), response.getMsg().trim().toString());
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回REQUEST_CODE_SELECTs
            if (data != null && requestCode == ID_CARD_FRONT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
//                ImagePicker.getInstance().getImageLoader().displayImage(this, images.get(0).path, idCardFront, 0, 0);
//   com.nostra13.universalimageloader.core.ImageLoader im=EcarApplication.getAppContext().getImageLoader();
                uplodingimage("tms_plancar_d", "loadimg", images.get(0).path, images.get(0).name);

            }

        }

    }

    private void uplodingimage(String tablename, String filefield, String path, String name) {
        Map<String, String> params = new HashMap<>();
        String strbase64 = null;
        try {
            strbase64 = Helper.toBase64(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        params.put("id", getIntent().getStringExtra("id"));
        params.put("tablename", tablename);
        params.put("filefield", filefield);
        params.put("filename", name);
        params.put("filestr", strbase64 + "");
        OkHttpClientManager.postAsyn(confing.path + confing.FileUpAction_appUpLoad, params, new OkHttpClientManager.ResultCallback<FileUpload>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(FileUpload response) {
                if (response.isSuccess()) {
                    Helper.showMsg(getApplication(), response.getMsg());
                } else {
                    Helper.showMsg(getApplication(), response.getMsg() + "请重新上传");
                }

            }
        });
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {

            }

            @Override
            public void clearMemoryCache() {

            }
        });
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(false);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    @OnClick({R.id.sure_bill, R.id.cancel_bill})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sure_bill:
                Sure_Bill();
                break;
            case R.id.cancel_bill:
                cancelbill();
                break;
        }
    }

    private void cancelbill() {
        Map<String, String> params = new HashMap<>();
        params.put("id", getIntent().getStringExtra("id"));
        params.put("orderid", getIntent().getStringExtra("orderid"));
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_cancelOrderBill, params, new OkHttpClientManager.ResultCallback<Msg>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Msg response) {
                if (response.isIsSuccess()) {
                    finish();
                }
            }
        });
    }

}
