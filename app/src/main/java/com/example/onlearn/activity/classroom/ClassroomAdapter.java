package com.example.onlearn.activity.classroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.models.KHOAHOC;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ClassroomAdapter  extends RecyclerView.Adapter<ClassroomAdapter.KHUNGNHIN>{
    Context context;
    ArrayList<KHOAHOC> dulieu;
    private OnClickRCL_Classroom listener;


    String urlimg = GLOBAL.ip + GLOBAL.urlimg + "courses/";

    public ClassroomAdapter(Context context, ArrayList<KHOAHOC> dulieu, OnClickRCL_Classroom listener) {
        this.context = context;
        this.dulieu = dulieu;
        this.listener = listener;
    }
    @NonNull

    @Override
    public ClassroomAdapter.KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_courses,null);
        return new ClassroomAdapter.KHUNGNHIN(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ClassroomAdapter.KHUNGNHIN holder, int position) {
        KHOAHOC kh = dulieu.get(position);

        //img
        Picasso.with(context)
                .load(urlimg + kh.HinhAnh)
                .placeholder(R.drawable.no_image_found)
                .into(holder.imgKH);

        holder.tenkh.setText(kh.TenKhoaHoc);
        holder.tengv.setText(kh.TenGV);
        //set format cho giá
        holder.giakh.setText(kh.TenLoai);
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

            //an btn
            btnDelete.setVisibility(itemView.INVISIBLE);
            btnAdd.setVisibility(itemView.INVISIBLE);


            //Xu ly su kien click item cua recycle view
            itemView.setOnClickListener(v -> listener.itemClickClassroom(khoahoc));
        }
    }




}