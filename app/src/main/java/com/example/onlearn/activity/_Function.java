package com.example.onlearn.activity;

import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.detail_course.DetailCourseActivity;
import com.example.onlearn.models.KHUYENMAI;
import com.example.onlearn.models.USER;
import com.example.onlearn.utils.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class _Function {

    String titleActionBar = "";
//    //action bar
//    void DecorateActionBar(){
//        //action bar
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + titleActionBar + "</font>"));
//        //doi mau thanh action bars
//        ColorDrawable colorDrawable
//                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
//        // Set BackgroundDrawable
//        actionBar.setBackgroundDrawable(colorDrawable);
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            // Respond to the action bar's Up/Home button
//            case android.R.id.home:
//                this.finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    //set up adapter
//    lessonAdapter = new LessonAdapter(this, datales, this);
//        rclLesson.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
//        rclLesson.setAdapter(lessonAdapter);
//    //        Chèn một kẻ ngang giữa các phần tử
//    DividerItemDecoration dividerHorizontal =
//            new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        dividerHorizontal.
//                setDrawable(ContextCompat.getDrawable(this, R.drawable.black_duongkengangitem));
//        rclLesson.addItemDecoration(dividerHorizontal);
//
//        rclLesson.addItemDecoration(new SpacesItemDecoration(30));


//get json array
//private void getAllCoupon() {
//    RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//    com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
//        for (int i = 0; i < response.length(); i++) {
//            try {
//                JSONObject jsonObject = response.getJSONObject(i);
//                if(jsonObject.getBoolean("DangMoBan") == true){
//                    datakm.add(new KHUYENMAI(jsonObject.getInt("MaKM"),
//                            "",
//                            jsonObject.getString("TenKM"),
//                            jsonObject.getString("HinhAnh"),
//                            jsonObject.getString("GiaTri"),
//                            jsonObject.getString("ThoiGianKeoDai"),
//                            jsonObject.getString("DiemCanMua")
//                    ));
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        getResetUser();
//        kmAdapter.notifyDataSetChanged();
//    };
//
//    com.android.volley.Response.ErrorListener thatbai = error ->{
//        if(error.getMessage() != null)
//            Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
//
//    };
//
//    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlkm, null, thanhcong, thatbai);
//    requestQueue.add(jsonArrayRequest);
//
//}

    //get jsonObject
//    public void getResetUser() {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        com.android.volley.Response.Listener<JSONObject> thanhcong = response -> {
//            try {
//
//                GLOBAL.userlogin = new USER(response.getInt("UserId"),
//                        response.getString("UserName"),
//                        response.getString("Name"),
//                        response.getString("Email"),
//                        response.getString("DoB"),
//                        response.getString("Gender"),
//                        response.getString("Address"),
//                        response.getString("Number"),
//                        response.getString("CMND"),
//                        response.getString("HinhAnh"),
//                        response.getInt("DiemTichLuy"),
//                        response.getInt("GroupID"),
//                        response.getString("Salary")
//                );
//                tvDiemTL.setText(utils.formatNumberCurrency(String.valueOf(GLOBAL.userlogin.getDiemTichLuy())));
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//        };
//        com.android.volley.Response.ErrorListener thatbai = error ->
//        {
//            if(error.getMessage() != null){
//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        };
//
//        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlUser, null, thanhcong, thatbai);
//        requestQueue.add(jsonArrayRequest);
//    }


    //Dialog

//    AlertDialog.Builder builder = new AlertDialog.Builder(DetailCourseActivity.this);
//
//    //setTitle
//                builder.setTitle("Thông báo");
//                builder.setMessage("Thêm vào giỏ hàng thất bại\nKhóa học đã được mua hoặc có trong giỏ hàng");
//                builder.setIcon(R.drawable.ic_chatbot);
//
//
//                builder.setCancelable(true);
//
//                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
//        public void onClick(DialogInterface dialog, int id) {
//
//            //  Cancel
//            dialog.cancel();
//        }
//    });
//    // Create AlertDialog:
//    AlertDialog alert = builder.create();
//                alert.show();

}
