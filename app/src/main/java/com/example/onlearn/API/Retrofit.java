package com.example.onlearn.API;

import com.example.onlearn.GLOBAL;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    //http://localhost:63702/topcategory/
    //http://192.168.1.9:45455/

    private static retrofit2.Retrofit getRetrofit() {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(GLOBAL.ip)
                .build();
        return retrofit;
    };

    public static CallAPIRetro getserviceAPI ()
    {
        CallAPIRetro callAPI_Retro_admin = getRetrofit().create(CallAPIRetro.class);
        return callAPI_Retro_admin;
    }
}
