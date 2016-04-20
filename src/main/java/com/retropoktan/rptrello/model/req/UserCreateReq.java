package com.retropoktan.rptrello.model.req;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RetroPoktan on 4/20/16.
 */
public class UserCreateReq {
    @SerializedName("validate_code")
    private String code;
    private String email;
    private String nick;
    private String password;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
