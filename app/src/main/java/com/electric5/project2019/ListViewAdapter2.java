package com.electric5.project2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


class ReportCapture {
    String item_datetime; // text
    String item_capture_path; // text
    ReportCapture(String datetime, String capturepath) { item_datetime = datetime; item_capture_path = capturepath; }
}

// 캡쳐 파일 불러오는 어댑터
public class ListViewAdapter2 extends BaseAdapter {
    private LayoutInflater inflater;
    private Context mContext;
    private int mResource;
    private ArrayList<ReportCapture> mItems = new ArrayList<ReportCapture>();

    public ListViewAdapter2(Context context, int layout, ArrayList<ReportCapture> datas){
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

        ReportCapture reportCapture = mItems.get(position);

        TextView item_datetime = (TextView)convertView.findViewById(R.id.item_datetime);
        WebView item_capture = (WebView) convertView.findViewById(R.id.item_capture);

        item_datetime.setText(mItems.get(position).item_datetime);
        item_capture.loadUrl(mItems.get(position).item_capture_path);

        return convertView;
    }
}