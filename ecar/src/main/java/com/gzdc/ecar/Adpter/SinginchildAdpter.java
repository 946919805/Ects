package com.gzdc.ecar.Adpter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.gzdc.ecar.Model.SingInModel;
import com.gzdc.ecar.Model.TaskModel;
import com.gzdc.ecar.R;

import java.util.List;

/**
 * Created by pengzhuoneng on 2017/3/1.
 */

public class SinginchildAdpter extends BaseAdapter implements View.OnClickListener {
    private Context mcontext;
    private LayoutInflater mInflater;
    private List<SingInModel.TaskBean.AddressBean> datas;

    public SinginchildAdpter(Context context, List<SingInModel.TaskBean.AddressBean> datas, SingforAdpter.Callback callback) {
        this.datas = datas;
        this.mcontext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mCallback = callback;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
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
            convertView = mInflater.inflate(R.layout.singin_child_item, null);
            vHolder.clientbill= (TextView) convertView.findViewById(R.id.clientbill);
            vHolder.linkman = (TextView) convertView.findViewById(R.id.linkman);
            vHolder.linktel = (TextView) convertView.findViewById(R.id.linktel);
            vHolder.address = (TextView) convertView.findViewById(R.id.address);
            vHolder.sign_for = (Button) convertView.findViewById(R.id.sign_for);
            vHolder.uploding_bill = (Button) convertView.findViewById(R.id.uploding_bill);
            vHolder.back_bill = (Button) convertView.findViewById(R.id.back_bill);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        SingInModel.TaskBean.AddressBean tempEntity = (SingInModel.TaskBean.AddressBean) datas.get(position);
        vHolder.clientbill.setText(tempEntity.getClientbill());
        vHolder.linkman.setText(tempEntity.getLinkman());
        vHolder.linktel.setText(tempEntity.getLinktel());
        vHolder.address.setText(tempEntity.getAddress());
        vHolder.sign_for.setOnClickListener(this);

        vHolder.sign_for.setTag(R.id.tag_first,tempEntity.getId());
        vHolder.sign_for.setTag(R.id.tag_second,tempEntity.getOrderid());
        vHolder.sign_for.setTag(R.id.tag_three,tempEntity.getInput_flag());
        vHolder.sign_for.setTag(R.id.tag_four,tempEntity.getFormtype());

        vHolder.uploding_bill.setTag(R.id.tag_first,tempEntity.getOrderid());
        vHolder.uploding_bill.setTag(R.id.tag_second,tempEntity.getInput_flag());
        vHolder.uploding_bill.setOnClickListener(this);
//       vHolder.back_bill.setTag();
        vHolder.back_bill.setTag(R.id.tag_first,tempEntity.getId());
        vHolder.back_bill.setTag(R.id.tag_second,tempEntity.getOrderid());
        vHolder.back_bill.setTag(R.id.tag_three,tempEntity.getInput_flag());

        vHolder.back_bill.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        Log.i("ssss", "onClick:+s ");
        mCallback.click(v);
    }

    private SingforAdpter.Callback mCallback;

    class ViewHolder {
        TextView linkman, linktel, address,clientbill;
        Button sign_for, back_bill, uploding_bill;

    }

}
