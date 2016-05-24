package com.retropoktan.rptrello.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RetroPoktan on 4/21/16.
 */
public class Label implements Parcelable {

    public static final Creator<Label> CREATOR = new Creator<Label>() {
        @Override
        public Label createFromParcel(Parcel source) {
            return new Label(source);
        }

        @Override
        public Label[] newArray(int size) {
            return new Label[size];
        }
    };
    private String name;
    private String color;

    public Label() {

    }

    public Label(Parcel in) {
        name = in.readString();
        color = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(color);
    }
}
