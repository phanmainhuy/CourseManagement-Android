package com.example.onlearn.activity.change_profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.squareup.picasso.Picasso;

public class ChangeProfileActivity extends AppCompatActivity {
    String titleActionBar = "Sửa thông tin người dùng";
    Button btnSave, btnLogout;
    EditText txtName, txtNumber, txtEmail, txtDoB, txtAddress, txtCMND;
    TextView tvUserName;
    ImageView imgAvatar;

    String urlImgUser = GLOBAL.ip + GLOBAL.urlimg + "users/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeprofile);
        DecorateActionBar();

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

//        add thong tin
        tvUserName.setText(GLOBAL.userlogin.getUserName());
        Picasso.with(this)
                .load(urlImgUser + GLOBAL.userlogin.getImgUser())
                .placeholder(R.drawable.no_image_found)
                .into(imgAvatar);




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

    void DecorateActionBar(){
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