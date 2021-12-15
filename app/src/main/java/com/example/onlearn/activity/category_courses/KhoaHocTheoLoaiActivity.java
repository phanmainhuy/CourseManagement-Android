package com.example.onlearn.activity.category_courses;

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
import com.example.onlearn.activity.detail_course.DetailCourseActivity;
import com.example.onlearn.models.KHOAHOC;
import com.example.onlearn.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class KhoaHocTheoLoaiActivity extends AppCompatActivity implements OnClickRCL_KhoaHocTheoLoai{
    String titleActionBar = GLOBAL.LoaiKHClick.getTenLoai();
    String urlKHtheoLoai = GLOBAL.ip + "api/khoahoc?maLoai=" + GLOBAL.LoaiKHClick.getMaLoai();
    RecyclerView rclKHtheoLoai;
    KhoaHocTheoLoaiAdapter khoahocAdapter;
    ArrayList<KHOAHOC> datakh = new ArrayList<>();
//    TextView isEmpty;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoahoctheoloai);

        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);
        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" +titleActionBar+ "</font>"));

        //anh xa
        rclKHtheoLoai = findViewById(R.id.KHtheoloai_rcl);
//        isEmpty = findViewById(R.id.tvEmpty_KHtheoLoai);

        //set up rcl
        khoahocAdapter = new KhoaHocTheoLoaiAdapter(this, datakh, this);
        rclKHtheoLoai.setHasFixedSize(true);
        rclKHtheoLoai.setAdapter(khoahocAdapter);
        rclKHtheoLoai.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //get data
        getKHTheoLoai();




    }

    //get data
    private void getKHTheoLoai() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    datakh.add(new KHOAHOC(jsonObject.getInt("MaKhoaHoc"), jsonObject.getInt("MaLoai"),
                            jsonObject.getString("TenKhoaHoc"), jsonObject.getString("DonGia"),
                            jsonObject.getInt("SoLuongMua"), jsonObject.getString("TrangThai")
                            , jsonObject.getString("HinhAnh"), jsonObject.getInt("MaGV")
                            , jsonObject.getString("TenGV"), jsonObject.getInt("DanhGia")
                            , jsonObject.getString("GioiThieu"), jsonObject.getString("NgayTao")
                            , jsonObject.getString("NgayChapThuan")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            khoahocAdapter.notifyDataSetChanged();
        };

        com.android.volley.Response.ErrorListener thatbai = error ->
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlKHtheoLoai, null, thanhcong, thatbai);
            requestQueue.add(jsonArrayRequest);
    }


    // nut tro ve
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

    @Override
    public void itemClickKhoaHoc(KHOAHOC khoahoc) {
        GLOBAL.KhoaHocClick = khoahoc;
        Intent intent1 = new Intent(this, DetailCourseActivity.class);
        startActivity(intent1);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}