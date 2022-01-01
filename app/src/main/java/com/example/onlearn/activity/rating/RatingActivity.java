package com.example.onlearn.activity.rating;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;

public class RatingActivity extends AppCompatActivity {
    String titleActionBar = "Đánh giá khóa học";

    String urlgetImgUser = GLOBAL.ip + GLOBAL.urlimg + "users/";
    String urlgetImgCourses = GLOBAL.ip + GLOBAL.urlimg + "courses/";
    String urlgetUserRating = GLOBAL.ip + "api/DanhGia?MaKhoaHoc="+GLOBAL.learn.getMaKH()+"&&MaND=" + GLOBAL.idUser;

    ImageView imgKH, imgUser;
    TextView tvTenKH, tvUserName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        DecorateActionBar();

        //map
        imgKH = findViewById(R.id.imgKH_Rating);
        imgUser = findViewById(R.id.imgAvatarUser_Rating);
        tvTenKH = findViewById(R.id.tvTenKH_Rating);
        tvUserName = findViewById(R.id.tvUsername_Rating);

        //set data




    }


    //action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void DecorateActionBar(){
        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + titleActionBar + "</font>"));
        //doi mau thanh action bars
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
    }



}