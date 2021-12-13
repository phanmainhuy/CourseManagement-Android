package com.example.onlearn.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.models.LOAIKHOAHOC;
import com.example.onlearn.OnClick.OnClickRCL_LoaiKH;
import com.example.onlearn.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaiKhoaHocAdapter_rcl extends RecyclerView.Adapter<LoaiKhoaHocAdapter_rcl.KHUNGNHIN>{
    Context context;
    ArrayList<LOAIKHOAHOC> dulieu;

    private OnClickRCL_LoaiKH listener;


    String urlimg = GLOBAL.ip + GLOBAL.urlimg + "courses/";

    public LoaiKhoaHocAdapter_rcl(Context context, ArrayList<LOAIKHOAHOC> dulieu, OnClickRCL_LoaiKH listener) {
        this.context = context;
        this.dulieu = dulieu;
        this.listener = listener;

    }
    @NonNull

    @Override
    public KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_loaikhoahoc,null);
        return new KHUNGNHIN(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiKhoaHocAdapter_rcl.KHUNGNHIN holder, int position) {
        LOAIKHOAHOC loaikh = dulieu.get(position);

        holder.ten.setText(loaikh.getTenLoai());

        Picasso.with(context)
                .load(urlimg + loaikh.getHinhAnh())
                .placeholder(R.drawable.no_image_found)
                .into(holder.hinhanh);

        holder.loaikhoahoc = dulieu.get(position);
    }



    @Override
    public int getItemCount() {
        return dulieu.size();
    }


    public class KHUNGNHIN extends RecyclerView.ViewHolder
    {
        LOAIKHOAHOC loaikhoahoc;
        ImageView hinhanh;
        TextView ten;

        //TextView mota;

        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);

            hinhanh = itemView.findViewById(R.id.imgLoaiKH_LoaiKH);
            ten = itemView.findViewById(R.id.tvTenLoai_LoaiKH);



            //Xu ly su kien click item cua recycle view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.ItemClickLoaiKhoaHoc(loaikhoahoc);
                }
            });
        }
    }

//
//
//    //Tạo format tiền VND
//    public static String formatNumberCurrency(String gia)
//    {
//        DecimalFormat format = new DecimalFormat("#,###");
//        return format.format(Double.parseDouble(gia));
//    }
}