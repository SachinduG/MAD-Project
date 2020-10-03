package com.example.mad_project.model;

public class Parks {
    public int id;
    public String email,town,address,mobile,description;

    public Parks(int id, String email, String town, String address, String mobile, String description) {
        this.id = id;
        this.email = email;
        this.town = town;
        this.address = address;
        this.mobile = mobile;
        this.description = description;
    }

    public Parks() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
