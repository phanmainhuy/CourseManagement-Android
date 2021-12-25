package com.example.onlearn.models;

import java.io.Serializable;

public class EXCERCISE implements Serializable {
    public int MaBaiTap;
    public String TenBaiTap;
    public String Filepdf;

    public EXCERCISE(int maBaiTap, String tenBaiTap, String filepdf) {
        MaBaiTap = maBaiTap;
        TenBaiTap = tenBaiTap;
        Filepdf = filepdf;
    }

    public int getMaBaiTap() {
        return MaBaiTap;
    }

    public void setMaBaiTap(int maBaiTap) {
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
