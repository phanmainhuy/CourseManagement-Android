package com.example.onlearn.activity.payment_methods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.pay_edit.PayEditUserActivity;
import com.example.onlearn.activity.pay_offline.PayOfflineActivity;
import com.example.onlearn.activity.payment_online_method.PaymentOnlineWalletActivity;

public class PaymentMethodsActivity extends AppCompatActivity {
    String titleActionBar = "Thanh toán";
    RadioButton rdoOffline, rdoOnline, rdoCreditCard;
    Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);
        DecorateActionBar();

        //map
        rdoOffline = findViewById(R.id.rdo_Offline_Payment);
        rdoOnline = findViewById(R.id.rdo_OnlineWallet_Payment);
        rdoCreditCard = findViewById(R.id.rdo_CreditCart_Payment);
        btnContinue = findViewById(R.id.btnContinue_PaymentMethods);

        btnContinue.setOnClickListener(v -> {
            if(!rdoOffline.isChecked() && !rdoOnline.isChecked() && !rdoCreditCard.isChecked()){
                //Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                //setTitle
                builder.setTitle("Thông báo");
                builder.setMessage("Vui lòng chọn phương thức thanh toán");
                builder.setIcon(R.drawable.ic_chatbot);


                builder.setCancelable(true);

                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //  Cancel
                        dialog.cancel();
                    }
                });
                // Create AlertDialog:
                AlertDialog alert = builder.create();
                alert.show();
            }

            if (rdoOffline.isChecked()) {
//                this.finish();
                Intent intent = new Intent(this, PayEditUserActivity.class);
                startActivity(intent);
            }
            if(rdoOnline.isChecked()){
//                this.finish();
                Intent intent = new Intent(this, PaymentOnlineWalletActivity.class);
                startActivity(intent);
            }

            if (rdoCreditCard.isChecked()){
                //Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                //setTitle
                builder.setTitle("Thông báo");
                builder.setMessage("Tính năng này đang được phát triển\nVui lòng chọn phương thức thanh toán khác");
                builder.setIcon(R.drawable.ic_chatbot);


                builder.setCancelable(true);

                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //  Cancel
                        dialog.cancel();
                    }
                });
                // Create AlertDialog:
                AlertDialog alert = builder.create();
                alert.show();
            }



        });


    }


    //action bar
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