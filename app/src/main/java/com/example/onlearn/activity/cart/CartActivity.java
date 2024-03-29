package com.example.onlearn.activity.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.API.API;
import com.example.onlearn.API.ICallBack;
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.pay_edit.PayEditUserActivity;
import com.example.onlearn.activity.payment_methods.PaymentMethodsActivity;
import com.example.onlearn.models.CART;
import com.example.onlearn.models.Items_CART;
import com.example.onlearn.utils.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    Button btnContinue, btnThanhToan;
    String titleActionBar = "Giỏ hàng";
    RecyclerView rclCart;
    TextView tvThanhTien, tvNull;
    CartAdapter cartAdapter;
    Context context;
    OnClickRCL_Cart deleteCart;
    public static ArrayList<Items_CART> dataCart = new ArrayList<>();
    API api;


    String urlgetCart = GLOBAL.ip + "api/cartitem/?pUserID=" + GLOBAL.idUser;

    String urlpostCart = GLOBAL.ip + "api/Payment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        DecorateActionBar();
        context = getApplicationContext();

        api = new API(CartActivity.this);

        //anh xa
        btnContinue = findViewById(R.id.btn_Cart_Continue);
        btnThanhToan = findViewById(R.id.btn_Cart_Pay);
        tvNull = findViewById(R.id.lbl_Cart_notificationcart);
        rclCart = findViewById(R.id.lst_Cart);
        tvThanhTien = findViewById(R.id.tv_Cart_Total);


        //set data rcl


        deleteCart = c -> {
            dataCart.remove(c);
//            dataCart.clear();
//            getCartItems();
            cartAdapter = new CartAdapter(CartActivity.this, dataCart, deleteCart);
            rclCart.setAdapter(cartAdapter);
            checkData();

        };

        cartAdapter = new CartAdapter(this, dataCart, deleteCart);
        rclCart.setHasFixedSize(true);
        rclCart.setAdapter(cartAdapter);
        rclCart.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        cartAdapter.notifyDataSetChanged();

        //get data
        getCartItems();


//        tvThanhTien.setText(utils.formatNumberCurrency(GLOBAL.cart.getTongTien()));
//        checkData();


        //xu ly
        btnContinue.setOnClickListener(v -> {
            this.finish();
            Intent intent = new Intent(CartActivity.this, HomeActivity.class);
            startActivity(intent);

        });

        btnThanhToan.setOnClickListener(v -> {
            if (dataCart.size() <= 0) {
                //Toast.makeText(context, "Bạn chưa có gì trong giỏ hàng", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                //setTitle
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn chưa có gì trong giỏ hàng");
                builder.setIcon(R.drawable.ic_chatbot);


                builder.setCancelable(true);

                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //  Cancel
                        dialog.cancel();
                    }
                });
                // Create AlertDialog:
                AlertDialog alert = builder.create();
                alert.show();



            }
            else {
                //post gio hang
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

                //setTitle
                builder1.setTitle("Thông báo");
                builder1.setMessage("Khi chuyển sang thanh toán sẽ xóa sạch khóa học trong giỏ hàng, bạn có đồng ý không?");
                builder1.setIcon(R.drawable.ic_chatbot);


                builder1.setCancelable(true);

                builder1.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            postCart_CreateOrder();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //  Cancel
                        dialog.cancel();
                    }
                });
                builder1.setPositiveButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // Create AlertDialog:
                AlertDialog alert = builder1.create();
                alert.show();


//                Intent intent1 = new Intent(CartActivity.this, PayActivity.class);
//                startActivity(intent1);


            }


        });


    }

    private void checkData() {
        if (dataCart.size() <= 0) {
            cartAdapter.notifyDataSetChanged();
            tvNull.setVisibility(View.VISIBLE);
//            rclCart.setVisibility(View.INVISIBLE);

        } else {
            cartAdapter.notifyDataSetChanged();
            tvNull.setVisibility(View.INVISIBLE);
//            rclCart.setVisibility(View.VISIBLE);
        }
    }


    public void getCartItems() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONObject> thanhcong = response -> {

            try {

                JSONArray Data = response.getJSONArray("CartItems");

                GLOBAL.cart = new CART(response.getInt("CourseCartID"),
                        response.getInt("UserID"),
                        response.getString("TongTien"));

                tvThanhTien.setText(utils.formatNumberCurrency(GLOBAL.cart.getTongTien()) + " VND");
                dataCart.clear();
                for (int a = 0; a < Data.length(); a++) //have length
                {
                    JSONObject inData = Data.getJSONObject(a);
//                    dataCart = new ArrayList<>();

                    dataCart.add(new Items_CART(
                                    inData.getInt("CourseID"),
                                    inData.getString("CourseName"),
                                    inData.getString("OriginPrice"),
                                    inData.getString("AfterPrice"),
                                    inData.getString("TeacherName"),
                                    inData.getString("ImageName")
                            )
                    );
                }
                GLOBAL.itemsCart_items = dataCart;
                if (GLOBAL.itemsCart_items.size() > 0) {
                    tvNull.setVisibility(View.INVISIBLE);
                } else {
                    tvNull.setVisibility(View.VISIBLE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            cartAdapter.notifyDataSetChanged();


//            checkData();

        };

        com.android.volley.Response.ErrorListener thatbai = error -> {
            if (error.getMessage() != null) {
//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlgetCart, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);


    }

    private void postCart_CreateOrder() throws JSONException {
        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();


        //put parmas
        parmas.put("MaND", GLOBAL.idUser);
        parmas.put("MaGioHang", GLOBAL.cart.getCartID());



        paramsHeaders.put("Content-Type", "application/json");

        api.CallAPI(urlpostCart, Request.Method.POST, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {

                Log.i("success", "my response" + dataResponse);

                GLOBAL.idHD_pay = Integer.parseInt(dataResponse);
                Toast.makeText(getApplicationContext(), "Tạo hóa đơn thành công ", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(CartActivity.this, PaymentMethodsActivity.class);
                startActivity(intent1);
                dataCart.clear();
                CartActivity.this.finish();

            }

            @Override
            public void ReponseError(String error) {

                Log.e("error", "my error: " + error);
                Toast.makeText(getApplicationContext(), "Lỗi khi tạo hóa đơn ", Toast.LENGTH_LONG).show();
            }
        });
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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}