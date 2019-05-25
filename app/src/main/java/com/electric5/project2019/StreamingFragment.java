package com.electric5.project2019;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

// 실시간 스트리밍 프래그먼트
// controlbutton2 녹음 재생 TODO: 녹음 재생 기능 추가
// controlbutton3 asmr 재생 TODO : ASMR 재생 추가
// https://blog.naver.com/PostView.nhn?blogId=cosmosjs&logNo=220786475003&categoryNo=83&parentCategoryNo=0&viewDate=&currentPage=19&postListTopCurrentPage=&from=thumbnailList
public class StreamingFragment extends Fragment {

    private WebView video;
    private Context context;

    private int motor = 0;

    //@TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    //@android.support.annotation.RequiresApi(api = Build.VERSION_CODES.ECLAIR_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_streaming, container, false);

        context = container.getContext();
        //Button motorctl = (Button) view.findViewById(R.id.controlbutton1);

        final Button motorctl = (Button) view.findViewById(R.id.controlbutton1);

        if (video != null) { video.destroy(); }

        video = (WebView) view.findViewById(R.id.videoview);
        video.getSettings().setBuiltInZoomControls(false);
        video.getSettings().setJavaScriptEnabled(true);
        video.getSettings().setLoadWithOverviewMode(true);
        video.getSettings().setUseWideViewPort(true);

        video.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        video.loadUrl("http://223.194.132.29:8090/?action=stream");  // TODO: 라즈베리파이 서버 주소


        //모터 제어 버튼
        motorctl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (motor ==0 ) {
                        startMotor();
                        motorctl.setText("모빌 작동 중"); // 작동 중일 때 텍스트변경
                    }
                    else
                        stopMotor();
                } catch (Exception e) {
                    e.printStackTrace();
                    motorctl.setText("모빌 작동"); // 기본 텍스트
                }
            }
        });

        return view;
    }

    // 모터 시작
    private void startMotor() throws Exception {
        JSONObject postDataParam = new JSONObject(); //JSON생성 : JSONObject는 JSON형태의 데이터를 관리해 주는 메서드

        //stopMotor();

        postDataParam.put("msg", "MOTORON"); // 데이터 집어넣기

        ModeChange.act = 3;
        String result = new ControlRequest(getActivity()).execute(postDataParam).get();

        //결과값 받기
        JSONObject jsonObject = new JSONObject(result);
        String success = jsonObject.getString("success");

        //녹음버튼처럼 녹음 유무에 따라 motor on/off 제어
        if (success.equals("true")) {
            //Toast.makeText(getActivity(), "모빌 작동", Toast.LENGTH_LONG).show();
            motor = 1;
        } else {
            Toast.makeText(context, "잠시 후에 다시 시도하세요", Toast.LENGTH_LONG).show();
        }

    }

    // 모터 중단
    private void stopMotor()  {
        JSONObject postDataParam = new JSONObject(); //JSON생성 : JSONObject는 JSON형태의 데이터를 관리해 주는 메서드

        try {
            postDataParam.put("msg", "MOTOROFF"); // 데이터 집어넣기

            ModeChange.act = 3;
            String result = new ControlRequest(getActivity()).execute(postDataParam).get();

            //결과값 받기.
            JSONObject jsonObject = new JSONObject(result);
            String success = jsonObject.getString("success");

            //녹음버튼처럼 녹음 유무에 따라 motor on/off 제어
            if (success.equals("true")) {
                Toast.makeText(context, "모빌 중지", Toast.LENGTH_LONG).show();
                motor=0;
            } else {
                Toast.makeText(context, "잠시 후에 다시 시도하세요", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Log.e("TAG", "JSONEXception");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}


