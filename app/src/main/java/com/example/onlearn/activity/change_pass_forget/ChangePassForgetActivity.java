package com.example.onlearn.activity.change_pass_forget;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.example.onlearn.R;
import com.example.onlearn.activity.forget_pass.ForgetpassActivity;
import com.example.onlearn.activity.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePassForgetActivity extends AppCompatActivity {
    Button btnResendCode, btnChangePass;
    EditText txtPass, txtRePass, txtCode;
    TextView tvValidate, tvTime;
    API api;
    Context context;
    Activity activity = ChangePassForgetActivity.this;


    String urlPostPass = GLOBAL.ip + "api/nguoidung/doimatkhau2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass_forget);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        api = new API(ChangePassForgetActivity.this);
        context = getApplicationContext();

        //Change status bar

    Window window = activity.getWindow();

    // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

    // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(activity, R.color.black));


        //map
        btnResendCode = findViewById(R.id.btnResendCode_ForgetPass);
        btnChangePass = findViewById(R.id.btnChangePass_FG);
        txtPass = findViewById(R.id.txtPassword_ForgetPass);
        txtRePass = findViewById(R.id.txtRePass_ForgetPass);
        txtCode = findViewById(R.id.txtCode_ForgetPass);
        tvTime = findViewById(R.id.tvTimeCode_ForgetPass);
        tvValidate = findViewById(R.id.tvValidate_ForgetPass2);

        countTime();

        //events
        btnChangePass.setOnClickListener(v ->{
            if(txtPass.getText().toString().trim().equals("") || txtRePass.getText().toString().trim().equals("")){
                tvValidate.setText("Vui lòng nhập đầy đủ thông tin mật khẩu");
                return;
            }
            if(txtCode.getText().toString().trim().equals("")){
                tvValidate.setText("Vui lòng nhập Code");
                return;
            }
            if(!txtPass.getText().toString().trim().equals(txtRePass.getText().toString().trim())){
                tvValidate.setText("Mật khẩu nhập lại không khớp");
                return;
            }

            else {
                try {
                    postChangeForgetPass();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

        btnResendCode.setOnClickListener(v -> {
            try {
                postForgetPass();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });


    }

    private void countTime(){
        CountDownTimer Timer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                btnResendCode.setVisibility(View.INVISIBLE);
                tvTime.setText("Thời gian còn: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                tvTime.setText("done!");
                btnResendCode.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private void postForgetPass() throws JSONException {

        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();



        //put parmas
        parmas.put("UserName", GLOBAL.getUsnForget);
        parmas.put("Email", GLOBAL.getEmForget);
        String urlpostForgetPass = GLOBAL.ip + "api/nguoidung/quenmatkhau";

        paramsHeaders.put("Content-Type", "application/json");
        api.CallAPI(urlpostForgetPass, Request.Method.POST, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {
                Log.i("success", dataResponse);
                Toast.makeText(getApplicationContext(), "Đã gửi lại mã xác nhận vào email\nQuý khách vui lòng kiểm tra email để lấy mã", Toast.LENGTH_LONG).show();
                countTime();
            }

            @Override
            public void ReponseError(String error) {
                Log.e("error", "My error: " + error);
                Toast.makeText(getApplicationContext(), "Gửi mail thất bại", Toast.LENGTH_LONG).show();
            }
        });


    }


    private void postChangeForgetPass() throws JSONException {

        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();


        String pass = txtPass.getText().toString();
        String code = txtCode.getText().toString();

        //put parmas
        parmas.put("UserName", GLOBAL.getUsnForget);
        parmas.put("NewPassword", pass);
        parmas.put("Code", code);
        paramsHeaders.put("Content-Type", "application/json");
        api.CallAPI(urlPostPass, Request.Method.POST, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {
                Log.i("success", dataResponse);

                Toast.makeText(context, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                ChangePassForgetActivity.this.finish();
                Intent intent1 = new Intent(ChangePassForgetActivity.this, LoginActivity.class);
                startActivity(intent1);
            }

            @Override
            public void ReponseError(String error) {
                Log.e("error", "My error: " + error);
                tvValidate.setText("Đổi thất bại, vui lòng kiểm tra kỹ thông tin");
                Toast.makeText(getApplicationContext(), "Đổi mật khẩu thất bại", Toast.LENGTH_LONG).show();
            }
        });


    }



}