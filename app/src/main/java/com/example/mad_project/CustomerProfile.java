package com.example.mad_project;

public class CustomerProfile {


    private static String fName;
    private static String fEmail;
    private static String fMobile;
    private static String fAddress;
    private static String fNic;
    private static String fPassword;

    public CustomerProfile(String fName, String fEmail, String fMobile, String fAddress, String fNic, String fPassword) {
        this.fName = fName;
        this.fEmail = fEmail;
        this.fMobile = fMobile;
        this.fNic = fNic;
        this.fAddress = fAddress;


        this.fPassword = fPassword;
    }

    public static String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public static String getfEmail() {
        return fEmail;
    }

    public void setfEmail(String fEmail) {
        this.fEmail = fEmail;
    }

    public static String getfMobile() {
        return fMobile;
    }

    public void setfMobile(String fMobile) {
        this.fMobile = fMobile;
    }

    public static String getfAddress() {
        return fAddress;
    }

    public void setfAddress(String fAddress) {
        this.fAddress = fAddress;
    }

    public static String getfNic() {
        return fNic;
    }

    public void setfNic(String fNic) {
        this.fNic = fNic;
    }

    public static String getfPassword() {
        return fPassword;
    }

    public void setfPassword(String fPassword) {
        this.fPassword = fPassword;
    }
}
