package com.example.appenglish.Model;

import androidx.annotation.NonNull;

public class PlayScreen {
    private  int ID;

    private int lvId;
    private String title;
    private String point;





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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getLvId() {
        return lvId;
    }

    public void setLvId(int lvId) {
        this.lvId = lvId;
    }

    public PlayScreen(int id, int lvId, String title, String point) {
        ID = id;
        this.lvId = lvId;

        this.title = title;
        this.point = point;

    }


    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
