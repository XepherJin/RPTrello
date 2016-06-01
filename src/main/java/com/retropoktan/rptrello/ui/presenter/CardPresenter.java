package com.retropoktan.rptrello.ui.presenter;

import com.retropoktan.rptrello.model.TaskModel;
import com.retropoktan.rptrello.model.entity.Msg;
import com.retropoktan.rptrello.model.entity.Task;
import com.retropoktan.rptrello.ui.presenter.base.PtrBasePresenter;
import com.retropoktan.rptrello.ui.view.ICardView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by RetroPoktan on 5/22/16.
 */
public class CardPresenter extends PtrBasePresenter<ICardView> {

    private final TaskModel mTaskModel;

    private long cardId;

    @Inject
    public CardPresenter(TaskModel taskModel) {
        mTaskModel = taskModel;
    }

    @Override
    protected void loadMore() {

    }

    @Override
    public void refresh(boolean isNew) {
        Subscription subscription = mTaskModel.getCardTasks(cardId, new DefaultSubscriber<Msg<List<Task>>>() {
            @Override
            protected void parseMsg(Msg<List<Task>> listMsg) {
                if (listMsg.isResultOK()) {
                    List<Task> list = listMsg.getData();
                    if (list != null) {
                        mView.showContent(list);
                        return;
                    }
                    mView.showEmpty();
                    return;
                }
                mView.showLoadingError(listMsg.getMsg());
            }
        });
        addSubscription(subscription);
    }

    public void onTaskClick(Task task, int position) {
        mView.seeTaskDetail(task);
    }

    @Override
    public void attachView(ICardView view) {
        super.attachView(view);
        cardId = view.getCardId();
    }
}
