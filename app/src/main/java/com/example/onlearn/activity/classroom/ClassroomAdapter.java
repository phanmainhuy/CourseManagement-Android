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
import com.example.onlearn.models.LEARN;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.ArrayList;

public class ClassroomAdapter  extends RecyclerView.Adapter<ClassroomAdapter.KHUNGNHIN>{
    Context context;
    ArrayList<LEARN> dulieu;
    private OnClickRCL_Classroom listener;


    String urlimg = GLOBAL.ip + GLOBAL.urlimg + "courses/";

    public ClassroomAdapter(Context context, ArrayList<LEARN> dulieu, OnClickRCL_Classroom listener) {
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
        LEARN kh = dulieu.get(position);

        //img
        Picasso.with(context)
                .load(urlimg + kh.imgKH)
                .placeholder(R.drawable.no_image_found)
                .into(holder.imgKH);

        holder.tenkh.setText(kh.TenKH);
        holder.tengv.setText("Giảng viên: "+kh.tenGV);
        //set format cho giá
        try {
            holder.giakh.setText("Ngày mua: "+utils.converDateFormate(kh.ngaymua));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //set rating
        holder.ratingkh.setRating(kh.rating);

        holder.khoahoc = dulieu.get(position);
    }





    @Override
    public int getItemCount() {
        return dulieu.size();

    }


    public class KHUNGNHIN extends RecyclerView.ViewHolder
    {
        LEARN khoahoc;
        ImageView imgKH;
        TextView tenkh, tengv, giakh;
        RatingBar ratingkh;


        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);

            imgKH = itemView.findViewById(R.id.imgKH_Home);
            tenkh = itemView.findViewById(R.id.tvTenKH_Home);
            tengv = itemView.findViewById(R.id.tvTenGV_Home);
            ratingkh = itemView.findViewById(R.id.ratingBar_Home);
            giakh = itemView.findViewById(R.id.tvGiaKH_Home);





            //Xu ly su kien click item cua recycle view
            itemView.setOnClickListener(v -> listener.itemClickClassroom(khoahoc));
        }
    }




}