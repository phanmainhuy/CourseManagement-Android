package com.example.onlearn.activity.coupon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.category_courses.KhoaHocTheoLoaiAdapter;
import com.example.onlearn.activity.coupon_wallet.CouponWalletActivity;
import com.example.onlearn.models.KHOAHOC;
import com.example.onlearn.models.KHUYENMAI;
import com.example.onlearn.models.USER;
import com.example.onlearn.utils.SpacesItemDecoration;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CouponActivity extends AppCompatActivity implements OnClickRCL_Coupon{
    CouponAdapter kmAdapter;
    ArrayList<KHUYENMAI> datakm = new ArrayList<>();
    RecyclerView rclCoupon;
    ImageView imgAvatar;
    TextView tvUsername, tvName;
    TextView tvDiemTL;
//    OnClickRCL_Coupon onBuyCoupon;

    String urlAvatar = GLOBAL.ip + GLOBAL.urlimg +  "users/";

    String urlUser = GLOBAL.ip + "api/hocvien?userId=" + GLOBAL.idUser;

    //post + get
    String urlkm = GLOBAL.ip + "api/khuyenmai";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

       decorateActionBar();

        //anh xa
        rclCoupon = findViewById(R.id.rclCoupon);
        imgAvatar = findViewById(R.id.imgAvatar_Coupon);
        tvUsername = findViewById(R.id.tvUsername_Coupon);
        tvName = findViewById(R.id.tvName_Coupon);
        tvDiemTL = findViewById(R.id.tvDiemTL_Coupon);


        //set data rcl
        kmAdapter = new CouponAdapter(this, datakm, this);
        rclCoupon.setHasFixedSize(true);
        rclCoupon.setAdapter(kmAdapter);
        rclCoupon.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //Ngan giua cac item
        rclCoupon.addItemDecoration(new SpacesItemDecoration(30));

//        //Chèn một kẻ ngang giữa các phần tử
//        DividerItemDecoration dividerHorizontal =
//                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//
//        dividerHorizontal.
//                setDrawable(ContextCompat.getDrawable(this, R.drawable.coupon_duongkengangitem));
//        rclCoupon.addItemDecoration(dividerHorizontal);


        getAllCoupon();
        getUser();


    }

    private void getUser() {
        tvUsername.setText(GLOBAL.userlogin.getUserName());
        tvName.setText(GLOBAL.userlogin.getTen());
        tvDiemTL.setText(utils.formatNumberCurrency(String.valueOf(GLOBAL.userlogin.getDiemTichLuy())));
        Picasso.with(this)
                .load(urlAvatar + GLOBAL.userlogin.getImgUser())
                .placeholder(R.drawable.no_image_found)
                .into(imgAvatar);

    }


    private void decorateActionBar() {
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);
        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + "Khuyến mãi" + "</font>"));
    }

    private void getAllCoupon() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    datakm.add(new KHUYENMAI(jsonObject.getInt("MaKM"),
                            "",
                            jsonObject.getString("TenKM"),
                            jsonObject.getString("HinhAnh"),
                            jsonObject.getString("GiaTri"),
                            jsonObject.getString("ThoiGianKeoDai"),
                            jsonObject.getString("DiemCanMua")
                    ));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            getResetUser();
            kmAdapter.notifyDataSetChanged();
        };

        com.android.volley.Response.ErrorListener thatbai = error ->{
            if(error.getMessage() != null)
                Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();

        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlkm, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

    }

    public void getResetUser() {
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
                tvDiemTL.setText(utils.formatNumberCurrency(String.valueOf(GLOBAL.userlogin.getDiemTichLuy())));

//                (response.getString("Name"));
//                (response.getString("Email"));
//                tvDoB.setText(utils.converDateFormate(response.getString("DoB")));
//                tvAddress.setText(response.getString("Address"));
//                tvGender.setText(response.getString("Gender"));
//                tvCmnd.setText(response.getString("CMND"));
//                tvDiemTL.setText(utils.formatNumberCurrency(response.getString("DiemTichLuy")));
//
//                Picasso.with(this)
//                        .load(urlImgUser + response.getString("HinhAnh"))
//                        .placeholder(R.drawable.no_image_found)
//                        .into(imgUser);


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

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlUser, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_couponwallet, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_coupon:
                Intent intent = new Intent(CouponActivity.this, CouponWalletActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void buyCoupon(KHUYENMAI khuyenmai) {

    }
}