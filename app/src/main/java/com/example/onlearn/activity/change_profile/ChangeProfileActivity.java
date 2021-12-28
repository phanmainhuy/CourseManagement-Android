package com.example.onlearn.activity.change_profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.onlearn.API.API;
import com.example.onlearn.API.ICallBack;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.activity.login.LoginActivity;
import com.example.onlearn.activity.profile_user.UserActivity;
import com.example.onlearn.models.USER;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class ChangeProfileActivity extends AppCompatActivity {
    String titleActionBar = "Sửa thông tin người dùng";
    Button btnSave, btnLogout;
    EditText txtName, txtNumber, txtEmail, txtDoB, txtAddress, txtCMND;
    TextView tvUserName;
    ImageView imgAvatar;
    RadioButton rdoMale, rdoFemale;
    Context context;


    API api;
    //url
    String urlputUser = GLOBAL.ip + "api/nguoidung";
    String urlImgUser = GLOBAL.ip + GLOBAL.urlimg + "users/";
    private USER user = GLOBAL.userlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeprofile);
        DecorateActionBar();


        context = getApplicationContext();
        api = new API(ChangeProfileActivity.this);
        //anh xa
        btnLogout = findViewById(R.id.btn_User_LogOut);
        btnSave = findViewById(R.id.btn_User_Save);
        txtName = findViewById(R.id.txt_User_Name);
        txtNumber = findViewById(R.id.txt_User_Phone);
        txtEmail = findViewById(R.id.txt_User_Email);
        txtDoB = findViewById(R.id.txt_User_Birthday);
        txtAddress = findViewById(R.id.txt_User_Address);
        txtCMND = findViewById(R.id.txt_User_CMND);
        tvUserName = findViewById(R.id.tv_User_username);
        imgAvatar = findViewById(R.id.img_User_Avatar);
        rdoMale = findViewById(R.id.rdb_User_Male);
        rdoFemale = findViewById(R.id.rdb_User_Female);


        //add data
        tvUserName.setText(user.getUserName());
        txtName.setText(user.getTen());
        txtNumber.setText(user.getNumber());
        txtEmail.setText(user.getEmail());
        try {
            txtDoB.setText(utils.converDateFormate(user.getBirthday()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        txtAddress.setText(user.getAddress());
        txtCMND.setText(user.getCMND());
        Picasso.with(this)
                .load(urlImgUser + user.getImgUser())
                .placeholder(R.drawable.no_image_found)
                .into(imgAvatar);

        //set Gender
        if (user.getGender().equals("Nữ")) {
            rdoFemale.setChecked(true);
        } else {
            rdoMale.setChecked(true);
        }
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        btnSave.setOnClickListener(v -> {
            try {
                changeProfileUser();

            } catch (JSONException | ParseException e) {
                e.printStackTrace();
            }

        });



    }

    //put method volley
    private void changeProfileUser() throws JSONException, ParseException {

        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();

        //set cứng
//        parmas.put("UserId", 2);
//        parmas.put("UserName", "PhanMaiNhuY");
//        parmas.put("Name", "Test put");
        parmas.put("UserID", GLOBAL.idUser);
        parmas.put("UserName", tvUserName.getText().toString());
        parmas.put("Name", txtName.getText().toString());
        parmas.put("CMND", txtCMND.getText().toString());
        parmas.put("Number", txtNumber.getText().toString());
        parmas.put("Email", txtEmail.getText().toString());
        parmas.put("DoB", utils.converDatePutPost(txtDoB.getText().toString()));
        parmas.put("Address", txtAddress.getText().toString());
        parmas.put("HinhAnh", GLOBAL.userlogin.getImgUser());
        parmas.put("GroupID", GLOBAL.userlogin.getGroupID());
        parmas.put("Salary", GLOBAL.userlogin.getSalary());
        paramsHeaders.put("Content-Type", "application/json");
        //print paramas
        Log.i("infoput", parmas.toString());


        //api null
        api.CallAPI(urlputUser, Request.Method.PUT, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {

                    Log.i("success", "my response" +dataResponse);

//                try {
//                    JSONObject result = new JSONObject(dataResponse);
//                    GLOBAL.idUser = result.getInt("UserID");
////                    Toast.makeText(getApplicationContext(), GLOBAL.idUser, Toast.LENGTH_LONG).show();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                Intent intent = new Intent(context, UserActivity.class);
                startActivity(intent);
            }

            @Override
            public void ReponseError(String error) {

                    Log.e("error", "my error: "+ error);
                    Toast.makeText(getApplicationContext(), "Sửa không thành công", Toast.LENGTH_LONG).show();
            }
        });

    }


    //action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void DecorateActionBar() {
        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + titleActionBar + "</font>"));
        //doi mau thanh action bars
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
    }
}