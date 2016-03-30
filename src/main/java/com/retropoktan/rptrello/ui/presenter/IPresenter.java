package com.retropoktan.rptrello.ui.presenter;

/**
 * Created by RetroPoktan on 2/10/16.
 */
public interface IPresenter<T> {
    void attachView(T view);

    void onDestroy();
}
