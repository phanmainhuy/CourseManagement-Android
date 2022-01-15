package com.example.onlearn.activity.excercise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.coupon.CouponAdapter;
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.models.EXCERCISE;
import com.example.onlearn.models.LESSON;
import com.example.onlearn.utils.VideoViewUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExcerciseActivity extends AppCompatActivity implements OnClickRCL_Excercise{
    String titleActionBar = "Bài " +GLOBAL.lesson.getSTT() + ": " + GLOBAL.lesson.getTenBaiHoc();
    String urlGetEx = GLOBAL.ip + "api/baihoc?MaBaiHoc=" + GLOBAL.lesson.getMaBaiHoc();
    ExcerciseAdapter exAdapter;
    ArrayList<EXCERCISE> dataEx = new ArrayList<>();
    RecyclerView rclPDF;
    String URL_VIDEO_SAMPLE  = GLOBAL.ip+"assets/video/" + GLOBAL.lesson.getVideo();
//    String urlgetPDF = GLOBAL.ip + "assets/PDF/";


    VideoView videoLearn;
    private int position = 0;
    MediaController mediaController;
    Button btnUrl, buttonFullScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise);

        DecorateActionBar();

        //anh xa
        btnUrl = findViewById(R.id.button_url);
        videoLearn = findViewById(R.id.videoLearn);
        rclPDF = findViewById(R.id.rclbaitap_Excercise);
        buttonFullScreen = findViewById(R.id.buttonFullScreen);

        //set data for rcl
        exAdapter = new ExcerciseAdapter(this, dataEx, this);
        rclPDF.setHasFixedSize(true);
        rclPDF.setAdapter(exAdapter);
        rclPDF.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        buttonFullScreen.setOnClickListener(v -> {
            Intent intent = new Intent(this, VideoScreenActivity.class);
            startActivity(intent);
        });
        //set the controller button
        if(mediaController == null){
            mediaController = new MediaController(this);

            //set the videoView that anchor for the MediaController
            mediaController.setAnchorView(videoLearn);
            //set mediaController for VideoView
            videoLearn.setMediaController(mediaController);
        }
        // When the video file ready for playback
        videoLearn.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {

                videoLearn.seekTo(position);
                if (position == 0) {
                    videoLearn.start();
                }

                // When video Screen change size.
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        // Re-Set the videoView that acts as the anchor for the MediaController
                        mediaController.setAnchorView(videoLearn);
                    }
                });
            }
        });

//        //set full mh
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) videoLearn.getLayoutParams();
//        params.width = metrics.widthPixels;
//        params.height = metrics.heightPixels;
//        params.leftMargin = 0;
//        videoLearn.setLayoutParams(params);


        btnUrl.setOnClickListener(v -> {
            String videoURL = URL_VIDEO_SAMPLE;
            VideoViewUtils.playURLVideo(ExcerciseActivity.this, videoLearn, videoURL);
        });
        // When you change direction of phone, this method will be called.
        // It store the state of video (Current position)



//        dataEx.add(new EXCERCISE(1,
//                "Bai 1",
//                "luyen thi dh"
//        ));
        getEx();

    }



    //videoView
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Store current position.
        savedInstanceState.putInt("CurrentPosition", videoLearn.getCurrentPosition());
        videoLearn.pause();
    }


    // After rotating the phone. This method is called.
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Get saved position.
        position = savedInstanceState.getInt("CurrentPosition");
        videoLearn.seekTo(position);
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
    private void getEx() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONObject> thanhcong = response -> {
                try {
                    JSONArray baihocs = response.getJSONArray("DanhSachBaiTap");

                    for (int a = 0; a < baihocs.length(); a++) {
                        JSONObject jsonBaihoc = baihocs.getJSONObject(a);
                        dataEx.add(new EXCERCISE(jsonBaihoc.getInt("MaBaiTap"),
                                jsonBaihoc.getString("TenBaiTap"),
                                jsonBaihoc.getString("LinkPDF")
                        ));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            exAdapter.notifyDataSetChanged();

        };
        com.android.volley.Response.ErrorListener thatbai = error ->{
            if(error.getMessage()!=null){
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlGetEx, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void itemClickEx(EXCERCISE excercise) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}