package com.example.onlearn.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoViewUtils{
    public static final String URL_VIDEO_SAMPLE  = "https://ex1.o7planning.com/_testdatas_/mov_bbb.mp4";
    public static final String LOG_TAG= "AndroidVideoView";
    // String videoURL = "https://ex1.o7planning.com/_testdatas_/mov_bbb.mp4";
    public static void playURLVideo(Context context, VideoView videoView, String videoURL)  {
        try {
            Log.i(LOG_TAG, "Video URL: "+ videoURL);

            Uri uri= Uri.parse(videoURL);

            videoView.setVideoURI(uri);
            videoView.requestFocus();

        } catch(Exception e) {
            Log.e(LOG_TAG, "Error Play URL Video: "+ e.getMessage());
            Toast.makeText(context,"Error Play URL Video: "+ e.getMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}
