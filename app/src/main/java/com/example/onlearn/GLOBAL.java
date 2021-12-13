package com.example.onlearn;

import com.example.onlearn.models.DANHMUC;
import com.example.onlearn.models.KHOAHOC;
import com.example.onlearn.models.LOAIKHOAHOC;
import com.example.onlearn.models.USER;

public class GLOBAL {
    public static String colorActionBar = "#3399FF";
    //api
    //Y: 192.168.1.8
    //http://192.168.1.9:63702/
    //https://localhost:63702/
    //10.0.2.2
    public static String ip = "http://192.168.1.160:45455/";
    public static String urlimg = "assets/images/";

    //object
    public static DANHMUC DMClick;
    public static LOAIKHOAHOC LoaiKHClick;
    public static KHOAHOC KhoaHocClick;

    public static USER  userlogin;
    public static int idUser;

}
