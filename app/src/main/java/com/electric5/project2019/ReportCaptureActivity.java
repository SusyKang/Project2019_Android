package com.electric5.project2019;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

// TODO: 라즈베리파이가 보내준 위기상황 캡쳐 불러오기
// 참고1-volley 사용 https://www.thetechnologyupdates.com/volley-network-image-loader-to-manage-your-gridview-images/
// 참고2 http://www.masterqna.com/android/7965/%EC%84%9C%EB%B2%84%EC%97%90-%EC%9E%88%EB%8A%94-db%EC%97%90%EC%84%9C-%EC%9D%B4%EB%AF%B8%EC%A7%80%EC%A0%95%EB%B3%B4%EB%A5%BC-%EB%B6%88%EB%9F%AC%EC%99%80%EC%84%9C-%EC%9D%B4%EB%AF%B8%EC%A7%80%EB%B7%B0%EC%97%90-%EB%B3%B4%EC%97%AC%EC%A3%BC%EA%B8%B0-%EC%A7%88%EB%AC%B8
public class ReportCaptureActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_capture);


        //유저정보 받아오는 코드
        SharedPreferences info =getSharedPreferences("id", MODE_PRIVATE);
        final String localid = info.getString("id", "");

        //로그인된 사용자 정보 로드
        JSONObject postDataParam = new JSONObject(); //JSON생성 : JSONObject는 JSON형태의 데이터를 관리해 주는 메서드
        try {
            postDataParam.put("id", localid);
            String result = new BoardRequest(ReportCaptureActivity.this).execute(postDataParam).get();

            JSONObject jsonObject = new JSONObject(result);
            String success = jsonObject.getString("success");
            if (success.equals("true")) {
                JSONArray dataArray = jsonObject.getJSONArray("data");

                ArrayList<ReportCapture> data = new ArrayList<ReportCapture>();

                for(int i=0; i<dataArray.length(); i++) {
                    JSONObject jsonObject2 = dataArray.getJSONObject(i);

                    ReportCapture myItem = new ReportCapture(jsonObject2.getString("datetime"),jsonObject2.getString("capture"));
                    data.add(myItem);
                }

                // 어댑터생성
                ListViewAdapter2 l_adapter2 = new ListViewAdapter2(this, R.layout.item_listview2, data);
                //어댑터연결
                ListView listView2 = (ListView)findViewById(R.id.listview2);
                listView2.setAdapter(l_adapter2);

            } else {
                Toast.makeText(getApplicationContext(), "데이터를 불러오는데 실패했습니다.", Toast.LENGTH_LONG).show();
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
