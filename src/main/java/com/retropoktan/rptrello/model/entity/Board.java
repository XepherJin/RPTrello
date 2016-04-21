package com.retropoktan.rptrello.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RetroPoktan on 4/20/16.
 */
public class Board {

    public static final String TAG = "board";

    private long id;
    private String name;
    private String description;
    @SerializedName("belong_team")
    private long teamId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
