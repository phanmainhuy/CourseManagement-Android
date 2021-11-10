package Model;

import java.io.Serializable;

public class DANHMUCKHOAHOC implements Serializable {
    public int MaDanhMuc;
    public String TenDanhMuc;
    public String HinhAnh;

    public DANHMUCKHOAHOC(int maDanhMuc, String tenDanhMuc, String hinhAnh) {
        MaDanhMuc = maDanhMuc;
        TenDanhMuc = tenDanhMuc;
        HinhAnh = hinhAnh;
    }

    public int getMaDanhMuc() {
        return MaDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        MaDanhMuc = maDanhMuc;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        TenDanhMuc = tenDanhMuc;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
