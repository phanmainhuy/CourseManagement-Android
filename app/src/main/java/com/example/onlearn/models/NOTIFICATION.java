package com.example.onlearn.models;

import java.io.Serializable;

public class NOTIFICATION implements Serializable {
    public int ImgLogo;
    public String Title;
    public String Content;
    public String Date;

    public NOTIFICATION(int imglogo, String title, String content, String date) {
        ImgLogo = imglogo;
        Title = title;
        Content = content;
        Date = date;
    }

    public int getImgLogo() {
        return ImgLogo;
    }

    public void setImgLogo(int imgLogo) {
        this.ImgLogo = imgLogo;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }
}
