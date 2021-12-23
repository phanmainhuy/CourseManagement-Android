package com.example.onlearn.activity.category_big;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
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
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.activity.category_small.LoaiKhoaHocActivity;
import com.example.onlearn.models.DANHMUC;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.models.KHOAHOC;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DanhMucActivity extends AppCompatActivity implements OnClickRCL_DanhMuc {

    RecyclerView rclDanhMuc;
    DanhMucAdapter_rcl danhmucAdapter;

    private ArrayList<DANHMUC> data = new ArrayList<>();

    //url
    String urlDanhmuc = GLOBAL.ip + "topcategory/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);
        //action bar
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);
        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + "Danh mục khóa học" + "</font>"));

        //anh xa
        rclDanhMuc = findViewById(R.id.rclDanhMucKH_DanhMuc);

//        //add data load tu home
//        ArrayList<DANHMUC> data = new ArrayList<>();

        //Load du lieu va xu ly
        danhmucAdapter = new DanhMucAdapter_rcl(this, data, this);
        rclDanhMuc.setHasFixedSize(true);
        rclDanhMuc.setAdapter(danhmucAdapter);
        rclDanhMuc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        danhmucAdapter.notifyDataSetChanged();

        /*datadanhmuc.add(new DANHMUCKHOAHOC(1, "Imformation Technology", ""));
        datadanhmuc.add(new DANHMUCKHOAHOC(1, "Data Science", ""));*/
//        datadanhmuc.add(new DANHMUCKHOAHOC(1, "Công nghệ thông tin", "CNTT.jpg"));
//        datadanhmuc.add(new DANHMUCKHOAHOC(1, "Công nghệ thông tin", "CNTT.jpg"));
//

//        LaySanPham();
        GetDanhMuc();
//        Chèn một kẻ ngang giữa các phần tử
        DividerItemDecoration dividerHorizontal =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        dividerHorizontal.
                setDrawable(ContextCompat.getDrawable(this, R.drawable.black_duongkengangitem));
        rclDanhMuc.addItemDecoration(dividerHorizontal);

    }

    private void GetDanhMuc() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    if (jsonObject.getBoolean("HienThi") == true)
                    {
                        JSONArray tongsokhoahoc = jsonObject.getJSONArray("DanhSachTheLoai");
                        data.add(new DANHMUC(jsonObject.getInt("MaDanhMuc"),
                                jsonObject.getString("TenDanhMuc"),
                                jsonObject.getString("HinhAnh"),
                              tongsokhoahoc.length()
                        ));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            danhmucAdapter.notifyDataSetChanged();
        };

        com.android.volley.Response.ErrorListener thatbai = error ->{
            if(error.getMessage()!=null){
                Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
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
    public void ItemClickDM(DANHMUC danhmuc) {
        Intent intent = new Intent(this, LoaiKhoaHocActivity.class);
        GLOBAL.DMClick = danhmuc;
        startActivity(intent);
    }

}