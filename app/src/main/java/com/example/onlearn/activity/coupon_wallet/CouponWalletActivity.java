package com.example.onlearn.activity.coupon_wallet;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
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
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.models.KHUYENMAI_KH;
import com.example.onlearn.utils.SpacesItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CouponWalletActivity extends AppCompatActivity {
    String titleActionBar = "Ví khuyến mãi";
    RecyclerView rclCPWallet;
    CPWalletAdapter walletAdapter;
    ArrayList<KHUYENMAI_KH> dataKMKH = new ArrayList<>();
    String urlCPwallet = GLOBAL.ip + "api/KhuyenMaiDaMua?MaND=" + GLOBAL.idUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_couponwallet);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + titleActionBar + "</font>"));
        //doi mau thanh action bars
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);


        //anh xa
        rclCPWallet = findViewById(R.id.rclCouponWallet);


        //set du lieu
        walletAdapter = new CPWalletAdapter(this, dataKMKH);
        rclCPWallet.setAdapter(walletAdapter);
        rclCPWallet.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rclCPWallet.addItemDecoration(new SpacesItemDecoration(10));
//        dataKMKH.add(new KHUYENMAI_KH(1, 2, "Khuyến Mãi Đầu Khóa", "coupon1.png",
//                "1","BUTA5678","2021-12-13T00:00:00+07:00"));

        //get data
        getCouponCus();

        //Chèn một kẻ ngang giữa các phần tử
        DividerItemDecoration dividerHorizontal =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        dividerHorizontal.
                setDrawable(ContextCompat.getDrawable(this, R.drawable.black_duongkengangitem));
        rclCPWallet.addItemDecoration(dividerHorizontal);

    }

    private void getCouponCus() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        Response.Listener<JSONArray> thanhcong = response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    dataKMKH.add(new KHUYENMAI_KH(jsonObject.getInt("MaKM"),
                            jsonObject.getInt("MaHV"),
                            jsonObject.getString("TenKM"),
                            jsonObject.getString("HinhAnh"),
                            jsonObject.getString("GiaTri"),
                            jsonObject.getString("MaApDung"),
                            jsonObject.getString("HanSuDung")
                    ));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            walletAdapter.notifyDataSetChanged();
        };

        Response.ErrorListener thatbai = error -> {
            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlCPwallet, null, thanhcong, thatbai);
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