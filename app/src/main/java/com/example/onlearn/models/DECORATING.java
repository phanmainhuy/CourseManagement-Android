package com.example.onlearn.models;

import java.io.Serializable;

public class DECORATING implements Serializable {
    public int MaTrangTri;
    public String GiaTri;
    public int MaLoaiTrangTri;

    public DECORATING(int maTrangTri, String giaTri, int maLoaiTrangTri) {
        MaTrangTri = maTrangTri;
        GiaTri = giaTri;
        MaLoaiTrangTri = maLoaiTrangTri;
    }

    public int getMaTrangTri() {
        return MaTrangTri;
    }

    public void setMaTrangTri(int maTrangTri) {
        MaTrangTri = maTrangTri;
    }

    public String getGiaTri() {
        return GiaTri;
    }

    public void setGiaTri(String giaTri) {
        GiaTri = giaTri;
    }

    public int getMaLoaiTrangTri() {
        return MaLoaiTrangTri;
    }

    public void setMaLoaiTrangTri(int maLoaiTrangTri) {
        MaLoaiTrangTri = maLoaiTrangTri;
    }
}
