package com.electric5.project2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


// TODO: 리포트 프래그먼트에서 넘어온 파일명, 이미지 값 가져와야함
// 파일명 popup_filename, 이미지 popup_image
// 참고 링크 https://binshuuuu.tistory.com/57
public class PopUpActivity extends AppCompatActivity {

    Button okbutton;
    TextView popup_filename;
    ImageView popup_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //상태바 제거, 전체화면 모드
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_pop_up);

        okbutton = (Button) findViewById(R.id.popup_ok);

        popup_filename = (TextView) findViewById(R.id.popup_filename);
        popup_image = (ImageView) findViewById(R.id.popup_image);
    }

    //확인 버튼 눌렸을 때 팝업창 닫기
    public void okbtn(View view){
        finish();
    }

    //바깥 쪽 클릭 시 창이 닫히지 않게 함
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}
