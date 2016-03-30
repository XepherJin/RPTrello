package com.retropoktan.rptrello.ui.presenter;

import com.retropoktan.rptrello.ui.view.IView;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by RetroPoktan on 2/16/16.
 */
public abstract class BasePresenter<T extends IView> implements IPresenter<T> {

    protected T mView;
    protected CompositeSubscription mSubscription;

    @Override
    public void attachView(T view) {
        mView = view;
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void onDestroy() {
        if (mSubscription != null && mSubscription.hasSubscriptions()) {
            mSubscription.unsubscribe();
        }
        mView = null;
    }

}
