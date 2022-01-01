package com.example.onlearn.activity.excercise;

import android.content.Context;
import android.text.util.Linkify;
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
import com.example.onlearn.activity.category_courses.KhoaHocTheoLoaiAdapter;
import com.example.onlearn.models.EXCERCISE;
import com.example.onlearn.models.KHOAHOC;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExcerciseAdapter extends RecyclerView.Adapter<ExcerciseAdapter.KHUNGNHIN> {
    Context context;
    ArrayList<EXCERCISE> dulieu;
    private OnClickRCL_Excercise listener;

    String urlgetPDF = GLOBAL.ip + "assets/PDF/";

//        String urlimg = GLOBAL.ip + GLOBAL.urlimg + "courses/";

    public ExcerciseAdapter(Context context, ArrayList<EXCERCISE> dulieu, OnClickRCL_Excercise listener) {
        this.context = context;
        this.dulieu = dulieu;
        this.listener = listener;
    }

    @NonNull

    @Override
    public ExcerciseAdapter.KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_excercise, null);
        return new ExcerciseAdapter.KHUNGNHIN(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ExcerciseAdapter.KHUNGNHIN holder, int position) {
        EXCERCISE bt = dulieu.get(position);

        holder.tvPDF.setText(bt.getFilepdf());
        holder.tvTenBT.setText(bt.getTenBaiTap());


        holder.baitap = dulieu.get(position);
    }


    @Override
    public int getItemCount() {
        return dulieu.size();

    }


    public class KHUNGNHIN extends RecyclerView.ViewHolder {
        EXCERCISE baitap;
        TextView tvPDF, tvTenBT;


        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);

            //map
            tvPDF = itemView.findViewById(R.id.tvTenpdf_Ex);
            tvTenBT = itemView.findViewById(R.id.tvTenBT_Ex);


            //Xu ly su kien click item cua recycle view
            itemView.setOnClickListener(v -> {
                        listener.itemClickEx(baitap);
                        tvPDF.setText(urlgetPDF + tvPDF.getText().toString());
                        Linkify.addLinks(tvPDF, Linkify.ALL);
//                        tvPDF.performClick();
                
                    }
            );
        }
    }


}
