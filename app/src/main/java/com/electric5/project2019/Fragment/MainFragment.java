package com.electric5.project2019.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.electric5.project2019.ModeChange;
import com.electric5.project2019.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.content.Context.MODE_PRIVATE;

public class MainFragment extends Fragment {
    public MainFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //유저정보 받아오는 코드
        SharedPreferences info = getActivity().getSharedPreferences("id", getActivity().MODE_PRIVATE);
        String babyname = info.getString("id","");
        final TextView mainbabyname = (TextView)view.findViewById(R.id.mainbabyname);

        if (info==null){
            mainbabyname.setText("kkk");
        } else{
            mainbabyname.setText(babyname);

        }

     /*
        try {

            JSONObject postDataParam = new JSONObject();
            postDataParam.put("id", info.getString("id",""));

            String result = new MyinfoRequest(getActivity()).execute(postDataParam).get();

            //결과값 받기.
            JSONObject jsonObject = new JSONObject(result);
            JSONArray dataArray = jsonObject.getJSONArray("data");

            if (result!=null) {
                ArrayList<String> data = new ArrayList<String>();

                for(int i=0; i<dataArray.length(); i++) {
                    JSONObject jsonObject2 = dataArray.getJSONObject(i);

                    data.add(jsonObject2.getString("baby"));
                }
                final TextView mainbabyname = (TextView)view.findViewById(R.id.mainbabyname);
                mainbabyname.setText((CharSequence) data);

            } else {
                final TextView mainbabyname = (TextView)view.findViewById(R.id.mainbabyname);
                mainbabyname.setText("ttt");            }
            /*
            JSONObject jsonObject = new JSONObject(result);
            JSONArray dataArraye = jsonObject.getJSONArray("data");

            String babyname = dataArraye.getString(1);
            final TextView mainbabyname = (TextView)view.findViewById(R.id.mainbabyname);
            mainbabyname.setText(babyname);
*/
            /*
            String result = new MyinfoRequest(getActivity()).execute(local).get();

            if (result==null) {
                final TextView mainbabyname = (TextView)view.findViewById(R.id.mainbabyname);
                mainbabyname.setText(local[0]);
            } else {
                JSONObject jsonObject = new JSONObject(result);
                String data = jsonObject.getString("data");
                JSONObject jsonObject2 = new JSONObject(data);
                String babyname = jsonObject2.getString("baby");

                final TextView mainbabyname = (TextView)view.findViewById(R.id.mainbabyname);
                mainbabyname.setText(babyname);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
*/
        return view;
    }
}
