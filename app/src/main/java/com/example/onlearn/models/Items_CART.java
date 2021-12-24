package com.example.onlearn.models;

import java.io.Serializable;

public class Items_CART implements Serializable {
    public int CourseID;
    public String CourseName;
    public String OriginPrice;
    public String AfterPrice;
    public int TeacherID;
    public String TeacherName;
    public String imgName;

    public Items_CART(int courseID, String courseName, String originPrice, String afterPrice,
                      int teacherID, String teacherName, String imgName)
    {
        CourseID = courseID;
        CourseName = courseName;
        OriginPrice = originPrice;
        AfterPrice = afterPrice;
        TeacherID = teacherID;
        TeacherName = teacherName;
        this.imgName = imgName;
    }



    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int courseID) {
        CourseID = courseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getOriginPrice() {
        return OriginPrice;
    }

    public void setOriginPrice(String originPrice) {
        OriginPrice = originPrice;
    }

    public String getAfterPrice() {
        return AfterPrice;
    }

    public void setAfterPrice(String afterPrice) {
        AfterPrice = afterPrice;
    }

    public int getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(int teacherID) {
        TeacherID = teacherID;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
