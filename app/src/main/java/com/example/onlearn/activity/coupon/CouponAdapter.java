package com.example.onlearn.activity.coupon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.category_courses.KhoaHocTheoLoaiAdapter;
import com.example.onlearn.models.KHOAHOC;
import com.example.onlearn.models.KHUYENMAI;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.KHUNGNHIN>{
    Context context;
    ArrayList<KHUYENMAI> dulieu;



    String urlimg = GLOBAL.ip + GLOBAL.urlimg + "sales/";

    public CouponAdapter(Context context, ArrayList<KHUYENMAI> dulieu) {
        this.context = context;
        this.dulieu = dulieu;
    }
    @NonNull

    @Override
    public CouponAdapter.KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_coupon,null);
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
        holder.hsdkm.setText("Thời hạn: "+km.HSD);
        //set format cho giá
        holder.giatrikm.setText("Giảm giá: "+ utils.formatNumberCurrency(km.GiaTri)+ " VND");
        holder.diemmua.setText("Điểm mua: "+utils.formatNumberCurrency(km.Diem)+ " điểm");

        holder.khuyenmai = dulieu.get(position);
    }





    @Override
    public int getItemCount() {
        return dulieu.size();

    }


    public class KHUNGNHIN extends RecyclerView.ViewHolder
    {
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



            //Xu ly su kien click item cua recycle view
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClickDanhMuc();
                }
            });*/
        }






    }




}
