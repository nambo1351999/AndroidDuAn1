package com.namphan.androidduan1.model;

public class User {


    private String email;
    private String fistname;
    private String lastname;
    private String password;
    private String phone;

    public User(String email, String fistname, String lastname, String password, String phone) {
        this.email = email;
        this.fistname = fistname;
        this.lastname = lastname;
        this.password = password;
        this.phone = phone;
    }

    public User() {
        this.email = "nambo@gmail.com";
        this.fistname = "nam";
        this.lastname = "Phan";
        this.password = "123456789";
        this.phone = "0869981562";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFistname() {
        return fistname;
    }

    public void setFistname(String fistname) {
        this.fistname = fistname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}