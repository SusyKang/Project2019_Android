package com.electric5.project2019;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.electric5.project2019.model.Board;

import java.util.List;

public class BoardAdapter extends BaseAdapter {
    private Context mContext;
    private List<Board> mBoardList;
    private String type;

    public BoardAdapter(Context mContext, List<Board> mBoardList) {
        this.mContext = mContext;
        this.mBoardList = mBoardList;
    }

    @Override
    public int getCount() {
        return mBoardList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBoardList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mBoardList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item, null);
        TextView tvBoardName = (TextView)v.findViewById(R.id.item_boardname);
        TextView tvWriterName = (TextView)v.findViewById(R.id.item_writername);

        tvBoardName.setText(mBoardList.get(position).getBoardName());
        tvWriterName.setText(mBoardList.get(position).getWriterName());

        v.setTag( mBoardList.get(position).getId());
        return v;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void updateList(List<Board> lstItem) {
        mBoardList.clear();
        mBoardList.addAll(lstItem);
        this.notifyDataSetChanged();
    }

}
