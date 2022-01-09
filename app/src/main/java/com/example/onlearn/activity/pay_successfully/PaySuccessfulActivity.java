package com.example.onlearn.activity.pay_successfully;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
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
        //Change status bar
        Activity activity = PaySuccessfulActivity.this;
        Window window = activity.getWindow();

    // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

    // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(activity, R.color.statusBar_payment_successful));



    }
}