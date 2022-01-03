package com.example.onlearn.activity.pay_edit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;

public class PayEditUserActivity extends AppCompatActivity {
    String titleActionBar = "Thay đổi thông tin người đặt";
    EditText txtName, txtEmail, txtAddress, txtPhone;
    Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payedit_user);
        decorateActionBar();

        //map
        txtName = findViewById(R.id.txtName_Pay);
        txtEmail = findViewById(R.id.txtEmail_Pay);
        txtAddress = findViewById(R.id.txtAddress_Pay);
        txtPhone = findViewById(R.id.txtPhone_Pay);
        btnSave = findViewById(R.id.btnSaveInfo_Pay);
        btnCancel = findViewById(R.id.btnCancelSaveInfo_Pay);



        //event
        btnCancel.setOnClickListener(v -> {
            this.finish();
        });

        btnSave.setOnClickListener(v -> {
            
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