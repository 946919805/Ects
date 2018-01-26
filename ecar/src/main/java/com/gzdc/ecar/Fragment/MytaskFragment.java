package com.gzdc.ecar.Fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.gzdc.ecar.Activity.AllocateMapActivity;
import com.gzdc.ecar.Activity.Login_Activity;
import com.gzdc.ecar.Activity.MapActivity;
import com.gzdc.ecar.Activity.MapTwoActivity;
import com.gzdc.ecar.Activity.TrunkMapActivity;
import com.gzdc.ecar.Adpter.ParentAdapt;
import com.gzdc.ecar.Adpter.Parentadpter;
import com.gzdc.ecar.Model.FileUpload;
import com.gzdc.ecar.Model.Msg;
import com.gzdc.ecar.Model.TaskModel;
import com.gzdc.ecar.R;
import com.gzdc.ecar.Until.GlideImageLoader;
import com.gzdc.ecar.Until.Helper;
import com.gzdc.ecar.Until.PreferencesTools;
import com.gzdc.ecar.Until.sendGDS;
import com.gzdc.ecar.config.confing;
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
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.path;
import static android.R.attr.wallpaperCloseEnterAnimation;
import static com.gzdc.ecar.Activity.UplodingcardActivity.ID_CARD_BACK;
import static com.gzdc.ecar.Activity.UplodingcardActivity.ID_CARD_FRONT;
import static com.gzdc.ecar.Until.PreferencesTools.CEHICLE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MytaskFragment extends BaseFragment implements Parentadpter.Callback {

    private ParentAdapt mParentAdapt;
    private Parentadpter mparentAdpter;
    @BindView(R.id.task)
    ListView task;
    private List<TaskModel.TaskBean> ta, tass;
    private sendGDS senGps;
    private String id = null;
    private int postion;
    private ArrayList<ImageItem> images;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mytask, container, false);

        ButterKnife.bind(this, view);
        initImagePicker();
        return view;
    }

    private void inidtata() {
        showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("id", "ssss");
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_itemScheTask, params, new OkHttpClientManager.ResultCallback<TaskModel>() {
            @Override
            public void onError(Request request, Exception e) {
                forwardRight(Login_Activity.class);

            }

            @Override
            public void onResponse(TaskModel response) {
                if (response != null && response.isIsSuccess()) {
                    ta = response.getTask();

                    PreferencesTools.setStringValue(CEHICLE, ta.get(0).getPlandate());
                    initView();

                }
                dissmissProgress();
            }
        });
    }

    private void initView() {
        mparentAdpter = new Parentadpter(getActivity(), ta, this);
        task.setAdapter(mparentAdpter);
        task.setOnItemClickListener(new AdaptItemClick());

        //长按listview
//		listView.setOnItemLongClickListener(listener);

    }

    //lsitview按钮点击事件
    @Override
    public void click(View v) {
        Bundle bundle1 = new Bundle();
        String i = v.getTag().toString();
        final int pos = Integer.parseInt(i);
        String billtype = ta.get(pos).getBilltype();
        switch (v.getId()) {
            case R.id.in_bill:
//接单确认
                car_sure(pos);
                break;
            case R.id.sure_bill:
                id = ta.get(pos).getId();
                //交单确认
                doneScheTask(pos);
                break;
            case R.id.cancle_bill:
                //拒绝接单
                cancelScheTask(pos);
                break;
            case R.id.daohan:

                switch (billtype) {

                    case "1":
                        bundle1.putString("bill", ta.get(pos).getBill());
                        forwardRight(MapTwoActivity.class, bundle1);
                        break;
                    case "4":
                        bundle1.putString("bill", ta.get(pos).getBill());
                        forwardRight(MapActivity.class, bundle1);
                        break;
                    case "z":
                        bundle1.putString("bill", ta.get(pos).getBill());
                        forwardRight(MapActivity.class, bundle1);
                        break;
                    default:
                        bundle1.putString("bill", ta.get(pos).getBill());
                        forwardRight(AllocateMapActivity.class, bundle1);
                        break;

                }


//                else if(ta.get(pos).getBilltype().equals("3")) {
//                   Bundle bundle = new Bundle();
//                   bundle.putString("id", ta.get(pos).getBill());
//                   forwardRight(MapActivity.class, bundle);
//               }else if(ta.get(pos).getBilltype().equals("4")){
//                   Bundle bundle = new Bundle();
//                   bundle.putString("id", ta.get(pos).getBill());
//                   forwardRight(MapActivity.class, bundle);}
//                else {
//                   Bundle bundle = new Bundle();
//                   bundle.putString("id", ta.get(pos).getBill());
//                   forwardRight(MapActivity.class, bundle);}
                break;
            case R.id.care_bill:
//发车确认
                showProgress();
//                startScheTask(pos);
//                id = ta.get(pos).getId();
//                postion = pos;
                break;
        }

    }

    private void car_sure(int pos) {
        showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("bill", ta.get(pos).getBill());
        params.put("billtype", ta.get(pos).getBilltype());
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_sureScheTask, params, new OkHttpClientManager.ResultCallback<
                Msg>() {
            @Override
            public void onError(Request request, Exception e) {
                dissmissProgress();
            }

            @Override
            public void onResponse(Msg response) {
                Helper.showMsg(getApplication(), response.getMsg() + "");
                dissmissProgress();
            }
        });
    }

    private void startScheTask(int i) {
        showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("bill", ta.get(i).getBill());
        params.put("billtype", ta.get(i).getBilltype());
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_startScheTask, params, new OkHttpClientManager.ResultCallback<
                Msg>() {
            @Override
            public void onError(Request request, Exception e) {
                dissmissProgress();
            }

            @Override
            public void onResponse(Msg response) {
                dissmissProgress();
                Helper.showMsg(getApplication(), response.getMsg() + "");
                Intent intent = new Intent(getApplication(), ImageGridActivity.class);
                ImagePicker.getInstance().setSelectLimit(1);
                startActivityForResult(intent, ID_CARD_FRONT);
            }
        });
    }

    private void doneScheTask(int i) {
        showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("bill", ta.get(i).getBill());
        params.put("billtype", ta.get(i).getBilltype());
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_doneScheTask, params, new OkHttpClientManager.ResultCallback<
                Msg>() {
            @Override
            public void onError(Request request, Exception e) {
                dissmissProgress();
            }

            @Override
            public void onResponse(Msg response) {
                Helper.showMsg(getApplication(), response.getMsg() + "");
                dissmissProgress();
                Intent intent = new Intent(getApplication(), ImageGridActivity.class);

                ImagePicker.getInstance().setSelectLimit(1);

                startActivityForResult(intent, ID_CARD_BACK);
            }
        });
    }

    private void cancelScheTask(int pos) {
        showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("bill", ta.get(pos).getBill());
        params.put("billtype", ta.get(pos).getBilltype());
        OkHttpClientManager.postAsyn(confing.path + confing.CarTastAction_cancelScheTask, params, new OkHttpClientManager.ResultCallback<
                Msg>() {
            @Override
            public void onError(Request request, Exception e) {
                dissmissProgress();
            }

            @Override
            public void onResponse(Msg response) {
                inidtata();
                dissmissProgress();

                Helper.showMsg(getApplication(), response.getMsg() + "");
            }
        });
    }


    private class AdaptItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            if (ParentAdapt.mParentItem == position && ParentAdapt.mbShowChild) {
                ParentAdapt.mbShowChild = false;
            } else {
                ParentAdapt.mbShowChild = true;
            }
            ParentAdapt.mParentItem = position;
            mParentAdapt.notifyDataSetChanged();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            inidtata();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        inidtata();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回REQUEST_CODE_SELECTs
             images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (data != null && requestCode == ID_CARD_FRONT) {
//   com.nostra13.universalimageloader.core.ImageLoader im=EcarApplication.getAppContext().getImageLoader();
                if (ta.get(postion).getBilltype().equals("z")) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            showProgress();
                    uploding("tms_runcont_h", "driverbegimg", images.get(0).path, images.get(0).name);
                        }
                    }, 1000);

                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            showProgress();
                    uploding("tms_plancar_h", "driverbegimg", images.get(0).path, images.get(0).name);
                        }
                    }, 1000);

                }
            } else if (data != null && requestCode == ID_CARD_BACK) {
                if (ta.get(postion).getBilltype().equals("z")) {
                    uploding("tms_runcont_h", "driverendimg", images.get(0).path, images.get(0).name);
                    inidtata();
//                    mparentAdpter.notify();
                } else {
                    uploding("tms_plancar_h", "driverendimg", images.get(0).path, images.get(0).name);
                    inidtata();
//                    mparentAdpter.notify();

                }
            }
        }

    }


    private void uploding(String tablename, String filefield, String file, String name) {
        showProgress();
        Map<String, String> params = new HashMap<>();
        String path1 = null;
        try {
            path1 = Helper.toBase64(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        params.put("id", id);
        params.put("tablename", tablename);
        params.put("filefield", filefield);
        params.put("filename", name);
        params.put("filestr", path1 + "");
        OkHttpClientManager.postAsyn(confing.path + confing.FileUpAction_appUpLoad, params, new OkHttpClientManager.ResultCallback<FileUpload>() {
            @Override
            public void onError(Request request, Exception e) {
                dissmissProgress();
            }

            @Override
            public void onResponse(FileUpload response) {
                if (response.isSuccess()) {
                    dissmissProgress();
                    Helper.showMsg(getApplication(), "上传成功");

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
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

}
