package com.example.onlearn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlearn.OnClick.OnClickRCL_Home;
import com.example.onlearn.R;

import java.util.ArrayList;

import com.example.onlearn.models.OPTION;

public class OptionAdapter_Home_rcl extends RecyclerView.Adapter<OptionAdapter_Home_rcl.KHUNGNHIN> {
    Context context;
    ArrayList<OPTION> dulieu;
    private OnClickRCL_Home listener;
//    private OnClickListener listener;

//    String url = "http://" + GLOBAL.ip + "/topcategory";

    public OptionAdapter_Home_rcl(Context context, ArrayList<OPTION> dulieu, OnClickRCL_Home listener) {
        this.context = context;
        this.dulieu = dulieu;
        this.listener = listener;
    }

    @NonNull
    @Override
    public KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_option_home,null);
        return new KHUNGNHIN(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHIN holder, int position) {
        OPTION option = dulieu.get(position);

        holder.title.setText(option.Title);
        holder.hinh.setImageResource(option.Image);


        holder.option = dulieu.get(position);
    }




    @Override
    public int getItemCount() {
        return dulieu.size();
    }

    public class KHUNGNHIN extends RecyclerView.ViewHolder
    {
        OPTION option;
        ImageView hinh;
        TextView title;

        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);
            hinh = itemView.findViewById(R.id.imgOption_Home);
            title = itemView.findViewById(R.id.lblTitleOption_Home);


            //Xu ly su kien click item cua recycle view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClickOption(option);
                }
            });
        }

    }

}
