package com.example.onlearn.activity.rating;

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
import com.example.onlearn.activity.lesson.LessonAdapter;
import com.example.onlearn.activity.lesson.OnClickRCL_Lesson;
import com.example.onlearn.models.LESSON;
import com.example.onlearn.models.RATING;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.ArrayList;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.KHUNGNHIN> {
    Context context;
    ArrayList<RATING> dulieu;

    String urlimgUser = GLOBAL.ip + GLOBAL.urlimg + "users/";


    public RatingAdapter(Context context, ArrayList<RATING> dulieu) {
        this.context = context;
        this.dulieu = dulieu;

    }

    @NonNull

    @Override
    public RatingAdapter.KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_rating_community, null);
        return new RatingAdapter.KHUNGNHIN(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingAdapter.KHUNGNHIN holder, int position) {
        RATING rating = dulieu.get(position);

        //set rating
        holder.ratingBar.setRating(rating.getDiem());

        holder.tvNameUser.setText(rating.getTenND());
        try {
            holder.tvDateRate.setText(utils.converDateFormate(rating.getNgayDanhGia()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.tvNumbRate.setText(rating.getDiem() + " ");


        holder.tvNoiDung.setText(rating.getNoiDung());

        Picasso.with(context)
                .load(urlimgUser + rating.getAvatar())
                .placeholder(R.drawable.ic_user)
                .into(holder.imgUser);


        holder.rating = dulieu.get(position);
    }


    @Override
    public int getItemCount() {
        return dulieu.size();
    }


    public class KHUNGNHIN extends RecyclerView.ViewHolder {
        RATING rating;
        ImageView imgUser;
        TextView tvNameUser, tvDateRate, tvNumbRate, tvNoiDung;
        RatingBar ratingBar;


        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);

            imgUser = itemView.findViewById(R.id.imgUserAvatar_Community);

            tvNameUser = itemView.findViewById(R.id.tvName_Community);
            tvDateRate = itemView.findViewById(R.id.tvDateRating_Community);
            tvNumbRate = itemView.findViewById(R.id.tvSoRating_Community);
            tvNoiDung = itemView.findViewById(R.id.tvNoidung_Community);

            ratingBar = itemView.findViewById(R.id.ratingBar_Community);


        }
    }

}