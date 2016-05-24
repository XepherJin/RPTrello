package com.retropoktan.rptrello.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RetroPoktan on 4/20/16.
 */
public class Team implements Parcelable {

    public static final String TAG = "team";
    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel source) {
            return new Team(source);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
    private long id;
    private String description;
    private String name;
    private String pic;
    @SerializedName("create_date")
    private String createTime;
    private List<Member> manager = new ArrayList<>();
    private Member owner;
    private List<Member> member = new ArrayList<>();

    public Team() {

    }

    public Team(Parcel in) {
        id = in.readLong();
        description = in.readString();
        name = in.readString();
        pic = in.readString();
        createTime = in.readString();
        in.readTypedList(manager, Member.CREATOR);
        owner = in.readParcelable(Member.class.getClassLoader());
        in.readTypedList(member, Member.CREATOR);
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(description);
        dest.writeString(name);
        dest.writeString(pic);
        dest.writeString(createTime);
        dest.writeTypedList(manager);
        dest.writeParcelable(owner, flags);
        dest.writeTypedList(member);
    }
}
