package com.electric5.project2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

// TODO: 라즈베리파이가 보내준 위기상황 캡쳐 불러오기
// 참고1-volley 사용 https://www.thetechnologyupdates.com/volley-network-image-loader-to-manage-your-gridview-images/
// 참고2 http://www.masterqna.com/android/7965/%EC%84%9C%EB%B2%84%EC%97%90-%EC%9E%88%EB%8A%94-db%EC%97%90%EC%84%9C-%EC%9D%B4%EB%AF%B8%EC%A7%80%EC%A0%95%EB%B3%B4%EB%A5%BC-%EB%B6%88%EB%9F%AC%EC%99%80%EC%84%9C-%EC%9D%B4%EB%AF%B8%EC%A7%80%EB%B7%B0%EC%97%90-%EB%B3%B4%EC%97%AC%EC%A3%BC%EA%B8%B0-%EC%A7%88%EB%AC%B8
public class ReportCaptureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_capture);

        GridView gridView = (GridView)findViewById(R.id.gridview);

        ArrayList<ReportCapture> data = new ArrayList<>();
        GridViewAdapter g_adapter = new GridViewAdapter(this, R.layout.item_gridview, data);
        gridView.setAdapter(g_adapter);

        /*
        gridView = (GridView) view.findViewById(R.id.gridview);
        ImageView item_image = (ImageView) view.findViewById(R.id.item_image);
        TextView item_filename = (TextView) view.findViewById(R.id.item_filename);

        //mDBHelper = new DatabaseHelper(getContext());
        //mImageList = mDBHelper.getListImage();
        //adapter = new ImageAdapter(getContext(), mImageList);
        gridView.setAdapter(adapter);
*/
    }

     /* private GridView gridView;

    private GridViewAdapter adapter;
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
        // TODO: 인텐트 사용해서 PopUpActivity로 파일명, 사진 보내기
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
*/
}
