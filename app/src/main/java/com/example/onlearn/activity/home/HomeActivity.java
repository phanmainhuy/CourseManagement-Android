package com.example.onlearn.activity.home;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.activity.change_pass.ChangePassActivity;
import com.example.onlearn.activity.introduction.IntroductionActivity;
import com.example.onlearn.activity.search.SearchActivity;
import com.example.onlearn.activity.profile_user.ProfileUserActivity;
import com.example.onlearn.activity.cart.CartActivity;
import com.example.onlearn.activity.category_big.DanhMucActivity;
import com.example.onlearn.activity.notification.NotificationActivity;
import com.example.onlearn.activity.chatbot.SupportChatActivity;
import com.example.onlearn.activity.classroom.ClassRoomActivity;
import com.example.onlearn.activity.coupon.CouponActivity;
import com.example.onlearn.activity.coupon_wallet.CouponWalletActivity;
import com.example.onlearn.activity.detail_course.DetailCourseActivity;
import com.example.onlearn.activity.login.LoginActivity;
import com.example.onlearn.models.CART;
import com.example.onlearn.models.DANHMUC;
import com.example.onlearn.models.KHOAHOC;
import com.example.onlearn.R;
import com.example.onlearn.models.PAY_ReceiptOrder;
import com.example.onlearn.models.USER;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.models.OPTION;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnClickRCL_Home {

    ViewFlipper viewQuangCao;
    DrawerLayout drawerLayout;
    NavigationView navigationLeft;
    Toolbar toolbar;

    ArrayList<OPTION> listOption = new ArrayList<>();
    RecyclerView rclOption, rclFavoriteCourses;
    OptionAdapter_Home_rcl optionAdapter;
    TopBuyCourseAdapter_rcl fvrCoursesAdapter;


    String urlGetIDCart = GLOBAL.ip + "api/cartitem/?pUserID=" + GLOBAL.idUser;

    String urlUser = GLOBAL.ip + "api/hocvien?userId=" + GLOBAL.idUser;
    String urlImgUser = GLOBAL.ip + GLOBAL.urlimg + "users/";


    //url take most buy course
    String urlFvrCourses = GLOBAL.ip + "MostBuyCourse/?limit=10";

    //tao list data
    public static List<DANHMUC> danhMuc;
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
        navigationLeft = findViewById(R.id.navigationviewLeft_Home);
        drawerLayout = findViewById(R.id.drawerlayout_Home);
        rclOption = findViewById(R.id.rclOption_Home);
        rclFavoriteCourses = findViewById(R.id.rcl_BestSellingCourse_Home);
        toolbar = findViewById(R.id.toolbar_Home);

        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        //setdata favorite courses

        //set cung khoa hoc
        /*ArrayList<KHOAHOC> data = new ArrayList<>();*/

        fvrCoursesAdapter = new TopBuyCourseAdapter_rcl(this, data, this);
        rclFavoriteCourses.setHasFixedSize(true);
        rclFavoriteCourses.setAdapter(fvrCoursesAdapter);
        rclFavoriteCourses.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

//        rclFavoriteCourses.notifyDataSetChanged();


        //get data
//        GetDanhMuc();
        getOptionHome();
        getMostBuyCourses();
        getInfoUser();
//        Toast.makeText(getApplicationContext(), lstUser.size(), Toast.LENGTH_SHORT).show();


//        //toolbar
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


        //Xu ly Navigation Left
        //tao su kien click navigationLeft
        navigationLeft.setNavigationItemSelectedListener(this);
        //load quang cao
        loadViewFlipper();
//        getCart();

    }


    private void getMostBuyCourses() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    if (jsonObject.getBoolean("HienThi") == true) {
                        data.add(new KHOAHOC(jsonObject.getInt("MaKhoaHoc"), jsonObject.getInt("MaLoai"),
                                jsonObject.getInt("MaDM"), jsonObject.getString("TenLoai"),
                                jsonObject.getString("TenDanhMuc"),
                                jsonObject.getString("TenKhoaHoc"), jsonObject.getString("DonGia"),
//                            jsonObject.getInt("SoLuongMua") "",
                                jsonObject.getString("TrangThai")
                                , jsonObject.getString("HinhAnh"), jsonObject.getInt("MaGV")
                                , jsonObject.getString("TenGV"), jsonObject.getInt("DanhGia")
                                , jsonObject.getString("GioiThieu"), jsonObject.getString("NgayTao")
                                , jsonObject.getString("NgayChapThuan")));
                    }
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
        listOption.add(new OPTION(R.drawable.ic_searchhome, "Tìm kiếm"));
        listOption.add(new OPTION(R.drawable.ic_options, "Giới thiệu"));


        //load Option
        rclOption.setAdapter(new OptionAdapter_Home_rcl(this, listOption, this));
        rclOption.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        rclOption.setHasFixedSize(true);

    }

    void loadViewFlipper() {
        String urlCarousel = GLOBAL.ip + "api/decorating?MaLoaiTrangTri=0";
        String urlimgCarousel = GLOBAL.ip + "assets/images/carousel/";
        ArrayList<String> mangslide = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    if (jsonObject.getInt("MaLoaiTrangTri") == 0) {
                        mangslide.add(jsonObject.getString("GiaTri"));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            for (int x = 0; x < mangslide.size(); x++) {
                ImageView imageView = new ImageView(this);
                Picasso.with(this).load(urlimgCarousel + mangslide.get(x)).into(imageView);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                viewQuangCao.addView(imageView);
            }
            viewQuangCao.setAutoStart(true);
            viewQuangCao.setFlipInterval(2000);
            viewQuangCao.startFlipping();
        };

        com.android.volley.Response.ErrorListener thatbai = error ->
        {
            if (error.getMessage() != null) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlCarousel, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

    }

    //dieu huong navigation
    private void navigation(int mSelectedId) {
        Intent intent = null;
        if (mSelectedId == R.id.mnu_user) {
            intent = new Intent(HomeActivity.this, ProfileUserActivity.class);
            startActivity(intent);

            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if (mSelectedId == R.id.mnu_classroom) {
            intent = new Intent(HomeActivity.this, ClassRoomActivity.class);
            startActivity(intent);

            drawerLayout.closeDrawer(GravityCompat.START);
        }

        if (mSelectedId == R.id.mnu_wallet) {
            intent = new Intent(HomeActivity.this, CouponWalletActivity.class);
            startActivity(intent);
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        if (mSelectedId == R.id.mnu_contact) {
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0948462040"));
            startActivity(intent);
        }
        if (mSelectedId == R.id.mnu_help) {
            intent = new Intent(this, SupportChatActivity.class);
            startActivity(intent);
        }

        if(mSelectedId == R.id.mnu_changepass){
            intent = new Intent(this, ChangePassActivity.class);
            startActivity(intent);
        }

        if (mSelectedId == R.id.mnu_logout) {
            GLOBAL.notifications.clear();
//            GLOBAL.itemsCart_items.clear();
            intent = new Intent(this, LoginActivity.class);
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


    //icon menu select
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
            Intent intent = new Intent(this, CouponActivity.class);
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
            Intent intent1 = new Intent(this, IntroductionActivity.class);
            startActivity(intent1);
        }

    }

    @Override
    public void ItemClickCourse(KHOAHOC data) {
        GLOBAL.KhoaHocClick = data;
        Intent intent = new Intent(this, DetailCourseActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void getInfoUser() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        com.android.volley.Response.Listener<JSONObject> thanhcong = response -> {
            try {

                GLOBAL.userlogin = new USER(response.getInt("UserId"),
                        response.getString("UserName"),
                        response.getString("Name"),
                        response.getString("Email"),
                        response.getString("DoB"),
                        response.getString("Gender"),
                        response.getString("Address"),
                        response.getString("Number"),
                        response.getString("CMND"),
                        response.getString("HinhAnh"),
                        response.getInt("DiemTichLuy"),
                        response.getInt("GroupID"),
                        response.getString("Salary")

                );
                GLOBAL.infoThuHo = new PAY_ReceiptOrder(
                        response.getString("Name"),
                        response.getString("Email"),
                        response.getString("Address"),
                        response.getString("Number")
                );

            } catch (JSONException e) {
                e.printStackTrace();
            }


        };
        com.android.volley.Response.ErrorListener thatbai = error ->
        {
            if (error.getMessage() != null) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlUser, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
    }

    public void getCart() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONObject> thanhcong = response -> {

            try {
                GLOBAL.cart = new CART(response.getInt("CourseCartID"),
                        response.getInt("UserID"),
                        response.getString("TongTien")
                );
            } catch (JSONException e) {
                e.printStackTrace();
            }


        };

        com.android.volley.Response.ErrorListener thatbai = error -> {
            if (error.getMessage() != null) {

//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlGetIDCart, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);


    }

//    //Lay danh muc
//    private void GetDanhMuc() {
//        Call<List<DANHMUC>> danhMucCall = Retrofit.getserviceAPI().getDanhMuc();
//        danhMucCall.enqueue(new Callback<List<DANHMUC>>() {
//            @Override
//            public void onResponse(Call<List<DANHMUC>> call, Response<List<DANHMUC>> response) {
//                danhMuc = response.body();
//                Log.e("ERRr", response.message());
//            }
//
//            @Override
//            public void onFailure(Call<List<DANHMUC>> call, Throwable t) {
//                Log.e("ERRr", t.getMessage());
//            }
//        });
//    }


}