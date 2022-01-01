package com.example.onlearn.models;

import java.io.Serializable;

public class RATING implements Serializable {
    public int MaDanhGia;
    public int MaND;
    public int MaKH;
    public String TenKH;
    public int Diem;
    public double TongDiem;
    public String NoiDung;
    public String TenND;
    public String Avatar;
    public String NgayDanhGia;

    public RATING(int maDanhGia, int maND, int maKH, String tenKH, int diem, double tongDiem, String noiDung, String tenND, String avatar, String ngayDanhGia) {
        MaDanhGia = maDanhGia;
        MaND = maND;
        MaKH = maKH;
        TenKH = tenKH;
        Diem = diem;
        TongDiem = tongDiem;
        NoiDung = noiDung;
        TenND = tenND;
        Avatar = avatar;
        NgayDanhGia = ngayDanhGia;
    }

    public int getMaDanhGia() {
        return MaDanhGia;
    }

    public void setMaDanhGia(int maDanhGia) {
        MaDanhGia = maDanhGia;
    }

    public int getMaND() {
        return MaND;
    }

    public void setMaND(int maND) {
        MaND = maND;
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

    public int getDiem() {
        return Diem;
    }

    public void setDiem(int diem) {
        Diem = diem;
    }

    public double getTongDiem() {
        return TongDiem;
    }

    public void setTongDiem(double tongDiem) {
        TongDiem = tongDiem;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getTenND() {
        return TenND;
    }

    public void setTenND(String tenND) {
        TenND = tenND;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getNgayDanhGia() {
        return NgayDanhGia;
    }

    public void setNgayDanhGia(String ngayDanhGia) {
        NgayDanhGia = ngayDanhGia;
    }
}
