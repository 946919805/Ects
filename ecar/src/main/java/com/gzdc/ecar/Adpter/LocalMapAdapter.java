package com.gzdc.ecar.Adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.gzdc.ecar.R;
import com.gzdc.ecar.Until.Callbaciclick;

import java.util.ArrayList;

/**
 * 离线地图管理列表适配器
 */
public class LocalMapAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<MKOLUpdateElement> localMapList;
    private Callbaciclick mback;

    //响应按钮点击事件,调用子定义接口，并传入View

    public LocalMapAdapter(Context context, ArrayList<MKOLUpdateElement> datas, Callbaciclick callback) {
        super();
        mContext = context;
        mInflater = LayoutInflater.from(context);
        localMapList = datas;
        mback = callback;
    }

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
        MKOLUpdateElement e = (MKOLUpdateElement) getItem(position);
        ViewHolder vHolder = null;
        if (convertView == null) {
            vHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.offline_localmap_list, null);
            vHolder.title = (TextView) convertView.findViewById(R.id.title);
            vHolder.update = (TextView) convertView.findViewById(R.id.update);
            vHolder.ratio = (TextView) convertView.findViewById(R.id.ratio);
            vHolder.remove = (Button) convertView.findViewById(R.id.remove);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        vHolder.ratio.setTag(e.ratio + "%");
        vHolder.title.setText(e.cityName);
        vHolder.remove.setTag(e.cityID);
        if (e.update) {
            vHolder.update.setText("可更新");
        } else {
            vHolder.update.setText("最新");
        }
        vHolder.remove.setOnClickListener(this);

        return convertView;
    }


    @Override
    public void onClick(View view) {
        mback.click(view);
    }


    class ViewHolder {
        Button remove;
        TextView title;
        TextView update;
        TextView ratio;


    }

}

