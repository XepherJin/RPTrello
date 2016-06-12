package com.retropoktan.rptrello.ui.presenter;

import android.widget.ImageView;

import com.retropoktan.rptrello.model.TaskModel;
import com.retropoktan.rptrello.model.entity.Comment;
import com.retropoktan.rptrello.model.entity.Msg;
import com.retropoktan.rptrello.ui.presenter.base.BasePresenter;
import com.retropoktan.rptrello.ui.view.ITaskDetailView;
import com.retropoktan.rptrello.utils.DisplayUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by RetroPoktan on 5/24/16.
 */
public class TaskDetailPresenter extends BasePresenter<ITaskDetailView> {

    private final TaskModel mTaskModel;

    @Inject
    public TaskDetailPresenter(TaskModel taskModel) {
        mTaskModel = taskModel;
    }

    public void loadTaskPicUrl(String url, ImageView imageView) {
        mTaskModel.getPicasso().load(url)
                .resize(DisplayUtil.dip2px(mView.getContext(), 180), DisplayUtil.dip2px(mView.getContext(), 100))
                .into(imageView);
    }

    public void getTaskComments(long taskId) {
        Subscription subscription = mTaskModel.getTaskComments(taskId, new DefaultSubscriber<Msg<List<Comment>>>() {
            @Override
            protected void parseMsg(Msg<List<Comment>> listMsg) {
                if (listMsg.isResultOK()) {
                    List<Comment> list = listMsg.getData();
                    if (list != null) {
                        mTaskModel.saveAllComments(list);
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
                List<Comment> list = mTaskModel.getCachedComments();
                mView.showContent(list);
            }
        });
        addSubscription(subscription);
    }

    public void onCommentClick(Comment comment, int i) {

    }

}
