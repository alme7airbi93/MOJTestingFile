package com.mohammad.mojapplication.Objects;

/**
 * Created by alisa on 12/12/2015.
 */
public class NotaryTemlpate {
    private String notary;
    private String nType;


    public NotaryTemlpate() {
        notary = "";
        nType = "";
    }

    public NotaryTemlpate(String notary, String type) {
        this.notary = notary;
        this.nType = type;
    }

    public String getNotary() {
        return notary;
    }

    public void setNotary(String notary) {
        this.notary = notary;
    }

    public String getType() {
        return nType;
    }

    public void setType(String nType) {
        this.nType = nType;
    }
}
