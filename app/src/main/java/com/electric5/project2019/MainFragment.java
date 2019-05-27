package com.electric5.project2019;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.electric5.project2019.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static android.app.Activity.RESULT_OK;

// TODO: babyphoto, mainicon 잘되는지 확인
public class MainFragment extends Fragment {
    public MainFragment() {
    }

    private Button babyphotoupload, babyphotosave;
    ImageView babyphoto;
    TextView tv_path;

    String path; // babyphoto 에 등록한 사진의 실제 경로
    String saved_path = null; // 서버에 저장된 path를 가져와 저장하는 스트링

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //유저정보 받아오는 코드
        SharedPreferences info = getActivity().getSharedPreferences("id", getActivity().MODE_PRIVATE);
        final String localid = info.getString("id", "");
        final TextView mainbabyname = (TextView) view.findViewById(R.id.mainbabyname);
        final TextView mainbyear = (TextView) view.findViewById(R.id.mainbyear);
        final TextView mainbmonth = (TextView) view.findViewById(R.id.mainbmonth);
        final TextView mainbday = (TextView) view.findViewById(R.id.mainbday);

        ImageView mainicon = (ImageView) view.findViewById(R.id.mainicon);

        babyphoto = (ImageView) view.findViewById(R.id.babyphoto);
        babyphotoupload = (Button) view.findViewById(R.id.babyphotoupload);
        babyphotosave = (Button) view.findViewById(R.id.babyphotosave);

        tv_path = (TextView) view.findViewById(R.id.tv_path);

        try {
            //로그인된 사용자 정보 로드
            JSONObject postDataParam = new JSONObject(); //JSON생성 : JSONObject는 JSON형태의 데이터를 관리해 주는 메서드
            postDataParam.put("id", localid);
            String result = new MyinfoRequest(getActivity()).execute(postDataParam).get();
            JSONObject jsonObject = new JSONObject(result);
            String success = jsonObject.getString("success");

            if (success.equals("true")) {
                String data = jsonObject.getString("data");
                JSONObject jsonObject2 = new JSONObject(data);

                mainbabyname.setText(jsonObject2.getString("baby"));
                mainbyear.setText(jsonObject2.getString("Byear"));
                mainbmonth.setText(jsonObject2.getString("Bmonth"));
                mainbday.setText(jsonObject2.getString("Bday"));
                saved_path = jsonObject2.getString("imgpath");

                File imgFile = new File(saved_path);
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                babyphoto.setImageBitmap(myBitmap);
                tv_path.setText(saved_path);

                // 생일 비교
                Calendar cal = Calendar.getInstance();

                int month = cal.get(Calendar.MONTH) + 1; // 0~11로 출력되므로 +1
                int date = cal.get(Calendar.DATE);

                String s_month = String.valueOf(month);
                String s_date = String.valueOf(date);

                // 기본값 달력인 메인화면아이콘이 생일이면 케이크아이콘으로 바뀜
                if ((mainbmonth.getText().toString() == s_month) && (mainbday.getText().toString() == s_date))
                    mainicon.setImageResource(R.drawable.birthday);
                else
                    mainicon.setImageResource(R.drawable.calendar);
            }
        } catch (JSONException e) {
            Log.e("TAG", "JSONEXception");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

     /*
        try {

            JSONObject postDataParam = new JSONObject();
            postDataParam.put("id", info.getString("id",""));

            String result = new MyinfoRequest(getActivity()).execute(postDataParam).get();

            //결과값 받기.
            JSONObject jsonObject = new JSONObject(result);
            JSONArray dataArray = jsonObject.getJSONArray("data");

            if (result!=null) {
                ArrayList<String> data = new ArrayList<String>();

                for(int i=0; i<dataArray.length(); i++) {
                    JSONObject jsonObject2 = dataArray.getJSONObject(i);

                    data.add(jsonObject2.getString("baby"));
                }
                final TextView mainbabyname = (TextView)view.findViewById(R.id.mainbabyname);
                mainbabyname.setText((CharSequence) data);

            } else {
                final TextView mainbabyname = (TextView)view.findViewById(R.id.mainbabyname);
                mainbabyname.setText("ttt");            }
            /*
            JSONObject jsonObject = new JSONObject(result);
            JSONArray dataArraye = jsonObject.getJSONArray("data");

            String babyname = dataArraye.getString(1);
            final TextView mainbabyname = (TextView)view.findViewById(R.id.mainbabyname);
            mainbabyname.setText(babyname);
*/
            /*
            String result = new MyinfoRequest(getActivity()).execute(local).get();

            if (result==null) {
                final TextView mainbabyname = (TextView)view.findViewById(R.id.mainbabyname);
                mainbabyname.setText(local[0]);
            } else {
                JSONObject jsonObject = new JSONObject(result);
                String data = jsonObject.getString("data");
                JSONObject jsonObject2 = new JSONObject(data);
                String babyname = jsonObject2.getString("baby");

                final TextView mainbabyname = (TextView)view.findViewById(R.id.mainbabyname);
                mainbabyname.setText(babyname);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
*/

        // 사진 등록 버튼
        babyphotoupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    }, 2000);
                } else {
                    startGallery();
                }
            }
        });

        // 사진 저장 버튼 - path(휴대폰 속 실제 경로)를 서버로 업로드
        babyphotosave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                babyphoto.isSaveEnabled();
                JSONObject DataParam = new JSONObject();

                try {
                    //데이터 집어넣기
                    DataParam.put("id", localid);
                    DataParam.put("path", path);

                    String result = new PathRequest(getActivity()).execute(DataParam).get();
                    JSONObject jsonObject = new JSONObject(result);
                    String success = jsonObject.getString("success");

                    if (success.equals("true")) {
                        Toast.makeText(getContext(), path, Toast.LENGTH_LONG).show(); // 사진 가져온 경로 토스트
                        tv_path.setText(path);
                    }
                } catch (JSONException e) {
                    Log.e("TAG", "JSONEXception");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

/*
        //saved_path = path;

        // 어플 시작 시 서버에 저장된 path를 saved_path에 저장하여 가져옴
        if (saved_path != null) {
            File imgFile = new File(saved_path);

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            babyphoto.setImageBitmap(myBitmap);
            tv_path.setText(saved_path);
        }
*/
        return view;
    }

    // 갤러리에서 사진 선택
    private void startGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, 1);
        }
    }

    // 선택한 사진의 실제 경로 가져오기
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 1 && resultCode == RESULT_OK && intent != null && intent.getData() != null) {
            Uri returnUri = intent.getData();

            try {
                Bitmap bitmapImage = (Bitmap) MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
                babyphoto.setImageBitmap(bitmapImage);

                path = Utils.getActualPath(getContext(), returnUri);

            } catch (Exception e) {

            }
        }
    }

}
