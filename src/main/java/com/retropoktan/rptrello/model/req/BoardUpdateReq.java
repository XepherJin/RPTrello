package com.retropoktan.rptrello.model.req;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RetroPoktan on 5/22/16.
 */
public class BoardUpdateReq {

    @SerializedName("name")
    private String boardName;
    private String description;

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
