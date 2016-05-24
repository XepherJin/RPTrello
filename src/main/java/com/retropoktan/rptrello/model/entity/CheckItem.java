package com.retropoktan.rptrello.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RetroPoktan on 4/21/16.
 */
public class CheckItem implements Parcelable {

    public static final Creator<CheckItem> CREATOR = new Creator<CheckItem>() {
        @Override
        public CheckItem createFromParcel(Parcel source) {
            return new CheckItem(source);
        }

        @Override
        public CheckItem[] newArray(int size) {
            return new CheckItem[size];
        }
    };
    private String name;
    @SerializedName("belong_mission")
    private long taskId;
    @SerializedName("created_date")
    private String createTime;
    private int status = -1;

    public CheckItem() {

    }

    public CheckItem(Parcel in) {
        name = in.readString();
        taskId = in.readLong();
        createTime = in.readString();
        status = in.readInt();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(taskId);
        dest.writeString(createTime);
        dest.writeInt(status);
    }
}
