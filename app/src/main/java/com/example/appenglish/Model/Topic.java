package com.example.appenglish.Model;

import java.util.ArrayList;

public class Topic {
    public static ArrayList<Topic> topics=new ArrayList<>();
    private int id_topic;
    private String title;
    private int point;
    private String img;

    public Topic() {

    }

    public int getId_topic() {
        return id_topic;
    }

    public void setId_topic(int id_topic) {
        this.id_topic = id_topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Topic(int id_topic, String title, int point, String img) {
        this.id_topic = id_topic;
        this.title = title;
        this.point = point;
        this.img = img;
    }
}
