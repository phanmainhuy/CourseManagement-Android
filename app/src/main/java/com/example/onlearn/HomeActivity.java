package com.example.onlearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ViewFlipper viewQuangCao;
    DrawerLayout drawerLayout;
    NavigationView navigationLeft;
    Toolbar toolbar;

    //navigation handle
    private int mSelectedId;
    private static final String SELECTED_ITEM_ID = "selected"; //nguoi dung da select item
    //private static final String FRIST_TIME = "fist_time"; // nguoi dung select lan dau
    private boolean mUserSawDrawer = false; //neu nguoi dung mo thi sau do khong hien thi lai

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" +"Trang chủ"+ "</font>"));
        //doi mau thanh action bars
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#00CD66"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);






        //anh xa
        viewQuangCao = findViewById(R.id.viewQuangCao_Home);
        toolbar = findViewById(R.id.toolbar_Home);
        navigationLeft = findViewById(R.id.navigationviewLeft_Home);
        drawerLayout = findViewById(R.id.drawerlayout_Home);

        //toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //Xu ly Navigation Left
        //tao su kien click navigationLeft
        navigationLeft.setNavigationItemSelectedListener(this);

        loadViewFlipper();

    }

    void loadViewFlipper() {

        ArrayList<Integer> mangslide = new ArrayList<>();

        mangslide.add(R.drawable.slide1);
        mangslide.add(R.drawable.slide2);
        mangslide.add(R.drawable.slide3);
        mangslide.add(R.drawable.slide4);


        for (int i = 0; i < mangslide.size(); i++) {
            ImageView imageView = new ImageView(this);
            Picasso.with(this).load(mangslide.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewQuangCao.addView(imageView);
        }

        viewQuangCao.setAutoStart(true);
        viewQuangCao.setFlipInterval(2000);
        viewQuangCao.startFlipping();
    }
    //dieu huong navigation
    private void navigation(int mSelectedId) {
        Intent intent = null;
        if (mSelectedId == R.id.mnu_user) {
            intent = new Intent(HomeActivity.this, UserActivity.class);
            startActivity(intent);

            drawerLayout.closeDrawer(GravityCompat.START);
        }
//        if (mSelectedId == R.id.mnu_cart) {
//            intent = new Intent(HomeActivity.this, CartActivity.class);
//            startActivity(intent);
//
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }

//            if (mSelectedId == R.id.mnu_address) {
//                intent = new Intent(HomeActivity.this, LocationActivity.class);
//                startActivity(intent);
//
//                drawerLayout.closeDrawer(GravityCompat.START);
//            }
        if(mSelectedId == R.id.mnu_contact)
        {
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:028 3510 6870"));
            startActivity(intent);
        }
//            if(mSelectedId == R.id.mnu_help)
//            {
//                intent = new Intent(this,SupportChatActivity.class);
//                startActivity(intent);
//            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        getMenuInflater().inflate(R.menu.menu_notification,menu);
        getMenuInflater().inflate(R.menu.menu_cart,menu);

        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //Intent intent = null;
        menuItem.setChecked(true);
        mSelectedId = menuItem.getItemId();
        navigation(mSelectedId);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_ITEM_ID, mSelectedId);
    }

    //nut tro ve navigation
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cart)
        {
            Intent intent = new Intent(this,CartActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_notification)
        {
            Intent intent = new Intent(this,ForgetpassActivity.class);
            startActivity(intent);
        }
        return true;
    }


}