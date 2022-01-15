package com.example.onlearn.activity.learn_demo;

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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.chapter.ChapterAdapter;
import com.example.onlearn.activity.chapter.OnClickRCL_Chapter;
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.activity.learn_demo.lesson_demo.LessonDemoActivity;
import com.example.onlearn.activity.lesson.LessonActivity;
import com.example.onlearn.models.CHAPTER;
import com.example.onlearn.utils.SpacesItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LearnDemoActivity extends AppCompatActivity implements OnClickRCL_Chapter {
    String titleActionBar = "Chọn chương học thử";
    String urlChap = GLOBAL.ip +"api/chuong?MaKhoaHoc=" +GLOBAL.KhoaHocClick.getMaKhoaHoc();
    RecyclerView rclChap;
    ChapterAdapter chapterAdapter;
    ArrayList<CHAPTER> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_demo);
        DecorateActionBar();
        //map
        rclChap = findViewById(R.id.rclChapter_ChapDemo);

        //set adapter
        chapterAdapter = new ChapterAdapter(this, data, this);
        rclChap.setHasFixedSize(true);
        rclChap.setAdapter(chapterAdapter);
        rclChap.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        Chèn một kẻ ngang giữa các phần tử
        DividerItemDecoration dividerHorizontal =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        dividerHorizontal.
                setDrawable(ContextCompat.getDrawable(this, R.drawable.black_duongkengangitem));
        rclChap.addItemDecoration(dividerHorizontal);

        rclChap.addItemDecoration(new SpacesItemDecoration(50));



        //data
        getChap();



    }

    private void getChap() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    data.add(new CHAPTER(i+1,jsonObject.getInt("MaChuong"),
                            jsonObject.getInt("MaKhoaHoc"),
                            jsonObject.getString("TenChuong"), jsonObject.getString("TenKhoaHoc"))
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            chapterAdapter.notifyDataSetChanged();

        };


        com.android.volley.Response.ErrorListener thatbai = error ->{
            if(error.getMessage()!=null){
                Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
            }

        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlChap, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

    }

    void DecorateActionBar(){
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void ItemClickLoaiKhoaHoc(CHAPTER chapter) {
        GLOBAL.chapter = chapter;
        Intent intent = new Intent(this, LessonDemoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}