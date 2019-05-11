package com.electric5.project2019;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SettingFragment extends Fragment {

    private MediaRecorder mMediaRecorder;
    private MediaPlayer mMediaPlayer;
    private String mVoiceFileName = null;

    String audio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        final Button record = (Button)view.findViewById(R.id.record);
        final Button play = (Button)view.findViewById(R.id.recordplay);

        checkDangerousPermissions(); // 접근 권한 체크

        //녹음 버튼
        record.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mMediaRecorder == null) {
                    startAudioRec();
                    record.setText("녹음 중");
                } else {
                    stopAudioRec();
                    record.setText("녹음 완료");
                }
            }
        });

        //재생 버튼
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    playAudio(audio);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    final int  REQUEST_EXTERNAL_STORAGE_FOR_MULTIMEDIA = 1;

    // 접근 권한 체크
    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(getContext(), permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), permissions, REQUEST_EXTERNAL_STORAGE_FOR_MULTIMEDIA);

        }

    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) { // permission was granted
            switch (requestCode) {
                case REQUEST_EXTERNAL_STORAGE_FOR_MULTIMEDIA:
                    break;
            }
        } else { // permission was denied
            Toast.makeText(getContext(),"접근 권한이 필요합니다",Toast.LENGTH_SHORT).show();
        }
    }


    // 재생 시작
    private void playAudio(String uri) throws Exception {
        killMediaPlayer();
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setDataSource(getActivity(), Uri.parse(uri));
        mMediaPlayer.prepare();
        mMediaPlayer.start();
    }

    // 재생 중단
    private void killMediaPlayer() {
        if (mMediaPlayer != null) {
            try {
                mMediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    // 녹음 시작
    private void startAudioRec()  {
        mMediaRecorder = new MediaRecorder();
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

        mVoiceFileName = "VOICE_" + currentDateTimeFormat() + ".mp3"; // 파일 이름 형식
        mMediaRecorder.setOutputFile(Environment.getExternalStorageDirectory().getPath() + "/Music/" + mVoiceFileName); // 파일 저장 경로

        try {
            mMediaRecorder.prepare();
            Toast.makeText(getActivity(), "녹음을 시작합니다", Toast.LENGTH_SHORT).show();
            mMediaRecorder.start();
        } catch (Exception ex) {
            Log.e("SampleAudioRecorder", "Exception : ", ex);
        }
    }

    // 녹음 중단
    private void stopAudioRec()  {
        mMediaRecorder.stop();
        mMediaRecorder.release();
        mMediaRecorder = null;
        Uri uri = Uri.parse("file://" + Environment.getExternalStorageDirectory().getPath() + "/Music/"+ mVoiceFileName);
        audio = uri.toString();
        Toast.makeText(getActivity(), "녹음을 중단합니다", Toast.LENGTH_SHORT).show();

    }

    //mp3파일 저장 명에 사용될 날짜 형식
    private String currentDateTimeFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm");
        String currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }

}