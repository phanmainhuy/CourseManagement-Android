package com.example.onlearn.models;

import java.io.Serializable;

public class AVATAR implements Serializable {
    public String nameAva;
    public String avatar;

    public AVATAR(String nameAva, String avatar) {
        this.nameAva = nameAva;
        this.avatar = avatar;
    }

    public String getNameAva() {
        return nameAva;
    }

    public void setNameAva(String nameAva) {
        this.nameAva = nameAva;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
