package com.example.onlearn.activity.pay_successfully;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.onlearn.R;
import com.example.onlearn.activity.home.HomeActivity;

public class PaySuccessfulActivity extends AppCompatActivity {
    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paysuccessful);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnReturn = findViewById(R.id.btnReturn_order_success);


        btnReturn.setOnClickListener(v ->{
            this.finish();
            Intent intent = new Intent(PaySuccessfulActivity.this, HomeActivity.class);
            startActivity(intent);
        });



    }
}