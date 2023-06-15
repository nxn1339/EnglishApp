package com.example.appenglish.Model;

import java.util.ArrayList;

public class User {
    private int id_user;
    private String user_name;

    private String full_name;

    public int getID() {
        return id_user;
    }

    public void setID(int id_user) {
        this.id_user = id_user;
    }

    private String password;
    private String img_avatar;
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

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getImg_avatar() {
        return img_avatar;
    }

    public void setImg_avatar(String img_avatar) {
        this.img_avatar = img_avatar;
    }

    public User(int id_user, String user_name, String full_name, String password, String img_avatar, String role) {
        this.id_user = id_user;
        this.user_name = user_name;
        this.full_name = full_name;
        this.password = password;
        this.img_avatar = img_avatar;
        this.role = role;
    }
}
