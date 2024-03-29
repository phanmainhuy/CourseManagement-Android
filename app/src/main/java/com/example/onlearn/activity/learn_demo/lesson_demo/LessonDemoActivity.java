package com.example.onlearn.activity.learn_demo.lesson_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.excercise.ExcerciseActivity;
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.activity.lesson.LessonAdapter;
import com.example.onlearn.activity.lesson.OnClickRCL_Lesson;
import com.example.onlearn.models.LESSON;
import com.example.onlearn.utils.SpacesItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LessonDemoActivity extends AppCompatActivity implements OnClickRCL_Lesson {
    String titleActionBar = "Chương " + GLOBAL.chapter.getSTT() + ": "+GLOBAL.chapter.getTenChuong();
    String urllesson = GLOBAL.ip + "api/BaiHoc/GetByParentId?MaChuong=" + GLOBAL.chapter.getMaChuong();
    RecyclerView rclLesson;
    LessonAdapter lessonAdapter;
    ArrayList<LESSON> datales = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_demo);
        DecorateActionBar();

        //anh xa
        rclLesson = findViewById(R.id.rclLesson_Demo);

        //set adapter
        lessonAdapter = new LessonAdapter(this, datales, this);
        rclLesson.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rclLesson.setAdapter(lessonAdapter);
//        Chèn một kẻ ngang giữa các phần tử
        DividerItemDecoration dividerHorizontal =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerHorizontal.
                setDrawable(ContextCompat.getDrawable(this, R.drawable.black_duongkengangitem));
        rclLesson.addItemDecoration(dividerHorizontal);

        rclLesson.addItemDecoration(new SpacesItemDecoration(30));

        checkIsDemo();

    }

//    private void getLesson() {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
//            for (int i = 0; i < response.length(); i++) {
//                try {
//                    JSONObject jsonObject = response.getJSONObject(i);
//                    datales.add(new LESSON(i+1,jsonObject.getInt("MaBaiHoc"),
//                            jsonObject.getString("TenBaiHoc"),
//                            jsonObject.getInt("MaChuong"),
//                            jsonObject.getString("TenChuong"),
//                            jsonObject.getString("VideoName")
//                    ));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            lessonAdapter.notifyDataSetChanged();
//
//        };
//        com.android.volley.Response.ErrorListener thatbai = error -> {
//            if (error.getMessage() != null) {
//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        };
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urllesson, null, thanhcong, thatbai);
//        requestQueue.add(jsonArrayRequest);
//    }
    private void checkIsDemo() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    if (jsonObject.getBoolean("isHocThu") == true){
                        datales.add(new LESSON(i+1,jsonObject.getInt("MaBaiHoc"),
                                jsonObject.getString("TenBaiHoc"),
                                jsonObject.getInt("MaChuong"),
                                jsonObject.getString("TenChuong"),
                                jsonObject.getString("VideoName")
                        ));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            lessonAdapter.notifyDataSetChanged();

        };
        com.android.volley.Response.ErrorListener thatbai = error -> {
            if (error.getMessage() != null) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urllesson, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);


        return super.onCreateOptionsMenu(menu);
    }
    //action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_home:
                this.finish();
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

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
    public void ItemClickLesson(LESSON lesson) {
        GLOBAL.lesson = lesson;
        Intent intent = new Intent(this, ExcerciseActivity.class);
        startActivity(intent);
    }
    private void notificateNotForDemo(){
        //Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setTitle
        builder.setTitle("Thông báo");
        builder.setMessage("Video này không cho học thử");
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
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}