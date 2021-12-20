package com.example.onlearn.activity.lesson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.excercise.ExcerciseActivity;
import com.example.onlearn.models.CHAPTER;
import com.example.onlearn.models.LESSON;
import com.example.onlearn.utils.SpacesItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LessonActivity extends AppCompatActivity implements OnClickRCL_Lesson {
    String titleActionBar = GLOBAL.chapter.getTenChuong();
    String urllesson = GLOBAL.ip + "api/BaiHoc/GetByParentId?MaChuong=" + GLOBAL.chapter.getMaChuong();
    RecyclerView rclLesson;
    LessonAdapter lessonAdapter;
    ArrayList<LESSON> datales = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        DecorateActionBar();

        //anh xa
        rclLesson = findViewById(R.id.rclLesson);

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



        getLesson();


    }

    private void getLesson() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    datales.add(new LESSON(jsonObject.getInt("MaBaiHoc"),
                            jsonObject.getString("TenBaiHoc"),
                            jsonObject.getInt("MaChuong"),
                            jsonObject.getString("TenChuong"),
                            jsonObject.getString("VideoName")
                            ));


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


    //action bar
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}