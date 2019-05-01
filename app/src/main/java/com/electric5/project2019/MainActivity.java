package com.electric5.project2019;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
