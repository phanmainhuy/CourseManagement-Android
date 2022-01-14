package com.example.onlearn.activity.payment_razor_wallet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.onlearn.R;
import com.example.onlearn.activity.pay_successfully.PaySuccessfulActivity;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class RazorPayWalletActivity extends AppCompatActivity implements PaymentResultListener {

    private static final String TAG = RazorPayWalletActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razor_pay_wallet);
        //actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("RazorPay");
        Checkout.preload(getApplicationContext());
        //start
        startPayment();

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

            options.put("name", "Tea factory");
            options.put("description", "Buy Green tea");
            options.put("currency", "INR");
            options.put("amount", "5000");//pass amount in currency subunits
            options.put("prefill.email", "baotiantu@gmail.com");
            options.put("prefill.contact","0948462040");


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
        Intent intent = new Intent(this, PaySuccessfulActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPaymentError(int code, String response) {
        /**
         * Add your logic here for a failed payment response
         */
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}