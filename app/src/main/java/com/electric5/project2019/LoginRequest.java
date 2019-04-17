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
            String ip =  activity.getResources().getString(R.string.ip_address);
            url = new URL(ip + "/users/login");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}