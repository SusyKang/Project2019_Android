package com.electric5.project2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

//https://medium.com/android-develop-android/android%EA%B0%9C%EB%B0%9C-5-listview%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EB%A6%AC%EC%8A%A4%ED%8A%B8%EB%A7%8C%EB%93%A4%EA%B8%B0-215b9693d33b
public class ListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<ReportSleepTime> data;
    private int layout;

    public ListViewAdapter(Context context, int layout, ArrayList<ReportSleepTime> data){
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount(){return data.size();}

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView=inflater.inflate(layout,parent,false);
        }
        ReportSleepTime reportSleepTime = data.get(position);

        TextView item_date = (TextView)convertView.findViewById(R.id.item_date);
        TextView item_sleeptime = (TextView)convertView.findViewById(R.id.item_sleeptime);

        item_date.setText(reportSleepTime.getDate());
        item_sleeptime.setText(reportSleepTime.getSleepTime());

        return convertView;
    }
}