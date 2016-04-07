package com.retropoktan.rptrello.ui.presenter.base;

import com.retropoktan.rptrello.ui.view.IAdapterView;

/**
 * Created by RetroPoktan on 4/4/16.
 */
public abstract class PtrBasePresenter<T extends IAdapterView> extends BasePresenter<T> {

    private int curPage;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int page) {
        curPage = page;
    }

    protected void showRetry() {
        mView.showRetry();
    }

    protected void hideRetry() {
        mView.hideRetry();
    }

    protected void showEmpty() {
        mView.showEmpty();
    }

    protected void hideEmpty() {
        mView.hideEmpty();
    }

    protected abstract void loadMore();

    protected abstract void refresh(boolean isNew);
}
