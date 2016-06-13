package com.retropoktan.rptrello.ui.presenter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.retropoktan.rptrello.model.TaskModel;
import com.retropoktan.rptrello.model.entity.Comment;
import com.retropoktan.rptrello.model.entity.Msg;
import com.retropoktan.rptrello.model.req.CommentAddReq;
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

    public void sendComment(long taskId, CharSequence comment) {
        CommentAddReq req = new CommentAddReq();
        if (!TextUtils.isEmpty(comment)) {
            req.setComment(comment.toString());
        }
        Subscription subscription = mTaskModel.sendComment(taskId, req, new DefaultSubscriber<Msg<Comment>>() {
            @Override
            protected void parseMsg(Msg<Comment> msg) {
                if (msg.isResultOK()) {
                    Comment comment = msg.getData();
                    if (comment != null) {
                        mView.addNewComment(comment);
                        mView.clearText();
                        return;
                    }
                    mView.showEmpty();
                    return;
                }
                mView.showLoadingError(msg.getMsg());
            }
        });
        addSubscription(subscription);
    }

    public void onCommentClick(Comment comment, int i) {

    }

    public void likeTask() {
        mView.showOperationResult("关注成功");
        mView.setFABLike();
    }

    public void dislikeTask() {
        mView.showOperationResult("取消关注成功");
        mView.setFABDislike();
    }

    public void showMoreMenu() {
        mView.showBottomSheet();
    }

}
