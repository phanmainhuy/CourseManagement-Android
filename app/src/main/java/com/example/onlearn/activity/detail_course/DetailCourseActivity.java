package com.example.onlearn.activity.detail_course;

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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.models.LOAIKHOAHOC;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailCourseActivity extends AppCompatActivity {
    String TitleActionBar = "Chi tiết khóa học";

    //http://192.168.1.160:45455/api/khoahoc?makhoa=1
    String urlgetKH = GLOBAL.ip + "api/khoahoc?makhoa=" + GLOBAL.KhoaHocClick.getMaKhoaHoc();
    String urlgetimgKH = GLOBAL.ip + GLOBAL.urlimg + "courses/";

    ImageView imgKH;
    TextView tvGiaKH, tvNgayKhaiGiang, tvLuotMua, tvDanhMuc, tvTheLoai, tvTenGV, tvMoTa;
    RatingBar ratingKH;
    Button btnMuaNgay, btnAddCart;

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
        ratingKH = findViewById(R.id.rating_Detail);
        tvMoTa = findViewById(R.id.tvGioiThieu_Detail);
        btnAddCart = findViewById(R.id.AddCart_Detail);
        btnMuaNgay = findViewById(R.id.btnDKHoc_Detail);

        //get data
        getDetailCourse();

        //xu ly button












    }

    private void getDetailCourse() {

            RequestQueue requestQueue = Volley.newRequestQueue(this);

            com.android.volley.Response.Listener<JSONObject> thanhcong = response -> {

                try {
                        tvMoTa.setText(response.getString("GioiThieu"));


                } catch (JSONException e) {
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