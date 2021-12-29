package com.example.onlearn.activity.coupon;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.example.onlearn.API.API;
import com.example.onlearn.API.ICallBack;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.login.LoginActivity;
import com.example.onlearn.activity.register.RegisterActivity;
import com.example.onlearn.models.KHUYENMAI;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.KHUNGNHIN> {
    Context context;
    ArrayList<KHUYENMAI> dulieu;
    private OnClickRCL_Coupon listener;
    API api;


    String urlimg = GLOBAL.ip + GLOBAL.urlimg + "sales/";
    String urlBuyCP = GLOBAL.ip + "/api/khuyenmai";

    public CouponAdapter(Context context, ArrayList<KHUYENMAI> dulieu, OnClickRCL_Coupon listener) {
        this.context = context;
        this.dulieu = dulieu;
        this.listener = listener;
    }

    @NonNull

    @Override
    public CouponAdapter.KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_coupon, null);
        api = new API(parent.getContext());
        return new CouponAdapter.KHUNGNHIN(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CouponAdapter.KHUNGNHIN holder, int position) {
        KHUYENMAI km = dulieu.get(position);

        //img
        Picasso.with(context)
                .load(urlimg + km.imgKM)
                .placeholder(R.drawable.no_image_found)
                .into(holder.imgKM);

        holder.tenkm.setText(km.TenKM);
        holder.hsdkm.setText(km.HSD);
        //set format cho giá
        holder.giatrikm.setText(utils.formatNumberCurrency(km.GiaTri) + " VND");
        holder.diemmua.setText((km.Diem) + " ");

//        holder.btnMuaMa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.itemClickCoupon(dulieu.get(position));
//                Toast.makeText(context.getApplicationContext(), "Mua mã" + km.TenKM, Toast.LENGTH_SHORT).show();
//
//            }
//        });


        holder.khuyenmai = dulieu.get(position);

    }


    @Override
    public int getItemCount() {
        return dulieu.size();

    }


    public class KHUNGNHIN extends RecyclerView.ViewHolder {
        KHUYENMAI khuyenmai;
        ImageView imgKM;
        TextView tenkm, giatrikm, hsdkm, diemmua;
        Button btnMuaMa;


        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);

            //anh xa
            imgKM = itemView.findViewById(R.id.imgKM_Coupon);
            btnMuaMa = itemView.findViewById(R.id.btnBuyCoupon_Coupon);
            tenkm = itemView.findViewById(R.id.tvTenKM_Coupon);
            giatrikm = itemView.findViewById(R.id.tvValueKM_Coupon);
            hsdkm = itemView.findViewById(R.id.tvHSD_Coupon);
            diemmua = itemView.findViewById(R.id.tvDiemMua_Coupon);


            btnMuaMa.setOnClickListener(v -> {
                if (GLOBAL.userlogin.getDiemTichLuy() <= Integer.parseInt(khuyenmai.getDiem())) {
                    Toast.makeText(context.getApplicationContext(), "Bạn chưa đủ điểm để mua khuyến mãi", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    listener.buyCoupon(khuyenmai);
                    try {
                        postBuyCP();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    Toast.makeText(context.getApplicationContext(), "Đã mua khuyến mãi", Toast.LENGTH_SHORT).show();

                }


            });


            //Xu ly su kien click item cua recycle view
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClickDanhMuc();
                }
            });*/
        }
        private void postBuyCP() throws JSONException {

            JSONObject parmas = new JSONObject();
            Map<String, String> paramsHeaders = new HashMap<>();

            String iduser = String.valueOf(GLOBAL.idUser);
            String makm = String.valueOf(khuyenmai.getMaKM());

            //put parmas
            parmas.put("MaHV", iduser);
            parmas.put("MaKM", makm);
            paramsHeaders.put("Content-Type", "application/json");
            api.CallAPI(urlBuyCP, Request.Method.POST, parmas.toString(), null, paramsHeaders, new ICallBack() {
                @Override
                public void ReponseSuccess(String dataResponse) {
                    Log.i("success",dataResponse);

                    try {
                        JSONObject result = new JSONObject(dataResponse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ((CouponActivity) context).getResetUser();

                    Toast.makeText(context.getApplicationContext(), "Mua khuyến mãi "+ tenkm.getText().toString() + " thành công", Toast.LENGTH_SHORT).show();

                }
                @Override
                public void ReponseError(String error) {
                    Log.e("error", "My error: "+ error);
                    Toast.makeText(context.getApplicationContext(), "Bạn đã có mã khuyến mãi này trong ví", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


}
