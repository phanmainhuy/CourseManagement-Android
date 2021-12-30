package com.example.onlearn.activity.cart;

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
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlearn.API.API;
import com.example.onlearn.API.ICallBack;
import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.coupon.CouponActivity;
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.models.CART;
import com.example.onlearn.models.Items_CART;
import com.example.onlearn.utils.utils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.KHUNGNHIN> {
    Context context;
    ArrayList<Items_CART> dulieu;
    private OnClickRCL_Cart listener;
    API api;



    String urlimg = GLOBAL.ip + GLOBAL.urlimg + "courses/";

    public CartAdapter(Context context, ArrayList<Items_CART> dulieu, OnClickRCL_Cart listener) {
        this.context = context;
        this.dulieu = dulieu;
        this.listener = listener;
    }

    @NonNull

    @Override
    public CartAdapter.KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_itemcart, null);
        api = new API(parent.getContext());
        return new CartAdapter.KHUNGNHIN(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CartAdapter.KHUNGNHIN holder, int position) {
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



            btnDeleteCart.setOnClickListener(v -> {
                //dulieu.remove(items_cart);
                if (CartActivity.dataCart.size()<= 0){
                    ((CartActivity)(context)).tvNull.setVisibility(View.VISIBLE);
                }
                if ((CartActivity.dataCart.size()>=0))
                {
                    ((CartActivity)(context)).rclCart.setVisibility(View.VISIBLE);
                    listener.itemClick(items_cart);
                    try {
                        btnDeleteCart.setEnabled(false);
                        deleteCart();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    ((CartActivity)(context)).getCartItems();

                }
                else if(CartActivity.dataCart.size()<=0)
                {
                    ((CartActivity)(context)).tvNull.setVisibility(View.VISIBLE);
//                        ((CartActivity)(context)).cartAdapter.notify();
//                    ((CartActivity)(context)).getCartItems();

                }


            });


            //Xu ly su kien click item cua recycle view
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClickDanhMuc();
                }
            });*/
        }

        private void deleteCart() throws JSONException {

            JSONObject parmas = new JSONObject();
            Map<String, String> paramsHeaders = new HashMap<>();
            int maGioHang, maKhoaHoc;


            //put parmas
//            parmas.put("maGioHang", GLOBAL.cart.getCartID());
//            parmas.put("maKhoaHoc", items_cart.getCourseID());
            maGioHang = GLOBAL.cart.getCartID();
            maKhoaHoc = items_cart.getCourseID();
            //http://192.168.1.160:45455/api/cartitem/?maGioHang=18&maKhoaHoc=1
            String urlApiDeleteCart = GLOBAL.ip + "api/cartitem/?maGioHang="+maGioHang+"&maKhoaHoc="+maKhoaHoc;
            paramsHeaders.put("Content-Type", "application/json");
            api.CallAPI(urlApiDeleteCart, Request.Method.DELETE, parmas.toString(), null, paramsHeaders, new ICallBack() {
                @Override
                public void ReponseSuccess(String dataResponse) {
                    Log.i("success", dataResponse);

                    try {
                        JSONObject result = new JSONObject(dataResponse);
//                    GLOBAL.idUser = result.getInt("UserID");
//                    Toast.makeText(getApplicationContext(), GLOBAL.idUser, Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String tenkh = tenKH.getText().toString();
                    Toast.makeText(context.getApplicationContext(), "Đã xóa khóa học " + tenkh, Toast.LENGTH_SHORT).show();
//                    setItems_cart(context);
                    ((CartActivity) context).getCartItems();
//                Intent intent1 = new Intent(RegisterActivity.this ,LoginActivity.class);
//                startActivity(intent1);
                    // nếu data trả về là object thì --> tạo dataJsonObject cho data {"message:"success",data:[{id:"1",name:"gido"},{id:"2",name:"123"]}
                    // JSONObject objResult = new JSONObject(dataResponse);
                    // }
                    //
                    //   JSONArray arrayResult = objResult.getJSONArray("data");
                }

                @Override
                public void ReponseError(String error) {
                    Log.e("error", "My error: " + error);
                    Toast.makeText(context.getApplicationContext(), "Xóa giỏ hàng thất bại", Toast.LENGTH_LONG).show();
                }
            });
        }
//        private void setItems_cart(Context context) {
//            RequestQueue requestQueue = Volley.newRequestQueue(context);
//
//            com.android.volley.Response.Listener<JSONObject> thanhcong = response -> {
//
//                try {
//                    JSONArray Data = response.getJSONArray("CartItems");
//
//                    GLOBAL.cart = new CART(response.getInt("CourseCartID"),
//                            response.getInt("UserID"),
//                            response.getString("TongTien"));
//                    dulieu = new ArrayList<>();
//                    for (int a = 0; a < Data.length(); a++) //have length
//                    {
//                        JSONObject inData = Data.getJSONObject(a);
//
//                        dulieu.add(new Items_CART(
//                                        inData.getInt("CourseID"),
//                                        inData.getString("CourseName"),
//                                        inData.getString("OriginPrice"),
//                                        inData.getString("AfterPrice"),
//                                        inData.getString("TeacherName"),
//                                        inData.getString("ImageName")
//                                )
//                        );
//
////                        GLOBAL.itemsCart_items = dulieu;
////                        if (GLOBAL.itemsCart_items.size() > 0) {
////                            ((CartActivity) context).tvNull.setVisibility(View.INVISIBLE);
////                        } else {
////                            ((CartActivity) context).tvNull.setVisibility(View.VISIBLE);
////                        }
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
// //               ((CartActivity) context).cartAdapter.notifyDataSetChanged();
//            };
//
//            com.android.volley.Response.ErrorListener thatbai = error -> {
//                if (error.getMessage() != null) {
//                    Toast.makeText(context.getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            };
//            String urlgetCart = GLOBAL.ip + "api/cartitem/?pUserID=" + GLOBAL.idUser;
//
//            JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlgetCart, null, thanhcong, thatbai);
//            requestQueue.add(jsonArrayRequest);
//
//
//        }
//        private void resetCart(){
//
//        }


    }
}

