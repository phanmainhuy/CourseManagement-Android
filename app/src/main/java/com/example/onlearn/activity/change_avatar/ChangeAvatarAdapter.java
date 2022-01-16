package com.example.onlearn.activity.change_avatar;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.Request;
import com.example.onlearn.API.API;
import com.example.onlearn.API.ICallBack;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.change_profile.ChangeProfileActivity;
import com.example.onlearn.activity.profile_user.ProfileUserActivity;
import com.example.onlearn.models.AVATAR;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChangeAvatarAdapter extends BaseAdapter {
    API api;
    private ArrayList<AVATAR> data;
    private LayoutInflater layoutInflater;
    private Context context;
    String urlimg = GLOBAL.ip + GLOBAL.urlimg + "users/";
    public ChangeAvatarAdapter(Context aContext,  ArrayList<AVATAR> data) {
        this.context = aContext;
        this.data = data;
        layoutInflater = LayoutInflater.from(aContext);
    }



    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.layout_1dong_changeavatar, null);
            holder = new ViewHolder();
            holder.imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar_ChangeAvatar);
            api = new API(parent.getContext());
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        AVATAR avatar = this.data.get(position);


        //img
        Picasso.with(context)
                .load(urlimg + avatar.avatar)
                .placeholder(R.drawable.no_image_found)
                .into(holder.imgAvatar);

//        holder.countryNameView.setText(avatar.getCountryName());
//        holder.populationView.setText("" + avatar.getPopulation());
//
//        String imageId = this.getMipmapResIdByName(avatar.getFlagName());

        holder.imgAvatar.setOnClickListener(v -> {
            GLOBAL.nameAvatar = avatar.getAvatar();
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);

            //setTitle
            builder1.setTitle("Thông báo");
            builder1.setMessage("Bạn có đồng ý chọn "+ avatar.nameAva+" làm avatar không?");
            builder1.setIcon(R.drawable.ic_chatbot);


            builder1.setCancelable(true);

            builder1.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

//                    Toast.makeText(context, "Đã chọn " + avatar.getNameAva(), Toast.LENGTH_SHORT).show();
                    try {
                        changeProfileUser();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            });
            builder1.setPositiveButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //  Cancel
                    dialog.cancel();
                }
            });
            // Create AlertDialog:
            AlertDialog alert = builder1.create();
            alert.show();





        });



        return convertView;
    }
    static class ViewHolder {
        ImageView imgAvatar;
    }
    //put method volley
    private void changeProfileUser() throws JSONException, ParseException {

        JSONObject parmas = new JSONObject();
        Map<String, String> paramsHeaders = new HashMap<>();

        parmas.put("UserID", GLOBAL.idUser);
        parmas.put("UserName", GLOBAL.userlogin.getUserName());
        parmas.put("Name", GLOBAL.userlogin.getTen());
        parmas.put("CMND", GLOBAL.userlogin.getCMND().trim());
        parmas.put("Number", GLOBAL.userlogin.getNumber());
        parmas.put("Email", GLOBAL.userlogin.getEmail());
        parmas.put("Gender", GLOBAL.userlogin.getGender());
        parmas.put("DoB", GLOBAL.userlogin.getBirthday());
        parmas.put("Address", GLOBAL.userlogin.getAddress());
        parmas.put("HinhAnh", GLOBAL.nameAvatar);
        parmas.put("GroupID", GLOBAL.userlogin.getGroupID());
        parmas.put("Salary", GLOBAL.userlogin.getSalary());

        paramsHeaders.put("Content-Type", "application/json");
        String urlputUser = GLOBAL.ip + "api/nguoidung/?MaNDUpdate="+GLOBAL.idUser;
        api.CallAPI(urlputUser, Request.Method.PUT, parmas.toString(), null, paramsHeaders, new ICallBack() {
            @Override
            public void ReponseSuccess(String dataResponse) {

                Log.i("success", "my response" +dataResponse);
                ((Activity)context).finish();
                Intent intent = new Intent(context, ChangeProfileActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void ReponseError(String error) {
                Log.e("error", "my error: "+ error);
                Toast.makeText(context.getApplicationContext(), "Thay đổi avatar không thành công", Toast.LENGTH_LONG).show();
            }
        });
    }

}
