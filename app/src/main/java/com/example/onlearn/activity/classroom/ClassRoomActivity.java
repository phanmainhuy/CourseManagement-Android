package com.example.onlearn.activity.classroom;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
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
import com.android.volley.toolbox.Volley;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.classroom_detail.ClassroomDetailActivity;
import com.example.onlearn.models.LEARN;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ClassRoomActivity extends AppCompatActivity implements OnClickRCL_Classroom{
    String titleActionBar = "Phòng học của " +GLOBAL.userlogin.getTen();
    String urlClassroom = GLOBAL.ip + "api/KhoaHocTheoHocVien?MaHV=" +GLOBAL.idUser;
    RecyclerView rclClassroom;
    ClassroomAdapter adapterclassroom;
    ArrayList<LEARN> dataclass = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);
        DecorateActionBar();

        //anh xa
        rclClassroom = findViewById(R.id.rclClassroom);

        //set up rcl
        adapterclassroom = new ClassroomAdapter(this, dataclass, this);
        rclClassroom.setAdapter(adapterclassroom);
        rclClassroom.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rclClassroom.setHasFixedSize(true);
        //get data
//        getClassroom();
        try {
            getClassroom();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private void getClassroom() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    dataclass.add(new LEARN(jsonObject.getInt("MaKhoaHoc"),
//                            jsonObject.getInt("MaLoai"),
//                            jsonObject.getInt("MaDM"),
//                            jsonObject.getString("TenLoai"),
//                            jsonObject.getString("TenDanhMuc"),
                            jsonObject.getString("TenKhoaHoc"),
//                            jsonObject.getString("DonGia"),
//                            jsonObject.getInt("SoLuongMua"),
//                            jsonObject.getString("TrangThai"),
                             jsonObject.getString("HinhAnh"),
                            jsonObject.getInt("MaGV"),
                             jsonObject.getString("TenGV"),
                            jsonObject.getInt("DanhGia"),
                            jsonObject.getString("GioiThieu"),
                            jsonObject.getString("NgayMua")
//                            , jsonObject.getString("NgayChapThuan")
                    ));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            adapterclassroom.notifyDataSetChanged();

        };
//        Map<String, String> paramsHeaders = new HashMap<>();
//        paramsHeaders.put("Content-Type", "application/json");
        com.android.volley.Response.ErrorListener thatbai = error ->{
            if(error.getMessage()!=null){
                Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
            }

        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlClassroom, null, thanhcong, thatbai);
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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void itemClickClassroom(LEARN khoahoc) {
        GLOBAL.learn = khoahoc;
        Intent intent = new Intent(this, ClassroomDetailActivity.class);
        startActivity(intent);
    }
}