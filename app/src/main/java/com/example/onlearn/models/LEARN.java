package com.example.onlearn.models;

import java.io.Serializable;

public class LEARN implements Serializable {
    public int MaKH; //ma khoa hoc
    public String TenKH;
    public String imgKH;
    public int MaGV;
    public String tenGV;
    public int rating;
    public String gioithieu;
    public String ngaymua;

    public LEARN(int maKH, String tenKH, String imgKH,
                 int maGV, String tenGV,
                 int rating,
                 String gioithieu, String ngaymua) {
        MaKH = maKH;
        TenKH = tenKH;
        this.imgKH = imgKH;
        MaGV = maGV;
        this.tenGV = tenGV;
        this.rating = rating;
        this.gioithieu = gioithieu;
        this.ngaymua = ngaymua;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int maKH) {
        MaKH = maKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getImgKH() {
        return imgKH;
    }

    public void setImgKH(String imgKH) {
        this.imgKH = imgKH;
    }

    public int getMaGV() {
        return MaGV;
    }

    public void setMaGV(int maGV) {
        MaGV = maGV;
    }

    public String getTenGV() {
        return tenGV;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getGioithieu() {
        return gioithieu;
    }

    public void setGioithieu(String gioithieu) {
        this.gioithieu = gioithieu;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }


}
