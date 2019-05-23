package com.electric5.project2019;

import android.app.Activity;

import java.net.MalformedURLException;
import java.net.URL;

public class ControlRequest extends PostRequest {

    public ControlRequest(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
        try {
            String ip =  "http://223.194.134.64:80/test"; // TODO: 라즈베리파이 서버 주소
            url = new URL(ip);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}