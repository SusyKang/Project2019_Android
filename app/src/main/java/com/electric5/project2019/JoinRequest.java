package com.electric5.project2019;

import android.app.Activity;

import java.net.MalformedURLException;
import java.net.URL;

//회원가입
public class JoinRequest extends PostRequest {
    //final static String Url = "http://192.168.200.125:3000";

    public JoinRequest(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
        try {
            String ip = "http://192.168.0.28:80"; // TODO: 테스트 시 수정
            //집  172.30.1.21
            //304  192.168.0.28
            // http://서버주소:80
            url = new URL(ip + "/users/join");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
