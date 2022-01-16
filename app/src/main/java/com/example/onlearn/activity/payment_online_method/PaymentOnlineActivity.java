package com.example.onlearn.activity.payment_online_method;

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
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.onlearn.activity.coupon_wallet.CouponWalletActivity;
import com.example.onlearn.activity.pay_offline.PayOfflineAdapter;
import com.example.onlearn.activity.payment_methods.PaymentMethodsActivity;
import com.example.onlearn.activity.payment_razor_wallet.RazorPayWalletActivity;
import com.example.onlearn.models.CART;
import com.example.onlearn.models.Items_CART;
import com.example.onlearn.utils.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PaymentOnlineActivity extends AppCompatActivity {
    String urlgetPay = GLOBAL.ip + "api/payment?MaHoaDon=" + GLOBAL.idHD_pay;
    String urlgetCoupon = GLOBAL.ip + "api/KhuyenMai/ApDung?MaND="+GLOBAL.idUser+"&&MaApDung=";


    String titleActionBar = "Thanh toán online";
    TextView tvTotalCourses, tvTongTien, tvGiamGia, tvThanhTien;
    Button btnMyCoupon, btngetCoupon, btnPayOnline;
    EditText txtCoupon;
    API api;
    RecyclerView rclPayOnline;
    PaymentOnlineAdapter paymentOnlineAdapter;
    ArrayList<Items_CART> dataPay = new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_online);
        DecorateActionBar();

        api = new API(this);
        context = getApplicationContext();

        //map
        tvTotalCourses = findViewById(R.id.tvTotalCourses_PayOnline);
        tvTongTien = findViewById(R.id.tvTongTien_PayOnline);
        tvThanhTien = findViewById(R.id.tvThanhTien_PayOnline);
        tvGiamGia = findViewById(R.id.tvGiamGia_PayOnline);
        txtCoupon = findViewById(R.id.txtMaApDung_PayOnline);

        btnMyCoupon = findViewById(R.id.btnXemCoupon_PayOnline);
        btngetCoupon = findViewById(R.id.btnApdungGiamGia_PayOnline);
        btnPayOnline = findViewById(R.id.btnPay_PayOnline);

        rclPayOnline = findViewById(R.id.rclPay_PayOnline);


        //set rcl
        paymentOnlineAdapter = new PaymentOnlineAdapter(this, dataPay);
        rclPayOnline.setHasFixedSize(true);
        rclPayOnline.setAdapter(paymentOnlineAdapter);
        rclPayOnline.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        getPayItems();

        btnMyCoupon.setOnClickListener(v -> {
            Intent intent = new Intent(this, CouponWalletActivity.class);
            startActivity(intent);
        });

        btngetCoupon.setOnClickListener(v -> {
            if(txtCoupon.getText().toString().trim().equals("")){
                Toast.makeText(getApplicationContext(), "Bạn chưa nhập mã giảm giá, vui lòng nhập để áp dụng", Toast.LENGTH_SHORT).show();
                tvGiamGia.setText("");
                return;
            }
            else {
                try {
                    getMaGiamGia();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        btnPayOnline.setOnClickListener(v -> {
            GLOBAL.MaApDung.equals(txtCoupon.getText().toString().trim());
            Intent intent = new Intent(this, PaymentOnlineWalletActivity.class);
            startActivity(intent);
        });


    }
    private void getMaGiamGia() throws JSONException {
        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();


//        //put parmas
//        parmas.put("MaND", GLOBAL.idUser);
//        parmas.put("MaGioHang", GLOBAL.cart.getCartID());



        paramsHeaders.put("Content-Type", "application/json");
        String urlgetMaApDung = urlgetCoupon + txtCoupon.getText().toString().trim();
        api.CallAPI(urlgetMaApDung, Request.Method.GET, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {

                Log.i("success", "my response" + dataResponse);
                GLOBAL.SoGiaGiam = dataResponse;
                Double giamgia = Double.parseDouble(GLOBAL.SoGiaGiam);
//                Log.i("gia giam", "so gia " + x);
//                int giamgiaint =  Integer.parseInt(GLOBAL.SoGiaGiam);
//                int tongtienint = Integer.parseInt(GLOBAL.cart.getTongTien());
//                int thanhtienint = tongtienint - giamgiaint;
//                GLOBAL.ThanhTien = thanhtienint;


                Double tongtien = Double.parseDouble(GLOBAL.cart.getTongTien());
                Double thanhtien = tongtien - giamgia;

                if (thanhtien <= 0){
                    thanhtien = Double.valueOf(0);
                }

                String thanhtienin = String.valueOf(thanhtien);
                tvThanhTien.setText(utils.formatNumberCurrency(thanhtienin)+" đ");
//                int thanhtien = Integer.parseInt(GLOBAL.cart.getTongTien()) - Integer.parseInt(GLOBAL.SoGiaGiam);
//                tvThanhTien.setText(thanhtien + " Đ");
                tvGiamGia.setText("- "+utils.formatNumberCurrency(GLOBAL.SoGiaGiam)+" đ");
                Toast.makeText(getApplicationContext(), "Áp dụng thành công", Toast.LENGTH_SHORT).show();
//                txtMaApDung.setEnabled(false);

            }

            @Override
            public void ReponseError(String error) {

                Log.e("error", "my error: " + error);
                Toast.makeText(getApplicationContext(), "Áp dụng thất bại, người dùng không có mã", Toast.LENGTH_LONG).show();
            }
        });
    }
    //action bar
    void DecorateActionBar(){
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

    public void getPayItems() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONObject> thanhcong = response -> {

            try {

                JSONArray Data = response.getJSONArray("DanhSachHangHoa");

                GLOBAL.cart = new CART(response.getInt("MaHoaDon"),
                        response.getInt("MaND"),
                        response.getString("TongThanhToan"));
                GLOBAL.ThanhTien =  response.getInt("TongThanhToan");
                Log.e("thanhtien", "onCreate: " + GLOBAL.ThanhTien);
                tvThanhTien.setText(utils.formatNumberCurrency(GLOBAL.cart.getTongTien()) + " VND");
                tvTongTien.setText(utils.formatNumberCurrency(GLOBAL.cart.getTongTien()) + " VND");
                dataPay.clear();
                for (int a = 0; a < Data.length(); a++) //have length
                {
                    JSONObject inData = Data.getJSONObject(a);

                    dataPay.add(new Items_CART(
                                    inData.getInt("CourseID"),
                                    inData.getString("CourseName"),
                                    inData.getString("LastPrice"),
                                    inData.getString("LastPrice"),
                                    inData.getString("TeacherName"),
                                    inData.getString("ImageName")
                            )
                    );
                }
                tvTotalCourses.setText("("+Data.length()+" khóa học)");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            paymentOnlineAdapter.notifyDataSetChanged();



        };

        com.android.volley.Response.ErrorListener thatbai = error -> {
            if (error.getMessage() != null) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlgetPay, null, thanhcong, thatbai);
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