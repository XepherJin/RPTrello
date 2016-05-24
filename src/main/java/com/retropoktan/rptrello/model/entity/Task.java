package com.retropoktan.rptrello.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RetroPoktan on 4/21/16.
 */
public class Task implements Parcelable {

    public static final String TAG = "task";
    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
    private long id;
    private String name;
    @SerializedName("belong_list")
    private Card card;
    @SerializedName("belong_project")
    private Board board;
    private String description;
    private List<Member> worker = new ArrayList<>();
    @SerializedName("created_date")
    private String createTime;
    private String deadline;
    @SerializedName("update_date")
    private String updateTime;
    @SerializedName("check_item")
    private List<CheckItem> checkItemList = new ArrayList<>();
    private List<Label> labels = new ArrayList<>();

    public Task() {

    }

    public Task(Parcel in) {
        id = in.readLong();
        name = in.readString();
        card = in.readParcelable(Card.class.getClassLoader());
        board = in.readParcelable(Board.class.getClassLoader());
        description = in.readString();
        in.readTypedList(worker, Member.CREATOR);
        createTime = in.readString();
        deadline = in.readString();
        updateTime = in.readString();
        in.readTypedList(checkItemList, CheckItem.CREATOR);
        in.readTypedList(labels, Label.CREATOR);
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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Member> getWorker() {
        return worker;
    }

    public void setWorker(List<Member> worker) {
        this.worker = worker;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<CheckItem> getCheckItemList() {
        return checkItemList;
    }

    public void setCheckItemList(List<CheckItem> checkItemList) {
        this.checkItemList = checkItemList;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeParcelable(card, flags);
        dest.writeParcelable(board, flags);
        dest.writeString(description);
        dest.writeTypedList(worker);
        dest.writeString(createTime);
        dest.writeString(deadline);
        dest.writeString(updateTime);
        dest.writeTypedList(checkItemList);
        dest.writeTypedList(labels);
    }
}
