package com.example.onlearn.utils;

import android.content.Context;
import android.util.Log;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class utils {
    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    //Tạo format tiền VND
    public static String formatNumberCurrency(String gia)
    {
        DecimalFormat format = new DecimalFormat("#,###");
        return format.format(Double.parseDouble(gia));
    }
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";// cấu trúc 1 email thông thường
    // Class kiểm định dạng email
    public boolean validate(final String hex) {
        Matcher matcher;
        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
//    public static Date formatStringtoDate(String datestring) throws ParseException {
////        String strDate = "2013-05-15T10:00:00-0700";
////        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//
//        return dateFormat.parse(datestring);
//
//    }

//    converDateFormate("dd/mm/yyyy hh:mm:ss", "dd/mm/yyyy", "12/12/2012 12:12:12");
    public static String converDateFormate(String dateString) throws ParseException {
        String oldpattern = "yyyy-MM-dd'T'HH:mm";
        String newPattern = "dd-MM-yyyy";

        SimpleDateFormat format = new SimpleDateFormat(oldpattern);
        Date testDate;
        testDate = format.parse(dateString);

        SimpleDateFormat formatter = new SimpleDateFormat(newPattern);
        String newFormat = formatter.format(testDate);
        return newFormat;
    }


    public static String converDatePutPost(String dateString) throws ParseException {
        String oldpattern = "dd-MM-yyyy";
        String newPattern = "yyyy-MM-dd";

        SimpleDateFormat format = new SimpleDateFormat(oldpattern);
        Date testDate;
        testDate = format.parse(dateString);

        SimpleDateFormat formatter = new SimpleDateFormat(newPattern);
        String newFormat = formatter.format(testDate);
        return newFormat;
    }

    public void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
    }

    public static boolean emailFormat(String email){
        String regex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(email.matches(regex)){
            return true;
        }
        else
            return false;
    }

    //format trung binh danh gia
    public static String formatTotalRating(Double x){
        DecimalFormat f = new DecimalFormat("##.0");
        String a = f.format(x);
        return a;
    }



}
