package com.retropoktan.rptrello.model.req;

/**
 * Created by RetroPoktan on 4/20/16.
 */
public class UserLoginReq {

    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
