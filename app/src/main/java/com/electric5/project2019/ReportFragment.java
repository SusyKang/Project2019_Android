package com.electric5.project2019;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.electric5.project2019.R;

import java.util.List;

// 리포트 프래그먼트 - 위기상황 캡쳐 보기, 수면 시간 기록 보기 (각각 액티비티로 이동)
public class ReportFragment extends Fragment {

    //  private Button report_capture, report_sleeptime;
    //  Activity activity;

 /*   @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        activity = getActivity();
        report_capture.setOnClickListener(SelectActivityListener);
        report_sleeptime.setOnClickListener(SelectActivityListener);
    }*/

    Button.OnClickListener SelectActivityListener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.report_capture:
                    ModeChange.act=3;
                    Intent intent1 = new Intent(getActivity(), ReportCaptureActivity.class);
                    getActivity().startActivity(intent1);
                    break;
                case R.id.report_sleeptime:
                    ModeChange.act=3;
                    Intent intent2 = new Intent(getActivity(), ReportSleeptimeActivity.class);
                    getActivity().startActivity(intent2);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        Button b1 = (Button) view.findViewById(R.id.report_capture);
        Button b2 = (Button) view.findViewById(R.id.report_sleeptime);

        b1.setOnClickListener(SelectActivityListener);
        b2.setOnClickListener(SelectActivityListener);

        return view;
    }
/*
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.report_capture:
                Intent intent1 = new Intent(getActivity(), ReportCaptureActivity.class);
                getActivity().startActivity(intent1);
                break;
            case R.id.report_sleeptime:
                Intent intent2 = new Intent(getActivity(), ReportSleeptimeActivity.class);
                getActivity().startActivity(intent2);
                break;
        }
    }
*/
}

