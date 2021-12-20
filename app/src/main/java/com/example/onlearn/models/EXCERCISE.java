package com.example.onlearn.models;

import java.io.Serializable;

public class EXCERCISE implements Serializable {
    public String MaBaiTap;
    public String TenBaiTap;
    public String Filepdf;

    public EXCERCISE(String maBaiTap, String tenBaiTap, String filepdf) {
        MaBaiTap = maBaiTap;
        TenBaiTap = tenBaiTap;
        Filepdf = filepdf;
    }

    public String getMaBaiTap() {
        return MaBaiTap;
    }

    public void setMaBaiTap(String maBaiTap) {
        MaBaiTap = maBaiTap;
    }

    public String getTenBaiTap() {
        return TenBaiTap;
    }

    public void setTenBaiTap(String tenBaiTap) {
        TenBaiTap = tenBaiTap;
    }

    public String getFilepdf() {
        return Filepdf;
    }

    public void setFilepdf(String filepdf) {
        this.Filepdf = filepdf;
    }
}
