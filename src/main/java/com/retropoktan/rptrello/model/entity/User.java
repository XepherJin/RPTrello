package com.retropoktan.rptrello.model.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by RetroPoktan on 2/10/16.
 */
public class User implements Parcelable {

    public static final String TAG = "user";
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    private long id;
    private String nick;
    private String email;
    private String avatar;
    private String token;

    public User() {

    }

    private User(Parcel in) {
        id = in.readLong();
        nick = in.readString();
        email = in.readString();
        avatar = in.readString();
        token = in.readString();
    }

    public boolean isAvailable() {
        return !TextUtils.isEmpty(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
        dest.writeString(token);
    }
}
