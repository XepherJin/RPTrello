package com.retropoktan.rptrello.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RetroPoktan on 4/20/16.
 */
public class Member implements Parcelable {

    public static final Creator<Member> CREATOR = new Creator<Member>() {
        @Override
        public Member createFromParcel(Parcel source) {
            return new Member(source);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };
    protected long id;
    protected String nick;
    protected String email;
    protected String avatar;

    public Member() {

    }

    public Member(Parcel in) {
        id = in.readLong();
        nick = in.readString();
        email = in.readString();
        avatar = in.readString();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nick);
        dest.writeString(email);
        dest.writeString(avatar);
    }
}
