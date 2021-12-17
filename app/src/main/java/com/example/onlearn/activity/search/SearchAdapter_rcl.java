package com.example.onlearn.activity.search;

import android.content.Context;
import android.util.Log;
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
import com.example.onlearn.models.KHOAHOC;
import com.example.onlearn.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SearchAdapter_rcl extends RecyclerView.Adapter<SearchAdapter_rcl.KHUNGNHIN>{
        Context context;
        ArrayList<KHOAHOC> dulieu;
        private OnClickRCL_Search listener;


        String urlimg = GLOBAL.ip + GLOBAL.urlimg + "courses/";

public SearchAdapter_rcl(Context context, ArrayList<KHOAHOC> dulieu, OnClickRCL_Search listener) {
        this.context = context;
        this.dulieu = dulieu;
        this.listener = listener;
        }
@NonNull

@Override
public SearchAdapter_rcl.KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_courses,null);
        return new SearchAdapter_rcl.KHUNGNHIN(view);
        }

@Override
public void onBindViewHolder(@NonNull KHUNGNHIN holder, int position) {
        KHOAHOC kh = dulieu.get(position);

        //img
        Picasso.with(context)
        .load(urlimg + kh.HinhAnh)
        .placeholder(R.drawable.no_image_found)
        .into(holder.imgKH);

        holder.tenkh.setText(kh.TenKhoaHoc);
        holder.tengv.setText("Giảng viên: "+kh.TenGV);
        //set format cho giá
        holder.giakh.setText(formatNumberCurrency(kh.DonGia)+ " đ");
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


        //onclick 2 button
        btnDelete.setVisibility(itemView.INVISIBLE);
        btnAdd.setOnClickListener(v -> {
            btnAdd.setVisibility(itemView.INVISIBLE);
            btnDelete.setVisibility(itemView.VISIBLE);
        });


        btnDelete.setOnClickListener(v -> {
            btnDelete.setVisibility(itemView.INVISIBLE);
            btnAdd.setVisibility(itemView.VISIBLE);

        });


        //Xu ly su kien click item cua recycle view
           itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClickKhoaHoc(khoahoc);
                }
            });
    }
}



    //Tạo format tiền VND
    public static String formatNumberCurrency(String gia)
    {
        DecimalFormat format = new DecimalFormat("#,###");
        return format.format(Double.parseDouble(gia));
    }
}
