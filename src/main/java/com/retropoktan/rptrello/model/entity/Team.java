package com.retropoktan.rptrello.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RetroPoktan on 4/20/16.
 */
public class Team {

    public static final String TAG = "team";

    private long id;
    private String description;
    private String name;
    private String pic;
    @SerializedName("create_date")
    private String createTime;
    private List<Member> manager;
    private Member owner;
    private List<Member> member;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<Member> getManager() {
        return manager;
    }

    public void setManager(List<Member> manager) {
        this.manager = manager;
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public List<Member> getMember() {
        return member;
    }

    public void setMember(List<Member> member) {
        this.member = member;
    }
}
