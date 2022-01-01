package com.example.onlearn.models;

import java.io.Serializable;

public class CHAPTER implements Serializable {
    public int STT;


    public int MaChuong;
    public int MaKH;
    public String TenChuong;
    public String TenKH;

    public CHAPTER(int stt, int maChuong, int maKH, String tenChuong, String tenKH) {
        STT = stt;
        MaChuong = maChuong;
        MaKH = maKH;
        TenChuong = tenChuong;
        TenKH = tenKH;
    }

    public int getMaChuong() {
        return MaChuong;
    }

    public void setMaChuong(int maChuong) {
        MaChuong = maChuong;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int maKH) {
        MaKH = maKH;
    }

    public String getTenChuong() {
        return TenChuong;
    }

    public void setTenChuong(String tenChuong) {
        TenChuong = tenChuong;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }
    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

}
