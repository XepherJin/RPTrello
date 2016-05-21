package com.retropoktan.rptrello.ui.presenter;

import com.retropoktan.rptrello.model.TeamModel;
import com.retropoktan.rptrello.model.entity.Msg;
import com.retropoktan.rptrello.model.entity.Team;
import com.retropoktan.rptrello.ui.presenter.base.PtrBasePresenter;
import com.retropoktan.rptrello.ui.view.IAllTeamsView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by RetroPoktan on 4/21/16.
 */
public class AllTeamsPresenter extends PtrBasePresenter<IAllTeamsView> {

    private final TeamModel mTeamModel;

    @Inject
    public AllTeamsPresenter(TeamModel teamModel) {
        mTeamModel = teamModel;
    }

    @Override
    protected void loadMore() {

    }

    @Override
    public void refresh(boolean isNew) {
        Subscription subscription = mTeamModel.getAllTeams(new DefaultSubscriber<Msg<List<Team>>>() {
            @Override
            protected void parseMsg(Msg<List<Team>> listMsg) {
                if (listMsg.isResultOK()) {
                    List<Team> list = listMsg.getData();
                    if (list != null) {
                        mTeamModel.saveAllTeams(list);
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
                List<Team> list = mTeamModel.getCachedTeams();
                mView.showContent(list);
            }
        });
        addSubscription(subscription);
    }

    public void onTeamClick(Team team, int i) {

    }
}
