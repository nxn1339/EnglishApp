package com.example.appenglish.Model;

import java.util.ArrayList;

public class User {
    private int ID;
    private String user_name;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private String password;
    private String lv;
    public static ArrayList<User> users=new ArrayList<>();

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String role;

    public User() {

    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public User(int id, String user_name, String password, String lv, String role) {
        ID = id;
        this.user_name = user_name;
        this.password = password;
        this.lv = lv;
        this.role = role;
    }
}
