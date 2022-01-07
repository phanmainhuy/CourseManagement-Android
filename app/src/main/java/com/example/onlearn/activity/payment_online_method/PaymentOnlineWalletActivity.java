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
import android.widget.LinearLayout;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.payment_momo_wallet.MomoWalletActivity;

public class PaymentOnlineWalletActivity extends AppCompatActivity {
    String titleActionBar = "Thanh toán bằng ví điện tử";
    LinearLayout btnMoMo, btnAirPay, btnVNPay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_online_wallet);
        DecorateActionBar();
        //map
        btnMoMo = findViewById(R.id.btnMoMo_PaymentWallet);
        btnAirPay = findViewById(R.id.btnAirpay_PaymentWallet);
        btnVNPay = findViewById(R.id.btnVnPay_PaymentWallet);

        btnMoMo.setOnClickListener(v -> {
            Intent intent = new Intent(this, MomoWalletActivity.class);
            startActivity(intent);
        });



    }



//    //action bar
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