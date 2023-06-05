package com.example.appenglish.Model;

import java.util.ArrayList;

public class User {
    private int id_user;
    private String user_name;

    public int getID() {
        return id_user;
    }

    public void setID(int id_user) {
        this.id_user = id_user;
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

    public User(int id_user, String user_name, String password, String lv, String role) {
        this.id_user = id_user;
        this.user_name = user_name;
        this.password = password;
        this.lv = lv;
        this.role = role;
    }
}
