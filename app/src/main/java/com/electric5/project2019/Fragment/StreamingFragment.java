package com.electric5.project2019.Fragment;

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

public class StreamingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_streaming, container, false);

        String uri = "rtsp://223.194.128.30:8080/test"; //TODO: 라즈베리서버
        VideoView video = (VideoView) view.findViewById(R.id.videoview);
        video.setVideoURI(Uri.parse(uri));
        video.requestFocus();
        video.start();

        return view;
    }
}


