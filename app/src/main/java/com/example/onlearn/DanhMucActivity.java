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

import com.example.onlearn.Adapter.DanhMucAdapter_rcl;

import com.example.onlearn.Adapter.OnClickRCL_DanhMuc;
import com.example.onlearn.Model.DANHMUCKHOAHOC;
import com.example.onlearn.Model.GLOBAL;
import com.example.onlearn.processJson.ParseJson;
import com.example.onlearn.processJson._HttpsTrustManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DanhMucActivity extends AppCompatActivity implements OnClickRCL_DanhMuc {

    RecyclerView rclDanhMuc;
    ArrayList<DANHMUCKHOAHOC> datadanhmuc = new ArrayList<>();
    DanhMucAdapter_rcl danhmucAdapter;



    //url
    String urlDanhmuc="http://" + GLOBAL.ip + "/topcategory";

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
        rclDanhMuc.setAdapter(danhmucAdapter);
        rclDanhMuc.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        try {
//            datadanhmuc = layDanhMucKH();
//            if (datadanhmuc == null)
//            {
//                Toast.makeText(DanhMucActivity.this, "Lỗi load data", Toast.LENGTH_SHORT).show();
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        datadanhmuc.add(new DANHMUCKHOAHOC(1, "Công nghệ thông tin", "https://www.google.com.vn/search?q=c%C3%B4ng+ngh%E1%BB%87+th%C3%B4ng+tin&tbm=isch&ved=2ahUKEwivx82pmaD0AhU4-DgGHQbPDzEQ2-cCegQIABAA&oq=c%C3%B4ng+ngh%E1%BB%87+th%C3%B4ng+tin&gs_lcp=CgNpbWcQAzIFCAAQgAQyBQgAEIAEMgUIABCABDIECAAQHjIECAAQHjIECAAQHjIECAAQHjIECAAQHjIECAAQHjIECAAQHjoHCCMQ7wMQJzoECAAQQzoICAAQgAQQsQM6BwgAELEDEEM6CwgAEIAEELEDEIMBOgQIABATOggIABAIEB4QE1CfCFj1IWD6ImgEcAB4AYABZYgBtRCSAQQyMy4xmAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=22CVYe_tNrjw4-EPhp6_iAM&authuser=0&bih=722&biw=1536&hl=en#imgrc=0j8TDzeGhbFyBM"));





    }

    public ArrayList<DANHMUCKHOAHOC> layDanhMucKH() throws JSONException {
        _HttpsTrustManager.HttpsTrustManager.allowAllSSL();


        new Thread(new Runnable() {

            @Override
            public void run() {
                ParseJson parseJson = new ParseJson();
                String p = parseJson.readStringFileContent(urlDanhmuc);
                JSONArray response = null;
                try {
                    response = new JSONArray(p);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        datadanhmuc.add(new DANHMUCKHOAHOC(jsonObject.getInt("MaDanhMuc"), jsonObject.getString("TenDanhMuc"),  jsonObject.getString("HinhAnh")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                danhmucAdapter.notifyDataSetChanged();
            }
        }).start();
        return datadanhmuc;



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
    public void itemClickDanhMuc(DANHMUCKHOAHOC danhmuckh) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}