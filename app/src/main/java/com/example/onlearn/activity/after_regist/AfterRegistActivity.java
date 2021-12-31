package com.example.onlearn.activity.after_regist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.onlearn.R;

public class AfterRegistActivity extends AppCompatActivity {
    TextView tvValidate;
    EditText txtName, txtPhone, txtEmail;
    Button btnSubmit;


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



        //event
        btnSubmit.setOnClickListener(v ->{
            if(txtName.getText().toString().equals("") || txtName.getText().toString().isEmpty()){
                tvValidate.setText("Vui lòng điền thông tin Họ và tên");
                return;
            }
            if(txtEmail.getText().toString().equals("") || txtEmail.getText().toString().isEmpty()){
                tvValidate.setText("Vui lòng điền thông tin Email");
                return;
            }
            if(txtPhone.getText().toString().equals("") || txtPhone.getText().toString().isEmpty()){
                tvValidate.setText("Vui lòng nhập Số điện thoại");
                return;
            }
            else
            {

            }




        });




    }







}