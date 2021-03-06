package com.namphan.androidduan1.model;

public class Cart {
    private String maMon;
    private String tenMon;
    private String moTa;
    private int gia;

    public Cart() {
    }

    public Cart(String maMon, String tenMon, String moTa, int gia) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.moTa = moTa;
        this.gia = gia;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
