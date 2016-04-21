package com.retropoktan.rptrello.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RetroPoktan on 4/21/16.
 */
public class CheckItem {

    private String name;
    @SerializedName("belong_mission")
    private long taskId;
    @SerializedName("created_date")
    private String createTime;
    private int status = -1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isFinished() {
        return status == 1;
    }
}
