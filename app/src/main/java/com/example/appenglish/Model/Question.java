package com.example.appenglish.Model;

import java.util.ArrayList;

public class Question {
    public static ArrayList<Question> questions=new ArrayList<>();
    private int id_question;
    private int id_topic;
    private String question;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Question(){

    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public int getId_topic() {
        return id_topic;
    }

    public void setId_topic(int id_topic) {
        this.id_topic = id_topic;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Question(int id_question, int id_topic, String question, int type) {
        this.id_question = id_question;
        this.id_topic = id_topic;
        this.question = question;
        this.type = type;
    }
}
