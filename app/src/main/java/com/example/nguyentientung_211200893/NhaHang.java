package com.example.nguyentientung_211200893;

import java.io.Serializable;

public class NhaHang implements Serializable {
    private int Id;
    private String Ten;
    private String DiaChi;
    private int SoPhieu;
    private double Diem;

    public NhaHang(int id, String ten, String diaChi, int soPhieu, double diem) {
        Id = id;
        Ten = ten;
        DiaChi = diaChi;
        SoPhieu = soPhieu;
        Diem = diem;
    }

    public int getId() {
        return Id;
    }

    public String getTen() {
        return Ten;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public int getSoPhieu() {
        return SoPhieu;
    }

    public double getDiem() {
        return Diem;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public void setSoPhieu(int soPhieu) {
        SoPhieu = soPhieu;
    }

    public void setDiem(double diem) {
        Diem = diem;
    }
}
