package com.retropoktan.rptrello.ui.presenter;

import com.retropoktan.rptrello.model.BoardModel;
import com.retropoktan.rptrello.model.TeamModel;
import com.retropoktan.rptrello.model.entity.Board;
import com.retropoktan.rptrello.model.entity.Msg;
import com.retropoktan.rptrello.model.entity.Team;
import com.retropoktan.rptrello.model.req.BoardCreateReq;
import com.retropoktan.rptrello.ui.presenter.base.BasePresenter;
import com.retropoktan.rptrello.ui.view.IBoardCreateView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by RetroPoktan on 6/9/16.
 */
public class BoardCreatePresenter extends BasePresenter<IBoardCreateView> {

    private final BoardModel mBoardModel;
    private final TeamModel mTeamModel;

    @Inject
    public BoardCreatePresenter(BoardModel boardModel, TeamModel teamModel) {
        mBoardModel = boardModel;
        mTeamModel = teamModel;
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

    public void getAllTeams() {
        Subscription subscription = mTeamModel.getAllTeams(new DefaultSubscriber<Msg<List<Team>>>() {
            @Override
            protected void parseMsg(Msg<List<Team>> listMsg) {
                if (listMsg.isResultOK()) {
                    List<Team> list = listMsg.getData();
                    if (list != null) {
                        mView.showTeams(list);
                        return;
                    }
                    mView.showEmpty();
                    return;
                }
                mView.showLoadingError(listMsg.getMsg());
            }

            @Override
            protected void readCache() {
                List<Team> list = mTeamModel.getCachedTeams();
                mView.showTeams(list);
            }
        });
        addSubscription(subscription);
    }

}
