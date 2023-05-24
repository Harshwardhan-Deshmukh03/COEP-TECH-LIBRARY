package com.example.crudoper;

public class users {
    String username,name,mis,password;

    public users() {
    }

    public users(String username, String name, String mis, String password) {
        this.username = username;
        this.name = name;
        this.mis = mis;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
