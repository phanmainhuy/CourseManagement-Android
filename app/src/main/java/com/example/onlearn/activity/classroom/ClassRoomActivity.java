package com.example.onlearn.activity.classroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;

public class ClassRoomActivity extends AppCompatActivity {
    String titleActionBar = "Phòng học của " + GLOBAL.userlogin.getTen();

    String urlClassroom = GLOBAL.ip + "api/KhoaHocTheoHocVien?MaHV=" +GLOBAL.idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);
        DecorateActionBar();






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