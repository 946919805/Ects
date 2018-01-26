package com.gzdc.ecar.Adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.gzdc.ecar.R;
import com.gzdc.ecar.Until.BaiduMapUntil;

import java.util.List;

/**
 * Created by pzn on 2017/1/18 0018.
 */

public class OfflinechildAdpter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<MKOLSearchRecord> childCities;

    public OfflinechildAdpter(Context context, List<MKOLSearchRecord> datas) {
        super();
        mContext = context;
        mInflater = LayoutInflater.from(context);
        childCities = datas;
    }

    @Override
    public int getCount() {
        return childCities.size();
    }

    @Override
    public Object getItem(int pos) {
        return childCities.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup viewGroup) {
        ViewHolder vHolder = null;
        if (convertView == null) {
            vHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.offlineitem, null);
            vHolder.city = (TextView) convertView.findViewById(R.id.city);
            vHolder.size = (TextView) convertView.findViewById(R.id.size);
            vHolder.state = (TextView) convertView.findViewById(R.id.state);
            vHolder.down = (ImageView) convertView.findViewById(R.id.downmap);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        vHolder.city.setText(childCities.get(pos).cityName);
        if (childCities.get(pos).size == 0) {
            vHolder.down.setVisibility(View.GONE);
            vHolder.state.setText("已下载");
        } else {
            vHolder.size.setText(BaiduMapUntil.formatDataSize(childCities.get(pos).size) + "");
            vHolder.down.setVisibility(View.VISIBLE);
            vHolder.state.setVisibility(View.GONE);
        }

        return convertView;
    }

    class ViewHolder {
        TextView city;
        TextView size;
        TextView state;
        ImageView down;

    }


}
