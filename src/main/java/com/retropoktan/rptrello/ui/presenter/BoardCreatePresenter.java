package com.retropoktan.rptrello.ui.presenter;

import android.util.Log;

import com.retropoktan.rptrello.model.BoardModel;
import com.retropoktan.rptrello.model.entity.Board;
import com.retropoktan.rptrello.model.entity.Msg;
import com.retropoktan.rptrello.model.req.BoardCreateReq;
import com.retropoktan.rptrello.ui.presenter.base.BasePresenter;
import com.retropoktan.rptrello.ui.view.IBoardCreateView;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by RetroPoktan on 6/9/16.
 */
public class BoardCreatePresenter extends BasePresenter<IBoardCreateView> {

    private final BoardModel mBoardModel;

    @Inject
    public BoardCreatePresenter(BoardModel boardModel) {
        mBoardModel = boardModel;
        Log.e("test", mBoardModel.toString());
    }

    public void createBoard() {
        BoardCreateReq req = new BoardCreateReq();
        req.setBoardName(mView.getBoardName());
        req.setTeamId(mView.getTeamId());
        req.setDescription(mView.getDescription());
        Subscription subscription = mBoardModel.createBoard(req, new DefaultSubscriber<Msg<Board>>() {
            @Override
            protected void parseMsg(Msg<Board> msg) {
                if (msg.isResultOK()) {
                    Board board = msg.getData();
                    if (board != null) {
                        mView.createSuccess(board);
                        return;
                    }
                    mView.showLoadingError(msg.getMsg());
                    return;
                }
                mView.showLoadingError(msg.getMsg());
            }
        });
        addSubscription(subscription);
    }
}
