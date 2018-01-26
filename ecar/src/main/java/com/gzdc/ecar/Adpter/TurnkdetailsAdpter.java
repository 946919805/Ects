package com.gzdc.ecar.Adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gzdc.ecar.Model.CarTastAction_showSendBill2;
import com.gzdc.ecar.Model.TurnkdetailsteoModel;
import com.gzdc.ecar.R;
import com.gzdc.ecar.conmon.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengzhuoneng on 2017/4/27.
 */

public class TurnkdetailsAdpter extends BaseAdapter{
    public static int mParentItem = -1;
    public static boolean mbShowChild = false;
    private LayoutInflater inflater;
    private Context mContext;
    private LayoutInflater mInflater;
    private List<TurnkdetailsteoModel.ItemlistBean> mDatas = new ArrayList<>();
    public TurnkdetailsAdpter(Context context,List<TurnkdetailsteoModel.ItemlistBean> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        if (datas != null) {
            mDatas = datas;
        }
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vHolder = null;

        if (convertView == null) {
            vHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.detailsintwo_item, null);
            vHolder.itemname = (TextView) convertView.findViewById(R.id.itemname);
            vHolder.standard = (TextView) convertView.findViewById(R.id.standard);
            vHolder.itemnum = (TextView) convertView.findViewById(R.id.itemnum);
            vHolder.signnum = (TextView) convertView.findViewById(R.id.signnum);

            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        TurnkdetailsteoModel.ItemlistBean   tempEntity =  mDatas.get(position);
        vHolder.itemname.setText(tempEntity.getItemname());
        vHolder.standard.setText(tempEntity.getStandard());
        vHolder.itemnum.setText(tempEntity.getItemnum() );
        vHolder.signnum.setText(tempEntity.getSignnum());

        return convertView;
    }
    class ViewHolder {

        TextView itemname;
        TextView standard;
        TextView itemnum;
        TextView signnum;

    }

}
