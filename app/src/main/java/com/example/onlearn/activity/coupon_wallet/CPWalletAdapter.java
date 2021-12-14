package com.example.onlearn.activity.coupon_wallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.models.KHUYENMAI_KH;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.ArrayList;

public class CPWalletAdapter extends RecyclerView.Adapter<CPWalletAdapter.KHUNGNHIN>{
    Context context;
    ArrayList<KHUYENMAI_KH> dulieu;



    String urlimg = GLOBAL.ip + GLOBAL.urlimg + "sales/";

    public CPWalletAdapter(Context context, ArrayList<KHUYENMAI_KH> dulieu) {
        this.context = context;
        this.dulieu = dulieu;
    }
    @NonNull

    @Override
    public CPWalletAdapter.KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_couponwallet,null);
        return new CPWalletAdapter.KHUNGNHIN(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CPWalletAdapter.KHUNGNHIN holder, int position) {
        KHUYENMAI_KH km = dulieu.get(position);

        //img
        Picasso.with(context)
                .load(urlimg + km.imgKM)
                .placeholder(R.drawable.no_image_found)
                .into(holder.imgKM);

        holder.tenkm.setText(km.TenKM);
        try {
            holder.hsdkm.setText("HSD: "+utils.converDateFormate(km.HSD));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        holder.hsdkm.setText("HSD: "+ (km.HSD));
        //set format cho giá
        holder.giatrikm.setText("Giá trị: "+ utils.formatNumberCurrency(km.GiaTri)+ " VND");
        holder.maapdung.setText("Mã áp dụng: "+km.MaApDung);


        holder.khuyenmai = dulieu.get(position);
    }





    @Override
    public int getItemCount() {
        return dulieu.size();

    }


    public class KHUNGNHIN extends RecyclerView.ViewHolder
    {
        KHUYENMAI_KH khuyenmai;
        ImageView imgKM;
        TextView tenkm, giatrikm, hsdkm, maapdung;
        Button btnCopy;


        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);

            //anh xa
            imgKM = itemView.findViewById(R.id.imgKM_CouponWallet);
            btnCopy = itemView.findViewById(R.id.btnCopy_CouponWallet);
            tenkm = itemView.findViewById(R.id.tvTenKM_CouponWallet);
            giatrikm = itemView.findViewById(R.id.tvValueKM_CouponWallet);
            hsdkm = itemView.findViewById(R.id.tvHSD_CouponWallet);
            maapdung = itemView.findViewById(R.id.tvMaApDung_CouponWallet);



            //Xu ly su kien click item cua recycle view
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClickDanhMuc();
                }
            });*/
        }






    }




}
