package com.example.onlearn.activity.login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.onlearn.API.API;
import com.example.onlearn.API.ICallBack;
import com.example.onlearn.activity.forget_pass.ForgetpassActivity;
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.activity.register.RegisterActivity;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    String urlApi = GLOBAL.ip + "api/identity";

    Button btnLogin, btnRegist, btnFB, btnGG;
    TextView btnForgetPass;
    SharedPreferences remember;
    CheckBox chkSave;
    EditText txtUsername, txtPassword;
    API api;
    Context context;

    //    public static List<KHOAHOC> favoriteCourses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        context = getApplicationContext();
        api = new API(LoginActivity.this);

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

        btnForgetPass.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Chuyển sang quên mật khẩu", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(LoginActivity.this, ForgetpassActivity.class);
            startActivity(intent1);
        });

        btnRegist.setOnClickListener(v -> {
            Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent1);
        });

        btnLogin.setOnClickListener(v ->{
            if (txtUsername.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Vui lòng điền tên đăng nhập", Toast.LENGTH_SHORT).show();
                return;
            }

            if (txtPassword.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                return;
            }

            //neu khong rong thi chay ham login
            else
            {
                try {
                    checkLogin();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    private void checkLogin() throws JSONException {

        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();

        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        parmas.put("username", username);
        parmas.put("password", password);
        paramsHeaders.put("Content-Type", "application/json");
        api.CallAPI(urlApi, Request.Method.POST, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {
                Log.i("success",dataResponse);

                try {
                    JSONObject result = new JSONObject(dataResponse);
                    GLOBAL.idUser = result.getInt("UserID");
//                    Toast.makeText(getApplicationContext(), GLOBAL.idUser, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                saveUserLogin();
                Intent intent1 = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent1);
                // nếu data trả về là object thì --> tạo dataJsonObject cho data {"message:"success",data:[{id:"1",name:"gido"},{id:"2",name:"123"]}
                // JSONObject objResult = new JSONObject(dataResponse);
                // }
                //
                //   JSONArray arrayResult = objResult.getJSONArray("data");
            }
            @Override
            public void ReponseError(String error) {
                Log.e("error",error);
                Toast.makeText(getApplicationContext(), "Sai mật khẩu", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveUserLogin(){
        //Luu lai thong tin dang nhap
        SharedPreferences.Editor editor = remember.edit();
        if (chkSave.isChecked()) {
            //luu lai thong tin
            editor.putString("username", txtUsername.getText().toString());
            editor.putString("password", txtPassword.getText().toString());
        }
        editor.putBoolean("saveinfo", chkSave.isChecked());
        editor.commit();
        Toast.makeText(getApplicationContext(), "Đã lưu thông tin đăng nhập", Toast.LENGTH_SHORT).show();
    }

//    private void  onLogin() throws JSONException {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//            String username = txtUsername.getText().toString();
//            String password = txtPassword.getText().toString();
//        Response.Listener<JSONObject> thanhcong = response -> {
//            Log.v("login response", response.toString());
//        };
//
//        Response.ErrorListener thatbai = error -> {
//            if(error.getMessage()!=null){
//                Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        };
//        LOGIN login = new LOGIN(username,password);
//
//
//        JSONObject jsonBody = new JSONObject();
////        jsonBody.put("model", login);
//        jsonBody.put("UserName", username);
//        jsonBody.put("Password", password);
//
//        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.POST, urlApi, jsonBody, thanhcong, thatbai);
//        requestQueue.add(jsonArrayRequest);
//    }





}