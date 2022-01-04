package com.example.onlearn.activity.pay;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.example.onlearn.API.API;
import com.example.onlearn.API.ICallBack;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.cart.CartActivity;
import com.example.onlearn.activity.cart.CartAdapter;
import com.example.onlearn.activity.cart.OnClickRCL_Cart;
import com.example.onlearn.models.Items_CART;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.KHUNGNHIN> {
    Context context;
    ArrayList<Items_CART> dulieu;


    String urlimg = GLOBAL.ip + GLOBAL.urlimg + "courses/";

    public PayAdapter(Context context, ArrayList<Items_CART> dulieu) {
        this.context = context;
        this.dulieu = dulieu;
    }

    @NonNull

    @Override
    public PayAdapter.KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_itemcart, null);
        return new PayAdapter.KHUNGNHIN(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PayAdapter.KHUNGNHIN holder, int position) {
        Items_CART cart = dulieu.get(position);

        //img
        Picasso.with(context).load(urlimg + cart.getImgName())
                .placeholder(R.drawable.no_image_found).into(holder.imgKH);

        holder.tenKH.setText(cart.getCourseName());
        holder.tenGV.setText(cart.getTeacherName());
        holder.dongia.setText(utils.formatNumberCurrency(cart.getOriginPrice()) + " đ");

        holder.items_cart = dulieu.get(position);

    }


    @Override
    public int getItemCount() {
        return dulieu.size();

    }


    public class KHUNGNHIN extends RecyclerView.ViewHolder {
        Items_CART items_cart;
        ImageView imgKH;
        TextView tenKH, tenGV, dongia;
        ImageButton btnDeleteCart;

        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);
            //map
            imgKH = itemView.findViewById(R.id.imgKH_Cart);
            tenKH = itemView.findViewById(R.id.tvTenKH_Cart);
            tenGV = itemView.findViewById(R.id.tvTenGV_Cart);
            dongia = itemView.findViewById(R.id.tvDonGia_Cart);
            btnDeleteCart = itemView.findViewById(R.id.btnRemoveCart_Cart);

            btnDeleteCart.setVisibility(View.INVISIBLE);


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
