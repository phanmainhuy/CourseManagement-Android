package com.example.onlearn.Model;

import java.io.Serializable;

public class KHOAHOC implements Serializable {
    public int MaKhoaHoc;
    public int MaLoai;
    public String TenKhoaHoc;
    public String DonGia;
    public int SoLuongMua;
    public String ThoiHanHoanTien;
    public Boolean TrangThai;
    public String HinhAnh;
    public int MaGV;
    public String MoTaKhoaHoc;
    public String NgayTao;
    public String NgayDuyet;

    public KHOAHOC(int maKhoaHoc, int maLoai, String tenKhoaHoc, String donGia, int soLuongMua, String thoiHanHoanTien, Boolean trangThai, String hinhAnh, int maGV, String moTaKhoaHoc, String ngayTao, String ngayDuyet) {
        MaKhoaHoc = maKhoaHoc;
        MaLoai = maLoai;
        TenKhoaHoc = tenKhoaHoc;
        DonGia = donGia;
        SoLuongMua = soLuongMua;
        ThoiHanHoanTien = thoiHanHoanTien;
        TrangThai = trangThai;
        HinhAnh = hinhAnh;
        MaGV = maGV;
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

    public int getSoLuongMua() {
        return SoLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        SoLuongMua = soLuongMua;
    }

    public String getThoiHanHoanTien() {
        return ThoiHanHoanTien;
    }

    public void setThoiHanHoanTien(String thoiHanHoanTien) {
        ThoiHanHoanTien = thoiHanHoanTien;
    }

    public Boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Boolean trangThai) {
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
}