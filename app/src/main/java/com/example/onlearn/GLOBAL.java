package com.example.onlearn;

import com.example.onlearn.models.CART;
import com.example.onlearn.models.Items_CART;
import com.example.onlearn.models.CHAPTER;
import com.example.onlearn.models.DANHMUC;
import com.example.onlearn.models.KHOAHOC;
import com.example.onlearn.models.LEARN;
import com.example.onlearn.models.LESSON;
import com.example.onlearn.models.LOAIKHOAHOC;
import com.example.onlearn.models.USER;

import java.util.ArrayList;

public class GLOBAL {
    public static String colorActionBar = "#43CD80";
    //api
    //Y: 192.168.1.8
    //http://192.168.1.9:63702/
    //https://localhost:63702/
    //10.0.2.2
    //192.168.3.43
    public static String ip = "http://192.168.1.160:45459/";
    public static String urlimg = "assets/images/";
    //object
    public static DANHMUC DMClick;
    public static LOAIKHOAHOC LoaiKHClick;
    public static KHOAHOC KhoaHocClick;
    public static LEARN learn;
    public static CHAPTER chapter;
    public static LESSON lesson;

    public static USER  userlogin;
    public static int idUser;
    //after register
    public static String username;

    public static String getUsnForget;

    public static ArrayList<Items_CART> itemsCart_items;
    public static CART cart;

}
