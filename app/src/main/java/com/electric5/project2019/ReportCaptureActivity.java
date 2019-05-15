package com.electric5.project2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

// TODO: 라즈베리파이가 보내준 위기상황 캡쳐 불러오기
// 참고1-volley 사용 https://www.thetechnologyupdates.com/volley-network-image-loader-to-manage-your-gridview-images/
// 참고2 http://www.masterqna.com/android/7965/%EC%84%9C%EB%B2%84%EC%97%90-%EC%9E%88%EB%8A%94-db%EC%97%90%EC%84%9C-%EC%9D%B4%EB%AF%B8%EC%A7%80%EC%A0%95%EB%B3%B4%EB%A5%BC-%EB%B6%88%EB%9F%AC%EC%99%80%EC%84%9C-%EC%9D%B4%EB%AF%B8%EC%A7%80%EB%B7%B0%EC%97%90-%EB%B3%B4%EC%97%AC%EC%A3%BC%EA%B8%B0-%EC%A7%88%EB%AC%B8
public class ReportCaptureActivity extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_capture);

        gridView = (GridView) findViewById(R.id.gridview);
        ImageView item_image = (ImageView) findViewById(R.id.item_image);
        TextView item_filename = (TextView) findViewById(R.id.item_filename);


        // 아이템 하나 선택 시 PopUpActivity로 넘어감
        // TODO: 인텐트로 파일명, 사진 보내기
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PopUpActivity.class);

                //String filename = ((TextView) view.findViewById(R.id.item_filename)).getText().toString();
                //intent.putExtra("file_name", filename);

                startActivity(intent);
            }
        });

    }




}
