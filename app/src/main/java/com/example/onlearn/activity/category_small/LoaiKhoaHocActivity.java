package com.example.onlearn.activity.category_small;

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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.activity.category_courses.KhoaHocTheoLoaiActivity;
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.models.LOAIKHOAHOC;
import com.example.onlearn.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoaiKhoaHocActivity extends AppCompatActivity implements OnClickRCL_LoaiKH {

    RecyclerView rclLoaikh;
    LoaiKhoaHocAdapter_rcl loaikhAdapter;
    int id = GLOBAL.DMClick.getMaDanhMuc();
    ArrayList<LOAIKHOAHOC> dataloaikh = new ArrayList<>();
    String titleActionBar = GLOBAL.DMClick.getTenDanhMuc();

    //http://192.168.1.9:45455/TopCategoryByID/?ID=1
    String url = GLOBAL.ip + "TopCategoryByID/?ID=" + id;
//    String url = GLOBAL.ip + "topcategory/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityloaikhoahoc);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);
        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + titleActionBar + "</font>"));

        //anh xa
        rclLoaikh = findViewById(R.id.rclLoaiKH_LoaiKH);



        //data rcl
        loaikhAdapter = new LoaiKhoaHocAdapter_rcl(this, dataloaikh, this);
        rclLoaikh.setHasFixedSize(true);
        rclLoaikh.setAdapter(loaikhAdapter);
        rclLoaikh.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //        Chèn một kẻ ngang giữa các phần tử
        DividerItemDecoration dividerHorizontal =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        dividerHorizontal.
                setDrawable(ContextCompat.getDrawable(this, R.drawable.black_duongkengangitem));
        rclLoaikh.addItemDecoration(dividerHorizontal);

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
                            if (inData.getBoolean("HienThi") == true){
                                dataloaikh.add(new LOAIKHOAHOC(
                                                inData.getInt("MaTheLoai"),
                                                response.getInt("MaDanhMuc"),
                                                inData.getString("TenTheLoai"),
                                                hinhanh,
                                                tenDM
                                        )
                                );
                            }

                        }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            loaikhAdapter.notifyDataSetChanged();
        };

        com.android.volley.Response.ErrorListener thatbai = error ->{
            if(error.getMessage()!=null){
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
        };

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

    }


    @Override
    public void ItemClickLoaiKhoaHoc(LOAIKHOAHOC loaikhoahoc) {
        Intent intent = new Intent(this, KhoaHocTheoLoaiActivity.class);
        GLOBAL.LoaiKHClick = loaikhoahoc;
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);


        return super.onCreateOptionsMenu(menu);
    }

    // nut tro ve
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_home:
                this.finish();
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}