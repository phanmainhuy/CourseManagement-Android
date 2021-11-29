package com.example.onlearn.API;

import com.example.onlearn.Model.DanhMuc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CallAPI {
    //http://localhost:63702/topcategory/

    @GET("topcategory/")
    Call<List<DanhMuc>> getDanhMuc ();



}
