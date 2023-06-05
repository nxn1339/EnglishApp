package com.example.appenglish.Model;

import java.util.ArrayList;

public class Topic {
    public static ArrayList<Topic> topics=new ArrayList<>();
    private int id_topic;
    private String title;
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



    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Topic(int id_topic, String title, String img) {
        this.id_topic = id_topic;
        this.title = title;
        this.img = img;
    }
}
