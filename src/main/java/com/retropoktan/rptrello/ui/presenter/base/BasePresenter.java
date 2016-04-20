package com.retropoktan.rptrello.ui.presenter.base;

import com.retropoktan.rptrello.model.entity.Msg;
import com.retropoktan.rptrello.ui.view.IView;

import rx.Subscriber;
import rx.Subscription;
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

    protected void showLoading() {
        mView.showLoading();
    }

    protected void showLoadingError(CharSequence errMsg) {
        mView.showLoadingError(errMsg);
    }

    protected void hideLoading() {
        mView.hideLoading();
    }

    protected void addSubscription(Subscription subscription) {
        if (mSubscription != null) {
            mSubscription.add(subscription);
        }
    }

    protected void removeSubscription(Subscription subscription) {
        if (mSubscription != null) {
            mSubscription.remove(subscription);
        }
    }

    protected abstract class DefaultSubscriber<T extends Msg> extends Subscriber<T> {

        @Override
        public void onCompleted() {
            removeSubscription(this);
            mView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            removeSubscription(this);
            mView.hideLoading();
            parseError(e);
        }

        protected void parseError(Throwable e) {
            mView.showLoadingError("加载失败");
        }

        @Override
        public void onNext(T t) {
            parseMsg(t);
        }

        protected abstract void parseMsg(T t);
    }

}
