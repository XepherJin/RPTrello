package com.retropoktan.rptrello.ui.presenter;

import com.retropoktan.rptrello.model.TaskModel;
import com.retropoktan.rptrello.ui.presenter.base.BasePresenter;
import com.retropoktan.rptrello.ui.view.ITaskDetailView;

import javax.inject.Inject;

/**
 * Created by RetroPoktan on 5/24/16.
 */
public class TaskDetailPresenter extends BasePresenter<ITaskDetailView> {

    private final TaskModel mTaskModel;

    @Inject
    public TaskDetailPresenter(TaskModel taskModel) {
        mTaskModel = taskModel;
    }


}
