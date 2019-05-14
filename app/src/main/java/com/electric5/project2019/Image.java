package com.electric5.project2019;

import android.widget.ImageView;

public class Image {
    public static int id;
    private ImageView ImageCapture;
    private String ImageFileName;


    public Image(int id, ImageView ImageCapture, String ImageFileName) {
        this.id = id;
        this.ImageCapture = ImageCapture;
        this.ImageFileName = ImageFileName;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public ImageView getImageCapture() {
        return ImageCapture;
    }
    public void setImageCapture(ImageView ImageCapture) {
        this.ImageCapture = ImageCapture;
    }

    public String getImageFileName() {
        return ImageFileName;
    }
    public void setImageFileName(String ImageFileName) {
        this.ImageFileName = ImageFileName;
    }

}
