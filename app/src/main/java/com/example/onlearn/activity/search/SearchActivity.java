package com.example.onlearn.activity.search;

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
import android.widget.Button;
import android.widget.EditText;
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

public class SearchActivity extends AppCompatActivity implements OnClickRCL_Search{
    SearchAdapter_rcl searchAdapter_rcl;
    RecyclerView rclSearch;
    Button btnSearch;
    EditText txtSearch;


    ArrayList<KHOAHOC> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + "Tìm kiếm" + "</font>"));
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);
        //doi mau thanh action bars
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        //anh xa
        btnSearch = findViewById(R.id.btnSearch_Search);
        txtSearch = findViewById(R.id.txtSearch_Search);
        rclSearch = findViewById(R.id.rclSearch);

//        search = txtSearch.getText().toString();

        //set up rcl
        searchAdapter_rcl = new SearchAdapter_rcl(this, data, this);
        rclSearch.setHasFixedSize(true);
        rclSearch.setAdapter(searchAdapter_rcl);
        rclSearch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        checkDataEmpty();
        btnSearch.setOnClickListener(v -> {


            if(txtSearch.getText().length() <= 0){
                Toast.makeText(getApplicationContext(), "Vui lòng nhập dữ liệu cần tìm", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                getSearchKH();
//                checkDataEmpty();
            }


        });




    }


    private void getSearchKH() {
        data.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    if(jsonObject.getBoolean("HienThi") == true){
                        data.add(new KHOAHOC(jsonObject.getInt("MaKhoaHoc"), jsonObject.getInt("MaLoai"),
                                jsonObject.getInt("MaDM"), jsonObject.getString("TenLoai"),
                                jsonObject.getString("TenDanhMuc"),
                                jsonObject.getString("TenKhoaHoc"), jsonObject.getString("DonGia"),
//                            jsonObject.getInt("SoLuongMua") "",
                                jsonObject.getString("TrangThai")
                                , jsonObject.getString("HinhAnh"), jsonObject.getInt("MaGV")
                                , jsonObject.getString("TenGV"), jsonObject.getInt("DanhGia")
                                , jsonObject.getString("GioiThieu"), jsonObject.getString("NgayTao")
                                , jsonObject.getString("NgayChapThuan")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            searchAdapter_rcl.notifyDataSetChanged();
        };

        com.android.volley.Response.ErrorListener thatbai = error ->{
            if(error.getMessage()!=null){
                Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        String search = txtSearch.getText().toString();
        if(search.equals("")){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập dữ liệu cần tìm", Toast.LENGTH_SHORT).show();
            return;
        }

        else
        {
            String urlSearch = GLOBAL.ip + "SearchAll?searchString="+ search;
            urlSearch = urlSearch.replaceAll(" ", "%20");
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlSearch, null, thanhcong, thatbai);
            requestQueue.add(jsonArrayRequest);
        }
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