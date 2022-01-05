package com.example.onlearn;

import com.example.onlearn.models.CART;
import com.example.onlearn.models.Items_CART;
import com.example.onlearn.models.CHAPTER;
import com.example.onlearn.models.DANHMUC;
import com.example.onlearn.models.KHOAHOC;
import com.example.onlearn.models.LEARN;
import com.example.onlearn.models.LESSON;
import com.example.onlearn.models.LOAIKHOAHOC;
import com.example.onlearn.models.PAY_ReceiptOrder;
import com.example.onlearn.models.RATING;
import com.example.onlearn.models.USER;

import java.util.ArrayList;

public class GLOBAL {

    //decorate
    public static String colorActionBar = "#7A67EE";
    public static int iconDialog = R.drawable.ic_chatbot;

    //api
    public static String ip = "http://192.168.3.19:45455/";
    public static String urlimg = "assets/images/";

    //object
    public static DANHMUC DMClick;
    public static LOAIKHOAHOC LoaiKHClick;
    public static KHOAHOC KhoaHocClick;
    public static LEARN learn;
    public static CHAPTER chapter;
    public static LESSON lesson;
    public static RATING userRating;
    //payment
    public static int idHD_pay;
    public static PAY_ReceiptOrder infoThuHo;
    public static String SoGiaGiam;
    public static double ThanhTienPay;

    public static USER userlogin;
    public static int idUser;
    public static String passwordLogin;


    //after register
    public static String username;

    public static String getUsnForget;

    public static ArrayList<Items_CART> itemsCart_items;
    public static CART cart;





}
