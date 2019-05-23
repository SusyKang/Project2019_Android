package com.electric5.project2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

// TODO: 라즈베리파이가 보내준 수면 시간 데이터 불러오기
public class ReportSleeptimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_sleeptime);

        ListView listView = (ListView)findViewById(R.id.listview);

        ArrayList<ReportSleepTime> data = new ArrayList<>();
        ListViewAdapter l_adapter = new ListViewAdapter(this, R.layout.item_listview, data);
        listView.setAdapter(l_adapter);
    }
}
