package com.mohammad.mojapplication.Objects;

import java.util.Date;

/**
 * Created by user on 11/1/2015.
 */
public class NIDCard {

    private String id;
    private String name;
    private String mobile;
    private String address;
    private Date dob;

    public NIDCard(String id, String name, String mobile, String address, Date dob) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.dob = dob;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
