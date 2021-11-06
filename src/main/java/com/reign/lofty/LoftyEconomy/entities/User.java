package com.reign.lofty.LoftyEconomy.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private String uid;
    private String name;
    private String profilephoto;
    private String email;
    private double currentMonthAmount;
    private double previousMonthAmount;

    private List<Record> records = new ArrayList<>();

    public User() {}

    public User(String uid, String profilephoto, String name, String email, double currentMonthAmount, double previousMonthAmount) {
        this.uid = uid;
        this.profilephoto = profilephoto;
        this.name = name;
        this.email = email;
        this.currentMonthAmount = currentMonthAmount;
        this.previousMonthAmount = previousMonthAmount;
    }

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Record> getRecords() {
        return records;
    }
    public String getProfilephoto() {
        return profilephoto;
    }
    public void setProfilephoto(String profilephoto) {
        this.profilephoto = profilephoto;
    }
    public double getCurrentMonthAmount() {
        return currentMonthAmount;
    }
    public void setCurrentMonthAmount(double currentMonthAmount) {
        this.currentMonthAmount = currentMonthAmount;
    }
    public double getPreviousMonthAmount() {
        return previousMonthAmount;
    }
    public void setPreviousMonthAmount(double previousMonthAmount) {
        this.previousMonthAmount = previousMonthAmount;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "name: %s\nPhoto: %s\nEmail: %s\nUID: %s\n%f\n%f\nrecords: " + records,
                this.getName(),
                this.profilephoto,
                this.getEmail(),
                this.getUid(),
                this.currentMonthAmount,
                this.previousMonthAmount
        );
    }
}