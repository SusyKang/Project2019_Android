package com.electric5.project2019;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.electric5.project2019.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainFragment extends Fragment {
    public MainFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //유저정보 받아오는 코드
        SharedPreferences info = getActivity().getSharedPreferences("id", getActivity().MODE_PRIVATE);
        String localid = info.getString("id","");
        final TextView mainbabyname = (TextView)view.findViewById(R.id.mainbabyname);
        final TextView mainbyear = (TextView)view.findViewById(R.id.mainbyear);
        final TextView mainbmonth = (TextView)view.findViewById(R.id.mainbmonth);
        final TextView mainbday = (TextView)view.findViewById(R.id.mainbday);


        try {
            //로그인된 사용자 정보 로드
            JSONObject postDataParam = new JSONObject(); //JSON생성 : JSONObject는 JSON형태의 데이터를 관리해 주는 메서드
            postDataParam.put("id", localid);
            String result = new MyinfoRequest(getActivity()).execute(postDataParam).get();
            JSONObject jsonObject = new JSONObject(result);
            String success = jsonObject.getString("success");

            if (success.equals("true")) {
                String data = jsonObject.getString("data");
                JSONObject jsonObject2 = new JSONObject(data);
//                String babyname = jsonObject2.getString("baby");

                mainbabyname.setText(jsonObject2.getString("baby"));
                mainbyear.setText(jsonObject2.getString("Byear"));
                mainbmonth.setText(jsonObject2.getString("Bmonth"));
                mainbday.setText(jsonObject2.getString("Bday"));

            }
        } catch (JSONException e) {
            Log.e("TAG", "JSONEXception");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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
