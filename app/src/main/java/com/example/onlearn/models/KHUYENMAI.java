package com.example.onlearn.models;

import java.io.Serializable;

public class KHUYENMAI implements Serializable {
    public int MaKM;
    public String MaApDung;
    public  String TenKM;
    public String HinhAnh;

    public KHUYENMAI(int maKM, String maApDung, String tenKM, String hinhAnh) {
        MaKM = maKM;
        MaApDung = maApDung;
        TenKM = tenKM;
        HinhAnh = hinhAnh;
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

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }








}
