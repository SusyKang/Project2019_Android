package com.electric5.project2019;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by kh on 2016. 8. 25..
 */
public interface NetworkService {

    @Multipart
    @POST("/uploadsound")
    Call<ResponseBody> upload(@Part MultipartBody.Part file, @Part("name") RequestBody description);
   //Call<ResponseBody> upload(@Part ("file") RequestBody file, @Part("name") RequestBody description);
}
