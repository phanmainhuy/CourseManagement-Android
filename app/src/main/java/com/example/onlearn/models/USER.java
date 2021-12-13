package com.example.onlearn.models;

import java.io.Serializable;

public class USER implements Serializable {
    public int MaND;
    public String UserName;
    public String Ten;
    public String Email;
    public String Birthday;
    public String Gender;
    public String Address;
    public String Number;
    public String ImgUser;
//    public String CMND;
    public int DiemTichLuy;

    public USER(int maND, String userName, String name, String email, String birthday, String gender, String address, String number, String imgUser, int diemTichLuy) {
        MaND = maND;
        UserName = userName;
        Ten = name;
        Email = email;
        Birthday = birthday;
        Gender = gender;
        Address = address;
        Number = number;
        ImgUser = imgUser;
//        this.CMND = CMND;
        DiemTichLuy = diemTichLuy;
    }



    public int getMaND() {
        return MaND;
    }

    public void setMaND(int maND) {
        MaND = maND;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getImgUser() {
        return ImgUser;
    }

    public void setImgUser(String imgUser) {
        ImgUser = imgUser;
    }

//    public String getCMND() {
//        return CMND;
//    }
//
//    public void setCMND(String CMND) {
//        this.CMND = CMND;
//    }

    public int getDiemTichLuy() {
        return DiemTichLuy;
    }

    public void setDiemTichLuy(int diemTichLuy) {
        DiemTichLuy = diemTichLuy;
    }




}
