package com.example.onlearn.activity.excercise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.models.LESSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExcerciseActivity extends AppCompatActivity {
    String titleActionBar = GLOBAL.lesson.getTenBaiHoc();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise);

        DecorateActionBar();








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
//    private void getLesson() {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
//            for (int i = 0; i < response.length(); i++) {
//                try {
//                    JSONObject jsonObject = response.getJSONObject(i);
//                    JSONArray baihocs = jsonObject.getJSONArray("DanhSachBaiHoc");
//
//                    for (int a = 0; a < baihocs.length(); a++) {
//                        JSONObject jsonBaihoc = baihocs.getJSONObject(a);
//                        datales.add(new LESSON(jsonBaihoc.getInt("MaBaiHoc"),
//                                jsonBaihoc.getString("TenBaiHoc"),
//                                jsonBaihoc.getInt("MaChuong"),
//                                jsonBaihoc.getString("TenChuong"),
//                                jsonBaihoc.getString("VideoName")
//                        ));
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            lessonAdapter.notifyDataSetChanged();
//
//        };
//        com.android.volley.Response.ErrorListener thatbai = error ->{
//            if(error.getMessage()!=null){
//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        };
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urllesson, null, thanhcong, thatbai);
//        requestQueue.add(jsonArrayRequest);
//    }

}