package com.example.appenglish.Model;

import java.util.ArrayList;

public class Answer {
    public static ArrayList<Answer> answers=new ArrayList<>();
    private int id_answer;
    private int id_question;
    private int id_topic;

    public int getId_topic() {
        return id_topic;
    }

    public void setId_topic(int id_topic) {
        this.id_topic = id_topic;
    }

    private String answer;

    private int correct;

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public Answer(){

    }

    public int getId_answer() {
        return id_answer;
    }

    public void setId_answer(int id_answer) {
        this.id_answer = id_answer;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Answer(int id_answer, int id_question, int id_topic, String answer, int correct) {
        this.id_answer = id_answer;
        this.id_question = id_question;
        this.id_topic = id_topic;
        this.answer = answer;
        this.correct = correct;
    }
}
