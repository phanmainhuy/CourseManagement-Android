package com.example.onlearn.models;

import java.io.Serializable;

public class PAY_ReceiptOrder implements Serializable {
    public String HoTen;
    public String Email;
    public String DiaChi;
    public String SDT;

    public PAY_ReceiptOrder(String hoTen, String email, String diaChi, String SDT) {
        HoTen = hoTen;
        Email = email;
        DiaChi = diaChi;
        this.SDT = SDT;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
}
