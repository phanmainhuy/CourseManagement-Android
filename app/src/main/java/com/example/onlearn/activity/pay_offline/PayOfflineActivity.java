package com.example.onlearn.activity.pay_offline;

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
import android.widget.LinearLayout;
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
import com.example.onlearn.activity.pay_edit.PayEditUserActivity;
import com.example.onlearn.activity.pay_successfully.PaySuccessfulActivity;
import com.example.onlearn.models.CART;
import com.example.onlearn.models.Items_CART;
import com.example.onlearn.models.NOTIFICATION;
import com.example.onlearn.utils.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PayOfflineActivity extends AppCompatActivity {
    String titleActionBar = "Thanh toán";

    PayOfflineAdapter payOfflineAdapter;
    API api;
    Context context;
    RecyclerView rcl_pay;
    ArrayList<Items_CART> dataPay = new ArrayList<>();

    //info
    LinearLayout lnEditUser;
    TextView tvName, tvEmail, tvAddress, tvPhone;

    String urlpostPayment = GLOBAL.ip + "api/Payment/ReceiptOrder";
    String urlgetPay = GLOBAL.ip + "api/payment?MaHoaDon=" + GLOBAL.idHD_pay;
    String urlgetCoupon = GLOBAL.ip + "api/KhuyenMai/ApDung?MaND="+GLOBAL.idUser+"&&MaApDung=";



    TextView tvTongTien, tvThanhTien, tvTongKH, tvGiamGia;
    Button btnPay, btnCoupon, btnMyCoupon;
    EditText txtMaApDung;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_offline);
        decorateActionBar();

        api = new API(PayOfflineActivity.this);
        context = getApplicationContext();



        //map
        lnEditUser = findViewById(R.id.layoutEditUser_Pay);
        tvName = findViewById(R.id.tvName_Pay);
        tvEmail = findViewById(R.id.tvEmail_Pay);
        tvAddress = findViewById(R.id.tvAddress_Pay);
        tvPhone = findViewById(R.id.tvPhone_Pay);
        rcl_pay = findViewById(R.id.rclPay_Pay);
        tvTongTien = findViewById(R.id.tvTongTien_Pay);
        tvThanhTien = findViewById(R.id.tvThanhTien_Pay);
        tvTongKH = findViewById(R.id.tvTotalCourses_Pay);
        btnPay = findViewById(R.id.btnPay_Pay);
        btnCoupon = findViewById(R.id.btn_Order_ApdungGiamGia);
        txtMaApDung = findViewById(R.id.txtMaApDung_Pay);
        btnMyCoupon = findViewById(R.id.btnXemCoupon_Pay);
        tvGiamGia = findViewById(R.id.tvGiamGia_Pay);

        //data
        loadDataInfo();

        //set rcl
        payOfflineAdapter = new PayOfflineAdapter(this, dataPay);
        rcl_pay.setHasFixedSize(true);
        rcl_pay.setAdapter(payOfflineAdapter);
        rcl_pay.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        getPayItems();

        //event

        //onclick edit user
        lnEditUser.setOnClickListener(v -> {
            Intent intent = new Intent(PayOfflineActivity.this, PayEditUserActivity.class);
            startActivity(intent);
        });
        btnMyCoupon.setOnClickListener(v -> {
            Intent intent = new Intent(PayOfflineActivity.this, CouponWalletActivity.class);
            startActivity(intent);
        });

        btnPay.setOnClickListener(v ->{
            if (tvName.getText().toString().trim().equals("")||tvEmail.getText().toString().trim().equals("")||tvAddress.getText().toString().trim().equals("")||tvPhone.getText().toString().trim().equals(""))
            {
                Toast.makeText(context, "Bạn thiếu thông tin người dùng", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                try {
                    postPay();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
        btnCoupon.setOnClickListener(v ->{
            if(txtMaApDung.getText().toString().trim().equals("")){
                Toast.makeText(context, "Bạn chưa nhập mã giảm giá, vui lòng nhập để áp dụng", Toast.LENGTH_SHORT).show();
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

    }

    private void getMaGiamGia() throws JSONException {
        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();


//        //put parmas
//        parmas.put("MaND", GLOBAL.idUser);
//        parmas.put("MaGioHang", GLOBAL.cart.getCartID());



        paramsHeaders.put("Content-Type", "application/json");
        String urlgetMaApDung = urlgetCoupon + txtMaApDung.getText().toString().trim();
        api.CallAPI(urlgetMaApDung, Request.Method.GET, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {

                Log.i("success", "my response" + dataResponse);
                GLOBAL.SoGiaGiam = dataResponse;
                String giamgia = dataResponse;
//                int thanhtien = Integer.parseInt(GLOBAL.cart.getTongTien()) - Integer.parseInt(GLOBAL.SoGiaGiam);
//                tvThanhTien.setText(thanhtien + " Đ");
                tvGiamGia.setText("- "+utils.formatNumberCurrency(giamgia)+" Đ");
                Toast.makeText(getApplicationContext(), "Áp dụng thành công", Toast.LENGTH_SHORT).show();
                txtMaApDung.setEnabled(false);

            }

            @Override
            public void ReponseError(String error) {

                Log.e("error", "my error: " + error);
                Toast.makeText(getApplicationContext(), "Áp dụng thất bại, người dùng không có mã", Toast.LENGTH_LONG).show();
            }
        });
    }





    private void loadDataInfo(){
        tvName.setText(GLOBAL.infoThuHo.getHoTen());
        tvEmail.setText(GLOBAL.infoThuHo.getEmail());
        tvAddress.setText(GLOBAL.infoThuHo.getDiaChi());
        tvPhone.setText(GLOBAL.infoThuHo.getSDT());
    }







    public void getPayItems() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONObject> thanhcong = response -> {

            try {

                JSONArray Data = response.getJSONArray("DanhSachHangHoa");

                GLOBAL.cart = new CART(response.getInt("MaHoaDon"),
                        response.getInt("MaND"),
                        response.getString("TongThanhToan"));

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
                tvTongKH.setText("("+Data.length()+" khóa học)");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            payOfflineAdapter.notifyDataSetChanged();



        };

        com.android.volley.Response.ErrorListener thatbai = error -> {
            if (error.getMessage() != null) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlgetPay, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);


    }


    private void postPay() throws JSONException {
        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();


        //put parmas
        parmas.put("MaKH", GLOBAL.idUser);
        parmas.put("MaHD", GLOBAL.idHD_pay);
        parmas.put("DiaChiThu", GLOBAL.infoThuHo.getDiaChi());
        parmas.put("Email", GLOBAL.infoThuHo.getEmail());
        parmas.put("SDTThu", GLOBAL.infoThuHo.getSDT());
        parmas.put("SoTienThu", GLOBAL.cart.getTongTien());
        parmas.put("MaApDung", txtMaApDung.getText().toString());




        paramsHeaders.put("Content-Type", "application/json");

        api.CallAPI(urlpostPayment, Request.Method.POST, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {

                Log.i("success", "my response" + dataResponse);
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                PayOfflineActivity.this.finish();
                GLOBAL.notifications.add(new NOTIFICATION(R.drawable.ic_logonotification, "Thông báo mua khóa học thành công",
                "Bạn đã đặt khóa học thành công! Vui lòng thanh toán và đợi nhân viên duyệt đơn hàng, sau khi nhân viên hệ thống sẽ thêm khóa học vào phòng học của bạn." +
                        "\nMọi thắc mắc về thanh toán, vui lòng gọi hotline hỗ trợ của OnLearn." + "\nOnLearn xin chân thành cảm ơn bạn.",
                        currentDate));

                Toast.makeText(getApplicationContext(), "Thanh toán thành công ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(PayOfflineActivity.this, PaySuccessfulActivity.class);
                startActivity(intent);


            }

            @Override
            public void ReponseError(String error) {

                Log.e("error", "my error: " + error);
                Toast.makeText(getApplicationContext(), "Lỗi khi tạo hóa đơn ", Toast.LENGTH_LONG).show();
            }
        });
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
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + titleActionBar + "</font>"));
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