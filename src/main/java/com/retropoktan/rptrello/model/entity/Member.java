package com.retropoktan.rptrello.model.entity;

/**
 * Created by RetroPoktan on 4/20/16.
 */
public class Member {

    protected long id;
    protected String nick;
    protected String email;
    protected String avatar;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
