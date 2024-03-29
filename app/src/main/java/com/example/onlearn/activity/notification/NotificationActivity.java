package com.example.onlearn.activity.notification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.onlearn.activity.coupon.CouponActivity;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.models.NOTIFICATION;
import com.example.onlearn.R;

public class NotificationActivity extends AppCompatActivity {
    ListView lstNotification;
    NotificationAdapter_Listview notificationAdapter;

    TextView btnVoucher, tvNull;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);
        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" +"Thông báo"+ "</font>"));

        //anh xa
        lstNotification = findViewById(R.id.lst_Notification);
        btnVoucher = findViewById(R.id.btnVoucher_Notification);
        tvNull = findViewById(R.id.tvNull_Notification);




//        //set data listview
//        dataNotification.add(new NOTIFICATION(R.drawable.coupon1, "Thông báo mua khóa học thành công",
//                "We' were happy to let you know that we’ve received your order." +
//
//                "Once your package ships, we will send you an email with a tracking number and link so you can see the movement of your package.\n" +
//                "\n" +
//                "If you have any questions, contact us here or call us on [contact number]!",
//                "10/10/2021"));
//
//        dataNotification.add(new NOTIFICATION(R.drawable.ic_logonotification, "Thông báo mua khóa học thất bại",
//                "Bạn đã thanh toán thất bại, vui lòng thực hiện thanh toán lại khóa học. \nMọi thắc mắc về thanh toán, vui lòng gọi hotline hỗ trợ của OnLearn.",
//                "11/10/2021"));
//        dataNotification.add(new NOTIFICATION(R.drawable.ic_logonotification, "Thông báo mua khóa học thành công",
//                "Bạn đã thanh toán khóa học thành công! Vui lòng đợi nhân viên duyệt đơn hàng, sau khi nhân viên hệ thống sẽ thêm khóa học vào phòng học của bạn. OnLearn xin chân thành cảm ơn bạn.",
//                "11/10/2021"));



        notificationAdapter = new NotificationAdapter_Listview(this, GLOBAL.notifications);
        lstNotification.setAdapter(notificationAdapter);

        if (GLOBAL.notifications.size() > 0){
            tvNull.setVisibility(View.INVISIBLE);
        }
        else {
            tvNull.setVisibility(View.VISIBLE);
        }


        //OnClick
        btnVoucher.setOnClickListener(v -> {
            this.finish();
            Intent intent = new Intent(NotificationActivity.this, CouponActivity.class);
            startActivity(intent);
        });







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