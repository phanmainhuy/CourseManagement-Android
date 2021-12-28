package com.example.onlearn.activity.register;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlearn.API.API;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.activity.login.LoginActivity;
import com.example.onlearn.R;

public class RegisterActivity extends AppCompatActivity {

    String urlPostRegist = GLOBAL.ip + "api/Identity/student";

    Button btnCancel, btnRegist;
    EditText txtUserName, txtPassword, txtRePass;
    API api;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //set up context
        context = getApplicationContext();
        api = new API(RegisterActivity.this);



        //anh xa
        btnCancel = findViewById(R.id.btnCancel_Register);
        btnRegist = findViewById(R.id.btnRegister_Register);
        txtUserName = findViewById(R.id.txt_username_Register);
        txtPassword = findViewById(R.id.txt_password_Register);
        txtRePass = findViewById(R.id.txt_repass_Register);



        //Onclick
        btnCancel.setOnClickListener(v -> {
            Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent1);
        });


        btnRegist.setOnClickListener(v -> {
            if (txtUserName.getText().equals("")|| txtRePass.getText().equals("")||txtPassword.getText().equals("")){
                Toast.makeText(context, "Bạn nhập thiếu thông tin", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!txtPassword.getText().equals(txtRePass.getText())){
                Toast.makeText(context, "Mật khẩu nhập lại không khớp", Toast.LENGTH_SHORT).show();
            }



        });



    }








}