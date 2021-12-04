package com.example.onlearn.Model;

import java.io.Serializable;

public class LOAIKHOAHOC implements Serializable {
    public int MaLoai;
    public int MaDanhMuc;
    public String TenLoai;
    public String HinhAnh;

    public LOAIKHOAHOC(int maLoai, int maDanhMuc, String tenLoai, String hinhAnh) {
        MaLoai = maLoai;
        MaDanhMuc = maDanhMuc;
        TenLoai = tenLoai;
        HinhAnh = hinhAnh;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public int getMaDanhMuc() {
        return MaDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        MaDanhMuc = maDanhMuc;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
