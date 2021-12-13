package com.example.onlearn.utils;

import java.text.DecimalFormat;

public class utils {

    //Tạo format tiền VND
    public static String formatNumberCurrency(String gia)
    {
        DecimalFormat format = new DecimalFormat("#,###");
        return format.format(Double.parseDouble(gia));
    }

}
