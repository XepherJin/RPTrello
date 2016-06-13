package com.retropoktan.rptrello.ui.presenter;

import com.retropoktan.rptrello.model.CardModel;
import com.retropoktan.rptrello.model.entity.Card;
import com.retropoktan.rptrello.model.entity.Msg;
import com.retropoktan.rptrello.ui.presenter.base.BasePresenter;
import com.retropoktan.rptrello.ui.view.IBoardDetailView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by RetroPoktan on 5/21/16.
 */
public class BoardDetailPresenter extends BasePresenter<IBoardDetailView> {

    private final CardModel mCardModel;

    @Inject
    public BoardDetailPresenter(CardModel cardModel) {
        mCardModel = cardModel;
    }

    public void getBoardDetail() {
        Subscription subscription = mCardModel.getBoardCards(new DefaultSubscriber<Msg<List<Card>>>() {
            @Override
            protected void parseMsg(Msg<List<Card>> listMsg) {
                if (listMsg.isResultOK()) {
                    List<Card> list = listMsg.getData();
                    if (list != null) {
                        mCardModel.saveCardsForBoard(list);
                        mView.setTabs(list);
                        return;
                    }
                    mView.showEmpty();
                    return;
                }
                mView.showLoadingError(listMsg.getMsg());
            }

            @Override
            protected void readCache() {
                List<Card> list = mCardModel.getCachedCards();
                mView.setTabs(list);
            }
        });
        addSubscription(subscription);
    }

    public void showMoreMenu() {
        mView.showMoreMenu();
    }

}
