package com.electric5.project2019;


import android.app.Activity;
import android.widget.Toast;

import com.electric5.project2019.PostRequest;
import com.electric5.project2019.R;

import java.net.MalformedURLException;
import java.net.URL;

public class MyinfoRequest extends PostRequest {
    //static URL url;
    public MyinfoRequest(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
        try {
            String ip = activity.getResources().getString(R.string.ip_address);
            url = new URL(  ip+ "/boards/getinfo");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(String jsonString) {
        //Toast.makeText(activity,jsonString,Toast.LENGTH_SHORT).show();
    }
}