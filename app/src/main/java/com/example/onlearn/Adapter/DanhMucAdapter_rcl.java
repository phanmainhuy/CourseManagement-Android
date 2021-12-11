package com.example.onlearn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlearn.Model.DANHMUC;
import com.example.onlearn.OnClick.OnClickRCL_DanhMuc;
import com.example.onlearn.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.example.onlearn.GLOBAL;

public class DanhMucAdapter_rcl extends RecyclerView.Adapter<DanhMucAdapter_rcl.KHUNGNHIN>{
    Context context;
    ArrayList<DANHMUC> dulieu;


    private OnClickRCL_DanhMuc listener;


    String urlimg = GLOBAL.ip + GLOBAL.urlimg + "courses/";

    public DanhMucAdapter_rcl(Context context, ArrayList<DANHMUC> dulieu, OnClickRCL_DanhMuc listener) {
        this.context = context;
        this.dulieu = dulieu;
        this.listener = listener;

    }
    @NonNull

    @Override
    public KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_danhmuckh,null);
        return new KHUNGNHIN(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhMucAdapter_rcl.KHUNGNHIN holder, int position) {
        DANHMUC danhmuc = dulieu.get(position);

        holder.ten.setText(danhmuc.getTenDanhMuc());

        Picasso.with(context)
                .load(urlimg + danhmuc.getHinhAnh())
                .placeholder(R.drawable.no_image_found)
                .into(holder.hinhanh);

        holder.danhmuckhoahoc = dulieu.get(position);

    }


    @Override
    public int getItemCount() {
        return dulieu.size();
    }


    public class KHUNGNHIN extends RecyclerView.ViewHolder
    {
        DANHMUC danhmuckhoahoc;
        ImageView hinhanh;
        TextView ten;

        //TextView mota;

        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);

            hinhanh = itemView.findViewById(R.id.imgDanhMuc_DanhMuc);
            ten = itemView.findViewById(R.id.tvTenDM_DanhMuc);



            //Xu ly su kien click item cua recycle view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.ItemClickDM(danhmuckhoahoc);
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
