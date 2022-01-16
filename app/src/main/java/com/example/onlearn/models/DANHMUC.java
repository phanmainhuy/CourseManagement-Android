package com.example.onlearn.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DANHMUC implements Serializable {
    int MaDanhMuc;
    String TenDanhMuc;
    String HinhAnh;
    int TongSoKhoaHoc;
    String HienThi;



    public DANHMUC(int maDanhMuc, String tenDanhMuc, String hinhAnh, int tongSoKhoaHoc) {
        MaDanhMuc = maDanhMuc;
        TenDanhMuc = tenDanhMuc;
        HinhAnh = hinhAnh;
        TongSoKhoaHoc = tongSoKhoaHoc;
    }

    public int getMaDanhMuc() {
        return MaDanhMuc;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }
    public String getHienThi() {
        return HienThi;
    }

    public void setHienThi(String hienThi) {
        HienThi = hienThi;
    }
    public int getTongSoKhoaHoc() {
        return TongSoKhoaHoc;
    }

//    @SerializedName("DanhSachTheLoai")
//    @Expose
//    private List<THELOAI> danhMucConList;
//
//    public List<THELOAI> getDanhMucConList() {
//        return danhMucConList;
//    }
}
