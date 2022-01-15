package com.example.onlearn.activity.payment_online_method;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.coupon_wallet.CouponWalletActivity;
import com.example.onlearn.activity.payment_methods.PaymentMethodsActivity;
import com.example.onlearn.activity.payment_razor_wallet.RazorPayWalletActivity;

public class PaymentOnlineActivity extends AppCompatActivity {
    String titleActionBar = "Thanh toán online";
    TextView tvTotalCourses, tvTongTien, tvGiamGia, tvThanhTien;
    Button btnMyCoupon, btngetCoupon, btnPayOnline;
    EditText txtCoupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_online);
        DecorateActionBar();

        //map
        tvTotalCourses = findViewById(R.id.tvTotalCourses_PayOnline);
        tvTongTien = findViewById(R.id.tvTongTien_PayOnline);
        tvThanhTien = findViewById(R.id.tvThanhTien_PayOnline);
        tvGiamGia = findViewById(R.id.tvGiamGia_PayOnline);
        txtCoupon = findViewById(R.id.txtMaApDung_PayOnline);

        btnMyCoupon = findViewById(R.id.btnXemCoupon_PayOnline);
        btngetCoupon = findViewById(R.id.btnApdungGiamGia_PayOnline);
        btnPayOnline = findViewById(R.id.btnPay_PayOnline);


        btnMyCoupon.setOnClickListener(v -> {
            Intent intent = new Intent(this, CouponWalletActivity.class);
            startActivity(intent);
        });
        btngetCoupon.setOnClickListener(v -> {
            if(txtCoupon.getText().toString().trim().equals("")){
                Toast.makeText(getApplicationContext(), "Bạn chưa nhập mã giảm giá", Toast.LENGTH_SHORT).show();
                tvGiamGia.setText("");
                return;
            }
            else {
                tvGiamGia.setText("- 20.000 đ");
            }
        });
        btnPayOnline.setOnClickListener(v -> {
            Intent intent = new Intent(this, PaymentOnlineWalletActivity.class);
            startActivity(intent);
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