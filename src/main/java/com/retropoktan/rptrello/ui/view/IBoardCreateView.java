package com.retropoktan.rptrello.ui.view;

import com.retropoktan.rptrello.model.entity.Board;

/**
 * Created by RetroPoktan on 6/9/16.
 */
public interface IBoardCreateView extends IView {
    void actionEnabled();

    void actionDisabled();

    String getBoardName();

    String getDescription();

    long getTeamId();

    void createSuccess(Board board);
}
