package com.example.onlearn.activity.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.activity.coupon.CouponAdapter;
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.models.CART;
import com.example.onlearn.models.Items_CART;
import com.example.onlearn.models.LOAIKHOAHOC;
import com.example.onlearn.utils.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements OnClickRCL_Cart {

    Button btnContinue, btnThanhToan;
    String titleActionBar = "Giỏ hàng";
    RecyclerView rclCart;
    TextView tvThanhTien, tvNull;
    CartAdapter cartAdapter;
    Context context;
    ArrayList<Items_CART> dataCart = new ArrayList<>();


    String urlgetCart = GLOBAL.ip + "api/cartitem/?pUserID=" + GLOBAL.idUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        DecorateActionBar();
        context = getApplicationContext();


        //anh xa
        btnContinue = findViewById(R.id.btn_Cart_Continue);
        btnThanhToan = findViewById(R.id.btn_Cart_Pay);
        tvNull = findViewById(R.id.lbl_Cart_notificationcart);
        rclCart = findViewById(R.id.lst_Cart);
        tvThanhTien = findViewById(R.id.tv_Cart_Total);

        //set data rcl
        cartAdapter = new CartAdapter(this, dataCart, this);
        rclCart.setHasFixedSize(true);
        rclCart.setAdapter(cartAdapter);
        rclCart.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        tvThanhTien.setText(utils.formatNumberCurrency(GLOBAL.cart.TongTien));
        //get data
        getCartItems();


        //xu ly
        btnContinue.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, HomeActivity.class);
            startActivity(intent);

        });

        btnThanhToan.setOnClickListener(v -> {
            Toast.makeText(context, "thanh toán", Toast.LENGTH_SHORT).show();
        });


    }

    private void getCartItems() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONObject> thanhcong = response -> {

            try {
                JSONArray Data = response.getJSONArray("CartItems");
//
//                GLOBAL.cart = new CART(response.getInt("CourseCartID"),
//                        response.getInt("UserID"),
//                        response.getString("TongTien")
//                );
                tvThanhTien.setText(utils.formatNumberCurrency(GLOBAL.cart.getTongTien()) + "VND");
                for (int a = 0; a < Data.length(); a++) //have length
                {
                    JSONObject inData = Data.getJSONObject(a);

                    dataCart.add(new Items_CART(
                            inData.getInt("CourseID"),
                            inData.getString("CourseName"),
                            inData.getString("OriginPrice"),
                            inData.getString("AfterPrice"),
                            inData.getString("ImageName")

                            )

                    );

                    GLOBAL.itemsCart_items = dataCart;
                    if(GLOBAL.itemsCart_items.size() >0){
                        tvNull.setVisibility(View.INVISIBLE);
                    }
                    else {
                        tvNull.setVisibility(View.VISIBLE);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            cartAdapter.notifyDataSetChanged();
        };

        com.android.volley.Response.ErrorListener thatbai = error -> {
            if (error.getMessage() != null) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlgetCart, null, thanhcong, thatbai);
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
    public void itemClick(Items_CART items) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}