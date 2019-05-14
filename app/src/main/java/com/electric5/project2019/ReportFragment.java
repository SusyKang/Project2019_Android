package com.electric5.project2019;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.electric5.project2019.R;
import com.electric5.project2019.ImageAdapter;
import java.util.List;

// 리포트 프래그먼트 - 통계 보기
// TODO: 라즈베리파이가 보내준 위기상황 캡쳐 불러오기
public class ReportFragment extends Fragment {

    private GridView gridView;

    private ImageAdapter adapter;
    //private DatabaseHelper mDBHelper;
    //private List<Image> mImageList;

    Activity activity;

    public ReportFragment() { }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        activity = getActivity();
        gridView.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        // 아이템 하나 선택 시 PopUpActivity로 넘어감
        // TODO: 인텐트로 파일명, 사진 보내기
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), PopUpActivity.class);

                String filename = ((TextView) view.findViewById(R.id.item_filename)).getText().toString();
                intent.putExtra("file_name",filename);

                getActivity().startActivity(intent);
            }
        }) ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        gridView = (GridView) view.findViewById(R.id.gridview);
        ImageView item_image = (ImageView) view.findViewById(R.id.item_image);
        TextView item_filename = (TextView) view.findViewById(R.id.item_filename);

        //mDBHelper = new DatabaseHelper(getContext());
        //mImageList = mDBHelper.getListImage();
        //adapter = new ImageAdapter(getContext(), mImageList);
        gridView.setAdapter(adapter);

        return view;
    }

}

