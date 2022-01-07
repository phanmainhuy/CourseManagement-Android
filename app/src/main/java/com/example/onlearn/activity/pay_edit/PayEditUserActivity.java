package com.example.onlearn.activity.pay_edit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.pay_offline.PayOfflineActivity;
import com.example.onlearn.models.PAY_ReceiptOrder;

public class PayEditUserActivity extends AppCompatActivity {
    String titleActionBar = "Thay đổi thông tin người đặt";
    EditText txtName, txtEmail, txtAddress, txtPhone;
    Button btnSave, btnCancel;
    TextView tvValidate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payedit_user);
//        decorateActionBar();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        //map
        txtName = findViewById(R.id.txtName_Pay);
        txtEmail = findViewById(R.id.txtEmail_Pay);
        txtAddress = findViewById(R.id.txtAddress_Pay);
        txtPhone = findViewById(R.id.txtPhone_Pay);
        btnSave = findViewById(R.id.btnSaveInfo_Pay);
        btnCancel = findViewById(R.id.btnCancelSaveInfo_Pay);
        tvValidate = findViewById(R.id.tvValueKM_EditInfoPay);

        loadData();
        //event
        btnCancel.setOnClickListener(v -> {
            this.finish();
        });

        btnSave.setOnClickListener(v -> {
            if (txtName.getText().length() < 5) {
                tvValidate.setText("Họ tên người dùng phải hơn 5 kí tự");
                return;
            }
            if (txtEmail.getText().length() < 5) {
                tvValidate.setText("Sai định dạng email");
                return;
            }
            if (txtAddress.getText().length() < 5) {
                tvValidate.setText("Địa chỉ phải hơn 5 kí tự");
                return;
            }
            if (txtPhone.getText().length() != 10) {
                tvValidate.setText("Số điện thoại phải có 10 kí tự");
                return;
            } else {
                GLOBAL.infoThuHo = new PAY_ReceiptOrder(
                        txtName.getText().toString(),
                        txtEmail.getText().toString(),
                        txtAddress.getText().toString(),
                        txtPhone.getText().toString()
                );
                this.finish();
                Intent intent = new Intent(this, PayOfflineActivity.class);
                startActivity(intent);
            }

        });


    }

    private void loadData(){
        txtName.setText(GLOBAL.infoThuHo.getHoTen());
        txtEmail.setText(GLOBAL.infoThuHo.getEmail());
        txtAddress.setText(GLOBAL.infoThuHo.getDiaChi());
        txtPhone.setText(GLOBAL.infoThuHo.getSDT());
    }
    private void decorateActionBar() {
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);
        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + titleActionBar + "</font>"));
    }

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
}