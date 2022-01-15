package com.example.onlearn.activity.excercise;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.register.RegisterActivity;
import com.example.onlearn.utils.VideoViewUtils;

public class VideoScreenActivity extends AppCompatActivity {
    String URL_VIDEO_SAMPLE  = GLOBAL.ip+"assets/video/" + GLOBAL.lesson.getVideo();
    VideoView videoLearn;
    private int position = 0;
    MediaController mediaController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_screen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //set status bar
        //Change status bar
        Activity activity = VideoScreenActivity.this;
        Window window = activity.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(activity, R.color.black));


        //map
        videoLearn = findViewById(R.id.videoFull);

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
        String videoURL = URL_VIDEO_SAMPLE;
        VideoViewUtils.playURLVideo(this, videoLearn, videoURL);




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




}