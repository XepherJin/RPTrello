package com.retropoktan.rptrello.ui.presenter.base;

/**
 * Created by RetroPoktan on 2/10/16.
 */
public interface IPresenter<T> {
    void attachView(T view);
    void onDestroy();
}
