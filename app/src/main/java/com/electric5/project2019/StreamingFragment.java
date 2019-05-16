package com.electric5.project2019;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.VideoView;

import com.electric5.project2019.R;

// 실시간 스트리밍 프래그먼트 -- TODO: 동작제어 기능 추가
// controlbutton1 모빌 작동
// controlbutton2 녹음 재생
// controlbutton3 asmr 재생
// https://blog.naver.com/PostView.nhn?blogId=cosmosjs&logNo=220786475003&categoryNo=83&parentCategoryNo=0&viewDate=&currentPage=19&postListTopCurrentPage=&from=thumbnailList
public class StreamingFragment extends Fragment {

    private WebView video;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_streaming, container, false);

        if (video != null) { video.destroy(); }

        video = (WebView) view.findViewById(R.id.videoview);
        // video.setPadding(0,0,0,0);
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
        video.loadUrl("http://223.194.128.235:8090/?action=stream");  // TODO: 라즈베리파이 서버 주소

        return view;
    }
}


