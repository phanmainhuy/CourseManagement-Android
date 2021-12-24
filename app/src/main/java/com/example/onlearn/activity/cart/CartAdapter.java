package com.example.onlearn.activity.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.models.Items_CART;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<Items_CART> carts;

    String urlimg = GLOBAL.ip + GLOBAL.urlimg + "courses/";


    public CartAdapter(Context context, ArrayList<Items_CART> carts) {
        this.context = context;
        this.carts = carts;
    }

    @Override
    public int getCount() {
        return carts.size();
    }

    @Override
    public Object getItem(int position) {
        return carts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        if (convertview != null) {
            convertview = LayoutInflater.from(context).inflate(R.layout.layout_1dong_itemcart, null);

            //khai bao
            ImageView imgKH;
            TextView tenKH, tenGV, dongia;
            ImageButton btnDeleteCart;

            //map
            imgKH = convertview.findViewById(R.id.imgKH_Cart);
            tenKH = convertview.findViewById(R.id.tvTenKH_Cart);
            tenGV = convertview.findViewById(R.id.tvTenGV_Cart);
            dongia = convertview.findViewById(R.id.tvDonGia_Cart);
            btnDeleteCart = convertview.findViewById(R.id.btnRemoveCart_Cart);

            //hooking
            Items_CART cart = (Items_CART)getItem(position);

            Picasso.with(context).load(urlimg + cart.getImgName())
                    .placeholder(R.drawable.no_image_found).into(imgKH);

            tenKH.setText(cart.getCourseName());
            tenGV.setText(cart.getTeacherName());
            dongia.setText(utils.formatNumberCurrency(cart.getOriginPrice()+ " đ"));





        }


        return convertview;
    }
}
