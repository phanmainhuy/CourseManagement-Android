package com.example.onlearn.activity.register;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.onlearn.API.API;
import com.example.onlearn.API.ICallBack;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.activity.after_regist.AfterRegistActivity;
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.activity.login.LoginActivity;
import com.example.onlearn.R;
import com.example.onlearn.utils.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    String urlPostRegist = GLOBAL.ip + "api/Identity/student";
    TextView tvValidation;

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

        //Change status bar
    Activity activity = RegisterActivity.this;
    Window window = activity.getWindow();

    // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

    // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(activity, R.color.statusBar_register));


        //anh xa
        btnCancel = findViewById(R.id.btnCancel_Register);
        btnRegist = findViewById(R.id.btnRegister_Register);
        txtUserName = findViewById(R.id.txt_username_Register);
        txtPassword = findViewById(R.id.txt_password_Register);
        txtRePass = findViewById(R.id.txt_repass_Register);
        tvValidation = findViewById(R.id.tvValidate_Register);



        //Onclick
        btnCancel.setOnClickListener(v -> {
            Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent1);
        });


        btnRegist.setOnClickListener(v -> {
            if (txtUserName.getText().toString().trim().equals("")) {
//                Toast.makeText(context, "Vui lòng không bỏ trống tên đăng nhập", Toast.LENGTH_SHORT).show();
                tvValidation.setText("Vui lòng không bỏ trống tên đăng nhập");
                return;
            }
            if (txtRePass.getText().toString().trim().equals("") || txtPassword.getText().toString().trim().equals("")) {
//                Toast.makeText(context, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                tvValidation.setText("Vui lòng nhập mật khẩu");
                return;
            }
            if (!txtPassword.getText().toString().equals(txtRePass.getText().toString())) {
//                Toast.makeText(context, "Mật khẩu nhập lại không khớp", Toast.LENGTH_SHORT).show();
                tvValidation.setText("Mật khẩu nhập lại không khớp");
                return;
            }
            if (txtUserName.getText().toString().length() < 6) {
//                Toast.makeText(context, "Tên đăng nhập phải trên 6 ký tự\nBạn đang nhập " + txtUserName.getText().toString().length() + " kí tự", Toast.LENGTH_SHORT).show();
                tvValidation.setText("Tên đăng nhập phải trên 6 ký tự\nBạn đang nhập " + txtUserName.getText().toString().length() + " kí tự");
                return;
            }
            if (txtPassword.getText().toString().length() < 6) {
//                Toast.makeText(context, "Mật khẩu phải trên 6 ký tự\nBạn đang nhập " + txtPassword.getText().toString().length() + " kí tự", Toast.LENGTH_SHORT).show();
                tvValidation.setText("Mật khẩu phải trên 6 ký tự\nBạn đang nhập " + txtPassword.getText().toString().length() + " kí tự");
                return;
            } else {
                try {
                    postRegister();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        });

    }

    private void postRegister() throws JSONException {

        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();

        String username = txtUserName.getText().toString();
        String password = txtPassword.getText().toString();

        //put parmas
        parmas.put("UserName", username);
        parmas.put("Password", password);
        paramsHeaders.put("Content-Type", "application/json");
        api.CallAPI(urlPostRegist, Request.Method.POST, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {
                Log.i("success", dataResponse);

                try {
                    JSONObject result = new JSONObject(dataResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent1);
                Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void ReponseError(String error) {
                Log.e("error", "My error: " + error);
                tvValidation.setText("Tên đăng nhập đã có người sử dụng");
                Toast.makeText(getApplicationContext(), "Đăng ký thất bại", Toast.LENGTH_LONG).show();
            }
        });
    }


}