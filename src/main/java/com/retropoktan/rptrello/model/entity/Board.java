package com.retropoktan.rptrello.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RetroPoktan on 4/20/16.
 */
public class Board {

    private long id;
    private String name;
    private String description;
    @SerializedName("belong_team")
    private String teamName;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
