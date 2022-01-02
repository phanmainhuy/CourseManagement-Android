package com.example.onlearn.activity.classroom_detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.category_small.LoaiKhoaHocAdapter_rcl;
import com.example.onlearn.activity.chapter.ChapterActivity;
import com.example.onlearn.activity.rating.RatingActivity;
import com.example.onlearn.models.CHAPTER;
import com.example.onlearn.models.LEARN;
import com.example.onlearn.models.LOAIKHOAHOC;
import com.example.onlearn.models.RATING;
import com.example.onlearn.models.USER;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

public class ClassroomDetailActivity extends AppCompatActivity implements OnClickRCL_InTro {
    String titleActionBar = "Chi tiết khóa học ";
    String urlimg = GLOBAL.ip + GLOBAL.urlimg + "courses/";
    ImageView imgKH;
    TextView tvTenKH, tvTenGV, tvNgayMua, tvGioiThieu;
    Button btnLearn, btnRating;
    RatingBar ratingBar;
    RecyclerView rclChapter;
    IntroAdapter chapAdapter;
    ArrayList<CHAPTER> dataintro = new ArrayList<>();

    String urlgetchap = GLOBAL.ip + "api/chuong?MaKhoaHoc=" + GLOBAL.learn.getMaKH();
    String urlRating = GLOBAL.ip + "api/DanhGia?MaKhoaHoc="+GLOBAL.learn.getMaKH()+"&&MaND=" + GLOBAL.idUser;


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
        btnRating = findViewById(R.id.btnRating_classroom);
        ratingBar = findViewById(R.id.rating_classroom);
        rclChapter = findViewById(R.id.rclChapter_classroom);

        //set adapter
        chapAdapter = new IntroAdapter(this, dataintro, this);
        rclChapter.setHasFixedSize(true);
        rclChapter.setAdapter(chapAdapter);
        rclChapter.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //getdata
        try {
            getDetailClassroom();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        getIntroChap();
        getUserRating();


        btnLearn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChapterActivity.class);
            startActivity(intent);
        });
        btnRating.setOnClickListener(v -> {
            Intent intent = new Intent(this, RatingActivity.class);
            startActivity(intent);
        });
    }


    private void getIntroChap() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    dataintro.add(new CHAPTER(i + 1, jsonObject.getInt("MaChuong"),
                            jsonObject.getInt("MaKhoaHoc"),
                            jsonObject.getString("TenChuong"), jsonObject.getString("TenKhoaHoc"))
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            chapAdapter.notifyDataSetChanged();

        };
//        Map<String, String> paramsHeaders = new HashMap<>();
//        paramsHeaders.put("Content-Type", "application/json");
        com.android.volley.Response.ErrorListener thatbai = error -> {
            if (error.getMessage() != null) {
                Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
            }

        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlgetchap, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

    }

    private void getUserRating() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        com.android.volley.Response.Listener<JSONObject> thanhcong = response -> {
            try {
                if (response.getString("Message").equals("Không có dữ liệu")) {
                    GLOBAL.userRating = new RATING(-1,
                            GLOBAL.userlogin.getMaND(),GLOBAL.learn.getMaKH(),GLOBAL.learn.getTenKH(),
                            0,0,"","", "", "");
                    return;
                }
                else {
                    GLOBAL.userRating = new RATING(response.getInt("MaDanhGia"),
                            GLOBAL.userlogin.getMaND(),GLOBAL.learn.getMaKH(),response.getString("TenKhoaHoc"),
                            response.getInt("Diem"),response.getDouble("TongDiem"),
                            response.getString("NoiDung"),response.getString("TenND"),
                            response.getString("HinhAnh"), response.getString("NgayDanhGia"));
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }


        };
        com.android.volley.Response.ErrorListener thatbai = error -> {
            if (error.getMessage() != null) {
                Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        };
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlRating, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

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

    void DecorateActionBar() {
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


    @Override
    public void itemClickChapter(CHAPTER chapter) {
        GLOBAL.chapter = chapter;
        Intent intent = new Intent(this, ChapterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}