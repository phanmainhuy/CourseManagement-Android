package com.example.onlearn.API;

import com.example.onlearn.models.DANHMUC;
import com.example.onlearn.models.MESSAGE;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CallAPIRetro {
    //chatbot
    @GET
    Call<MESSAGE> getMessage(@Url String url);


    //http://localhost:63702/topcategory/
    @GET("topcategory/")
    Call<List<DANHMUC>> getDanhMuc ();


//    @GET("MostBuyCourse/")
//    Call<List<KHOAHOC>> getFavoriteCourses (@Query("limit") int limits);



}
