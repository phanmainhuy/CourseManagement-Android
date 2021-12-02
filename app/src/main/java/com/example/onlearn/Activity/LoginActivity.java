package com.example.onlearn.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlearn.R;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnRegist, btnFB, btnGG;
    TextView btnForgetPass;
    SharedPreferences remember;
    CheckBox chkSave;
    EditText txtUsername, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //anh xa
        btnLogin = findViewById(R.id.btnLogin_Login);
        btnForgetPass = findViewById(R.id.btnForgetPass_Login);
        btnRegist = findViewById(R.id.btnRegist_Login);
        chkSave = findViewById(R.id.chkSave_Login);
        txtPassword = findViewById(R.id.txtPassword_Login);
        txtUsername = findViewById(R.id.txtusername_Login);


        //Xu ly checkbox luu mat khau
        remember = getSharedPreferences("data", MODE_PRIVATE);
        //nap du lieu
        if (remember.getBoolean("saveinfo", false) == true) {
            //load du lieu len editText
            txtUsername.setText(remember.getString("username", ""));
            txtPassword.setText(remember.getString("password", ""));
            chkSave.setChecked(true);
        }

        btnForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Chuyển sang quên mật khẩu", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(LoginActivity.this, ForgetpassActivity.class);
                startActivity(intent1);
            }
        });

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent1);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Luu lai thong tin dang nhap
                SharedPreferences.Editor editor = remember.edit();
                if (chkSave.isChecked()) {
                    //luu lai thong tin
                    editor.putString("username", txtUsername.getText().toString());
                    editor.putString("password", txtPassword.getText().toString());
                }
                editor.putBoolean("saveinfo", chkSave.isChecked());
                editor.commit();
                Toast.makeText(getApplicationContext(), "Đã lưu thông tin đăng nhập", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent1);

            }
        });














    }
}