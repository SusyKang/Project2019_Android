package com.electric5.project2019;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;


class ReportCapture {
    String item_datetime; // text
    String item_capture_name; // text
    ReportCapture(String datetime, String capturename) { item_datetime = datetime; item_capture_name = capturename; }
}

// 캡쳐 파일 불러오는 어댑터
public class ListViewAdapter2 extends BaseAdapter {
    private LayoutInflater inflater;
    private Context mContext;
    private int mResource;
    private ArrayList<ReportCapture> mItems = new ArrayList<ReportCapture>();

    String ip = mContext.getResources().getString(R.string.ip_address);

    public ListViewAdapter2(Context context, int layout, ArrayList<ReportCapture> datas){
        mContext = context;
        mItems = datas;
        mResource = layout;
    }

    @Override
    public int getCount(){ return mItems.size(); }
    @Override
    public Object getItem(int position) { return mItems.get(position); }
    @Override
    public long getItemId(int position) { return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            LayoutInflater inflater =
                    (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(mResource,parent,false);
        }

        ReportCapture reportCapture = mItems.get(position);

        TextView item_datetime = (TextView)convertView.findViewById(R.id.item_datetime);
        WebView item_capture = (WebView) convertView.findViewById(R.id.item_capture);

        item_capture.setBackgroundColor(0); //배경색 투명
        item_capture.setHorizontalScrollBarEnabled(false); //가로 스크롤
        item_capture.setVerticalScrollBarEnabled(false);   //세로 스크롤
        item_capture.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY); // 스크롤 노출 타입
        item_capture.setScrollbarFadingEnabled(true); // 스크롤 페이딩 처리 여부

        WebSettings settings = item_capture.getSettings(); //웹뷰 셋팅
        //settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setBuiltInZoomControls(false);

        settings.setJavaScriptEnabled(true);                         //자바스크립트 허용
        //settings.setJavaScriptCanOpenWindowsAutomatically(true);     //window.open() 동작하려면 필요
        //settings.setLoadsImagesAutomatically(true);                 // 웹뷰가 앱에 등록되어 있는 이미지 리로스를 자동으로 로드 하는속성
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);                           //html 컨텐츠가 웹뷰에 맞게 나타남


        //settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //settings.setAppCacheEnabled(false);     //앱 내부 캐시 사용여부

        item_capture.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        if (Build.VERSION.SDK_INT >= 21) { item_capture.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW); }

        item_datetime.setText(mItems.get(position).item_datetime);


        String capture_path = ip + mItems.get(position).item_capture_name;
        item_capture.loadUrl(capture_path);

        return convertView;
    }
}