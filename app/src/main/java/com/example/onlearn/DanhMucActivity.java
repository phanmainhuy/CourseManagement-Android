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

import com.example.onlearn.Adapter.DanhMucAdapter_rcl;

import com.example.onlearn.Adapter.OnClickRCL_DanhMuc;
import com.example.onlearn.Model.DANHMUC;
import com.example.onlearn.Model.GLOBAL;

import java.util.ArrayList;
import java.util.List;

public class DanhMucActivity extends AppCompatActivity implements OnClickRCL_DanhMuc {

    RecyclerView rclDanhMuc;
    DanhMucAdapter_rcl danhmucAdapter;

    private List<DANHMUC> sdanhMuc = HomeActivity.danhMuc;

    //url
    String urlDanhmuc= GLOBAL.ipGD + "topcategory/";

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
        ArrayList<DANHMUC> dara = new ArrayList<DANHMUC>(sdanhMuc);

        //Load du lieu va xu ly
        danhmucAdapter = new DanhMucAdapter_rcl(this, dara, this);
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
    public void itemClickDanhMuc(DANHMUC danhmuckh) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}