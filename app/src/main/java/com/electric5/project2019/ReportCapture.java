package com.electric5.project2019;

import android.net.Uri;

public class ReportCapture<uri extends Uri> {
    private uri item_image_uri;
    private String item_filename;

    public Uri getImageURI(){
        return item_image_uri;
    }

    public String getFileName(){
        return item_filename;
    }

    public void setImageURI(uri item_image_uri){
        this.item_image_uri = item_image_uri;
    }

    public void setFileName(String item_filename){
        this.item_filename = item_filename;
    }

    public ReportCapture(uri image_uri, String filename){
        item_image_uri = image_uri;
        item_filename = filename;
    }
}
