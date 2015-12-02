package com.mohammad.mojapplication.Objects;

/**
 * Created by user on 11/1/2015.
 */
public class User {

    private String id;
    private String name;
    private String mobile;
    private String address;
    private String userName;
    private String pass;
    private String servicePass;

    public User(String id, String name, String mobile, String address, String userName, String pass,String servicePass) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.userName = userName;
        this.pass = pass;
        this.servicePass = servicePass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getServicePass() {
        return servicePass;
    }

    public void setServicePass(String servicePass) {
        this.servicePass = servicePass;
    }
}
