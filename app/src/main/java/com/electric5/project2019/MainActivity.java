package com.electric5.project2019;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// TODO: 라즈베리에서 위험상황 json보내면 받아서 진동 or 소리
// 참고 링크 https://stackoverflow.com/questions/34063863/android-on-off-push-notification-when-on-off-toggle-button
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button b0 = (Button) findViewById(R.id.button0);
        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);


        final MainFragment mainFragment = new MainFragment();
        final StreamingFragment streamingFragment = new StreamingFragment();
        final ReportFragment reportFragment = new ReportFragment();
        final SettingFragment settingFragment = new SettingFragment();


        final FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, mainFragment);
        fragmentTransaction.commit();

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch(v.getId()){
                    case R.id.button0:
                        fragmentTransaction.replace(R.id.container, mainFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.button1:
                        fragmentTransaction.replace(R.id.container, streamingFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.button2:
                        fragmentTransaction.replace(R.id.container, reportFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.button3:
                        fragmentTransaction.replace(R.id.container, settingFragment);
                        fragmentTransaction.commit();
                        break;
                }
            }
        };


        b0.setOnClickListener(onClickListener);
        b1.setOnClickListener(onClickListener);
        b2.setOnClickListener(onClickListener);
        b3.setOnClickListener(onClickListener);


        SharedPreferences sf = getSharedPreferences("switchpref", Activity.MODE_PRIVATE);
        String vib_state = sf.getString("vib",""); //text라는 key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환
        String beep_state = sf.getString("beep","");

        Toast.makeText(this, "vib "+vib_state,Toast.LENGTH_LONG).show();
        Toast.makeText(this, "beep "+beep_state,Toast.LENGTH_LONG).show();

        // TODO: 라즈베리가 위험상황에 보낸 json 받아서 진동/소리 알림 실행
        if (vib_state.equals("on") && beep_state.equals("on")){ //진동 알림 on & 소리 알림 on일 때
            vibrate();
            beep();
        }
        else if (vib_state.equals("on")){ //진동 알림 on일 때
            vibrate();
        }
        else if (beep_state.equals("on")){ //소리 알림 on일 때
            beep();
        }
    }

    // 진동
    public void vibrate(){
        Vibrator vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        vib.vibrate(new long[]{500,1000,500,1000},-1); // 0.5초 대기 후 1초간 진동 2회, 반복 없음
        //vib.vibrate(1000); //1초간 진동
    }

    // 소리
    public void beep(){
        MediaPlayer player = MediaPlayer.create(this, R.raw.beep);
        player.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.editinfo:
                Intent editinfo = new Intent(this, EditInfoActivity.class);
                startActivity(editinfo);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
