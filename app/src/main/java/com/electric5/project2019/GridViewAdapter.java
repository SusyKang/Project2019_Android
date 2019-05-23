package com.electric5.project2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<ReportCapture> data;
    private int layout;

    public GridViewAdapter(Context context, int layout, ArrayList<ReportCapture> data){
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return 0;
    }

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
        ReportCapture reportCapture = data.get(position);

        ImageView item_image = (ImageView)convertView.findViewById(R.id.item_image);
        TextView item_filename = (TextView)convertView.findViewById(R.id.item_filename);

        item_image.setImageURI(reportCapture.getImageURI());
        item_filename.setText(reportCapture.getFileName());

        return convertView;
    }
}
