package com.retropoktan.rptrello.ui.presenter;

import com.retropoktan.rptrello.model.TaskModel;
import com.retropoktan.rptrello.model.entity.Msg;
import com.retropoktan.rptrello.model.entity.Task;
import com.retropoktan.rptrello.ui.presenter.base.PtrBasePresenter;
import com.retropoktan.rptrello.ui.view.IAllTasksView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by RetroPoktan on 4/22/16.
 */
public class AllTasksPresenter extends PtrBasePresenter<IAllTasksView> {

    private final TaskModel mTaskModel;

    @Inject
    public AllTasksPresenter(TaskModel TaskModel) {
        mTaskModel = TaskModel;
    }

    @Override
    protected void loadMore() {

    }

    @Override
    public void refresh(boolean isNew) {
        Subscription subscription = mTaskModel.getAllTasks(new DefaultSubscriber<Msg<List<Task>>>() {
            @Override
            protected void parseMsg(Msg<List<Task>> listMsg) {
                if (listMsg.isResultOK()) {
                    List<Task> list = listMsg.getData();
                    if (list != null) {
                        mTaskModel.saveAllTasks(list);
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
                List<Task> list = mTaskModel.getCachedTasks();
                mView.showContent(list);
            }
        });
        addSubscription(subscription);
    }

    public void onTaskClick(Task task, int i) {
        mView.seeTaskDetail(task, i);
    }

}
