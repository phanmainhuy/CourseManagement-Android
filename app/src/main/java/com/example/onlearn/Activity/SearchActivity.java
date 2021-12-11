package com.example.onlearn.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.Adapter.FavoriteCourseAdapter_rcl;
import com.example.onlearn.Adapter.SearchAdapter_rcl;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.Model.KHOAHOC;
import com.example.onlearn.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    SearchAdapter_rcl searchAdapter_rcl;
    RecyclerView rclSearch;
    Button btnSearch;
    EditText txtSearch;

    ArrayList<KHOAHOC> data = new ArrayList<>();
    String search="C#";
    String urlSearch = GLOBAL.ip + "Search/?searchstring="+search+"&&paging=1&&type=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + "Tìm kiếm" + "</font>"));
        //doi mau thanh action bars
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        //anh xa
        btnSearch = findViewById(R.id.btnSearch_Search);
        txtSearch = findViewById(R.id.txtSearch_Search);
        rclSearch = findViewById(R.id.rclSearch);

        search = txtSearch.getText().toString();

        //set up rcl
        searchAdapter_rcl = new SearchAdapter_rcl(this, data);
        rclSearch.setHasFixedSize(true);
        rclSearch.setAdapter(searchAdapter_rcl);
        rclSearch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtSearch.getText().equals("")){
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập dữ liệu cần tìm", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Toast.makeText(getApplicationContext(), "Tìm kiếm thành công", Toast.LENGTH_SHORT).show();
                    getFavoriteCourses();
                }


            }
        });




    }


    private void getFavoriteCourses() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    data.add(new KHOAHOC(jsonObject.getInt("MaKhoaHoc"), jsonObject.getInt("MaLoai"),
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
            searchAdapter_rcl.notifyDataSetChanged();
        };

        com.android.volley.Response.ErrorListener thatbai = error ->
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlSearch, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
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




}