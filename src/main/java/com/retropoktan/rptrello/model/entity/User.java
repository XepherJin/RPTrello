package com.retropoktan.rptrello.model.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by RetroPoktan on 2/10/16.
 */
public class User extends Member implements Parcelable {

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
    protected String token;

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
