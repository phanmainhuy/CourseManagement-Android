package com.example.onlearn.activity.coupon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        KHUYENMAI kh = dulieu.get(position);

        //img
        Picasso.with(context)
                .load(urlimg + kh.imgKM)
                .placeholder(R.drawable.no_image_found)
                .into(holder.imgKM);

        holder.tenkh.setText(kh.TenKM);
        holder.tengv.setText(kh.TenGV);
        //set format cho giá
        holder.giakh.setText(formatNumberCurrency(kh.DonGia)+ " đ");
        //set rating
        holder.ratingkh.setRating(kh.DanhGia);

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
        TextView tenkh, tengv, giakh;
        RatingBar ratingkh;


        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);

            imgKM = itemView.findViewById(R.id.imgKH_Home);
            tenkh = itemView.findViewById(R.id.tvTenKH_Home);
            tengv = itemView.findViewById(R.id.tvTenGV_Home);
            ratingkh = itemView.findViewById(R.id.ratingBar_Home);
            giakh = itemView.findViewById(R.id.tvGiaKH_Home);



            //Xu ly su kien click item cua recycle view
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClickDanhMuc();
                }
            });*/
        }
    }



    //Tạo format tiền VND
    public static String formatNumberCurrency(String gia)
    {
        DecimalFormat format = new DecimalFormat("#,###");
        return format.format(Double.parseDouble(gia));
    }
}
