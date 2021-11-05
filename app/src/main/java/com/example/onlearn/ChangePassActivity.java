package com.example.onlearn;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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




    }
}