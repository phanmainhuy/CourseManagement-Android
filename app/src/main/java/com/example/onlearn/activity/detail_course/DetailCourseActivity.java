package com.example.onlearn.activity.detail_course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.category_courses.KhoaHocTheoLoaiActivity;
import com.example.onlearn.activity.category_small.LoaiKhoaHocActivity;
import com.example.onlearn.models.DANHMUC;
import com.example.onlearn.models.LOAIKHOAHOC;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

public class DetailCourseActivity extends AppCompatActivity {


    //http://192.168.1.160:45455/api/khoahoc?makhoa=1
    String urlgetKH = GLOBAL.ip + "api/khoahoc?makhoa=" + GLOBAL.KhoaHocClick.getMaKhoaHoc();
    String urlgetimgKH = GLOBAL.ip + GLOBAL.urlimg + "courses/";

    ImageView imgKH;
    TextView tvGiaKH, tvNgayKhaiGiang, tvLuotMua, tvDanhMuc, tvTheLoai, tvTenGV, tvMoTa, tvTenKH;
    RatingBar ratingKH;
    Button btnMuaNgay, btnAddCart;
    String TitleActionBar = "Chi tiết khóa học";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailcourse);

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

        //anh xa
        imgKH = findViewById(R.id.imgKH_Detail);
        tvGiaKH = findViewById(R.id.tvGiaKH_Detail);
        tvNgayKhaiGiang = findViewById(R.id.tvNgayKhaiGiang_Detail);
        tvLuotMua = findViewById(R.id.tvLuotMua_Detail);
        tvDanhMuc = findViewById(R.id.tvTenDM_Detail);
        tvTheLoai = findViewById(R.id.tvTenLoai_Detail);
        tvTenGV = findViewById(R.id.tvTenGV_Detail);
        tvTenKH = findViewById(R.id.tvTenKH_Detail);
        ratingKH = findViewById(R.id.rating_Detail);
        tvMoTa = findViewById(R.id.tvGioiThieu_Detail);
        btnAddCart = findViewById(R.id.AddCart_Detail);
        btnMuaNgay = findViewById(R.id.btnDKHoc_Detail);

        //get data
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

    }

    private void getDetailCourse() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONObject> thanhcong = response -> {

            try {
                tvGiaKH.setText(utils.formatNumberCurrency(response.getString("DonGia")) + " đ");
                tvMoTa.setText(response.getString("GioiThieu"));
                tvNgayKhaiGiang.setText(utils.converDateFormate(response.getString("NgayChapThuan")));
                tvLuotMua.setText(String.valueOf(response.getInt("SoLuongMua")));
                tvDanhMuc.setText(response.getString("TenDanhMuc"));
                tvTheLoai.setText(response.getString("TenLoai"));
                tvTenGV.setText(response.getString("TenGV"));
                tvTenKH.setText(response.getString("TenKhoaHoc"));
                ratingKH.setRating(response.getInt("DanhGia"));

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
                        response.getString("TenDanhMuc"),"", 0);


            } catch (JSONException | ParseException e) {
                e.printStackTrace();
            }


        };

        com.android.volley.Response.ErrorListener thatbai = error ->
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlgetKH, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);


    }


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

}