package com.retropoktan.rptrello.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RetroPoktan on 4/21/16.
 */
public class Card implements Parcelable {

    public static final String TAG = "card";
    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel source) {
            return new Card(source);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
    private long id;
    private String name;
    @SerializedName("belong_project")
    private long boardId;

    public Card() {

    }

    public Card(Parcel in) {
        id = in.readLong();
        name = in.readString();
        boardId = in.readLong();
    }

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

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeLong(boardId);
    }
}
