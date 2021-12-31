package com.example.onlearn.activity.forget_pass;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.onlearn.API.API;
import com.example.onlearn.API.ICallBack;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.change_pass.ChangePassActivity;
import com.example.onlearn.activity.login.LoginActivity;
import com.example.onlearn.activity.register.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgetpassActivity extends AppCompatActivity {
    Button btnContinue, btnCancel;
    EditText txtusername, txtemail;
    API api;
    Context context;

    String urlpostForgetPass = GLOBAL.ip + "api/nguoidung/quenmatkhau";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //set up api
        api = new API(ForgetpassActivity.this);
        context = getApplicationContext();


        //anh xa
        txtusername = findViewById(R.id.txtUserName_ForgetPass);
        txtemail = findViewById(R.id.txtEmail_ForgetPass);
        btnContinue = findViewById(R.id.btnContinue_ForgetPass);
        btnCancel = findViewById(R.id.btnCancel_ForgetPass);


        //xu ly
        btnCancel.setOnClickListener(v -> {
            Intent intent = new Intent(ForgetpassActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        btnContinue.setOnClickListener(v -> {
            try {
                postForgetPass();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });





    }

    private void postForgetPass() throws JSONException {

            JSONObject parmas = new JSONObject();
            Map<String, String> paramsHeaders = new HashMap<>();

            String username = txtusername.getText().toString();
            String email = txtemail.getText().toString();

            //put parmas
            parmas.put("UserName", username);
            parmas.put("Email", email);
            paramsHeaders.put("Content-Type", "application/json");
            api.CallAPI(urlpostForgetPass, Request.Method.POST, parmas.toString(), null, paramsHeaders, new ICallBack() {
                @Override
                public void ReponseSuccess(String dataResponse) {
                    Log.i("success", dataResponse);

                    try {
                        JSONObject result = new JSONObject(dataResponse);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

//
//                    Intent intent1 = new Intent(ForgetpassActivity.this, LoginActivity.class);
//                    startActivity(intent1);
                    Toast.makeText(getApplicationContext(), "Đã gửi mã xác nhận vào email\nQuý khách vui lòng kiểm tra email để lấy mã", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void ReponseError(String error) {
                    Log.e("error", "My error: " + error);
                    Toast.makeText(getApplicationContext(), "Gửi mail thất bại\nVui lòng kiểm tra lại tên đăng nhập hoặc địa chỉ email", Toast.LENGTH_LONG).show();
                }
            });


    }




}