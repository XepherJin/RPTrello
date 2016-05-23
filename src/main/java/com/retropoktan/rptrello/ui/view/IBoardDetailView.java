package com.retropoktan.rptrello.ui.view;

import com.retropoktan.rptrello.model.entity.Card;

import java.util.List;

/**
 * Created by RetroPoktan on 5/21/16.
 */
public interface IBoardDetailView extends IView {
    void showEmpty();

    void setTabs(List<Card> list);
}
