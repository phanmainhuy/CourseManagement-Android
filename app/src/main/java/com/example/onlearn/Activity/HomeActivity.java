package com.example.onlearn.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.API.Retrofit;
import com.example.onlearn.Adapter.FavoriteCourseAdapter_rcl;
import com.example.onlearn.Model.DANHMUC;
import com.example.onlearn.Model.KHOAHOC;
import com.example.onlearn.Model.THELOAI;
import com.example.onlearn.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import com.example.onlearn.Adapter.OnClickRCL_Home;
import com.example.onlearn.Adapter.OptionAdapter_Home_rcl;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.Model.OPTION;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnClickRCL_Home {

    ViewFlipper viewQuangCao;
    DrawerLayout drawerLayout;
    NavigationView navigationLeft;
    Toolbar toolbar;

    ArrayList<OPTION> listOption = new ArrayList<>();
    RecyclerView rclOption, rclFavoriteCourses;
    OptionAdapter_Home_rcl optionAdapter;
    FavoriteCourseAdapter_rcl fvrCoursesAdapter;

    //url take most buy course
    String urlFvrCourses = GLOBAL.ip + "MostBuyCourse/?limit=10";

    //tao list data
    public static List<DANHMUC> danhMuc;
    public static List<THELOAI> danhMucConList;
//    private List<KHOAHOC> lstKhoahoc = LoginActivity.favoriteCourses;

    ArrayList<KHOAHOC> data = new ArrayList<>();

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
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + "Trang chủ" + "</font>"));
        //doi mau thanh action bars
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);


        //anh xa
        viewQuangCao = findViewById(R.id.viewQuangCao_Home);
        toolbar = findViewById(R.id.toolbar_Home);
        navigationLeft = findViewById(R.id.navigationviewLeft_Home);
        drawerLayout = findViewById(R.id.drawerlayout_Home);
        rclOption = findViewById(R.id.rclOption_Home);
        rclFavoriteCourses = findViewById(R.id.rcl_BestSellingCourse_Home);

        //setdata favorite courses

        //set cung khoa hoc
        /*ArrayList<KHOAHOC> data = new ArrayList<>();*/

        fvrCoursesAdapter = new FavoriteCourseAdapter_rcl(this, data, this);
        rclFavoriteCourses.setHasFixedSize(true);
        rclFavoriteCourses.setAdapter(fvrCoursesAdapter);
        rclFavoriteCourses.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

//        rclFavoriteCourses.notifyDataSetChanged();


        //get data
        GetDanhMuc();
        getOptionHome();
        getFavoriteCourses();


        //toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //Xu ly Navigation Left
        //tao su kien click navigationLeft
        navigationLeft.setNavigationItemSelectedListener(this);
        //load quang cao
        loadViewFlipper();

    }

    private void getFavoriteCourses() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    data.add(new KHOAHOC(jsonObject.getInt("MaKhoaHoc"), jsonObject.getInt("MaLoai"),
                            jsonObject.getString("TenKhoaHoc"), jsonObject.getString("DonGia"),
                            jsonObject.getInt("SoLuongMua"), jsonObject.getString("TrangThai")
                            , jsonObject.getString("HinhAnh"), jsonObject.getInt("MaGV")
                            , jsonObject.getString("TenGV"), jsonObject.getInt("DanhGia")
                            , jsonObject.getString("GioiThieu"), jsonObject.getString("NgayTao")
                            , jsonObject.getString("NgayChapThuan")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            fvrCoursesAdapter.notifyDataSetChanged();
        };

        com.android.volley.Response.ErrorListener thatbai = error ->
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlFvrCourses, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
    }


    void getOptionHome() {

        //add data Option
        listOption.add(new OPTION(R.drawable.ic_folder, "Danh mục"));
        listOption.add(new OPTION(R.drawable.ic_khuyenmai, "Khuyến mãi"));
        listOption.add(new OPTION(R.drawable.ic_option, "Tìm kiếm"));
        listOption.add(new OPTION(R.drawable.ic_options, "Giới thiệu"));

        //load Option
        rclOption.setAdapter(new OptionAdapter_Home_rcl(this, listOption, this));
        rclOption.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


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
        if (mSelectedId == R.id.mnu_classroom) {
            intent = new Intent(HomeActivity.this, ClassRoomActivity.class);
            startActivity(intent);

            drawerLayout.closeDrawer(GravityCompat.START);
        }

//            if (mSelectedId == R.id.mnu_address) {
//                intent = new Intent(HomeActivity.this, LocationActivity.class);
//                startActivity(intent);
//
//                drawerLayout.closeDrawer(GravityCompat.START);
//            }
        if (mSelectedId == R.id.mnu_contact) {
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0948462040"));
            startActivity(intent);
        }
//            if(mSelectedId == R.id.mnu_help)
//            {
//                intent = new Intent(this,SupportChatActivity.class);
//                startActivity(intent);
//            }

        if(mSelectedId == R.id.mnu_logout)
        {
            intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
    }


    //hien thi icon tren action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_search,menu);
        getMenuInflater().inflate(R.menu.menu_notification, menu);
        getMenuInflater().inflate(R.menu.menu_cart, menu);

        return true;
    }

    //xu ly chon menu tu Navigation
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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_notification) {
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }
        return true;
    }


    @Override
    public void itemClickOption(OPTION option) {
        if (option.getTitle().equals("Khuyến mãi")) {
            Intent intent = new Intent(this, PromotionActivity.class);
            startActivity(intent);
        }
        if (option.getTitle().equals("Danh mục")) {
            Intent intent1 = new Intent(this, DanhMucActivity.class);
            startActivity(intent1);
        }
        if (option.getTitle().equals("Tìm kiếm")) {
            Intent intent1 = new Intent(this, SearchActivity.class);
            startActivity(intent1);
        }
        if (option.getTitle().equals("Giới thiệu")) {
            Intent intent1 = new Intent(this, LoginActivity.class);
            startActivity(intent1);
        }

    }

    @Override
    public void ItemClickFavorite(KHOAHOC favorite_course) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    //Lay danh muc
    private void GetDanhMuc() {
        Call<List<DANHMUC>> danhMucCall = Retrofit.getserviceAPI().getDanhMuc();
        danhMucCall.enqueue(new Callback<List<DANHMUC>>() {
            @Override
            public void onResponse(Call<List<DANHMUC>> call, Response<List<DANHMUC>> response) {
                danhMuc = response.body();
                Log.e("ERRr", response.message());
            }

            @Override
            public void onFailure(Call<List<DANHMUC>> call, Throwable t) {
                Log.e("ERRr", t.getMessage());
            }
        });
    }


}