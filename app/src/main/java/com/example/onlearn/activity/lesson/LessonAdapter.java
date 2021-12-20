package com.example.onlearn.activity.lesson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlearn.R;
import com.example.onlearn.activity.chapter.ChapterAdapter;
import com.example.onlearn.activity.chapter.OnClickRCL_Chapter;
import com.example.onlearn.models.CHAPTER;
import com.example.onlearn.models.LESSON;

import java.util.ArrayList;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.KHUNGNHIN> {
    Context context;
    ArrayList<LESSON> dulieu;

    private OnClickRCL_Lesson listener;


//    String urlimg = GLOBAL.ip + GLOBAL.urlimg + "courses/";

    public LessonAdapter(Context context, ArrayList<LESSON> dulieu,  OnClickRCL_Lesson listener) {
        this.context = context;
        this.dulieu = dulieu;
        this.listener = listener;

    }

    @NonNull

    @Override
    public LessonAdapter.KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_basic, null);
        return new LessonAdapter.KHUNGNHIN(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonAdapter.KHUNGNHIN holder, int position) {
        LESSON les = dulieu.get(position);


        holder.ten.setText(les.getTenBaiHoc());


        holder.lesson = dulieu.get(position);
    }


    @Override
    public int getItemCount() {
        return dulieu.size();
    }


    public class KHUNGNHIN extends RecyclerView.ViewHolder {
        LESSON lesson;

        TextView ten;

        //TextView mota;

        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);

            ten = itemView.findViewById(R.id.tv_basic);

            //Xu ly su kien click item cua recycle view
            itemView.setOnClickListener(v -> {
                listener.ItemClickLesson(lesson);
            });
        }
    }

}