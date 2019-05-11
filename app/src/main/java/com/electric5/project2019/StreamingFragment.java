package com.electric5.project2019;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.electric5.project2019.R;

// 실시간 스트리밍 프래그먼트 -- TODO: 동작제어 기능 추가
// controlbutton1 모빌 작동
// controlbutton2 녹음 재생
// controlbutton3 asmr 재생
// https://blog.naver.com/PostView.nhn?blogId=cosmosjs&logNo=220786475003&categoryNo=83&parentCategoryNo=0&viewDate=&currentPage=19&postListTopCurrentPage=&from=thumbnailList
public class StreamingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_streaming, container, false);

        String uri = "rtsp://223.194.128.30:8080/test"; // TODO: 라즈베리파이 서버 주소
        VideoView video = (VideoView) view.findViewById(R.id.videoview);
        video.setVideoURI(Uri.parse(uri));
        video.requestFocus();
        video.start();

        return view;
    }
}


