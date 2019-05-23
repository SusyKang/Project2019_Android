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

            String ip = activity.getResources().getString(R.string.ip_address);
            url = new URL(ip + "/test");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}