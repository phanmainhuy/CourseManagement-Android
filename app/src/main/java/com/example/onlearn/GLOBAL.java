package com.example.onlearn;

import com.example.onlearn.models.CART;
import com.example.onlearn.models.Items_CART;
import com.example.onlearn.models.CHAPTER;
import com.example.onlearn.models.DANHMUC;
import com.example.onlearn.models.KHOAHOC;
import com.example.onlearn.models.KHUYENMAI;
import com.example.onlearn.models.KHUYENMAI_KH;
import com.example.onlearn.models.LEARN;
import com.example.onlearn.models.LESSON;
import com.example.onlearn.models.LOAIKHOAHOC;
import com.example.onlearn.models.NOTIFICATION;
import com.example.onlearn.models.PAY_ReceiptOrder;
import com.example.onlearn.models.RATING;
import com.example.onlearn.models.USER;

import java.util.ArrayList;

public class GLOBAL {

    //decorate
    public static String colorActionBar = "#FF6666";
    public static int iconDialog = R.drawable.ic_chatbot;

    //api
    public static String ip = "http://172.23.240.1:45455/";
    public static String urlimg = "assets/images/";

    //object
    public static DANHMUC DMClick;
    public static LOAIKHOAHOC LoaiKHClick;
    public static KHOAHOC KhoaHocClick;
    public static LEARN learn;
    public static CHAPTER chapter;
    public static LESSON lesson;
    public static RATING userRating;

    //video learn
//    public static String videoLearn;

    //payment
    public static int idHD_pay;
    public static PAY_ReceiptOrder infoThuHo;
    public static String SoGiaGiam;


    public static USER userlogin;
    public static int idUser;
    public static String passwordLogin;


    //after register
    public static String username;

    public static String getUsnForget;
    public static String getEmForget;

    public static ArrayList<Items_CART> itemsCart_items;
    public static CART cart;

    //notification
    public static ArrayList<NOTIFICATION> notifications = new ArrayList<>();






}
