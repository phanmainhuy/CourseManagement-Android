package com.example.onlearn.activity.rating;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.API.API;
import com.example.onlearn.API.ICallBack;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.classroom_detail.ClassroomDetailActivity;
import com.example.onlearn.activity.profile_user.ProfileUserActivity;
import com.example.onlearn.models.RATING;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RatingActivity extends AppCompatActivity {
    String titleActionBar = "Đánh giá khóa học";

    String urlgetImgUser = GLOBAL.ip + GLOBAL.urlimg + "users/";
    String urlgetImgCourses = GLOBAL.ip + GLOBAL.urlimg + "courses/";
    String urlgetUserRating = GLOBAL.ip + "api/DanhGia?MaKhoaHoc=" + GLOBAL.learn.getMaKH() + "&&MaND=" + GLOBAL.idUser;
    String urlgetCommunity = GLOBAL.ip + "api/DanhGia?MaKhoaHoc=" + GLOBAL.learn.getMaKH();
    String urlCRUDRating = GLOBAL.ip + "api/DanhGia";
    API api;
    Context context;

    ImageView imgKH, imgUser;
    TextView tvTenKH, tvUserName, tvSLUserRating, tvUserDate;

    RecyclerView rcl_RatingCommunity;
    ArrayList<RATING> dataRating = new ArrayList<>();
    RatingAdapter communityAdapter;

    RatingBar ratingTotal, ratingPerson;
    TextView tvTotalRating, tvRatingPerson, tvNull;
    Button btnCreate, btnUpdate, btnDelete;
    EditText tvNoiDung;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        DecorateActionBar();

        api = new API(RatingActivity.this);
        context = getApplicationContext();

        //map
        imgKH = findViewById(R.id.imgKH_Rating);
        imgUser = findViewById(R.id.imgAvatarUser_Rating);
        tvTenKH = findViewById(R.id.tvTenKH_Rating);
        tvUserName = findViewById(R.id.tvUsername_Rating);
        tvSLUserRating = findViewById(R.id.tvSLPersonRating_Rating);

        tvTotalRating = findViewById(R.id.tvTotalRating_Rating);
        tvRatingPerson = findViewById(R.id.tvPersonalRating_Rating);
        tvNoiDung = findViewById(R.id.tvNoiDungRating_Rating);
        tvUserDate = findViewById(R.id.tvDateRatingUser_Rating);
        tvNull = findViewById(R.id.tvNullComunity_Rating);

        //rcl
        rcl_RatingCommunity = findViewById(R.id.rclRatingCommunity_Rating);
        //btn
        btnCreate = findViewById(R.id.btnAddRating_Rating);
        btnUpdate = findViewById(R.id.btnUpdateRating_Rating);
        btnDelete = findViewById(R.id.btnDeleteRating_Rating);
        //rating
        ratingPerson = findViewById(R.id.PersonalRating_Rating);
        ratingTotal = findViewById(R.id.totalrating_Rating);


//        int x = GLOBAL.userRating.getMaDanhGia();
//        System.out.println(x);
        tvNull.setVisibility(View.INVISIBLE);

//        btnCreate.setVisibility(View.INVISIBLE);
//        ratingTotal.setStepSize(0.1f);
        //set data
        getCommunity();
        getSetUpData();
        //set up recycle

        communityAdapter = new RatingAdapter(this, dataRating);
        rcl_RatingCommunity.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcl_RatingCommunity.setAdapter(communityAdapter);


        btnDelete.setVisibility(View.INVISIBLE);
        btnUpdate.setVisibility(View.INVISIBLE);

        //listen change rating bar
        ratingPerson.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String rate = String.valueOf(ratingPerson.getRating());
                tvRatingPerson.setText(rate + " ");
            }
        });

//        //long click update to open update
//        btnUpdate.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//
//                return true;
//            }
//        });
        btnUpdate.setOnClickListener(v -> {
            if (btnUpdate.getText().toString().trim().equals("Chỉnh sửa")) {
                turnOnRating();
                btnUpdate.setText("Hủy");
                btnDelete.setVisibility(View.INVISIBLE);
                btnCreate.setVisibility(View.VISIBLE);
                btnCreate.setText("Lưu");
//                tvNoiDung.setText("");
//                ratingPerson.setRating(0);
//                tvRatingPerson.setText(0 + " ");
//                tvUserDate.setText("");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                //setTitle
                builder.setTitle("Thông báo");
                builder.setMessage("Chuyển sang chỉnh sửa đánh giá");
                builder.setIcon(R.drawable.ic_chatbot);


                builder.setCancelable(true);

                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //  Cancel
                        dialog.cancel();
                    }
                });
                // Create AlertDialog:
                AlertDialog alert = builder.create();
                alert.show();
//                Toast.makeText(getApplicationContext(), "Chuyển sang chỉnh sửa đánh giá ",
//                        Toast.LENGTH_SHORT).show();
                    return;
            }

            if (btnUpdate.getText().toString().trim().equals("Hủy")) {
                turnOffRating();
                btnUpdate.setText("Chỉnh sửa");
                btnCreate.setText("Thêm");
                getRatingTotal();
                return;
            }

            else {
                btnUpdate.setText("Chỉnh sửa");
                turnOffRating();
                btnCreate.setVisibility(View.VISIBLE);

            }


        });

        btnCreate.setOnClickListener(v -> {
            //update
            if (ratingPerson.getRating() <= 0) {
                Toast.makeText(context, "Đánh giá thấp nhất là 1 sao", Toast.LENGTH_SHORT).show();
                return;
            } else if (tvNoiDung.getText().toString().trim().length() < 5) {
                Toast.makeText(context, "Nội dung đánh giá phải ít nhất 5 ký tự", Toast.LENGTH_SHORT).show();
                return;
            }
            //update
            else if (btnCreate.getText().toString().trim().equals("Lưu")) {
                try {
                    putRating();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //create
            else {
                try {
                    postRating();
                    getRatingTotal();
                    getCommunity();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });

        btnDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            //setTitle
            builder.setTitle("Xác nhận xóa đánh giá");
            builder.setMessage("Bạn có chắc chắn muốn xóa đánh giá này không?");
            builder.setIcon(GLOBAL.iconDialog);
            builder.setCancelable(true);
            //create Cancel
            // Create "Positive" button with OnClickListener.
            builder.setPositiveButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
//                    Toast.makeText(context, "Đã hủy xóa đánh giá",
//                            Toast.LENGTH_SHORT).show();
                    //  Cancel
                    dialog.cancel();
                }
            });
//            builder.setPositiveButtonIcon(positiveIcon);

            //create Delete button
            builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    try {
                        deleteRating();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                }
            });
//            builder.setNegativeButtonIcon(context.getResources().getDrawable(R.drawable.ic_add_24));

            // Create AlertDialog:
            AlertDialog alert = builder.create();
            alert.show();

        });

    }

    private void getSetUpData() {
        tvTenKH.setText(GLOBAL.learn.getTenKH());
        tvUserName.setText(GLOBAL.userlogin.getTen());
        Picasso.with(this)
                .load(urlgetImgUser + GLOBAL.userlogin.getImgUser())
                .placeholder(R.drawable.no_image_found)
                .into(imgUser);

        Picasso.with(this)
                .load(urlgetImgCourses + GLOBAL.learn.getImgKH())
                .placeholder(R.drawable.no_image_found)
                .into(imgKH);
//        ratingTotal.setRating(3.5f);


    }

    private void getCommunity() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        com.android.volley.Response.Listener<JSONArray> thanhcong = response -> {
            dataRating.clear();
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    if (jsonObject.getInt("MaND") != GLOBAL.idUser) {


                        dataRating.add(new RATING(jsonObject.getInt("MaDanhGia"),
                                jsonObject.getInt("MaND"),
                                jsonObject.getInt("MaKhoaHoc"),
                                jsonObject.getString("TenKhoaHoc"),
                                jsonObject.getInt("Diem"),
                                jsonObject.getDouble("TongDiem"),
                                jsonObject.getString("NoiDung"),
                                jsonObject.getString("TenND"),
                                jsonObject.getString("HinhAnh"),
                                jsonObject.getString("NgayDanhGia")
                        ));
//                        tvTotalRating.setText();
                        ratingTotal.setRating((float) jsonObject.getDouble("TongDiem"));

                        tvTotalRating.setText(utils.formatTotalRating(jsonObject.getDouble("TongDiem")) + " ");

                    }
                    else if(jsonObject.getInt("MaND") == GLOBAL.idUser){
                        getRatingTotal();
                        ratingTotal.setRating((float) jsonObject.getDouble("TongDiem"));
                        tvTotalRating.setText(utils.formatTotalRating(jsonObject.getDouble("TongDiem")) + " ");

                        tvTotalRating.setText(utils.formatTotalRating(jsonObject.getDouble("TongDiem")) + " ");

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            getRatingTotal();
            tvSLUserRating.setText(response.length() + " lượt đánh giá");
            communityAdapter.notifyDataSetChanged();

            if (response.length() >= 1) {
                tvNull.setVisibility(View.INVISIBLE);
            } else {
                tvNull.setVisibility(View.VISIBLE);
            }

        };

        com.android.volley.Response.ErrorListener thatbai = error -> {
            if (error.getMessage() != null)
                Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
            tvNull.setVisibility(View.VISIBLE);


        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlgetCommunity, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

    }

    private void turnOffRating() {
        tvNoiDung.setEnabled(false);
        ratingPerson.setIsIndicator(true);

    }

    private void turnOnRating() {
        tvNoiDung.setEnabled(true);
        ratingPerson.setIsIndicator(false);
    }

    private void getRatingTotal() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        com.android.volley.Response.Listener<JSONObject> thanhcong = response -> {
            try {
//                if (response.getString("Message").equals("Không có dữ liệu")) {
//                    btnCreate.setVisibility(View.VISIBLE);
//                    btnDelete.setVisibility(View.INVISIBLE);
//                    btnUpdate.setVisibility(View.INVISIBLE);
//                    turnOnRating();
//                    tvTotalRating.setText(0 + " ");
//                    ratingTotal.setRating(0);
//                    tvSLUserRating.setText("0 đánh giá");
//
//                }
                if (response.getInt("Diem") > 0) {
                    ratingTotal.setRating((float) response.getDouble("TongDiem"));
                    ratingPerson.setRating(response.getInt("Diem"));
                    tvTotalRating.setText(utils.formatTotalRating(response.getDouble("TongDiem")) + " ");
                    tvRatingPerson.setText(response.getInt("Diem") + " ");
                    tvNoiDung.setText(response.getString("NoiDung") + "");
                    tvUserDate.setText(utils.converDateFormate(response.getString("NgayDanhGia")));

                    btnCreate.setVisibility(View.INVISIBLE);
                    btnDelete.setVisibility(View.VISIBLE);
                    btnUpdate.setVisibility(View.VISIBLE);
                    turnOffRating();

                } else {
                    btnCreate.setVisibility(View.VISIBLE);
                    btnDelete.setVisibility(View.INVISIBLE);
                    btnUpdate.setVisibility(View.INVISIBLE);
                    turnOnRating();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        };
        com.android.volley.Response.ErrorListener thatbai = error ->
        {
            if (error.getMessage() != null) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlgetUserRating, null, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
    }


    private void putRating() throws JSONException, ParseException {
        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();

        //put parmas
        parmas.put("MaND", GLOBAL.idUser);
        parmas.put("MaKhoaHoc", GLOBAL.learn.getMaKH());
        parmas.put("NoiDung", tvNoiDung.getText().toString());
        parmas.put("Diem", ratingPerson.getRating());

        paramsHeaders.put("Content-Type", "application/json");

        api.CallAPI(urlCRUDRating, Request.Method.PUT, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {

                Log.i("success", "my response" + dataResponse);

                Toast.makeText(getApplicationContext(), "Sửa thành công ", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(context, ProfileUserActivity.class);
//                startActivity(intent);
                btnUpdate.setText("Chỉnh sửa");
                btnCreate.setVisibility(View.INVISIBLE);
                btnCreate.setText("Thêm");
                turnOffRating();
                getRatingTotal();

            }

            @Override
            public void ReponseError(String error) {

                Log.e("error", "my error: " + error);
                Toast.makeText(getApplicationContext(), "Sửa không thành công ", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void postRating() throws JSONException, ParseException {
        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();

        //put parmas
        parmas.put("MaND", GLOBAL.idUser);
        parmas.put("MaKhoaHoc", GLOBAL.learn.getMaKH());
        parmas.put("NoiDung", tvNoiDung.getText().toString());
        parmas.put("Diem", ratingPerson.getRating());

        paramsHeaders.put("Content-Type", "application/json");

        api.CallAPI(urlCRUDRating, Request.Method.POST, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {

                Log.i("success", "my response" + dataResponse);

                Toast.makeText(getApplicationContext(), "Thêm thành công ", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(context, ProfileUserActivity.class);
//                startActivity(intent);

                btnCreate.setVisibility(View.INVISIBLE);
                btnUpdate.setVisibility(View.VISIBLE);
                btnDelete.setVisibility(View.VISIBLE);
                dataRating.clear();

                turnOffRating();
//                getRatingTotal();
                getCommunity();

            }

            @Override
            public void ReponseError(String error) {

                Log.e("error", "my error: " + error);
                Toast.makeText(getApplicationContext(), "Thêm không thành công, đánh giá đã tồn tại ", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteRating() throws JSONException, ParseException {
        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();

//        //put parmas
//        parmas.put("MaKhoaHoc", GLOBAL.learn.getMaKH());
//        parmas.put("MaND", GLOBAL.idUser);


        paramsHeaders.put("Content-Type", "application/json");

        String urlDelete = urlCRUDRating + "?MaND=" + GLOBAL.idUser + "&&MaKhoaHoc=" + GLOBAL.learn.getMaKH();

        api.CallAPI(urlDelete, Request.Method.DELETE, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {

                Log.i("success", "my response" + dataResponse);

                Toast.makeText(getApplicationContext(), "Xóa thành công ", Toast.LENGTH_LONG).show();

                turnOnRating();
                turnOnRating();
                tvNoiDung.setText("");
                tvUserDate.setText("");
                ratingPerson.setRating(0);
                tvRatingPerson.setText(0 + " ");
                tvTotalRating.setText(0+ "");
                tvSLUserRating.setText(0 + " lượt đánh giá");
                ratingTotal.setRating(0);
                dataRating.clear();
                btnCreate.setVisibility(View.VISIBLE);
                btnDelete.setVisibility(View.INVISIBLE);
                btnUpdate.setVisibility(View.INVISIBLE);
                getCommunity();
                getRatingTotal();


            }

            @Override
            public void ReponseError(String error) {

                Log.e("error", "my error: " + error);
                Toast.makeText(getApplicationContext(), "Xóa không thành công ", Toast.LENGTH_LONG).show();
            }
        });
    }

    //action bar
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

    void DecorateActionBar() {
        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + titleActionBar + "</font>"));
        //doi mau thanh action bars
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
    }


}