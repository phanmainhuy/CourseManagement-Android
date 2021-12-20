package com.example.onlearn.activity.chapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlearn.R;
import com.example.onlearn.activity.classroom_detail.IntroAdapter;
import com.example.onlearn.models.CHAPTER;

import java.util.ArrayList;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.KHUNGNHIN> {
    Context context;
    ArrayList<CHAPTER> dulieu;

private OnClickRCL_Chapter listener;


//    String urlimg = GLOBAL.ip + GLOBAL.urlimg + "courses/";

    public ChapterAdapter(Context context, ArrayList<CHAPTER> dulieu,  OnClickRCL_Chapter listener) {
        this.context = context;
        this.dulieu = dulieu;
        this.listener = listener;

    }

    @NonNull

    @Override
    public ChapterAdapter.KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_chapter, null);
        return new ChapterAdapter.KHUNGNHIN(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterAdapter.KHUNGNHIN holder, int position) {
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

            ten = itemView.findViewById(R.id.tv_basic_chapter);


            //Xu ly su kien click item cua recycle view
            itemView.setOnClickListener(v -> {
                listener.ItemClickLoaiKhoaHoc(chapter);
            });
        }
    }

}
