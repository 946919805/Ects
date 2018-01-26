package com.gzdc.ecar.Adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.gzdc.ecar.R;
import com.gzdc.ecar.Until.BaiduMapUntil;
import com.gzdc.ecar.Until.Callbaciclick;
import com.gzdc.ecar.Until.Itemclick;

import java.util.ArrayList;

/**
 * Created by pzn on 2017/1/18 0018.
 */

public class OfflineAdpter extends BaseAdapter implements View.OnClickListener {
    public static int mParentItem = -1;
    public static boolean mbShowChild = false;
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<MKOLSearchRecord> allCities;
    private Callbaciclick mback;
    private Itemclick itemclisk;

    //响应按钮点击事件,调用子定义接口，并传入View
    @Override
    public void onClick(View v) {
        mback.click(v);
    }

    public OfflineAdpter(Context context, ArrayList<MKOLSearchRecord> datas, Callbaciclick callback, Itemclick click) {
        super();
        mContext = context;
        mInflater = LayoutInflater.from(context);
        allCities = datas;
        mback = callback;
        itemclisk = click;
    }

    @Override
    public int getCount() {
        return allCities.size();
    }

    @Override
    public Object getItem(int i) {
        return allCities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder vHolder = null;
        if (convertView == null) {
            vHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.offlineitem, null);
            vHolder.city = (TextView) convertView.findViewById(R.id.city);
            vHolder.size = (TextView) convertView.findViewById(R.id.size);
            vHolder.state = (TextView) convertView.findViewById(R.id.state);
            vHolder.down = (ImageView) convertView.findViewById(R.id.downmap);
            vHolder.listViewItem = (ListView) convertView.findViewById(R.id.listview);

            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        vHolder.city.setText(allCities.get(position).cityName);
        vHolder.city.setTag(allCities.get(position).cityID);
        vHolder.size.setText(BaiduMapUntil.formatDataSize(allCities.get(position).size) + "");
        vHolder.state.setText(allCities.get(position).cityType + "");
        vHolder.down.setOnClickListener(this);
        vHolder.down.setTag(allCities.get(position).cityID);

        //点击那个弹出那个，如果已经弹出就收回子listview
        if (mParentItem == position && mbShowChild) {
            if (allCities.get(position).cityType == 1 && allCities.get(position).childCities != null) {
//            //子listview实在这里加载数据的
                final ArrayList<MKOLSearchRecord> aa = allCities.get(position).childCities;
                OfflinechildAdpter chideadpter = new OfflinechildAdpter(mContext, aa);
                vHolder.listViewItem.setAdapter(chideadpter);
                vHolder.listViewItem.setVisibility(View.VISIBLE);
                //子listview的点击监听
                vHolder.listViewItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        itemclisk.onItemClick(aa.get(position).cityID);

                    }
                });
            }
        } else {

            vHolder.listViewItem.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        TextView city;
        TextView size;
        TextView state;
        ImageView down;
        ListView listViewItem;

    }


}
