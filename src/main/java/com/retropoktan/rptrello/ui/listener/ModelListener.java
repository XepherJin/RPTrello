package com.retropoktan.rptrello.ui.listener;

import java.util.List;

/**
 * Created by RetroPoktan on 1/9/16.
 */
public abstract class ModelListener<T> {

    public void onSuccess(String data) {

    }
    public void onSuccess(T t) {

    }

    public void onSuccess(List<T> t) {

    }

    public void onFail() {

    }

}
