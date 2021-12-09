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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.Adapter.LoaiKhoaHocAdapter_rcl;
import com.example.onlearn.Adapter.OnClickRCL_LoaiKH;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.Model.LOAIKHOAHOC;
import com.example.onlearn.R;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoaiKhoaHocActivity extends AppCompatActivity implements OnClickRCL_LoaiKH {

    RecyclerView rclLoaikh;
    LoaiKhoaHocAdapter_rcl loaikhAdapter;
    int id = GLOBAL.idDMClick.getMaDanhMuc();
    ArrayList<LOAIKHOAHOC> dataloaikh = new ArrayList<>();

    //http://192.168.1.9:45455/TopCategoryByID/?ID=1
    String url = GLOBAL.ip + "TopCategoryByID/?ID=" + id;
//    String url = GLOBAL.ip + "topcategory/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_khoa_hoc);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);
        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + "Loại khóa học" + "</font>"));

        //anh xa
        rclLoaikh = findViewById(R.id.rclLoaiKH_LoaiKH);



        //data rcl

        loaikhAdapter = new LoaiKhoaHocAdapter_rcl(this, dataloaikh, this);
        rclLoaikh.setHasFixedSize(true);
        rclLoaikh.setAdapter(loaikhAdapter);
        rclLoaikh.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        getTheLoai();
    }

    private void getTheLoai() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONObject> thanhcong = response -> {

                try {
//                    JSONObject jsonObject = response.getJSONObject(i);
                    JSONArray Data = response.getJSONArray("DanhSachTheLoai");
                    String hinhanh = response.getString("HinhAnh");
                    String tenDM = response.getString("TenDanhMuc");
                        for(int a  = 0; a < Data.length(); a++) //have length
                        {
                            JSONObject inData = Data.getJSONObject(a);
                            dataloaikh.add(new LOAIKHOAHOC(
                                    inData.getInt("MaTheLoai"),
                                    response.getInt("MaDanhMuc"),
                                            inData.getString("TenTheLoai"),
                                            hinhanh,
                                            tenDM
                                    )
                            );
                        }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            loaikhAdapter.notifyDataSetChanged();
        };

        com.android.volley.Response.ErrorListener thatbai = error ->
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

    }


    @Override
    public void ItemClickLoaiKhoaHoc(LOAIKHOAHOC loaikhoahoc) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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

}