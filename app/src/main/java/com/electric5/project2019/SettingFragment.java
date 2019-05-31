package com.electric5.project2019;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;
import com.electric5.project2019.model.UploadResult;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SettingFragment extends Fragment {
    //upload
    ProgressDialog asyncDialog;
    private Button uploadsound;
    String getServerURL = "";
    String getmp3URL="/sdcard/Music/01.mp3";//폰 sdcard Music 에 녹음 파일 저장 후 upload하는 파일

    private MediaRecorder mMediaRecorder;
    private MediaPlayer mMediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        final Button record = (Button)view.findViewById(R.id.record);
        final Button play = (Button)view.findViewById(R.id.recordplay);
        uploadsound= (Button)view.findViewById(R.id.recordupload);

        checkDangerousPermissions(); // 접근 권한 체크

        getServerURL = getContext().getResources().getString(R.string.ip_address);//서버 ip
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
            public void onClick(View v) {//
                try {
                    playAudio();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        uploadsound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncDialog = new ProgressDialog(getContext());
                asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                asyncDialog.setMessage("로딩중입니다..");

                // show dialog
                asyncDialog.show();

                uploadFile(getmp3URL);//파일 업로드

            }
        });

        return view;
    }
    //파일 업로드
    private void uploadFile(String soundurl) {//

        /**
         * 현재 연결된 서버의 URL을 받아옴
         */
        String url = getServerURL;

        /**
         * 다시 연결 시도
         */
        // create upload service client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();

        NetworkService service = retrofit.create(NetworkService.class);


        /**
         * 서버로 보낼 파일의 전체 url을 이용해 작업
         */

        File sound = new File(soundurl);
        RequestBody soundBody = RequestBody.create(MediaType.parse("audio/wav"), sound);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("sound", sound.getName(), soundBody);
        Log.i("myTag","this file'name is "+ soundBody.toString());
        Log.i("myTag","this file'name is "+ sound.getPath());


        /**
         * 업로드하는 부분 // POST방식 이용
         */// add another part within the multipart request
        String descriptionString = "android";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
        Call<ResponseBody> call = service.upload(body,description);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.isSuccessful()){

                    Gson gson = new Gson();
                    try {
                        String getResult = response.body().string();
                        JsonParser parser = new JsonParser();
                        JsonElement rootObejct = parser.parse(getResult);

                        UploadResult example = gson.fromJson(rootObejct, UploadResult.class);
                        Log.i("mytag",example.url);
                        String result = example.result;
                        if(result.equals("success")){
                            Toast.makeText(getContext(),"업로드 성공!!!!",Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.i("MyTag", "error : "+e.getMessage());
                    }
                }else{
                    Toast.makeText(getContext(),"업로드 실패!!!!",Toast.LENGTH_SHORT).show();
                }
                // dismiss dialog
                asyncDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
                asyncDialog.dismiss();
            }
        });
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
    private void playAudio() throws Exception {
        killMediaPlayer();

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setDataSource(getmp3URL);
        mMediaPlayer.prepare();
        mMediaPlayer.start();
    }

    // 재생 중단
    private void killMediaPlayer() {
        if (mMediaPlayer != null) {
            try {
                mMediaPlayer.stop();
                mMediaPlayer.release();
                mMediaPlayer = null;
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

        mMediaRecorder.setOutputFile(getmp3URL);

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

        Toast.makeText(getActivity(), "녹음을 중단합니다", Toast.LENGTH_SHORT).show();
    }



}