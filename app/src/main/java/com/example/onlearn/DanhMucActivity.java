package com.example.onlearn;

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
import com.android.volley.toolbox.Volley;
import com.example.onlearn.Adapter.DanhMucAdapter_rcl;

import com.example.onlearn.Adapter.OnClickRCL_DanhMuc;
import com.example.onlearn.Model.DANHMUCKHOAHOC;
import com.example.onlearn.Model.GLOBAL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DanhMucActivity extends AppCompatActivity implements OnClickRCL_DanhMuc {

    RecyclerView rclDanhMuc;
    ArrayList<DANHMUCKHOAHOC> datadanhmuc = new ArrayList<>();
    DanhMucAdapter_rcl danhmucAdapter;



    //url
    String urlDanhmuc= GLOBAL.ip + "topcategory/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);
        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorTitle));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" +"Danh mục khóa học"+ "</font>"));

        //anh xa
        rclDanhMuc = findViewById(R.id.rclDanhMucKH_DanhMuc);


        //Load du lieu va xu ly
        danhmucAdapter = new DanhMucAdapter_rcl(this, datadanhmuc, this);
        rclDanhMuc.setHasFixedSize(true);
        rclDanhMuc.setAdapter(danhmucAdapter);
        rclDanhMuc.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        danhmucAdapter.notifyDataSetChanged();

        datadanhmuc.add(new DANHMUCKHOAHOC(1, "Công nghệ thông tin", "CNTT.jpg"));
        datadanhmuc.add(new DANHMUCKHOAHOC(1, "Công nghệ thông tin", "CNTT.jpg"));
        datadanhmuc.add(new DANHMUCKHOAHOC(1, "Công nghệ thông tin", "CNTT.jpg"));
        datadanhmuc.add(new DANHMUCKHOAHOC(1, "Công nghệ thông tin", "CNTT.jpg"));


        LaySanPham();




    }


    public void LaySanPham() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        datadanhmuc.add(new DANHMUCKHOAHOC(jsonObject.getInt("MaDanhMuc"), jsonObject.getString("TenDanhMuc"), jsonObject.getString("HinhAnh")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                danhmucAdapter.notifyDataSetChanged();
            }
        };
//        try {
//            Class.forName("dalvik.system.CloseGuard")
//                    .getMethod("setEnabled", boolean.class)
//                    .invoke(null, true);
//        } catch (ReflectiveOperationException e) {
//            throw new RuntimeException(e);
//        }
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlDanhmuc, null, thanhcong, thatbai);
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
    public void itemClickDanhMuc(DANHMUCKHOAHOC danhmuckh) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}