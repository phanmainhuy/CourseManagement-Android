package com.example.onlearn.API;

import com.example.onlearn.Model.DANHMUC;
import com.example.onlearn.Model.KHOAHOC;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CallAPI {
    //http://localhost:63702/topcategory/

    @GET("topcategory/")
    Call<List<DANHMUC>> getDanhMuc ();

    @GET("MostBuyCourse/?limit=10")
    Call<List<KHOAHOC>> getFavoriteCourses ();



}
