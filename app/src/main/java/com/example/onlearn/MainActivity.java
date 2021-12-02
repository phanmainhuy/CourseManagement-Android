package com.example.onlearn;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.onlearn.API.Retrofit;
import com.example.onlearn.Activity.LoginActivity;
import com.example.onlearn.Model.KHOAHOC;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static List<KHOAHOC> favoriteCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        },2000);

        getFavoriteCourses();


    }

    private void getFavoriteCourses()
    {
        Call<List<KHOAHOC>> favoriteCall = Retrofit.getserviceAPI().getFavoriteCourses();
        favoriteCall.enqueue(new Callback<List<KHOAHOC>>() {
            @Override
            public void onResponse(Call<List<KHOAHOC>> call, Response<List<KHOAHOC>> response) {
                favoriteCourses = response.body();
                Log.e("ERRr",response.message());
            }

            @Override
            public void onFailure(Call<List<KHOAHOC>> call, Throwable t) {
                Log.e("ERRr", t.getMessage());
            }

        });
    }





}