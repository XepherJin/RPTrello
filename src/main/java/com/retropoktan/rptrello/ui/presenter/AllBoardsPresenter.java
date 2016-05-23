package com.retropoktan.rptrello.ui.presenter;

import com.retropoktan.rptrello.model.BoardModel;
import com.retropoktan.rptrello.model.entity.Board;
import com.retropoktan.rptrello.model.entity.Msg;
import com.retropoktan.rptrello.ui.presenter.base.PtrBasePresenter;
import com.retropoktan.rptrello.ui.view.IAllBoardsView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by RetroPoktan on 4/21/16.
 */
public class AllBoardsPresenter extends PtrBasePresenter<IAllBoardsView> {

    private final BoardModel mBoardModel;

    @Inject
    public AllBoardsPresenter(BoardModel boardModel) {
        mBoardModel = boardModel;
    }

    @Override
    protected void loadMore() {

    }

    @Override
    public void refresh(boolean isNew) {
        Subscription subscription = mBoardModel.getAllBoards(new DefaultSubscriber<Msg<List<Board>>>() {
            @Override
            protected void parseMsg(Msg<List<Board>> listMsg) {
                if (listMsg.isResultOK()) {
                    List<Board> list = listMsg.getData();
                    if (list != null) {
                        mBoardModel.saveAllBoards(list);
                        mView.showContent(list);
                        return;
                    }
                    mView.showEmpty();
                    return;
                }
                mView.showLoadingError(listMsg.getMsg());
            }

            @Override
            protected void readCache() {
                List<Board> list = mBoardModel.getCachedBoards();
                mView.showContent(list);
            }
        });
        addSubscription(subscription);
    }

    public void onBoardClick(Board board, int i) {
        mView.seeBoardDetail(board);
    }

}
