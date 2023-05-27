package com.example.appenglish.Model;

public class PlayScreen {
    private  String ID;
    private int lvId;
    private String title;
    private String point;



    public int getLvId() {
        return lvId;
    }

    public void setLvId(int lvId) {
        this.lvId = lvId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public PlayScreen(String id, int lvId, String title, String point) {
        ID = id;

        this.lvId = lvId;
        this.title = title;
        this.point = point;
    }
}
