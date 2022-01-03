package com.example.onlearn.activity.change_pass;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.onlearn.API.API;
import com.example.onlearn.API.ICallBack;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.activity.login.LoginActivity;
import com.example.onlearn.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class ChangePassActivity extends AppCompatActivity {
    Button btnCancel, btnChangePass;
    EditText txtNewPass, txtRePass, txtOldPass;
    TextView tvValidation;

    String urlpostChange = GLOBAL.ip + "api/nguoidung/doimatkhau";
    API api;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        api = new API(ChangePassActivity.this);
        context = getApplicationContext();

        //anh xa
        btnChangePass = findViewById(R.id.btnChangePass_ChangePass);
        btnCancel = findViewById(R.id.btnReturn_ChangePass);
        txtNewPass = findViewById(R.id.txtNewPassword_ChangePass);
        txtRePass = findViewById(R.id.txtRePass_ChangePass);
        tvValidation = findViewById(R.id.tvValidate_ChangePass);
        txtOldPass = findViewById(R.id.txtOldPass_ChangePass);




        btnChangePass.setOnClickListener(v -> {

            if (txtOldPass.getText().toString().trim().equals("") ) {
                tvValidation.setText("Vui lòng nhập mật khẩu cũ");
                return;
            }
            if (txtNewPass.getText().toString().trim().equals("") || txtRePass.getText().toString().trim().equals("")) {
                tvValidation.setText("Vui lòng nhập mật khẩu mới");

                return;
            }

            if (txtNewPass.getText().toString().length() < 6 || txtNewPass.getText().toString().length() > 32) {
                tvValidation.setText("Mật khẩu phải từ 6 - 32 kí tự");
                Toast.makeText(context, "Mật khẩu phải từ 6 - 32 kí tự", Toast.LENGTH_SHORT).show();

                return;
            }
            if (!txtNewPass.getText().toString().equals(txtRePass.getText().toString())) {
                tvValidation.setText("Mật khẩu nhập lại không khớp");
                Toast.makeText(context, "Mật khẩu nhập lại không khớp", Toast.LENGTH_SHORT).show();

                return;
            }

            else {
                try {
                    postChangePass();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });


        btnCancel.setOnClickListener(v -> {
            this.finish();
        });


    }

    private void postChangePass() throws JSONException {
        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();


        //put parmas
        parmas.put("UserName", GLOBAL.userlogin.getUserName());
        parmas.put("OldPassword", GLOBAL.passwordLogin);
        parmas.put("NewPassword", txtNewPass.getText().toString());


        paramsHeaders.put("Content-Type", "application/json");

        api.CallAPI(urlpostChange, Request.Method.POST, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {

                Log.i("success", "my response" + dataResponse);
                tvValidation.setText("");
                GLOBAL.passwordLogin.equals(txtNewPass.getText().toString());
                Toast.makeText(getApplicationContext(), "Đổi thành công ", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(ChangePassActivity.this, LoginActivity.class);
                startActivity(intent1);


            }

            @Override
            public void ReponseError(String error) {

                Log.e("error", "my error: " + error);
                tvValidation.setText("Mật khẩu cũ không chính xác");
                Toast.makeText(getApplicationContext(), "Đổi không thành công ", Toast.LENGTH_LONG).show();
            }
        });
    }


}