package com.retropoktan.rptrello.model.entity;

/**
 * Created by RetroPoktan on 4/10/16.
 */
public class Msg<T> {
    private int result = -1;
    private T data;
    private String msg;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public boolean isResultOK() {
        return result == 0;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

