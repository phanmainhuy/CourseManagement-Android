package Model;

import java.io.Serializable;

public class USER implements Serializable {
    public int MaND;
    public int MaNhom;
    public String TenDN;
    public String MatKhau;
    public String HoTen;
    public String CMND;
    public String HinhAnh;
    public String SDT;
    public String Email;
    public String NgaySinh;
    public String DiaChi;

    public USER(int maND, int maNhom, String tenDN, String matKhau, String hoTen, String CMND, String hinhAnh, String SDT, String email, String ngaySinh, String diaChi) {
        MaND = maND;
        MaNhom = maNhom;
        TenDN = tenDN;
        MatKhau = matKhau;
        HoTen = hoTen;
        this.CMND = CMND;
        HinhAnh = hinhAnh;
        this.SDT = SDT;
        Email = email;
        NgaySinh = ngaySinh;
        DiaChi = diaChi;
    }

    public int getMaND() {
        return MaND;
    }

    public void setMaND(int maND) {
        MaND = maND;
    }

    public int getMaNhom() {
        return MaNhom;
    }

    public void setMaNhom(int maNhom) {
        MaNhom = maNhom;
    }

    public String getTenDN() {
        return TenDN;
    }

    public void setTenDN(String tenDN) {
        TenDN = tenDN;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }
}
