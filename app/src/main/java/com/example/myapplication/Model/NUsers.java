package com.example.myapplication.Model;

public class NUsers {
    private String Nname,Nphone,Npassword;
    public NUsers()
    {

    }
    public NUsers(String nname,String nphone,String npassword)
    {
        this.Nname=nname;
        this.Nphone=nphone;
        this.Npassword=npassword;
    }

    public String getNname() {
        return this.Nname;
    }

    public void setNname(String nname) {
        this.Nname = nname;
    }

    public String getNphone() {
        return this.Nphone;
    }

    public void setNphone(String nphone) {
        this.Nphone = nphone;
    }

    public String getNpassword() {
        return this.Npassword;
    }

    public void setNpassword(String npassword) {
        this.Npassword = npassword;
    }
}
