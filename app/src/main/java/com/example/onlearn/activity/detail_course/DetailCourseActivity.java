package com.example.onlearn.activity.detail_course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
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
import com.example.onlearn.API.API;
import com.example.onlearn.API.ICallBack;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.category_courses.KhoaHocTheoLoaiActivity;
import com.example.onlearn.activity.category_small.LoaiKhoaHocActivity;
import com.example.onlearn.activity.login.LoginActivity;
import com.example.onlearn.activity.register.RegisterActivity;
import com.example.onlearn.models.DANHMUC;
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


    //http://192.168.1.160:45455/api/khoahoc?makhoa=1
    String urlgetKH = GLOBAL.ip + "api/khoahoc?makhoa=" + GLOBAL.KhoaHocClick.getMaKhoaHoc();
    String urlgetimgKH = GLOBAL.ip + GLOBAL.urlimg + "courses/";
    String urlPostCart = GLOBAL.ip + "api/cartitem";
    API api;
    Context context;


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
            try {
                addCart();
            } catch (JSONException e) {
                e.printStackTrace();
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

        String GiaKH = tvGiaKH.getText().toString();

        //put parmas
        parmas.put("UserID", GLOBAL.idUser);
        parmas.put("CourseID", GLOBAL.KhoaHocClick.getMaKhoaHoc());
        parmas.put("OriginPrice", GiaKH);
        paramsHeaders.put("Content-Type", "application/json");
        api.CallAPI(urlPostCart, Request.Method.POST, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {
                Log.i("success",dataResponse);

                try {
                    JSONObject result = new JSONObject(dataResponse);
//                    GLOBAL.idUser = result.getInt("UserID");
//                    Toast.makeText(getApplicationContext(), GLOBAL.idUser, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


//                Intent intent1 = new Intent(RegisterActivity.this ,LoginActivity.class);
//                startActivity(intent1);
                Toast.makeText(getApplicationContext(), "Thêm vào thành công", Toast.LENGTH_SHORT).show();
                btnAddCart.setEnabled(false);
                // nếu data trả về là object thì --> tạo dataJsonObject cho data {"message:"success",data:[{id:"1",name:"gido"},{id:"2",name:"123"]}
                // JSONObject objResult = new JSONObject(dataResponse);
                // }
                //
                //   JSONArray arrayResult = objResult.getJSONArray("data");
            }
            @Override
            public void ReponseError(String error) {
                Log.e("error", "My error: "+ error);
                Toast.makeText(getApplicationContext(), "Thêm vào giỏ hàng thất bại\nKhóa học đã được mua hoặc có trong giỏ hàng", Toast.LENGTH_LONG).show();
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