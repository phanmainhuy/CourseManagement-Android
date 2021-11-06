package com.example.onlearn;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetpassActivity extends AppCompatActivity {

    Button btnXacNhan;
    EditText txtMaOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        //anh xa
        btnXacNhan = findViewById(R.id.btn_xacnhan_forgetpass);
        txtMaOTP = findViewById(R.id.txtOTP_Forgetpass);


        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtMaOTP.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập mã OTP", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    Intent intent1 = new Intent(ForgetpassActivity.this, ChangePassActivity.class);
                    startActivity(intent1);
                }

            }
        });


    }
}