package com.example.onlearn.activity.detail_course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.API.API;
import com.example.onlearn.API.ICallBack;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.cart.CartActivity;
import com.example.onlearn.activity.category_courses.KhoaHocTheoLoaiActivity;
import com.example.onlearn.activity.category_small.LoaiKhoaHocActivity;
import com.example.onlearn.activity.chapter.ChapterActivity;
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.activity.learn_demo.LearnDemoActivity;
import com.example.onlearn.activity.login.LoginActivity;
import com.example.onlearn.activity.rating.RatingActivity;
import com.example.onlearn.activity.register.RegisterActivity;
import com.example.onlearn.models.DANHMUC;
import com.example.onlearn.models.LEARN;
import com.example.onlearn.models.LOAIKHOAHOC;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class DetailCourseActivity extends AppCompatActivity {

    String urlClassroom = GLOBAL.ip + "api/KhoaHocTheoHocVien?MaHV=" +GLOBAL.idUser;
    //http://192.168.1.160:45455/api/khoahoc?makhoa=1
    String urlgetKH = GLOBAL.ip + "api/khoahoc?makhoa=" + GLOBAL.KhoaHocClick.getMaKhoaHoc();
    String urlgetimgKH = GLOBAL.ip + GLOBAL.urlimg + "courses/";
    String urlPostCart = GLOBAL.ip + "api/cartitem";
    API api;
    Context context;
    double priceCourses;

    ImageView imgKH;
    TextView tvGiaKH, tvNgayKhaiGiang, tvDanhMuc, tvTheLoai, tvTenGV, tvMoTa, tvTenKH;
    RatingBar ratingKH;
    Button btnMuaNgay, btnAddCart;
    String TitleActionBar = "Chi tiết khóa học";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailcourse);

        DecorateActionBar();
        context = getApplicationContext();
        api = new API(DetailCourseActivity.this);

        //anh xa
        imgKH = findViewById(R.id.imgKH_Detail);
        tvGiaKH = findViewById(R.id.tvGiaKH_Detail);
//        tvNgayKhaiGiang = findViewById(R.id.tvNgayKhaiGiang_Detail);
        tvDanhMuc = findViewById(R.id.tvTenDM_Detail);
        tvTheLoai = findViewById(R.id.tvTenLoai_Detail);
        tvTenGV = findViewById(R.id.tvTenGV_Detail);
        tvTenKH = findViewById(R.id.tvTenKH_Detail);
        ratingKH = findViewById(R.id.rating_Detail);
        tvMoTa = findViewById(R.id.tvGioiThieu_Detail);
        btnAddCart = findViewById(R.id.AddCart_Detail);
        btnMuaNgay = findViewById(R.id.btnDKHoc_Detail);

        //get data
//        getDetailCourse();
            getClassroom();
            getDetailCourse();


        //xu ly button
        tvTheLoai.setOnClickListener(v -> {
            Intent intent = new Intent(this, KhoaHocTheoLoaiActivity.class);
            startActivity(intent);
        });
        tvDanhMuc.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoaiKhoaHocActivity.class);
            startActivity(intent);
        });

        btnAddCart.setOnClickListener(v -> {
           if(btnAddCart.getText().toString().equals("Thêm vào giỏ hàng")){
                try {
                addCart();
            } catch (JSONException e) {
                e.printStackTrace();
            }
           }
           if (btnAddCart.getText().toString().equals("Đánh giá khoá học")){
               Intent intent = new Intent(this, RatingActivity.class);
               startActivity(intent);
           }
        });
        btnMuaNgay.setOnClickListener(v -> {
            if(btnMuaNgay.getText().toString().equals("Vào học")){

                Intent intent = new Intent(this, ChapterActivity.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(this, LearnDemoActivity.class);
                startActivity(intent);
            }
        });

    }

    void DecorateActionBar(){
        //ActionBar
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);
        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + TitleActionBar + "</font>"));
    }

    private void addCart() throws JSONException {

        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();


        //put parmas
        parmas.put("UserID", GLOBAL.idUser);
        parmas.put("CourseID", GLOBAL.KhoaHocClick.getMaKhoaHoc());
        parmas.put("OriginPrice", priceCourses);
        paramsHeaders.put("Content-Type", "application/json");
        api.CallAPI(urlPostCart, Request.Method.POST, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {
                Log.i("success",dataResponse);

                try {
                    JSONObject result = new JSONObject(dataResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Toast.makeText(getApplicationContext(), "Thêm vào thành công", Toast.LENGTH_SHORT).show();
                btnAddCart.setEnabled(false);
                btnAddCart.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey_hint_txt)));


            }
            @Override
            public void ReponseError(String error) {
                Log.e("error", "My error: "+ error);
                btnAddCart.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey_hint_txt)));
                btnAddCart.setEnabled(false);
//                Toast.makeText(getApplicationContext(), "Thêm vào giỏ hàng thất bại\nKhóa học đã được mua hoặc có trong giỏ hàng", Toast.LENGTH_LONG).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(DetailCourseActivity.this);

                //setTitle
                builder.setTitle("Thông báo");
                builder.setMessage("Thêm vào giỏ hàng thất bại\nKhóa học đã được mua hoặc có trong giỏ hàng");
                builder.setIcon(GLOBAL.iconDialog);


                builder.setCancelable(true);

                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //  Cancel
                        dialog.cancel();
                    }
                });
                // Create AlertDialog:
                AlertDialog alert = builder.create();
                alert.show();



            }
        });
    }


    private void getDetailCourse() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONObject> thanhcong = response -> {

            try {
                tvGiaKH.setText(utils.formatNumberCurrency(response.getString("DonGia")) + " đ");
                tvMoTa.setText(response.getString("GioiThieu"));
//                tvNgayKhaiGiang.setText(utils.converDateFormate(response.getString("NgayTao")));
//                tvLuotMua.setText(String.valueOf(response.getInt("SoLuongMua")));
                tvDanhMuc.setText(response.getString("TenDanhMuc"));
                tvTheLoai.setText(response.getString("TenLoai"));
                tvTenGV.setText(response.getString("TenGV"));
                tvTenKH.setText(response.getString("TenKhoaHoc"));
                ratingKH.setRating(response.getInt("DanhGia"));
                priceCourses = response.getDouble("DonGia");
                Picasso.with(this)
                        .load(urlgetimgKH + response.getString("HinhAnh"))
                        .placeholder(R.drawable.no_image_found)
                        .into(imgKH);
                GLOBAL.LoaiKHClick = new LOAIKHOAHOC(response.getInt("MaLoai"),
                        response.getInt("MaDM"),
                        response.getString("TenLoai"),
                        "", response.getString("TenDanhMuc")
                );
                GLOBAL.DMClick = new DANHMUC(response.getInt("MaDM"),
                        response.getString("TenDanhMuc"), "", 0);

                GLOBAL.learn = new LEARN(response.getInt("MaKhoaHoc"),
                        response.getString("TenKhoaHoc"),
                        response.getString("HinhAnh"),
                        response.getInt("MaGV"),
                        response.getString("TenGV"),
                        response.getInt("DanhGia"),
                        response.getString("GioiThieu"),
                        utils.converDateFormate(response.getString("NgayChapThuan")));

            } catch (JSONException | ParseException e) {
                e.printStackTrace();
            }


        };

        com.android.volley.Response.ErrorListener thatbai = error ->
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlgetKH, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);


    }

    private void getClassroom() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    if (jsonObject.getInt("MaKhoaHoc") == GLOBAL.KhoaHocClick.getMaKhoaHoc()){
                        btnMuaNgay.setText("Vào học");
                        btnAddCart.setText("Đánh giá khóa học");
                        btnMuaNgay.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue_deep)));
                        btnAddCart.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red_price)));

                    }
                    else {
                        getDetailCourse();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
//            adapterclassroom.notifyDataSetChanged();

        };
//        Map<String, String> paramsHeaders = new HashMap<>();
//        paramsHeaders.put("Content-Type", "application/json");
        com.android.volley.Response.ErrorListener thatbai = error ->{
            if(error.getMessage()!=null){
                Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
            }

        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlClassroom, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        getMenuInflater().inflate(R.menu.menu_cart, menu);


        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_cart:
                Intent intent = new Intent(DetailCourseActivity.this, CartActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_home:
                this.finish();
                Intent intent1 = new Intent(DetailCourseActivity.this, HomeActivity.class);
                startActivity(intent1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}