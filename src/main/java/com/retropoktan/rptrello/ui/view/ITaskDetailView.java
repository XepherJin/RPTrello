package com.retropoktan.rptrello.ui.view;

import android.content.Context;

import com.retropoktan.rptrello.model.entity.Comment;

import java.util.List;

/**
 * Created by RetroPoktan on 5/24/16.
 */
public interface ITaskDetailView extends IAdapterView {
    void loadTaskDetail();

    Context getContext();

    void showContent(List<Comment> list);

    void showOperationResult(CharSequence result);

    void setFABLike();

    void setFABDislike();

    void showBottomSheet();

    void addNewComment(Comment comment);

    void clearText();
}
