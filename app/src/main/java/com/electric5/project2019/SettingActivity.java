package com.electric5.project2019;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SettingActivity extends AppCompatActivity {

    private MediaRecorder mMediaRecorder;
    private MediaPlayer mMediaPlayer;
    private String mVoiceFileName = null;
    private Button play;

    String audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().hide();



        final Button record = (Button) findViewById(R.id.record);
        play = (Button)findViewById(R.id.recordplay);

        checkDangerousPermissions();


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


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String uri=mDBHelper.getDetailShelterByName(name).getUri();
                try {
                    playAudio(audio);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void playAudio(String uri) throws Exception {
        killMediaPlayer();
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setDataSource(getApplicationContext(), Uri.parse(uri));
        mMediaPlayer.prepare();
        mMediaPlayer.start();
    }
    private void killMediaPlayer() {
        if (mMediaPlayer != null) {
            try {
                mMediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    final int  REQUEST_EXTERNAL_STORAGE_FOR_MULTIMEDIA=1;


    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_EXTERNAL_STORAGE_FOR_MULTIMEDIA);
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
            Toast.makeText(getApplicationContext(),"권한이 필요합니다",Toast.LENGTH_SHORT).show();
        }
    }

    private void startAudioRec()  {
        mMediaRecorder = new MediaRecorder();
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

        mVoiceFileName = "VOICE" + currentDateTimeFormat() + ".mp3";
        mMediaRecorder.setOutputFile(Environment.getExternalStorageDirectory().getPath() + "/Music/" + mVoiceFileName);

        try {
            mMediaRecorder.prepare();
            Toast.makeText(getApplicationContext(), "녹음을 시작합니다", Toast.LENGTH_SHORT).show();
            mMediaRecorder.start();
        } catch (Exception ex) {
            Log.e("SampleAudioRecorder", "Exception : ", ex);
        }
    }

    private void stopAudioRec()  {
        mMediaRecorder.stop();
        mMediaRecorder.release();
        mMediaRecorder = null;
        Uri uri = Uri.parse("file://" + Environment.getExternalStorageDirectory().getPath() + "/Music/"+ mVoiceFileName);
        audio = uri.toString();
        Toast.makeText(getApplicationContext(), "녹음을 중단합니다", Toast.LENGTH_SHORT).show();
        //TODO : ?뱀쓬?뚯씪 ?쒕쾭濡??щ━湲?        // https://stackoverflow.com/questions/4966910/androidhow-to-upload-mp3-file-to-http-server
        // https://taetanee.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-php-%ED%8C%8C%EC%9D%BC-%EC%A0%84%EC%86%A1-%EC%98%88%EC%A0%9C
    }


    private String currentDateTimeFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm");
        String currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }


    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
