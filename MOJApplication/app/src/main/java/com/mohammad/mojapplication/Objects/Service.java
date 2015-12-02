package com.mohammad.mojapplication.Objects;

import java.util.Date;
import java.util.UUID;

/**
 * Created by user on 11/1/2015.
 */
public class Service {

    private String userID;
    private String type;
    private String serviceID;
    private Date date;
    private String serviceStatus;
    private String partyID1;
    private String partyID2;
    private String loc;

    public Service(String userID, String type, String serviceID, Date date,String serviceStatus,String partyID1,String partyID2,String loc) {
        this.userID = userID;
        this.type = type;
        this.serviceID = serviceID;
        this.date = date;
        this.serviceStatus = serviceStatus;
        this.partyID1 = partyID1;
        this.partyID2 = partyID2;
        this.loc = loc;

    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getPartyID1() {
        return partyID1;
    }

    public void setPartyID1(String partyID1) {
        this.partyID1 = partyID1;
    }

    public String getPartyID2() {
        return partyID2;
    }

    public void setPartyID2(String partyID2) {
        this.partyID2 = partyID2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}
