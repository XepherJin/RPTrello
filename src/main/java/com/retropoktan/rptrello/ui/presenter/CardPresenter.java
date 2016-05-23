package com.retropoktan.rptrello.ui.presenter;

import com.retropoktan.rptrello.model.TaskModel;
import com.retropoktan.rptrello.model.entity.Task;
import com.retropoktan.rptrello.ui.presenter.base.PtrBasePresenter;
import com.retropoktan.rptrello.ui.view.ICardView;

import javax.inject.Inject;

/**
 * Created by RetroPoktan on 5/22/16.
 */
public class CardPresenter extends PtrBasePresenter<ICardView> {

    private final TaskModel mTaskModel;

    @Inject
    public CardPresenter(TaskModel taskModel) {
        mTaskModel = taskModel;
    }

    @Override
    protected void loadMore() {

    }

    @Override
    public void refresh(boolean isNew) {

    }

    public void onTaskClick(Task task, int position) {

    }
}
