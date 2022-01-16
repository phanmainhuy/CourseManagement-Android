package com.example.onlearn.activity.change_profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.example.onlearn.activity.change_avatar.ChangeAvatarActivity;
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.activity.login.LoginActivity;
import com.example.onlearn.activity.profile_user.ProfileUserActivity;
import com.example.onlearn.models.USER;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ChangeProfileActivity extends AppCompatActivity {
    String titleActionBar = "Sửa thông tin người dùng";
    Button btnSave, btnLogout, btnChangeTime;
    EditText txtName, txtNumber, txtEmail, txtAddress;
    TextView tvUserName, txtDoB, btnChangeAvatar;
    ImageView imgAvatar;
    RadioButton rdoMale, rdoFemale;
    Context context;
    Calendar c;
    DatePickerDialog dpd;



    API api;
    //url
    String urlputUser = GLOBAL.ip + "api/nguoidung/?MaNDUpdate="+GLOBAL.idUser;
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
        tvUserName = findViewById(R.id.tv_User_username);
        imgAvatar = findViewById(R.id.img_User_Avatar);
        rdoMale = findViewById(R.id.rdb_User_Male);
        rdoFemale = findViewById(R.id.rdb_User_Female);
        btnChangeTime = findViewById(R.id.btnChangeDate_ChangeProfile);
        btnChangeAvatar = findViewById(R.id.btnChangeAva);


        setData();


        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        btnSave.setOnClickListener(v -> {
            try {
//                this.finish();
                changeProfileUser();

            } catch (JSONException | ParseException e) {
                e.printStackTrace();
            }

        });
        btnChangeTime.setOnClickListener(v -> {
           c = Calendar.getInstance();
           int day = c.get(Calendar.DAY_OF_MONTH);
           int month = c.get(Calendar.MONTH);
           int year = c.get(Calendar.YEAR);

           dpd = new DatePickerDialog(ChangeProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
               @Override
               public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                    txtDoB.setText(mDay+"-"+(mMonth+1)+"-"+mYear);
               }


           }, day, month,year);
           dpd.show();


        });
        btnChangeAvatar.setOnClickListener(v -> {
            this.finish();
            Intent intent = new Intent(this, ChangeAvatarActivity.class);
            startActivity(intent);
        });



    }

    //add data UI
    private void setData(){
        //add data
        tvUserName.setText(user.getUserName());
        txtName.setText(user.getTen());
        txtNumber.setText(user.getNumber());
        txtEmail.setText(user.getEmail());
        try {
            txtDoB.setText(utils.converDateFormate(user.getBirthday()));
            if(txtDoB.getText().toString().equals("01-01-0001")){
                txtDoB.setText("");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        txtAddress.setText(user.getAddress());
        Picasso.with(this)
                .load(urlImgUser + user.getImgUser())
                .placeholder(R.drawable.no_image_found)
                .into(imgAvatar);
        checkGender();

    }

    private void checkGender(){
        //set Gender
        if (user.getGender().equals("Nam")){
            rdoMale.setChecked(true);
        }
        else if (user.getGender().equals("Nữ")) {
            rdoFemale.setChecked(true);
        }
        else {
            rdoFemale.setChecked(false);
            rdoMale.setChecked(false);
        }
    }


    //put method volley
    private void changeProfileUser() throws JSONException, ParseException {

        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();
        String gender= "";

        if(rdoMale.isChecked()){
            gender = "Nam";
        }
        else if(rdoFemale.isChecked()){
            gender = "Nữ";
        }
        else {
            gender = "";
        }
        parmas.put("UserID", GLOBAL.idUser);
        parmas.put("UserName", tvUserName.getText().toString().trim());
        parmas.put("Name", txtName.getText().toString().trim());
        parmas.put("CMND", GLOBAL.userlogin.getCMND().trim());
        parmas.put("Number", txtNumber.getText().toString().trim());
        parmas.put("Email", txtEmail.getText().toString().trim());
        parmas.put("Gender", gender);
        parmas.put("DoB", utils.converDatePutPost(txtDoB.getText().toString().trim()));
        parmas.put("Address", txtAddress.getText().toString().trim());
        parmas.put("HinhAnh", GLOBAL.userlogin.getImgUser().trim());
        parmas.put("GroupID", GLOBAL.userlogin.getGroupID());
        parmas.put("Salary", GLOBAL.userlogin.getSalary());

        paramsHeaders.put("Content-Type", "application/json");
        //api null
        api.CallAPI(urlputUser, Request.Method.PUT, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {

                    Log.i("success", "my response" +dataResponse);
                ChangeProfileActivity.this.finish();
                Intent intent = new Intent(context, ProfileUserActivity.class);
                startActivity(intent);
            }

            @Override
            public void ReponseError(String error) {
                    Log.e("error", "my error: "+ error);
                    Toast.makeText(getApplicationContext(), "Sửa không thành công\nThông tin đã có người sử dụng", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);


        return super.onCreateOptionsMenu(menu);
    }
    //action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_home:
                this.finish();
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
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