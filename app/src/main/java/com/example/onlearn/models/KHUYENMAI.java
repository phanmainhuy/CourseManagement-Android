package com.example.onlearn.models;

import java.io.Serializable;

public class KHUYENMAI implements Serializable {
    public int MaKM;
    public String MaApDung;
    public  String TenKM;
    public String imgKM;
    public String GiaTri;
    public String HSD;
    public String Diem;




    public KHUYENMAI(int maKM, String maApDung, String tenKM, String imgKM, String giaTri, String hsd, String diem) {
        MaKM = maKM;
        MaApDung = maApDung;
        TenKM = tenKM;
        this.imgKM = imgKM;
        HSD = hsd;
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

    public String getDiem() {
        return Diem;
    }

    public void setDiem(String diem) {
        Diem = diem;
    }


    public String getHSD() {
        return HSD;
    }

    public void setHSD(String HSD) {
        this.HSD = HSD;
    }



}
