package com.example.onlearn.activity.pay;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.pay_edit.PayEditUserActivity;

public class PayActivity extends AppCompatActivity {
    String titleActionBar = "Thanh toán";

    //info
    LinearLayout lnEditUser;
    TextView tvName, tvEmail, tvAddress, tvPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        decorateActionBar();

        //map
        lnEditUser = findViewById(R.id.layoutEditUser_Pay);
        tvName = findViewById(R.id.tvName_Pay);
        tvEmail = findViewById(R.id.tvEmail_Pay);
        tvAddress = findViewById(R.id.tvAddress_Pay);
        tvPhone = findViewById(R.id.tvPhone_Pay);

        //data
        loadDataInfo();

        //event

        //onclick edit user
        lnEditUser.setOnClickListener(v -> {
            Intent intent = new Intent(PayActivity.this, PayEditUserActivity.class);
            startActivity(intent);
        });


    }


    private void loadDataInfo(){
        tvName.setText(GLOBAL.infoThuHo.getHoTen());
        tvEmail.setText(GLOBAL.infoThuHo.getEmail());
        tvAddress.setText(GLOBAL.infoThuHo.getDiaChi());
        tvPhone.setText(GLOBAL.infoThuHo.getSDT());
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