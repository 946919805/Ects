package com.gzdc.ecar.Adpter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gzdc.ecar.Model.TaskModel;
import com.gzdc.ecar.R;

import java.util.List;

/**
 * ��listview������
 *
 * @author mmsx
 */
public class ChildAdapter extends BaseArrayListAdapter {
    private boolean iisin = false;

    public ChildAdapter(Context context, List<TaskModel.TaskBean.AddressBean> datas, boolean isin) {
        super(context, datas);
        this.iisin = isin;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vHolder = null;
        Log.d("sssss", "嵌套第二层的 getView() called with: position = [" + position + "], convertView = [" + convertView + "], parent = [" + parent + "]");

        if (convertView == null) {
            vHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.activity_main_list_item_1, null);
            vHolder.clientbill = (TextView) convertView.findViewById(R.id.goodsname);
            vHolder.companyname = (TextView) convertView.findViewById(R.id.companyname);
            vHolder.name_phone = (TextView) convertView.findViewById(R.id.name);
            vHolder.address = (TextView) convertView.findViewById(R.id.address);
            vHolder.companyname_id = (TextView) convertView.findViewById(R.id.companyname_id);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        TaskModel.TaskBean.AddressBean tempEntity = (TaskModel.TaskBean.AddressBean) mDatas.get(position);
        vHolder.clientbill.setText(tempEntity.getClientbill());
        vHolder.companyname.setText(tempEntity.getCustname());
        vHolder.name_phone.setText(tempEntity.getLinkman() + "(" + tempEntity.getLinktel() + ")");
        vHolder.address.setText(tempEntity.getAddress());
        if (iisin) {
            vHolder.companyname_id.setText(R.string.receiving_party);
        } else {
            vHolder.companyname_id.setText(R.string.consigner);
        }
        return convertView;
    }

    class ViewHolder {
        TextView clientbill;
        TextView companyname;
        TextView name_phone;
        TextView address;
        TextView companyname_id;
    }
}
