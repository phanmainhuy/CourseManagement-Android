package com.example.onlearn.models;

import java.io.Serializable;

public class OPTION implements Serializable {
    public int Image;
    public String Title;

    public OPTION(int image, String title) {
        Image = image;
        Title = title;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }






}
