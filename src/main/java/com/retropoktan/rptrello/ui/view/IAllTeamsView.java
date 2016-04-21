package com.retropoktan.rptrello.ui.view;

import com.retropoktan.rptrello.model.entity.Team;

import java.util.List;

/**
 * Created by RetroPoktan on 4/21/16.
 */
public interface IAllTeamsView extends IAdapterView {

    void showContent(List<Team> list);

    void seeTeamDetail(Team team);

}
