package com.example.onlearn.models;

import java.io.Serializable;

public class COUPONS implements Serializable {

    public int MaND;
    public int MaKM;
    public String NgayBatDau;
    public String NgayKetThuc;

    public int getMaND() {
        return MaND;
    }

    public void setMaND(int maND) {
        MaND = maND;
    }

    public int getMaKM() {
        return MaKM;
    }

    public void setMaKM(int maKM) {
        MaKM = maKM;
    }

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        NgayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        NgayKetThuc = ngayKetThuc;
    }

    public COUPONS(int maND, int maKM, String ngayBatDau, String ngayKetThuc) {
        MaND = maND;
        MaKM = maKM;
        NgayBatDau = ngayBatDau;
        NgayKetThuc = ngayKetThuc;
    }







}
