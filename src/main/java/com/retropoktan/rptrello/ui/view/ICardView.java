package com.retropoktan.rptrello.ui.view;

import com.retropoktan.rptrello.model.entity.Task;

import java.util.List;

/**
 * Created by RetroPoktan on 5/22/16.
 */
public interface ICardView extends IAdapterView {

    void showContent(List<Task> list);

    void seeTaskDetail(Task task);

    long getCardId();

}
