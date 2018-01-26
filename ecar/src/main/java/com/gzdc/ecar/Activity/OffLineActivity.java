package com.gzdc.ecar.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;
import com.gzdc.ecar.Adpter.OfflineAdpter;
import com.gzdc.ecar.R;
import com.gzdc.ecar.Until.Callbaciclick;
import com.gzdc.ecar.Until.Itemclick;
import com.gzdc.ecar.conmon.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OffLineActivity extends BaseActivity implements MKOfflineMapListener, Callbaciclick, Itemclick {
    @BindView(R.id.mapcity)
    ListView mapcity;
    @BindView(R.id.citylist)
    RadioButton citylist;
    @BindView(R.id.downlist)
    RadioButton downlist;
    @BindView(R.id.downlistview)
    ListView downlistview;
    private MKOfflineMap mOffline = null;
    private OfflineAdpter offadpter;
    private LocalMapAdapter locaadpter = null;
    private ArrayList<MKOLSearchRecord> records2;
    private ArrayList<MKOLUpdateElement> localMapList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off_line);
        ButterKnife.bind(this);
        mOffline = new MKOfflineMap();
        mOffline.init(this);
        initview();
    }

    private void initview() {
        localMapList = new ArrayList<>();
        //获取能离线城市列表
        records2 = mOffline.getOfflineCityList();
        offadpter = new OfflineAdpter(getApplicationContext(), records2, this, this);
        mapcity.setAdapter(offadpter);
        mapcity.setOnItemClickListener(new AdaptItemClick());

        localMapList = mOffline.getAllUpdateInfo();
//        locaadpter = new LocalMapAdapter(getApplicationContext(), localMapList, this);
        locaadpter = new LocalMapAdapter();
        downlistview.setAdapter(locaadpter);
        downlistview.setVisibility(View.GONE);
    }


    @Override
    public void onGetOfflineMapState(int type, int state) {
        switch (type) {
            case MKOfflineMap.TYPE_DOWNLOAD_UPDATE: {
                MKOLUpdateElement update = mOffline.getUpdateInfo(state);
                // 处理下载进度更新提示
                if (update != null) {
//                    stateView.setText(String.format("%s : %d%%", update.cityName,
//                            update.ratio));
                    updateView();
                }
            }
            break;
            case MKOfflineMap.TYPE_NEW_OFFLINE:
                // 有新离线地图安装
                Log.d("OfflineDemo", String.format("add offlinemap num:%d", state));
                break;
            case MKOfflineMap.TYPE_VER_UPDATE:
                // 版本更新提示
                // MKOLUpdateElement e = mOffline.getUpdateInfo(state);

                break;
            default:
                break;
        }
    }

    public String formatDataSize(int size) {
        String ret = "";
        if (size < (1024 * 1024)) {
            ret = String.format("%dK", size / 1024);
        } else {
            ret = String.format("%.1fM", size / (1024 * 1024.0));
        }
        return ret;
    }

    @Override
    public void click(View v) {
        int id;
        switch (v.getId()) {

            case R.id.downmap:
                id = (int) v.getTag();
                start(id);
                updateView();
                break;
            case R.id.remove:
                id = (int) v.getTag();
                remove(id);
                updateView();
                break;

        }

    }

    @OnClick({R.id.citylist, R.id.downlist})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.citylist:
                mapcity.setVisibility(View.VISIBLE);
                downlistview.setVisibility(View.GONE);
                updateView();
                break;
            case R.id.downlist:
                mapcity.setVisibility(View.GONE);
                downlistview.setVisibility(View.VISIBLE);
                updateView();
                break;
        }
    }

    //child item点击事件
    @Override
    public void onItemClick(int position) {

        start(position);

    }

    private class AdaptItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            if (OfflineAdpter.mParentItem == position && OfflineAdpter.mbShowChild) {
                OfflineAdpter.mbShowChild = false;
            } else {
                OfflineAdpter.mbShowChild = true;
            }
            OfflineAdpter.mParentItem = position;
            offadpter.notifyDataSetChanged();
        }
    }


    /**
     * 开始下载
     *
     * @param cityid
     */
    public void start(int cityid) {
        mOffline.start(cityid);
        Toast.makeText(this, "开始下载离线地图. cityid: " + cityid, Toast.LENGTH_SHORT)
                .show();

        updateView();
    }

    /**
     * 暂停下载
     *
     * @param view
     */
    public void stop(View view) {
        int cityid = Integer.parseInt(view.getTag().toString());
        mOffline.pause(cityid);
        Toast.makeText(this, "暂停下载离线地图. cityid: " + cityid, Toast.LENGTH_SHORT)
                .show();
        updateView();
    }

    /**
     * 删除离线地图
     *
     * @param cityid
     */
    public void remove(int cityid) {

        mOffline.remove(cityid);
        Toast.makeText(this, "删除离线地图. cityid: " + cityid, Toast.LENGTH_SHORT)
                .show();
        updateView();
    }

    /**
     * 更新状态显示
     */
    public void updateView() {
        localMapList.clear();
        localMapList = mOffline.getAllUpdateInfo();
        String ss = null;
        for (int i = 0; i < localMapList.size(); i++) {
            Log.i("sssssss", "updateView: " + localMapList.size() + "---------" + localMapList.get(i).cityName + "===");
        }

        locaadpter.notifyDataSetChanged();
    }

    public class LocalMapAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (localMapList == null) {
                return 0;
            }
            return localMapList.size();
        }

        @Override
        public Object getItem(int index) {
            return localMapList.get(index);
        }

        @Override
        public long getItemId(int index) {
            return index;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup arg2) {
            final MKOLUpdateElement e = (MKOLUpdateElement) getItem(position);
            ViewHolder vHolder = null;
            if (convertView == null) {
                vHolder = new ViewHolder();
                convertView = View.inflate(OffLineActivity.this,
                        R.layout.offline_localmap_list, null);
                vHolder.title = (TextView) convertView.findViewById(R.id.title);
                vHolder.update = (TextView) convertView.findViewById(R.id.update);
                vHolder.ratio = (TextView) convertView.findViewById(R.id.ratio);
                vHolder.remove = (Button) convertView.findViewById(R.id.remove);
                convertView.setTag(vHolder);
            } else {
                vHolder = (ViewHolder) convertView.getTag();
            }

            vHolder.ratio.setText(e.ratio + "" + "%");
            vHolder.title.setText(e.cityName);
            vHolder.remove.setTag(e.cityID);
            if (e.update) {
                vHolder.update.setText("可更新");
            } else {
                vHolder.update.setText("最新");
            }
            if (e.ratio != 100) {
                vHolder.remove.setText("下载");
            } else {
                vHolder.remove.setText("删除");
            }
            vHolder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (e.ratio != 100) {
                        int id = (int) view.getTag();
                        start(id);
                        updateView();
                    } else {
                        int id = (int) view.getTag();
                        remove(id);
                        updateView();
                    }
                }
            });
            return convertView;
        }

        class ViewHolder {
            Button remove;
            TextView title;
            TextView update;
            TextView ratio;
        }

    }
}
