package com.gzdc.ecar.Adpter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gzdc.ecar.Model.SingInModel;
import com.gzdc.ecar.Model.TaskModel;
import com.gzdc.ecar.R;
import com.gzdc.ecar.Until.Callbaciclick;

import java.util.List;

/**
 * Created by pengzhuoneng on 2017/3/1.
 */
public class SingforAdpter extends BaseAdapter implements View.OnClickListener ,Callbaciclick{
    protected Context mContext;
    protected LayoutInflater mInflater;
    private List<SingInModel.TaskBean> lsits;

    @Override
    public void onClick(View view) {
        mCallback.click(view);
    }

    @Override
    public void click(View v) {
        Log.i("sssss", "click: ");
    }

    public interface Callback {
        public void click(View v);
    }

    private Callback mCallback;

    public SingforAdpter(Context context, List<SingInModel.TaskBean> datas, Callback callback) {
        this.lsits = datas;
        this.mCallback = callback;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lsits.size();
    }

    @Override
    public Object getItem(int position) {
        return lsits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) throws NullPointerException {
        ViewHolder vHolder = null;
        if (convertView == null) {
            vHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.singin_item, null);
            vHolder.no = (TextView) convertView.findViewById(R.id.no);
//       vHolder.vehicle = (TextView) convertView.findViewById(R.id.vehicle);
            vHolder.address = (TextView) convertView.findViewById(R.id.address);
            vHolder.date = (TextView) convertView.findViewById(R.id.date);
            vHolder.listViewItem = (ListView) convertView.findViewById(R.id.listView_child);
            vHolder.ico= (ImageView) convertView.findViewById(R.id.image_ico);
            vHolder.ico.setAlpha(50);
//            vHolder.buttonStake = (Button) convertView.findViewById(R.id.start);start
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        SingInModel.TaskBean tempEntity = (SingInModel.TaskBean) lsits.get(position);
        vHolder.no.setText(tempEntity.getClientbill() + "");
//        vHolder.vehicle.setText(tempEntity.getVehicle()+"");
        vHolder.address.setText(tempEntity.getPlannetname());
        vHolder.date.setText(tempEntity.getPlandate()+"");
   if(tempEntity.getBilltype().equals("4")){
       vHolder.ico.setImageResource(R.mipmap.four_ico);
   }else {
       vHolder.ico.setImageResource(R.mipmap.z_ico);
   }

//        vHolder.typename.setText(tempEntity.getTypenamen());
//        if (tempEntity.getTaskstate().equals("0")) {
//            vHolder.buttonStake.setText("启动");
//        } else {
//            vHolder.buttonStake.setText("进入");
//        }
        //点击那个弹出那个，如果已经弹出就收回子listview
//        if (mParentItem == position && mbShowChild) {
//            //子listview实在这里加载数据的
        SinginchildAdpter tempAdapt = new SinginchildAdpter(mContext, tempEntity.getAddress(),this.mCallback);
        vHolder.listViewItem.setAdapter(tempAdapt);
//            vHolder.listViewItem.setVisibility(View.VISIBLE);
//
//            //子listview的点击监听
//            vHolder.listViewItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view,
//                                        int position, long id) {
//                    Toast.makeText(mContext, "Child Listview" + String.valueOf(position), Toast.LENGTH_SHORT).show();
//
//                }
//            });
//        } else {
//            vHolder.listViewItem.setVisibility(View.GONE);
//        }

//        vHolder.buttonStake.setOnClickListener(this);
        //记住是那个button事件
//        vHolder.buttonStake.setTag(position);

        return convertView;
    }

    class ViewHolder {
        TextView no;
        TextView date;
        TextView address;
        TextView typename;
        ListView listViewItem;
ImageView ico;
//        Button buttonStake;Buttons
    }
}