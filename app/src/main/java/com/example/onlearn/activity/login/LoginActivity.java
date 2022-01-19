package com.example.onlearn.activity.login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.onlearn.API.API;
import com.example.onlearn.API.ICallBack;
import com.example.onlearn.activity.after_regist.AfterRegistActivity;
import com.example.onlearn.activity.forget_pass.ForgetpassActivity;
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.activity.register.RegisterActivity;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.terms.TermsOfUseActivity;
import com.example.onlearn.utils.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    String urlApi = GLOBAL.ip + "api/identity";

    Button btnLogin;
    TextView btnForgetPass, btnDieuKhoan, btnRegist;
    SharedPreferences remember;
    CheckBox chkSave;
    EditText txtUsername, txtPassword;
    API api;
    Context context;
    Activity activity = LoginActivity.this;

    //    public static List<KHOAHOC> favoriteCourses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();

        context = getApplicationContext();
        api = new API(LoginActivity.this);

        actionBar.hide();


        Window window = activity.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(activity, R.color.statusBar_payment_successful));


        //anh xa
        btnLogin = findViewById(R.id.btnLogin_Login);
        btnForgetPass = findViewById(R.id.btnForgetPass_Login);
        btnRegist = findViewById(R.id.btnRegist_Login);
        chkSave = findViewById(R.id.chkSave_Login);
        txtPassword = findViewById(R.id.txtPassword_Login);
        txtUsername = findViewById(R.id.txtusername_Login);
        btnDieuKhoan = findViewById(R.id.tvDieuKhoanSD_Login);


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
//            Toast.makeText(getApplicationContext(), "Chuyển sang quên mật khẩu", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(LoginActivity.this, ForgetpassActivity.class);
            startActivity(intent1);
        });

        btnDieuKhoan.setOnClickListener(v -> {
            Intent intent1 = new Intent(LoginActivity.this, TermsOfUseActivity.class);
            startActivity(intent1);
        });

        btnRegist.setOnClickListener(v -> {
            Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent1);
        });

        btnLogin.setOnClickListener(v -> {
            if (txtUsername.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Vui lòng điền tên đăng nhập", Toast.LENGTH_SHORT).show();
                return;
            }

            if (txtPassword.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                return;
            }

            //neu khong rong thi chay ham login
            else {
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
                Log.i("success", dataResponse);
                try {
                    JSONObject result = new JSONObject(dataResponse);
                    GLOBAL.idUser = result.getInt("UserID");
                    GLOBAL.username = result.getString("UserName");
                    String name = result.getString("Name");
                    GLOBAL.passwordLogin = txtPassword.getText().toString();
                    if (name.equals("null")) {
                        saveUserLogin();
                        LoginActivity.this.finish();
                        Intent intent1 = new Intent(LoginActivity.this, AfterRegistActivity.class);
                        startActivity(intent1);
                    } else {
                        saveUserLogin();
                        LoginActivity.this.finish();

                        Intent intent1 = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent1);
                    }
//                    Toast.makeText(getApplicationContext(), GLOBAL.idUser, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                // nếu data trả về là object thì --> tạo dataJsonObject cho data {"message:"success",data:[{id:"1",name:"gido"},{id:"2",name:"123"]}
                // JSONObject objResult = new JSONObject(dataResponse);
                // }
                //
                //   JSONArray arrayResult = objResult.getJSONArray("data");
            }

            @Override
            public void ReponseError(String error) {
                Log.e("error", "My error: " + error);
                Toast.makeText(getApplicationContext(), "Sai mật khẩu", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveUserLogin() {
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
}