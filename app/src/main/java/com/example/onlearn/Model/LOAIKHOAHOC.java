package com.example.onlearn.Model;

import java.io.Serializable;

public class LOAIKHOAHOC implements Serializable {
    public int MaLoai;
    public int MaDanhMuc;


    public String TenDanhMuc;
    public String TenLoai;
    public String HinhAnh;

    public LOAIKHOAHOC(int maLoai, int maDanhMuc, String tenLoai, String hinhAnh, String tenDanhMuc) {
        MaLoai = maLoai;
        MaDanhMuc = maDanhMuc;
        TenLoai = tenLoai;
        HinhAnh = hinhAnh;
        TenDanhMuc = tenDanhMuc;
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
    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        TenDanhMuc = tenDanhMuc;
    }

}
