package com.example.onlearn.activity.payment_vnpay_wallet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.onlearn.R;
import com.google.gson.Gson;
import com.vnpay.qr.VnpayQRReturnEntity;
import com.vnpay.qr.activity.QRActivity;
import com.vnpay.qr.utils.Constants;
import com.vnpay.qr.utils.VNPAYTags;


public class VNPayWalletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vnpay_wallet);
        QRActivity.setupQR(this, VNPAYTags.CURRENCY_TYPE2,"http://mobile.vnpay.vn/IVB/Merchant.html",VNPAYTags.LANG_VN,R.style.MyCustomQRTheme);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

//// khởi tạo dữ liệu đầu vào
//        VNPayQR.newBuilder()
//                .setCurrencyType(VNPAYTags.CURRENCY_TYPE1)
//                .setLinkMerchant("http://mobile.vnpay.vn/IVB/Merchant.html")
//                .setLanguage(VNPAYTags.LANG_EN)
//                .setTheme(R.style.MyCustomQRTheme)
//                .setSubmitRight(VNPAYTags.RIGHT_TITLE_BAR_SUBMIT)
//                .onStart(this);// Di chuyển tới màn hình Quét QR VNPAY






    }

//    // Nhận dữ liệu từ SDK QR VNPAY
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("test","test");
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("requestcode", String.valueOf(requestCode));
        Log.i("resultCode", String.valueOf(resultCode));
        if (requestCode == VNPAYTags.REQUEST_VNPAY_QR && resultCode == RESULT_OK && data != null) {
            String dataV = data.getStringExtra(VNPAYTags.QR_RESPONSE);
            Log.e("data",dataV);
            VnpayQRReturnEntity returnEntity = Constants.g().getGsonInstance().fromJson(dataV, VnpayQRReturnEntity.class);
            Log.e("result", String.valueOf(returnEntity));
            // Sử dụng Gson parse lại Entity từ Dữ liệu SDK
            //Tiếp tục chuyển tới màn hình thành toán,
        }
    }




}