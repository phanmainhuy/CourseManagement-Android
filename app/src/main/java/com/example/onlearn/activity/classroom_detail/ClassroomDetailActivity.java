package com.example.onlearn.activity.classroom_detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import java.text.ParseException;

public class ClassroomDetailActivity extends AppCompatActivity {
    String titleActionBar = "Chi tiết khóa học " ;
    String urlimg = GLOBAL.ip + GLOBAL.urlimg + "courses/";
    ImageView imgKH;
    TextView tvTenKH, tvTenGV, tvNgayMua, tvGioiThieu;
    Button btnLearn;
    RatingBar ratingBar;

    String urlgetchuong = GLOBAL.ip + "api/chuong?MaKhoaHoc=" + GLOBAL.learn.getMaKH();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_detail);
        DecorateActionBar();

        //Anh xa
        imgKH = findViewById(R.id.imgKH_classroom);
        tvTenKH = findViewById(R.id.tvTenKH_classroom);
        tvTenGV = findViewById(R.id.tvTenGV_classroom);
        tvGioiThieu = findViewById(R.id.tvGioiThieu_classroom);
        tvNgayMua = findViewById(R.id.tvNgayMua_classroom);
        btnLearn = findViewById(R.id.btnLearn_classroom);
        ratingBar = findViewById(R.id.rating_classroom);

        //getdata
        try {
            getDetailClassroom();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    private void getDetailClassroom() throws ParseException {
        tvTenKH.setText(GLOBAL.learn.getTenKH());
        tvTenGV.setText(GLOBAL.learn.getTenGV());
        ratingBar.setRating(GLOBAL.learn.getRating());
        tvNgayMua.setText(utils.converDateFormate(GLOBAL.learn.getNgaymua()));
        tvGioiThieu.setText(GLOBAL.learn.getGioithieu());
        Picasso.with(this)
                .load(urlimg + GLOBAL.learn.getImgKH())
                .placeholder(R.drawable.no_image_found)
                .into(imgKH);
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