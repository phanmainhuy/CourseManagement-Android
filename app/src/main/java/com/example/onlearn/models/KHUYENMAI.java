package com.example.onlearn.models;

import java.io.Serializable;

public class KHUYENMAI implements Serializable {
    public int MaKM;
    public String MaApDung;
    public  String TenKM;
    public String imgKM;
    public String GiaTri;
    public int Diem;

    public KHUYENMAI(int maKM, String maApDung, String tenKM, String imgKM, String giaTri, int diem) {
        MaKM = maKM;
        MaApDung = maApDung;
        TenKM = tenKM;
        this.imgKM = imgKM;
        GiaTri = giaTri;
        Diem = diem;
    }
    public int getMaKM() {
        return MaKM;
    }

    public void setMaKM(int maKM) {
        MaKM = maKM;
    }

    public String getMaApDung() {
        return MaApDung;
    }

    public void setMaApDung(String maApDung) {
        MaApDung = maApDung;
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

    public int getDiem() {
        return Diem;
    }

    public void setDiem(int diem) {
        Diem = diem;
    }






}
