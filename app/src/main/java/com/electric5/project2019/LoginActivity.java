package com.electric5.project2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LongSummaryStatistics;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button join = (Button)findViewById(R.id.joinbutton);
        Button login = (Button)findViewById(R.id.loginbutton);

        //Login 버튼이 눌리면 MainActivity로 가게함
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText login_id = (EditText) findViewById(R.id.idinput);
                EditText login_pw = (EditText) findViewById(R.id.pwinput);

                JSONObject postDataParam_login = new JSONObject(); //JSON생성 : JSONObject는 JSON형태의 데이터를 관리해 주는 메서드

                try {
                    postDataParam_login.put("id", login_id.getText().toString()); // 데이터 집어넣기
                    postDataParam_login.put("password", login_pw.getText().toString());

                    //로그인 요청
                    String result = new LoginRequest(LoginActivity.this).execute(postDataParam_login).get();

                    //결과값 받기.
                    JSONObject jsonObject = new JSONObject(result);
                    String success = jsonObject.getString("success");

                    if (success.equals("true")) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        ModeChange.act=2; // 메인 액티비티로
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호를 다시 확인하세요", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Log.e("TAG", "JSONEXception");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                new InsertData(LoginActivity.this).execute(postDataParam_login);

            }
        });

        //Join 버튼이 눌리면 JoinActivity로 가게함
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotojoin = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(gotojoin);
            }
        });
    }
}
