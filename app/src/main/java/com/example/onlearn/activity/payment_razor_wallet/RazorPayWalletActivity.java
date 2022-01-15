package com.example.onlearn.activity.payment_razor_wallet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.onlearn.API.API;
import com.example.onlearn.API.ICallBack;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.pay_offline.PayOfflineActivity;
import com.example.onlearn.activity.pay_successfully.PaySuccessfulActivity;
import com.example.onlearn.models.NOTIFICATION;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RazorPayWalletActivity extends AppCompatActivity implements PaymentResultListener {
    String urlPay = GLOBAL.ip + "api/payment/InstantRamenMobile";
    API api;
    String titleActionBar = "RazorPay";
    private static final String TAG = RazorPayWalletActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razor_pay_wallet);
        //actionbar
        decorateActionBar();
        api = new API(this);
        Checkout.preload(getApplicationContext());
        //start
        startPayment();

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
    public void startPayment() {
        /**
         * Instantiate Checkout
         */

        Checkout checkout = new Checkout();
        //ketID in dashboard
        checkout.setKeyID("rzp_test_pQth9LNun5YTUS");

        /**
         * Set your logo here
         */
//        checkout.setImage(R.drawable.ic_launcher_background);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            int pricePay = 199000 *100;
            String titlePay = "ONLEARN";
            String DetailPay = "Thanh toán khóa học online";
            String EmailCus = GLOBAL.userlogin.getEmail();
            String MobileCus = GLOBAL.userlogin.getUserName();


            options.put("name", titlePay);
            options.put("description", DetailPay);
            options.put("currency", "INR");
            options.put("amount", pricePay);//pass amount in currency subunits
            options.put("prefill.email", EmailCus);
            options.put("prefill.contact",MobileCus);


            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        Log.e("razorpayPaymentID", razorpayPaymentID);
        /**
         * Add your logic here for a successful payment response
         */
        try {
            postPay();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPaymentError(int code, String response) {
        /**
         * Add your logic here for a failed payment response
         */
        this.finish();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void postPay() throws JSONException {
        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();


        //put parmas

        parmas.put("MaHD", GLOBAL.idHD_pay);
        parmas.put("MaApDung", GLOBAL.MaApDung);




        paramsHeaders.put("Content-Type", "application/json");

        api.CallAPI(urlPay, Request.Method.POST, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {

                Log.i("success", "my response" + dataResponse);
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                GLOBAL.notifications.add(new NOTIFICATION(R.drawable.ic_logonotification, "Thông báo mua khóa học thành công",
                        "Bạn đã mua khóa học bằng ví RazorPay thành công! Hệ thống đã được thêm khóa học vào Phòng học của bạn." +
                                "\nVui lòng vào Phòng học để kiểm tra"+
                                "\nMọi thắc mắc về thanh toán, vui lòng gọi hotline hỗ trợ của OnLearn." + "\nOnLearn xin chân thành cảm ơn bạn.",
                        currentDate));

                Toast.makeText(getApplicationContext(), "Thanh toán thành công ", Toast.LENGTH_LONG).show();
                RazorPayWalletActivity.this.finish();
                Intent intent = new Intent(RazorPayWalletActivity.this, PaySuccessfulActivity.class);
                startActivity(intent);


            }

            @Override
            public void ReponseError(String error) {

                Log.e("error", "my error: " + error);
                Toast.makeText(getApplicationContext(), "Thanh toán thất bại", Toast.LENGTH_LONG).show();

            }
        });
    }





}