package com.example.onlearn.models;

import java.io.Serializable;

public class KHOAHOC implements Serializable {
    public int MaKhoaHoc;
    public int MaLoai;
    public int MaDM;
    public String TenLoai;
    public String TenDanhMuc;
    public String TenKhoaHoc;
    public String DonGia;
    public int SoLuongMua;
    public String TrangThai;
    public String HinhAnh;
    public int MaGV;
    public int DanhGia;
    public String TenGV;
    public String MoTaKhoaHoc;
    public String NgayTao;
    public String NgayDuyet;

    public int getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(int danhGia) {
        DanhGia = danhGia;
    }



    public KHOAHOC(int maKhoaHoc, int maLoai, int maDM, String tenLoai, String tenDM,
                   String tenKhoaHoc,
                   String donGia,
//                   int soLuongMua,
                   String trangThai,
                   String hinhAnh, int maGV, String TenGV, int danhgia, String moTaKhoaHoc, String ngayTao, String ngayDuyet) {
        MaKhoaHoc = maKhoaHoc;
        MaLoai = maLoai;
        MaDM = maDM;
        TenLoai = tenLoai;
        TenDanhMuc = tenDM;
        TenKhoaHoc = tenKhoaHoc;
        DonGia = donGia;
//        SoLuongMua = soLuongMua;
        TrangThai = trangThai;
        HinhAnh = hinhAnh;
        MaGV = maGV;
        DanhGia = danhgia;
        this.TenGV = TenGV;
        MoTaKhoaHoc = moTaKhoaHoc;
        NgayTao = ngayTao;
        NgayDuyet = ngayDuyet;
    }

    public int getMaKhoaHoc() {
        return MaKhoaHoc;
    }

    public void setMaKhoaHoc(int maKhoaHoc) {
        MaKhoaHoc = maKhoaHoc;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getTenKhoaHoc() {
        return TenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        TenKhoaHoc = tenKhoaHoc;
    }

    public String getDonGia() {
        return DonGia;
    }

    public void setDonGia(String donGia) {
        DonGia = donGia;
    }

//    public int getSoLuongMua() {
//        return SoLuongMua;
//    }
//
//    public void setSoLuongMua(int soLuongMua) {
//        SoLuongMua = soLuongMua;
//    }



    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public int getMaGV() {
        return MaGV;
    }

    public void setMaGV(int maGV) {
        MaGV = maGV;
    }

    public String getMoTaKhoaHoc() {
        return MoTaKhoaHoc;
    }

    public void setMoTaKhoaHoc(String moTaKhoaHoc) {
        MoTaKhoaHoc = moTaKhoaHoc;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String ngayTao) {
        NgayTao = ngayTao;
    }

    public String getNgayDuyet() {
        return NgayDuyet;
    }

    public void setNgayDuyet(String ngayDuyet) {
        NgayDuyet = ngayDuyet;
    }

    public String getTenGV() {
        return TenGV;
    }

    public void setTenGV(String tenGV) {
        TenGV = tenGV;
    }

}
