package com.retropoktan.rptrello.ui.view;

import com.retropoktan.rptrello.model.entity.Task;

import java.util.List;

/**
 * Created by RetroPoktan on 4/22/16.
 */
public interface IAllTasksView extends IAdapterView {
    void showContent(List<Task> list);

    void seeTaskDetail(Task task, int position);
}
