package com.example.appenglish.Model;

import java.util.ArrayList;

public class UserTopic {
    public static ArrayList<UserTopic> userTopics=new ArrayList<>();
    public static ArrayList<UserTopic> coutPoint=new ArrayList<>();
    private int id_user_topic;
    private int id_user;
    private int id_topic;
    private int point;

    public UserTopic() {

    }

    public int getId_user_topic() {
        return id_user_topic;
    }

    public void setId_user_topic(int id_user_topic) {
        this.id_user_topic = id_user_topic;
    }

    public int getId_user() {
        return id_user;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_topic() {
        return id_topic;
    }

    public void setId_topic(int id_topic) {
        this.id_topic = id_topic;
    }

    public UserTopic(int id_user_topic, int id_user, int id_topic, int point) {
        this.id_user_topic = id_user_topic;
        this.id_user = id_user;
        this.id_topic = id_topic;
        this.point = point;
    }
}
