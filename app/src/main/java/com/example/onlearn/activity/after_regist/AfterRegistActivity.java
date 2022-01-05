package com.example.onlearn.activity.after_regist;

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
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.activity.profile_user.ProfileUserActivity;
import com.example.onlearn.utils.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class AfterRegistActivity extends AppCompatActivity {
    TextView tvValidate;
    EditText txtName, txtPhone, txtEmail;
    Button btnSubmit;
    String urlputUser = GLOBAL.ip + "api/nguoidung/?MaNDUpdate="+GLOBAL.idUser;
    API api;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_regist);
        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //map
        tvValidate = findViewById(R.id.tvValidate_afterRegist);
        txtName = findViewById(R.id.txtName_Register);
        txtEmail = findViewById(R.id.txtEmail_Register);
        txtPhone = findViewById(R.id.txtPhone_Register);
        btnSubmit = findViewById(R.id.btnSubmit_AfterRegist);

        api = new API(AfterRegistActivity.this);
        context = getApplicationContext();


        //event
        btnSubmit.setOnClickListener(v -> {
            if (txtName.getText().toString().equals("") || txtName.getText().toString().isEmpty()) {
                tvValidate.setText("Vui lòng điền thông tin Họ và tên");
                return;
            }
            if (txtEmail.getText().toString().equals("") || txtEmail.getText().toString().isEmpty()) {
                tvValidate.setText("Vui lòng điền thông tin Email");
                return;
            }
            if (txtPhone.getText().toString().equals("") || txtPhone.getText().toString().isEmpty()) {
                tvValidate.setText("Vui lòng nhập Số điện thoại");
                return;
            }
            else
                {
                try {
                    updateAfterRegist();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }


        });


    }

    //put method volley
    private void updateAfterRegist() throws JSONException, ParseException {

        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();
        String gender = "";

//        parmas.put("MaNDUpdate=", GLOBAL.idUser);
        parmas.put("UserID", GLOBAL.idUser);
        parmas.put("UserName", GLOBAL.username);
        parmas.put("Name", txtName.getText().toString());
//        parmas.put("CMND", "");
        parmas.put("Number", txtPhone.getText().toString());
        parmas.put("Email", txtEmail.getText().toString());
        parmas.put("Gender", gender);
        parmas.put("DoB", "");
        parmas.put("Address", "");
        parmas.put("HinhAnh", "");
        parmas.put("GroupID", 1);
        parmas.put("Salary", 0);


        paramsHeaders.put("Content-Type", "application/json");


        api.CallAPI(urlputUser, Request.Method.PUT, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {

                Log.i("success", "my response" + dataResponse);

                Intent intent = new Intent(context, HomeActivity.class);
                startActivity(intent);
            }

            @Override
            public void ReponseError(String error) {

                Log.e("error", "my error: " + error);
//                Toast.makeText(getApplicationContext(), " " + error, Toast.LENGTH_LONG).show();
                tvValidate.setText("Sửa không thành công\nThông tin đã có người sử dụng");
            }
        });

    }


}