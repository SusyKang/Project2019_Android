package com.electric5.project2019;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
//TODO: 로그인된 사용자의 정보값을 기본적으로 띄워주고 edit 버튼 누르면 수정완료
public class EditInfoActivity extends Activity {

    EditText edit_pw, edit_pw2, editbabyname, editbirthy, editbirthm, editbirthd;
    ImageView setImage2; // 비밀번호 일치 확인 이미지
    RadioGroup editgendergroup;
    RadioButton editgenderFemale, editgenderMale;
    String editgender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        Button edit = (Button) findViewById(R.id.editbutton);

        edit_pw = (EditText) findViewById(R.id.editpwinput);
        edit_pw2 = (EditText) findViewById(R.id.editpwinput2);

        editbabyname = (EditText) findViewById(R.id.editbabynameinput);

        editgendergroup = (RadioGroup) findViewById(R.id.editgenderGroup);
        editgenderFemale = (RadioButton) findViewById(R.id.editgenderFemale);
        editgenderMale = (RadioButton) findViewById(R.id.editgenderMale);

        editbirthy = (EditText) findViewById(R.id.editbirthyear);
        editbirthm = (EditText) findViewById(R.id.editbirthmonth);
        editbirthd = (EditText) findViewById(R.id.editbirthday);

        setImage2 = (ImageView)findViewById(R.id.setImage2);

        // 비밀번호 & 비밀번호 재입력 텍스트 일치하는 지 비교
        edit_pw2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(edit_pw.getText().toString().equals(edit_pw2.getText().toString())) {
                    setImage2.setImageResource(R.drawable.checked);
                } else {
                    setImage2.setImageResource(R.drawable.unchecked);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        // RadioButton의 체크 상태에 따라 변화값을 주기 위해 setOncheckedChangeLinstener()메소드를 사용
        editgendergroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.editgenderFemale:
                        editgender = "F";
                        break;
                    case R.id.editgenderMale:
                        editgender = "M";
                        break;
                }
            }
        });





        // edit 버튼이 눌리면 정보수정 (db 업데이트)
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //유저정보 받아오는 코드
                SharedPreferences info = getSharedPreferences("id", MODE_PRIVATE);
                String localid = info.getString("id","");
                // JSON생성 : JSONObject는 JSON형태의 데이터를 관리해 주는 메서드
                JSONObject postDataParam = new JSONObject();

                try{
                    postDataParam.put("id", localid);
                    if (edit_pw.getText().toString().length() != 0) {
                        // 비밀번호&비밀번호확인 -- 일치하지 않으면 수정 불가
                        if (edit_pw.getText().toString().equals(edit_pw2.getText().toString())) {
                            //Toast.makeText(JoinActivity.this, "비밀번호 일치 확인", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EditInfoActivity.this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                            edit_pw2.requestFocus();
                            return;
                        }
                        if (edit_pw2.getText().toString().length() == 0) {
                            Toast.makeText(EditInfoActivity.this, "password 재확인을 입력하세요", Toast.LENGTH_SHORT).show();
                            edit_pw2.requestFocus();
                            return;
                        }
                        postDataParam.put("password", edit_pw.getText().toString());
                    }

                    if (editbabyname.getText().toString().length() != 0) {
                        postDataParam.put("baby", editbabyname.getText().toString());

                    }
                    if ((editgenderFemale.isChecked()) | (editgenderMale.isChecked())) {
                        postDataParam.put("gender", editgender);
                    }
                    if (editbirthy.getText().toString().length() != 0) {
                        postDataParam.put("Byear", editbirthy.getText().toString());
                    }
                    if (editbirthm.getText().toString().length() != 0) {
                        postDataParam.put("Bmonth", editbirthm.getText().toString());
                    }
                    if (editbirthd.getText().toString().length() != 0) {
                        postDataParam.put("Bday", editbirthd.getText().toString());
                    }


                    String result = new EditInfoRequest(EditInfoActivity.this).execute(postDataParam).get();
                    JSONObject jsonObject = new JSONObject(result);
                    String success = jsonObject.getString("success");


                    if (success.equals("true")) {
                        Toast.makeText(getApplicationContext(),"회원정보 수정 완료",Toast.LENGTH_LONG).show();
                        ModeChange.act = 2;
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
    }

}