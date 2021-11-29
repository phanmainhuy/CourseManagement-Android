package com.example.onlearn.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DanhMuc {
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
    private List<DanhMucCon> danhMucConList;

    public List<DanhMucCon> getDanhMucConList() {
        return danhMucConList;
    }
}
