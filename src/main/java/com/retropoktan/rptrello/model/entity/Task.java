package com.retropoktan.rptrello.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RetroPoktan on 4/21/16.
 */
public class Task {

    private long id;
    private String name;
    @SerializedName("belong_list")
    private Card card;
    @SerializedName("belong_project")
    private Board board;
    private String description;
    private List<Member> worker;
    @SerializedName("created_date")
    private String createTime;
    private String deadline;
    @SerializedName("update_date")
    private String updateTime;
    @SerializedName("check_item")
    private List<CheckItem> checkItemList;
    private List<Label> labels;

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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Member> getWorker() {
        return worker;
    }

    public void setWorker(List<Member> worker) {
        this.worker = worker;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<CheckItem> getCheckItemList() {
        return checkItemList;
    }

    public void setCheckItemList(List<CheckItem> checkItemList) {
        this.checkItemList = checkItemList;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }
}
