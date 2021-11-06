package com.example.onlearn;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassActivity extends AppCompatActivity {
    Button btnCancel, btnChangePass;
    EditText txtNewPass, txtRePass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //anh xa
        btnChangePass = findViewById(R.id.btnChangePass_ChangePass);




        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Đổi mật khẩu thành công", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(ChangePassActivity.this, LoginActivity.class);
                startActivity(intent1);
            }
        });



    }
}