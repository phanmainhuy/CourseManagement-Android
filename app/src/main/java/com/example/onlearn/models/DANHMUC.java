package com.example.onlearn.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DANHMUC {
    int MaDanhMuc;
    String TenDanhMuc;
    String HinhAnh;
    int TongSoKhoaHoc;

    public int getMaDanhMuc() {
        return MaDanhMuc;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public int getTongSoKhoaHoc() {
        return TongSoKhoaHoc;
    }

    @SerializedName("DanhSachTheLoai")
    @Expose
    private List<THELOAI> danhMucConList;

    public List<THELOAI> getDanhMucConList() {
        return danhMucConList;
    }
}
