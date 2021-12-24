package com.example.onlearn.models;

import java.io.Serializable;

public class CART implements Serializable {
    public int CartID;
    public int UserID;
    public String TongTien;

    public CART(int cartID, int userID, String tongTien) {
        CartID = cartID;
        UserID = userID;
        TongTien = tongTien;
    }

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int cartID) {
        CartID = cartID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getTongTien() {
        return TongTien;
    }

    public void setTongTien(String tongTien) {
        TongTien = tongTien;
    }
}
