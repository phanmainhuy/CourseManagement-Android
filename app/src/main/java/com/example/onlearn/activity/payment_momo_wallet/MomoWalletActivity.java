package com.example.onlearn.activity.payment_momo_wallet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.onlearn.R;
import com.example.onlearn.activity.pay_successfully.PaySuccessfulActivity;

public class MomoWalletActivity extends AppCompatActivity {
    Button btnSaveQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_momo_wallet);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //map
        btnSaveQR = findViewById(R.id.btnContinue_MomoWallet);


        //events
        btnSaveQR.setOnClickListener(v -> {
            this.finish();
            Intent intent = new Intent(this, PaySuccessfulActivity.class);
            startActivity(intent);

        });



    }
}