package com.example.abdelrahman.virtualbank;

/**
 * Created by abdelrahman on 26/01/18.
 */

public class AccInfo {

    private String name;
    private String phone;
    private String EMail;
    private String Pwd;

    public AccInfo(String nm , String phne, String email, String pwd){
        this.name = nm;
        this.phone = phne;
        this.EMail = email;
        this.Pwd = pwd;
    }

    public String getEMail() {
        return EMail;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPwd() {
        return Pwd;
    }

}
