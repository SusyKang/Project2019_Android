package com.electric5.project2019;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.electric5.project2019.BoardWriteActivity;
import com.electric5.project2019.R;

import java.text.SimpleDateFormat;
import java.util.Date;

// 게시판 프래그먼트
@SuppressLint("ValidFragment")
public class BoardFragment extends Fragment {
    private Button write;
    //private ListView boardlist;
    //private ListAdapter adapter;
    //private DatabaseHelper mDBHelper;
    //private List<Board> mBoardList;

    Activity activity;

    public BoardFragment(){  }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        activity = getActivity();
        write.setOnClickListener(writeClickListener);
    }

    View.OnClickListener writeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), BoardWriteActivity.class);
            getActivity().startActivity(intent);
            Toast.makeText(activity, "글쓰기", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        write = (Button)view.findViewById(R.id.writebutton);

        //boardlist = (ListView)view.findViewById(R.id.boardlist);
        //mDBHelper = new DatabaseHelper(getContext());

        //mBoardList = mDBHelper.getBoard();
        //adapter = new ListAdapter(getContext(), mBoardList);

        //boardlist.setAdapter(adapter);

        return view;
    }

    private String currentDateFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }

    private String currentDateTimeFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm");
        String currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }



}
