package com.retropoktan.rptrello.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RetroPoktan on 4/21/16.
 */
public class Card {

    private long id;
    private String name;
    @SerializedName("belong_project")
    private String boardName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
}
