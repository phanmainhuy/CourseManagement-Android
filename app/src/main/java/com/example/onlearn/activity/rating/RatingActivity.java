package com.example.onlearn.activity.rating;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.onlearn.models.RATING;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RatingActivity extends AppCompatActivity {
    String titleActionBar = "Đánh giá khóa học";

    String urlgetImgUser = GLOBAL.ip + GLOBAL.urlimg + "users/";
    String urlgetImgCourses = GLOBAL.ip + GLOBAL.urlimg + "courses/";
    String urlgetUserRating = GLOBAL.ip + "api/DanhGia?MaKhoaHoc=" + GLOBAL.learn.getMaKH() + "&&MaND=" + GLOBAL.idUser;
    String urlgetCommunity = GLOBAL.ip + "api/DanhGia?MaKhoaHoc=" + GLOBAL.learn.getMaKH();

    ImageView imgKH, imgUser;
    TextView tvTenKH, tvUserName, tvSLUserRating;

    RecyclerView rcl_RatingCommunity;
    ArrayList<RATING> dataRating = new ArrayList<>();
    RatingAdapter communityAdapter;

    RatingBar ratingTotal, ratingPerson;
    TextView tvTotalRating, tvRatingPerson;
    Button btnCreate, btnUpdate, btnDelete;
    EditText tvNoiDung;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        DecorateActionBar();

        //map
        imgKH = findViewById(R.id.imgKH_Rating);
        imgUser = findViewById(R.id.imgAvatarUser_Rating);
        tvTenKH = findViewById(R.id.tvTenKH_Rating);
        tvUserName = findViewById(R.id.tvUsername_Rating);
        tvSLUserRating = findViewById(R.id.tvSLPersonRating_Rating);

        tvTotalRating = findViewById(R.id.tvTotalRating_Rating);
        tvRatingPerson = findViewById(R.id.tvPersonalRating_Rating);
        tvNoiDung = findViewById(R.id.tvNoiDungRating_Rating);

        //rcl
        rcl_RatingCommunity = findViewById(R.id.rclRatingCommunity_Rating);
        //btn
        btnCreate = findViewById(R.id.btnAddRating_Rating);
        btnUpdate = findViewById(R.id.btnUpdateRating_Rating);
        btnDelete = findViewById(R.id.btnDeleteRating_Rating);
        //rating
        ratingPerson = findViewById(R.id.PersonalRating_Rating);
        ratingTotal = findViewById(R.id.totalrating_Rating);





        //set data
        getSetUpData();
        //set up recycle

        communityAdapter = new RatingAdapter(this, dataRating);
        rcl_RatingCommunity.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcl_RatingCommunity.setAdapter(communityAdapter);

        getCommunity();
        getRatingTotal();

    }

    private void getSetUpData() {
        tvTenKH.setText(GLOBAL.learn.getTenKH());
        tvUserName.setText(GLOBAL.userlogin.getTen());
        Picasso.with(this)
                .load(urlgetImgUser + GLOBAL.userlogin.getImgUser())
                .placeholder(R.drawable.no_image_found)
                .into(imgUser);

        Picasso.with(this)
                .load(urlgetImgCourses + GLOBAL.learn.getImgKH())
                .placeholder(R.drawable.no_image_found)
                .into(imgKH);

//        if (GLOBAL.userRating.getMaDanhGia()==-1){
//            btnDelete.setVisibility(View.INVISIBLE);
//            btnUpdate.setVisibility(View.INVISIBLE);
//            btnCreate.setVisibility(View.VISIBLE);
//        }
//        else {
//            btnCreate.setVisibility(View.INVISIBLE);
//            btnDelete.setVisibility(View.VISIBLE);
//            btnUpdate.setVisibility(View.VISIBLE);
//        }

    }

    private void getCommunity() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    dataRating.add(new RATING(jsonObject.getInt("MaDanhGia"),
                            jsonObject.getInt("MaND"),
                            jsonObject.getInt("MaKhoaHoc"),
                            jsonObject.getString("TenKhoaHoc"),
                            jsonObject.getInt("Diem"),
                            jsonObject.getDouble("TongDiem"),
                            jsonObject.getString("NoiDung"),
                            jsonObject.getString("TenND"),
                            jsonObject.getString("HinhAnh"),
                            jsonObject.getString("NgayDanhGia")
                            ));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            tvSLUserRating.setText(response.length() + " lượt đánh giá");
            communityAdapter.notifyDataSetChanged();
        };

        com.android.volley.Response.ErrorListener thatbai = error -> {
            if (error.getMessage() != null)
                Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();

        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlgetCommunity, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

    }

    private void getRatingTotal(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        com.android.volley.Response.Listener<JSONObject> thanhcong = response -> {
            try {

                    ratingTotal.setRating(response.getInt("TongDiem"));
                    ratingPerson.setRating(response.getInt("Diem"));
                    tvTotalRating.setText(utils.formatTotalRating(response.getDouble("TongDiem"))+ " ");
                    tvRatingPerson.setText(response.getInt("Diem") + " ");
                    tvNoiDung.setText(response.getString("NoiDung") + "");


            } catch (JSONException e) {
                e.printStackTrace();
            }


        };
        com.android.volley.Response.ErrorListener thatbai = error ->
        {
            if(error.getMessage() != null){
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlgetUserRating, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
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


}