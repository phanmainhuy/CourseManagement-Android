package com.example.onlearn.Adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlearn.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.example.onlearn.Model.DANHMUCKHOAHOC;
import com.example.onlearn.Model.GLOBAL;

public class DanhMucAdapter_rcl extends RecyclerView.Adapter<DanhMucAdapter_rcl.KHUNGNHIN>{
    Context context;
    ArrayList<DANHMUCKHOAHOC> dulieu;

    private OnClickRCL_DanhMuc listener;


    String url = "http://" + GLOBAL.ip + "/topcategory";

    public DanhMucAdapter_rcl(Context context, ArrayList<DANHMUCKHOAHOC> dulieu, OnClickRCL_DanhMuc listener) {
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
        DANHMUCKHOAHOC danhmuc = dulieu.get(position);

        holder.ten.setText(danhmuc.TenDanhMuc);

        // for image we add Glide library dependency for image fetching from server
        holder.hinhanh.setImageBitmap(this.converStringToBitmapFromAccess(danhmuc.getHinhAnh()));
        holder.hinhanh.setScaleType(ImageView.ScaleType.FIT_CENTER);

//        Picasso.with(context)
//                .load(url + danhmuc.HinhAnh)
//                .placeholder(R.drawable.no_image_found)
//                .into(holder.hinhanh);


        //holder.mota.setText(products.mota);
        //set format cho giá

        holder.danhmuckhoahoc = dulieu.get(position);

    }
    //bit map
    public Bitmap converStringToBitmapFromAccess(String filename){
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open(filename);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int getItemCount() {
        return dulieu.size();
    }


    public class KHUNGNHIN extends RecyclerView.ViewHolder
    {
        DANHMUCKHOAHOC danhmuckhoahoc;
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
                    listener.itemClickDanhMuc(danhmuckhoahoc);
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
