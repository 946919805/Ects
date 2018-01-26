package com.gzdc.ecar.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.squareup.okhttp.Request;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UplodingcardActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.id_card_front)
    ImageView idCardFront;
    @BindView(R.id.id_card_back)
    ImageView idCardBack;
    @BindView(R.id.drive_card_front)
    ImageView driveCardFront;
    @BindView(R.id.drive_card_back)
    ImageView driveVardBack;

    @BindView(R.id.uplodding)
    Button button2;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 1;
    //允许选择图片最大数
    private String TAG = "UplodingcardActivity";

    public static final int ID_CARD_FRONT = 100;
    public static final int ID_CARD_BACK = 101;
    public static final int DEICER_CARD_FRONT = 200;
    public static final int DEICER_CARD_BACK = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_uplodingcard);
        ButterKnife.bind(this);
        button2.setOnClickListener(this);
        initImagePicker();


    }


    @OnClick({R.id.id_card_front, R.id.id_card_back, R.id.drive_card_front, R.id.drive_card_back, R.id.uplodding})
    public void onClick(View view) {
        Intent intent = new Intent(this, ImageGridActivity.class);
        switch (view.getId()) {

            case R.id.id_card_front:
                ImagePicker.getInstance().setSelectLimit(1);

                startActivityForResult(intent, ID_CARD_FRONT);
                break;
            case R.id.id_card_back:

                ImagePicker.getInstance().setSelectLimit(1);

                startActivityForResult(intent, ID_CARD_BACK);
                break;
            case R.id.drive_card_front:
                ImagePicker.getInstance().setSelectLimit(1);

                startActivityForResult(intent, DEICER_CARD_FRONT);

                break;
            case R.id.drive_card_back:
                ImagePicker.getInstance().setSelectLimit(1);

                startActivityForResult(intent, DEICER_CARD_BACK);
                break;
            case R.id.uplodding:
                forwardRight(Login_Activity.class);
                finish();

                break;
        }
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
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回REQUEST_CODE_SELECTs
            if (data != null && requestCode == ID_CARD_FRONT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                ImagePicker.getInstance().getImageLoader().displayImage(this, images.get(0).path, idCardFront, 0, 0);
                idCardFront.setTag(images.get(0).path);
//   com.nostra13.universalimageloader.core.ImageLoader im=EcarApplication.getAppContext().getImageLoader();
                uploadimage("tms_vehicle", "cardimg1", images.get(0).path, images.get(0).name);

            } else if (data != null && requestCode == ID_CARD_BACK) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                ImagePicker.getInstance().getImageLoader().displayImage(this, images.get(0).path, idCardBack, 0, 0);
                uploadimage("tms_vehicle", "cardimg2", images.get(0).path, images.get(0).name);
            } else if (data != null && requestCode == DEICER_CARD_FRONT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                ImagePicker.getInstance().getImageLoader().displayImage(this, images.get(0).path, driveCardFront, 0, 0);
                uploadimage("tms_vehicle", "driverimg", images.get(0).path, images.get(0).name);
            } else if (data != null && requestCode == DEICER_CARD_BACK) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                ImagePicker.getInstance().getImageLoader().displayImage(this, images.get(0).path, driveVardBack, 0, 0);
                uploadimage("tms_vehicle", "carimg", images.get(0).path, images.get(0).name);
            }

        }

    }

    private void uploadimage(String tablename, String filefield, String file, String name) {

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Map<String, String> params = new HashMap<>();
        String strbase64 = null;
        try {
            strbase64 = Helper.toBase64(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        params.put("id", id);
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
}

