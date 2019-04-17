package com.electric5.project2019;

import android.app.Activity;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginRequest extends PostRequest {

    public LoginRequest(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
        try {
            String ip = "http://223.194.154.120:80"; // TODO: 테스트 시 수정
            url = new URL(ip + "/users/login");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}