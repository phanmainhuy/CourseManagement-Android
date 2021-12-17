package com.example.onlearn.activity.classroom_detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.models.CHAPTER;
import com.example.onlearn.models.LOAIKHOAHOC;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class IntroAdapter extends RecyclerView.Adapter<IntroAdapter.KHUNGNHIN> {
    Context context;
    ArrayList<CHAPTER> dulieu;

//private OnClickRCL_LoaiKH listener;


//    String urlimg = GLOBAL.ip + GLOBAL.urlimg + "courses/";

    public IntroAdapter(Context context, ArrayList<CHAPTER> dulieu) {
        this.context = context;
        this.dulieu = dulieu;
//        this.listener = listener;

    }

    @NonNull

    @Override
    public KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_basic, null);
        return new KHUNGNHIN(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IntroAdapter.KHUNGNHIN holder, int position) {
        CHAPTER chap = dulieu.get(position);

        holder.ten.setText(chap.getTenChuong());


        holder.chapter = dulieu.get(position);
    }


    @Override
    public int getItemCount() {
        return dulieu.size();
    }


    public class KHUNGNHIN extends RecyclerView.ViewHolder {
        CHAPTER chapter;

        TextView ten;

        //TextView mota;

        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);

            ten = itemView.findViewById(R.id.tvInfoText_basic);


            //Xu ly su kien click item cua recycle view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                listener.ItemClickLoaiKhoaHoc(loaikhoahoc);
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