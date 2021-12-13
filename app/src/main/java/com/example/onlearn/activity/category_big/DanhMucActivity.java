package com.example.onlearn.activity.category_big;

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

import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.activity.category_small.LoaiKhoaHocActivity;
import com.example.onlearn.models.DANHMUC;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;

import java.util.ArrayList;
import java.util.List;

public class DanhMucActivity extends AppCompatActivity implements OnClickRCL_DanhMuc {

    RecyclerView rclDanhMuc;
    DanhMucAdapter_rcl danhmucAdapter;

    private List<DANHMUC> sdanhMuc = HomeActivity.danhMuc;

    //url
    String urlDanhmuc= GLOBAL.ip + "topcategory/";

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
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" +"Danh mục khóa học"+ "</font>"));

        //anh xa
        rclDanhMuc = findViewById(R.id.rclDanhMucKH_DanhMuc);

        //add data load tu home
        ArrayList<DANHMUC> data = new ArrayList<>(sdanhMuc);

        //Load du lieu va xu ly
        danhmucAdapter = new DanhMucAdapter_rcl(this, data, this);
        rclDanhMuc.setHasFixedSize(true);
        rclDanhMuc.setAdapter(danhmucAdapter);
        rclDanhMuc.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        danhmucAdapter.notifyDataSetChanged();

        /*datadanhmuc.add(new DANHMUCKHOAHOC(1, "Imformation Technology", ""));
        datadanhmuc.add(new DANHMUCKHOAHOC(1, "Data Science", ""));*/
//        datadanhmuc.add(new DANHMUCKHOAHOC(1, "Công nghệ thông tin", "CNTT.jpg"));
//        datadanhmuc.add(new DANHMUCKHOAHOC(1, "Công nghệ thông tin", "CNTT.jpg"));
//

//        LaySanPham();




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