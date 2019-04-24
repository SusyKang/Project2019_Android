package com.electric5.project2019.Fragment;


import android.app.Activity;

import com.electric5.project2019.PostRequest;
import com.electric5.project2019.R;

import java.net.MalformedURLException;
import java.net.URL;

public class MyinfoRequest extends PostRequest {
    URL url;
    public MyinfoRequest(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
        try {
            String ip = activity.getResources().getString(R.string.ip_address);
            url = new URL(  ip+ "/boards/get_info");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(String jsonString) {
        //Toast.makeText(activity,jsonString,Toast.LENGTH_SHORT).show();
    }
}