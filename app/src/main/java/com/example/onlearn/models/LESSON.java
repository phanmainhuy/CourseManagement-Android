package com.example.onlearn.models;

import java.io.Serializable;

public class LESSON implements Serializable {
    public int MaBaiHoc;
    public String TenBaiHoc;
    public int MaChuong;
    public String TenChuong;
    public String Video;

    public LESSON(int maBaiHoc, String tenBaiHoc, int maChuong, String tenChuong, String video) {
        MaBaiHoc = maBaiHoc;
        TenBaiHoc = tenBaiHoc;
        MaChuong = maChuong;
        TenChuong = tenChuong;
        Video = video;
    }

    public int getMaBaiHoc() {
        return MaBaiHoc;
    }

    public void setMaBaiHoc(int maBaiHoc) {
        MaBaiHoc = maBaiHoc;
    }

    public String getTenBaiHoc() {
        return TenBaiHoc;
    }

    public void setTenBaiHoc(String tenBaiHoc) {
        TenBaiHoc = tenBaiHoc;
    }

    public int getMaChuong() {
        return MaChuong;
    }

    public void setMaChuong(int maChuong) {
        MaChuong = maChuong;
    }

    public String getTenChuong() {
        return TenChuong;
    }

    public void setTenChuong(String tenChuong) {
        TenChuong = tenChuong;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String video) {
        Video = video;
    }
}
