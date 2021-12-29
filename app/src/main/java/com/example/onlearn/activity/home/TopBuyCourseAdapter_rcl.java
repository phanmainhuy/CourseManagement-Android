package com.example.onlearn.activity.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.example.onlearn.API.API;
import com.example.onlearn.API.ICallBack;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.models.KHOAHOC;
import com.example.onlearn.R;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TopBuyCourseAdapter_rcl extends RecyclerView.Adapter<TopBuyCourseAdapter_rcl.KHUNGNHIN>{

    Context context;
    ArrayList<KHOAHOC> dulieu;
    API api;
    private OnClickRCL_Home listener;


    String urlimg = GLOBAL.ip + GLOBAL.urlimg + "courses/";
    String urlApiCart = GLOBAL.ip + "api/cartitem";


    public TopBuyCourseAdapter_rcl(Context context, ArrayList<KHOAHOC> dulieu, OnClickRCL_Home listener) {
        this.context = context;
        this.dulieu = dulieu;
        this.listener = listener;

    }
    @NonNull

    @Override
    public TopBuyCourseAdapter_rcl.KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_courses,null);
        api = new API(parent.getContext());
        return new TopBuyCourseAdapter_rcl.KHUNGNHIN(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHIN holder, int position) {
        KHOAHOC kh = dulieu.get(position);

        //img
        Picasso.with(context)
                .load(urlimg + kh.HinhAnh)
                .placeholder(R.drawable.no_image_found)
                .into(holder.imgKH);

        holder.tenkh.setText(kh.TenKhoaHoc);
        holder.tengv.setText("Giảng viên: "+kh.TenGV);
        //set format cho giá
        holder.giakh.setText(utils.formatNumberCurrency(kh.DonGia)+ " đ");
        //set rating
        holder.ratingkh.setRating(kh.DanhGia);

        holder.khoahoc = dulieu.get(position);
    }





    @Override
    public int getItemCount() {
        return dulieu.size();
    }


    public class KHUNGNHIN extends RecyclerView.ViewHolder
    {
        KHOAHOC khoahoc;
        ImageView imgKH;
        TextView tenkh, tengv, giakh;
        RatingBar ratingkh;
        ImageButton btnAdd, btnDelete;


        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);

            imgKH = itemView.findViewById(R.id.imgKH_Home);
            tenkh = itemView.findViewById(R.id.tvTenKH_Home);
            tengv = itemView.findViewById(R.id.tvTenGV_Home);
            ratingkh = itemView.findViewById(R.id.ratingBar_Home);
            giakh = itemView.findViewById(R.id.tvGiaKH_Home);
            btnAdd = itemView.findViewById(R.id.btnAddCart_Home);
            btnDelete = itemView.findViewById(R.id.btnRemoveCart_Home);


//            btnDelete.setEnabled(false);
            btnDelete.setVisibility(itemView.INVISIBLE);
            btnAdd.setVisibility(itemView.INVISIBLE);

            //xu ly su kien onclick btn add va delete
            btnAdd.setOnClickListener(v -> {
                btnAdd.setVisibility(itemView.INVISIBLE);
                btnDelete.setVisibility(itemView.VISIBLE);
//                listener.ItemClickCourse(khoahoc);
                try {
                    addCart();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            });

            btnDelete.setOnClickListener(v -> {
                btnDelete.setVisibility(itemView.INVISIBLE);
                btnAdd.setVisibility(itemView.VISIBLE);
//                listener.ItemClickCourse(khoahoc);

                String tenmh = tenkh.getText().toString();
                Toast.makeText(context.getApplicationContext(), "Đã xóa khóa học " + tenmh, Toast.LENGTH_SHORT).show();
            });


            //Xu ly su kien click item cua recycle view
            itemView.setOnClickListener(v -> listener.ItemClickCourse(khoahoc));
        }




        private void addCart() throws JSONException {

            JSONObject parmas = new JSONObject();
            Map<String, String> paramsHeaders = new HashMap<>();

            String GiaKH = giakh.getText().toString();
            String MaKH = String.valueOf(khoahoc.getMaKhoaHoc());

            //put parmas
            parmas.put("UserID", GLOBAL.idUser);
            parmas.put("CourseID", MaKH);
            parmas.put("OriginPrice", GiaKH);
            paramsHeaders.put("Content-Type", "application/json");
            api.CallAPI(urlApiCart, Request.Method.POST, parmas.toString(), null, paramsHeaders, new ICallBack() {
                @Override
                public void ReponseSuccess(String dataResponse) {
                    Log.i("success",dataResponse);

                    try {
                        JSONObject result = new JSONObject(dataResponse);
//                    GLOBAL.idUser = result.getInt("UserID");
//                    Toast.makeText(getApplicationContext(), GLOBAL.idUser, Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String tenmh = tenkh.getText().toString();
                    Toast.makeText(context.getApplicationContext(), "Đã thêm khóa học " + tenmh, Toast.LENGTH_SHORT).show();

//                Intent intent1 = new Intent(RegisterActivity.this ,LoginActivity.class);
//                startActivity(intent1);
                    // nếu data trả về là object thì --> tạo dataJsonObject cho data {"message:"success",data:[{id:"1",name:"gido"},{id:"2",name:"123"]}
                    // JSONObject objResult = new JSONObject(dataResponse);
                    // }
                    //
                    //   JSONArray arrayResult = objResult.getJSONArray("data");
                }
                @Override
                public void ReponseError(String error) {
                    Log.e("error", "My error: "+ error);
                    Toast.makeText(context.getApplicationContext(), "Thêm vào giỏ hàng thất bại\nKhóa học đã được mua hoặc có trong giỏ hàng", Toast.LENGTH_LONG).show();
                }
            });
        }







    }

}
