package com.example.onlearn.models;

import java.io.Serializable;

public class KHUYENMAI_KH implements Serializable {
    public int MaKM;
    public int MaHV;
    public String TenKM;
    public String imgKM;
    public String GiaTri;
    public String MaApDung;
    public String HSD;

    public KHUYENMAI_KH(int maKM, int maHV, String tenKM, String imgKM, String giaTri, String maApDung, String HSD) {
        MaKM = maKM;
        MaHV = maHV;
        TenKM = tenKM;
        this.imgKM = imgKM;
        GiaTri = giaTri;
        MaApDung = maApDung;
        this.HSD = HSD;
    }

    public int getMaKM() {
        return MaKM;
    }

    public void setMaKM(int maKM) {
        MaKM = maKM;
    }

    public int getMaHV() {
        return MaHV;
    }

    public void setMaHV(int maHV) {
        MaHV = maHV;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String tenKM) {
        TenKM = tenKM;
    }

    public String getImgKM() {
        return imgKM;
    }

    public void setImgKM(String imgKM) {
        this.imgKM = imgKM;
    }

    public String getGiaTri() {
        return GiaTri;
    }

    public void setGiaTri(String giaTri) {
        GiaTri = giaTri;
    }

    public String getMaApDung() {
        return MaApDung;
    }

    public void setMaApDung(String maApDung) {
        MaApDung = maApDung;
    }

    public String getHSD() {
        return HSD;
    }

    public void setHSD(String HSD) {
        this.HSD = HSD;
    }



}
