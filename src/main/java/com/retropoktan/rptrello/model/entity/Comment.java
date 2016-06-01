package com.retropoktan.rptrello.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RetroPoktan on 6/1/16.
 */
public class Comment {

    private long id;
    private String comment;
    @SerializedName("created_date")
    private String createTime;
    @SerializedName("belong_user")
    private Member member;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
