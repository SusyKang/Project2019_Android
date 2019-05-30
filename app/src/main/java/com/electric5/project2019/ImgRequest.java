package com.electric5.project2019;

import android.app.Activity;

import java.net.MalformedURLException;
import java.net.URL;

//회원가입
public class ImgRequest extends PostRequest {

    public ImgRequest(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
        try {
            String ip = activity.getResources().getString(R.string.ip_address);

            url = new URL(ip + "/boards/img");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
