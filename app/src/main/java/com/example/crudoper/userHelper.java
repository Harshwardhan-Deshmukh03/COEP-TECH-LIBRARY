package com.example.crudoper;

public class userHelper {
    String name,mis,password,oneName,oneSerial,oneIssue,oneDue,twoName,twoSerial,twoIssue,towDue,fine;

    public userHelper() {
    }

    public userHelper(String name, String mis, String password, String oneName, String oneSerial, String oneIssue, String oneDue, String twoName, String twoSerial, String twoIssue, String towDue,String fine) {
        this.name = name;
        this.mis = mis;
        this.password = password;
        this.oneName = oneName;
        this.oneSerial = oneSerial;
        this.oneIssue = oneIssue;
        this.oneDue = oneDue;
        this.twoName = twoName;
        this.twoSerial = twoSerial;
        this.twoIssue = twoIssue;
        this.towDue = towDue;
        this.fine=fine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMis() {
        return mis;
    }

    public void setMis(String mis) {
        this.mis = mis;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOneName() {
        return oneName;
    }

    public void setOneName(String oneName) {
        this.oneName = oneName;
    }

    public String getOneSerial() {
        return oneSerial;
    }

    public void setOneSerial(String oneSerial) {
        this.oneSerial = oneSerial;
    }

    public String getOneIssue() {
        return oneIssue;
    }

    public void setOneIssue(String oneIssue) {
        this.oneIssue = oneIssue;
    }

    public String getOneDue() {
        return oneDue;
    }

    public void setOneDue(String oneDue) {
        this.oneDue = oneDue;
    }

    public String getTwoName() {
        return twoName;
    }

    public void setTwoName(String twoName) {
        this.twoName = twoName;
    }

    public String getTwoSerial() {
        return twoSerial;
    }

    public void setTwoSerial(String twoSerial) {
        this.twoSerial = twoSerial;
    }

    public String getTwoIssue() {
        return twoIssue;
    }

    public void setTwoIssue(String twoIssue) {
        this.twoIssue = twoIssue;
    }

    public String getTowDue() {
        return towDue;
    }

    public void setTowDue(String towDue) {
        this.towDue = towDue;
    }
    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }
}
