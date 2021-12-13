package com.example.onlearn.activity.coupon;

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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.category_courses.KhoaHocTheoLoaiAdapter;
import com.example.onlearn.models.KHOAHOC;
import com.example.onlearn.models.KHUYENMAI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CouponActivity extends AppCompatActivity {
    CouponAdapter kmAdapter;
    ArrayList<KHUYENMAI> datakm = new ArrayList<>();
    RecyclerView rclCoupon;

    String urlkm = GLOBAL.ip + "api/khuyenmai";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);
        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + "Khuyến mãi" + "</font>"));

        //anh xa
        rclCoupon = findViewById(R.id.rclCoupon);

        //set data rcl
        kmAdapter = new CouponAdapter(this, datakm);
        rclCoupon.setHasFixedSize(true);
        rclCoupon.setAdapter(kmAdapter);
        rclCoupon.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //get data
        getAllCoupon();


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
                            "",
                            jsonObject.getInt("DiemCanMua")
                    ));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            kmAdapter.notifyDataSetChanged();
        };

        com.android.volley.Response.ErrorListener thatbai = error ->
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlkm, null, thanhcong, thatbai);
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