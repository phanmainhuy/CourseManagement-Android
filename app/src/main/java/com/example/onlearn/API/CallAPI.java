package com.example.onlearn.API;

import com.example.onlearn.Model.DANHMUC;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CallAPI {
    //http://localhost:63702/topcategory/

    @GET("topcategory/")
    Call<List<DANHMUC>> getDanhMuc ();



}
