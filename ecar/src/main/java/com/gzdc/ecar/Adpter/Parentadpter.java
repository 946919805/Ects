package com.gzdc.ecar.Adpter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gzdc.ecar.Model.TaskModel;
import com.gzdc.ecar.R;
import com.gzdc.ecar.Until.Callbaciclick;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * Created by pengzhuoneng on 2017/4/19.
 */

public class Parentadpter extends BaseAdapter implements View.OnClickListener {
    public static int mParentItem = -1;
    public static boolean mbShowChild = false;
    private LayoutInflater inflater;
    private Context mContext;
    private LayoutInflater mInflater;
    private List<TaskModel.TaskBean> mDatas = new ArrayList<>();

    @Override
    public void onClick(View view) {
        mCallback.click(view);
    }

    public interface Callback {
        public void click(View v);
    }

    private Callback mCallback;

    public Parentadpter(Context context, List<TaskModel.TaskBean> datas, Callback callback) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mCallback = callback;
        if (datas != null) {
            mDatas = datas;
        }else {
            mDatas=null;
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
        ViewHolder1 vHolder = null;


        inflater = LayoutInflater.from(mContext);
        int type = getItemViewType(position);
        if (convertView == null) {
            vHolder = new ViewHolder1();
            convertView = mInflater.inflate(R.layout.activity_main_list_item, null);
            vHolder.driver = (TextView) convertView.findViewById(R.id.no);
//            vHolder.vehicle = (TextView) convertView.findViewById(R.id.vehicle);
            vHolder.status = (TextView) convertView.findViewById(R.id.status);
            vHolder.typename = (TextView) convertView.findViewById(R.id.typename);
//
            vHolder.listViewItem = (ListView) convertView.findViewById(R.id.listView_child);
            vHolder.cancle_bill = (TextView) convertView.findViewById(R.id.cancle_bill);
            vHolder.in_bill = (Button) convertView.findViewById(R.id.in_bill);
            vHolder.care_bill = (Button) convertView.findViewById(R.id.care_bill);
            vHolder.daohan = (Button) convertView.findViewById(R.id.daohan);
            vHolder.sure_bill = (Button) convertView.findViewById(R.id.sure_bill);
            vHolder.ico = (ImageView) convertView.findViewById(R.id.image_ico);
            vHolder.ico.setAlpha(50);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder1) convertView.getTag();

        }
        TaskModel.TaskBean tempEntity;
        ChildAdapter tempAdapt;
        String billtype = mDatas.get(position).getBilltype();
        tempEntity = (TaskModel.TaskBean) mDatas.get(position);
        vHolder.in_bill.setOnClickListener(this);
        //记住是那个button事件
        vHolder.in_bill.setTag(position);
        vHolder.care_bill.setOnClickListener(this);
        vHolder.cancle_bill.setOnClickListener(this);
        vHolder.cancle_bill.setTag(position);
        vHolder.care_bill.setTag(position);
        vHolder.daohan.setOnClickListener(this);
        vHolder.daohan.setTag(position);
        vHolder.sure_bill.setOnClickListener(this);
        vHolder.sure_bill.setTag(position);
        switch (billtype) {
            case "1":
                Log.i("ssss", "getView: 11111");
                vHolder.driver.setText(tempEntity.getClientbill() + "");
//        vHolder.vehicle.setText(tempEntity.getVehicle()+"");
                vHolder.status.setText(tempEntity.getPlannetname());
                vHolder.typename.setText(tempEntity.getPlandate());

                //点击那个弹出那个，如果已经弹出就收回子listview
//        if (mParentItem == position && mbShowChild) {
//            //子listview实在这里加载数据的
                tempAdapt = new ChildAdapter(mContext, tempEntity.getAddress(), true);
                vHolder.listViewItem.setAdapter(tempAdapt);
                vHolder.ico.setImageResource(R.mipmap.one_ico);
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

                break;
            case "2":
                Log.i("ssss", "getView: 22222");
                vHolder.driver.setText(tempEntity.getClientbill() + "");
                vHolder.status.setText(tempEntity.getPlannetname());
                vHolder.typename.setText(tempEntity.getPlandate());

                tempAdapt = new ChildAdapter(mContext, tempEntity.getAddress(), true);
                vHolder.listViewItem.setAdapter(tempAdapt);
                vHolder.ico.setImageResource(R.mipmap.two_ico);
                break;
            case "3":
                Log.i("ssss", "getView: 33333");
                vHolder.driver.setText(tempEntity.getClientbill() + "");
                vHolder.status.setText(tempEntity.getPlannetname());
                vHolder.typename.setText(tempEntity.getPlandate());

                tempAdapt = new ChildAdapter(mContext, tempEntity.getAddress(), false);
                vHolder.listViewItem.setAdapter(tempAdapt);
                vHolder.ico.setImageResource(R.mipmap.three_ico);

                break;
            case "4":
                Log.i("ssss", "getView: 4444444");
                vHolder.driver.setText(tempEntity.getClientbill() + "");
                vHolder.status.setText(tempEntity.getPlannetname());
                vHolder.typename.setText(tempEntity.getPlandate());

                tempAdapt = new ChildAdapter(mContext, tempEntity.getAddress(), false);
                vHolder.listViewItem.setAdapter(tempAdapt);
                vHolder.ico.setImageResource(R.mipmap.four_ico);


                break;
            case "z":
                Log.i("ssss", "getView: 222");
                vHolder.driver.setText(tempEntity.getClientbill() + "");
//        vHolder.vehicle.setText(tempEntity.getVehicle()+"");
                vHolder.status.setText(tempEntity.getPlannetname());
                vHolder.typename.setText(tempEntity.getPlandate());
                vHolder.ico.setImageResource(R.mipmap.z_ico);
                //点击那个弹出那个，如果已经弹出就收回子listview
//        if (mParentItem == position && mbShowChild) {
//            //子listview实在这里加载数据的
                tempAdapt = new ChildAdapter(mContext, tempEntity.getAddress(), false);
                vHolder.listViewItem.setAdapter(tempAdapt);

                break;


        }


        return convertView;
    }

    class ViewHolder1 {
        ImageView ico;
        TextView driver;
        TextView vehicle;
        TextView status;
        TextView typename;
        ListView listViewItem;
        TextView cancle_bill;
        Button in_bill;
        Button care_bill;
        Button daohan;
        Button sure_bill;
    }

}
