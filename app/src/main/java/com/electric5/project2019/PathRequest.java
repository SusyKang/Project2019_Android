package com.electric5.project2019;

import android.app.Activity;

import java.net.MalformedURLException;
import java.net.URL;

//회원가입
public class PathRequest extends PostRequest {
    public PathRequest(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
        try {
            String ip = activity.getResources().getString(R.string.ip_address);

            url = new URL(ip + "/users/userimg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
