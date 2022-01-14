package com.example.onlearn.activity.payment_online_method;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.onlearn.activity.payment_razor_wallet.RazorPayWalletActivity;
import com.example.onlearn.activity.payment_vnpay_wallet.VNPayWalletActivity;

public class PaymentOnlineWalletActivity extends AppCompatActivity {
    String titleActionBar = "Thanh toán bằng ví điện tử";
    LinearLayout btnMoMo, btnAirPay, btnVNPay, btnZaloPay, btnRazorpay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_online_wallet);
        DecorateActionBar();
        //map
        btnMoMo = findViewById(R.id.btnMoMo_PaymentWallet);
        btnAirPay = findViewById(R.id.btnAirpay_PaymentWallet);
        btnVNPay = findViewById(R.id.btnVnPay_PaymentWallet);
        btnZaloPay = findViewById(R.id.btnZaloPay_PaymentWallet);
        btnRazorpay = findViewById(R.id.btnRazorPay_PaymentWallet);

        btnMoMo.setOnClickListener(v -> {
            Intent intent = new Intent(this, MomoWalletActivity.class);
            startActivity(intent);
        });

        btnVNPay.setOnClickListener(v -> {
            Intent intent = new Intent(this, VNPayWalletActivity.class);
            startActivity(intent);
        });
        btnZaloPay.setOnClickListener(v -> {
//            Intent intent = new Intent(this, VNPayWalletActivity.class);
//            startActivity(intent);
            notificateDeveloping();

        });
        btnAirPay.setOnClickListener(v -> {
            notificateDeveloping();

        });
        btnRazorpay.setOnClickListener(v -> {
                Intent intent = new Intent(this, RazorPayWalletActivity.class);
                startActivity(intent);
        });



    }

    private void notificateDeveloping(){
        //Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setTitle
        builder.setTitle("Thông báo");
        builder.setMessage("Tính năng này đang được phát triển.\nVui lòng chọn ví thanh toán online khác");
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