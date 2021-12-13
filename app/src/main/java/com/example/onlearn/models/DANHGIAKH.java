package com.example.onlearn.models;

import java.io.Serializable;

public class DANHGIAKH implements Serializable {
    public int MaKhoaHoc;
    public int MaND;
    public int Diem;
    public String NoiDung;

    public DANHGIAKH(int maKhoaHoc, int maND, int diem, String noiDung) {
        MaKhoaHoc = maKhoaHoc;
        MaND = maND;
        Diem = diem;
        NoiDung = noiDung;
    }

    public int getMaKhoaHoc() {
        return MaKhoaHoc;
    }

    public void setMaKhoaHoc(int maKhoaHoc) {
        MaKhoaHoc = maKhoaHoc;
    }

    public int getMaND() {
        return MaND;
    }

    public void setMaND(int maND) {
        MaND = maND;
    }

    public int getDiem() {
        return Diem;
    }

    public void setDiem(int diem) {
        Diem = diem;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }
}
