package com.example.onlearn.activity.change_pass_forget;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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


    String urlPostPass = GLOBAL.ip + "api/nguoidung/doimatkhau2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass_forget);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        api = new API(ChangePassForgetActivity.this);
        context = getApplicationContext();


        //map
        btnResendCode = findViewById(R.id.btnResendCode_ForgetPass);
        btnChangePass = findViewById(R.id.btnChangePass_FG);
        txtPass = findViewById(R.id.txtPassword_ForgetPass);
        txtRePass = findViewById(R.id.txtRePass_ForgetPass);
        txtCode = findViewById(R.id.txtCode_ForgetPass);
        tvTime = findViewById(R.id.tvTimeCode_ForgetPass);
        tvValidate = findViewById(R.id.tvValidate_ForgetPass2);



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