package com.example.onlearn.activity.profile_user;

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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.change_profile.ChangeProfileActivity;
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.models.USER;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

public class ProfileUserActivity extends AppCompatActivity {
    Button btnChangeInfo, btnBack;
    ImageView imgUser;
    TextView tvUserName, tvName, tvPhone, tvEmail, tvDoB, tvAddress, tvCmnd, tvDiemTL, tvGender;

    //url take imformation user
    String urlUser = GLOBAL.ip + "api/hocvien?userId="+GLOBAL.idUser;
    String urlImgUser = GLOBAL.ip + GLOBAL.urlimg + "users/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        //ActionBar
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);
        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" +"Thông tin người dùng"+ "</font>"));

        //anh xa
        btnChangeInfo = findViewById(R.id.btnChangeInfo_User);
        btnBack = findViewById(R.id.btnBack_User);
    tvUserName = findViewById(R.id.tvUserName_User);
        tvName = findViewById(R.id.tvName_User);
        tvPhone = findViewById(R.id.tvPhone_User);
        tvEmail = findViewById(R.id.tvEmail_User);
        tvDoB = findViewById(R.id.tvDoB_User);
        tvAddress = findViewById(R.id.tvAddress_User);
        tvCmnd = findViewById(R.id.tvCMND_User);
        tvDiemTL = findViewById(R.id.tvDiemTichLuy_User);
        imgUser = findViewById(R.id.imgAvatar);
        tvGender = findViewById(R.id.tvGender_User);

//        tvUserName.setText(GLOBAL.userlogin.getUserName());

        getInfoUser();

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });

        btnChangeInfo.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChangeProfileActivity.class);
            startActivity(intent);
        });

    }

    private void getInfoUser() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        com.android.volley.Response.Listener<JSONObject> thanhcong = response -> {
            try {
                    tvUserName.setText(response.getString("UserName"));
                    tvName.setText(response.getString("Name"));
                    tvEmail.setText(response.getString("Email"));
                    tvPhone.setText(response.getString("Number"));
                    tvDoB.setText(utils.converDateFormate(response.getString("DoB")));
                    tvAddress.setText(response.getString("Address"));
                        tvGender.setText(response.getString("Gender"));
                    tvCmnd.setText(response.getString("CMND"));
                    tvDiemTL.setText(utils.formatNumberCurrency(response.getString("DiemTichLuy")));

                    Picasso.with(this)
                        .load(urlImgUser + response.getString("HinhAnh"))
                        .placeholder(R.drawable.no_image_found)
                        .into(imgUser);

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


            } catch (JSONException | ParseException e) {
                e.printStackTrace();
            }


        };
        com.android.volley.Response.ErrorListener thatbai = error -> {
            if(error.getMessage()!=null){
                Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        };

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlUser, null, thanhcong, thatbai);
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