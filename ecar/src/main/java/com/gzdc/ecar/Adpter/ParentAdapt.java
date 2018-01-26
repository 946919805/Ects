package com.gzdc.ecar.Adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gzdc.ecar.Model.TaskModel;
import com.gzdc.ecar.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by pzn on 2017/1/12 0012.
 */

public class ParentAdapt extends BaseAdapter implements View.OnClickListener {

    public static int mParentItem = -1;
    public static boolean mbShowChild = false;
   private LayoutInflater inflater;
   private final int TYPE_1 = 0;
    private final int TYPE_2 = 1;
    final int TYPE_3 = 2;
    final int TYPE_4 = 3;
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

    public ParentAdapt(Context context, List<TaskModel.TaskBean> datas, Callback callback) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        if (datas != null) {
            mDatas = datas;
        }
    }

    @Override
    public int getItemViewType(int position) {

        if ( mDatas.get(position).getBilltype().equals("1"))
            return TYPE_1;
        else if (mDatas.get(position).getBilltype().equals("2"))
            return TYPE_2;
       else return TYPE_2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
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
        ViewHolder2 holder2 = null;

        inflater = LayoutInflater.from(mContext);
        int type=getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TYPE_1:
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
//                    vHolder.care_bill = (Button) convertView.findViewById(R.id.care_bill);
                    vHolder.daohan = (Button) convertView.findViewById(R.id.daohan);
                    vHolder.sure_bill = (Button) convertView.findViewById(R.id.sure_bill);
                    vHolder.ico = (ImageView) convertView.findViewById(R.id.image_ico);
                    vHolder.ico.setAlpha(50);
                    convertView.setTag(vHolder);
            break;
                case TYPE_2:
                    holder2 = new ViewHolder2();
                    convertView = mInflater.inflate(R.layout.activity_mian_list_item_two, null);
                    holder2.driver = (TextView) convertView.findViewById(R.id.no);
//            vHolder.vehicle = (TextView) convertView.findViewById(R.id.vehicle);
                    holder2.status = (TextView) convertView.findViewById(R.id.status);
                    holder2.typename = (TextView) convertView.findViewById(R.id.typename);

                    holder2.listViewItem = (ListView) convertView.findViewById(R.id.listView_child);
                    holder2.cancle_bill = (TextView) convertView.findViewById(R.id.cancle_bill_two);
                    holder2.in_bill = (Button) convertView.findViewById(R.id.in_bill_two);
                    holder2.care_bill = (Button) convertView.findViewById(R.id.care_bill_two);
                    holder2.daohan = (Button) convertView.findViewById(R.id.daohan_two);
//                    holder2.sure_bill = (Button) convertView.findViewById(R.id.sure_bill_two);
                    holder2.ico = (ImageView) convertView.findViewById(R.id.image_ico);
                    holder2.ico.setAlpha(50);
                    convertView.setTag(vHolder);
                    break;

            }

        } else {
            switch (type) {
                case TYPE_1:
                    vHolder = (ViewHolder1) convertView.getTag();
                    break;
                case TYPE_2:
                    holder2 = (ViewHolder2) convertView.getTag();
                    break;
               }
        }
        TaskModel.TaskBean tempEntity;
        ChildAdapter tempAdapt;
        switch (type){
    case TYPE_1:
         tempEntity = (TaskModel.TaskBean) mDatas.get(position);
        vHolder.driver.setText(tempEntity.getClientbill() + "");
//        vHolder.vehicle.setText(tempEntity.getVehicle()+"");
        vHolder.status.setText(tempEntity.getPlannetname());
        vHolder.typename.setText(tempEntity.getPlandate());

        //点击那个弹出那个，如果已经弹出就收回子listview
//        if (mParentItem == position && mbShowChild) {
//            //子listview实在这里加载数据的
//         tempAdapt = new ChildAdapter(mContext, tempEntity.getAddress());
//        vHolder.listViewItem.setAdapter(tempAdapt);

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

        vHolder.in_bill.setOnClickListener(this);
        //记住是那个button事件
        vHolder.in_bill.setTag(position);
        vHolder.cancle_bill.setOnClickListener(this);
        vHolder.cancle_bill.setTag(position);
//        vHolder.care_bill.setOnClickListener(this);
//        vHolder.care_bill.setTag(position);
        vHolder.daohan.setOnClickListener(this);
        vHolder.daohan.setTag(position);
        vHolder.sure_bill.setOnClickListener(this);
        vHolder.sure_bill.setTag(position);
        break;
    case TYPE_2:
//         tempEntity = (TaskModel.TaskBean) mDatas.get(position);
//        holder2.driver.setText(tempEntity.getBill() + "");
////        vHolder.vehicle.setText(tempEntity.getVehicle()+"");
//        holder2.status.setText(tempEntity.getNetname());
//        holder2.typename.setText(tempEntity.getPlandate());
//
//        //点击那个弹出那个，如果已经弹出就收回子listview
////        if (mParentItem == position && mbShowChild) {
////            //子listview实在这里加载数据的
////         tempAdapt = new ChildAdapter(mContext, tempEntity.getAddress());
////        holder2.listViewItem.setAdapter(tempAdapt);
//        holder2.ico.setImageResource(R.mipmap.transport);
////            vHolder.listViewItem.setVisibility(View.VISIBLE);
////
////            //子listview的点击监听
////            vHolder.listViewItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////
////                @Override
////                public void onItemClick(AdapterView<?> parent, View view,
////                                        int position, long id) {
////                    Toast.makeText(mContext, "Child Listview" + String.valueOf(position), Toast.LENGTH_SHORT).show();
////
////                }
////            });
////        } else {
////            vHolder.listViewItem.setVisibility(View.GONE);
////        }
//
//        holder2.in_bill.setOnClickListener(this);
//        //记住是那个button事件
//        holder2.in_bill.setTag(position);
//        holder2.cancle_bill.setOnClickListener(this);
//        holder2.cancle_bill.setTag(position);
//        holder2.care_bill.setOnClickListener(this);
//        holder2.care_bill.setTag(position);
//        holder2.daohan.setOnClickListener(this);
//        holder2.daohan.setTag(position);
//        holder2.sure_bill.setOnClickListener(this);
//        holder2.sure_bill.setTag(position);
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
    class ViewHolder2 {
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

