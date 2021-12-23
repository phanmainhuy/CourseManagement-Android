package com.example.onlearn.models;

public class THELOAI {
    int MaTheLoai;
    String TenTheLoai;
    String HienThi;

    public THELOAI(int maTheLoai, String tenTheLoai, String hienThi) {
        MaTheLoai = maTheLoai;
        TenTheLoai = tenTheLoai;
        HienThi = hienThi;
    }
    public String getHienThi() {
        return HienThi;
    }

    public void setHienThi(String hienThi) {
        HienThi = hienThi;
    }



    public int getMaTheLoai() {
        return MaTheLoai;
    }

    public String getTenTheLoai() {
        return TenTheLoai;
    }

}
