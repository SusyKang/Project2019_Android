package com.electric5.project2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


class ReportSleepTime {
    String item_date; // text
    String item_sleeptime; // text
    ReportSleepTime(String date, String sleeptime) { item_date = date; item_sleeptime = sleeptime; }
}

// 수면 시간 불러오는 어댑터
//https://medium.com/android-develop-android/android%EA%B0%9C%EB%B0%9C-5-listview%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EB%A6%AC%EC%8A%A4%ED%8A%B8%EB%A7%8C%EB%93%A4%EA%B8%B0-215b9693d33b
public class ListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context mContext;
    private int mResource;
    private ArrayList<ReportSleepTime> mItems = new ArrayList<ReportSleepTime>();

    public ListViewAdapter(Context context, int layout, ArrayList<ReportSleepTime> datas){
        mContext = context;
        mItems = datas;
        mResource = layout;
    }

    @Override
    public int getCount(){return mItems.size();}
    @Override
    public Object getItem(int position) { return mItems.get(position); }
    @Override
    public long getItemId(int position) { return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            LayoutInflater inflater =
                    (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(mResource,parent,false);
        }

        ReportSleepTime reportSleepTime = mItems.get(position);

        TextView item_date = (TextView)convertView.findViewById(R.id.item_date);
        TextView item_sleeptime = (TextView)convertView.findViewById(R.id.item_sleeptime);

        item_date.setText(mItems.get(position).item_date);

        String sleeptime = mItems.get(position).item_sleeptime;
        int int_sleeptime = Integer.parseInt(sleeptime);
        int hour = int_sleeptime / 3600;
        int minute = (int_sleeptime % 3600)/ 60;
        int second = (int_sleeptime % 3600) % 60;

        item_sleeptime.setText(hour+"시간 "+minute+"분 "+second+"초");

        return convertView;
    }
}