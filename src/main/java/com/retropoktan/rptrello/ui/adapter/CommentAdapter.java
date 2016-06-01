package com.retropoktan.rptrello.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.model.entity.Board;
import com.retropoktan.rptrello.model.entity.Comment;
import com.retropoktan.rptrello.model.entity.Task;
import com.retropoktan.rptrello.widget.BezelImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RetroPoktan on 5/24/16.
 */
public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_COMMENT = 1;

    private final LayoutInflater inflater;
    private OnClickListeners onItemClickListener;

    private List<Comment> comments;

    private Task mTask;

    @Inject
    public CommentAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = inflater.inflate(R.layout.item_comment, parent, false);
            return new HeaderViewHolder(view);
        }
        View view = inflater.inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {

        } else if (holder instanceof HeaderViewHolder) {

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_COMMENT;
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public void setOnClickListeners(OnClickListeners l) {
        onItemClickListener = l;
    }

    public interface OnClickListeners {
        void onItemClick(Board board, int i);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.comment_avatar_iv)
        BezelImageView avatar;
        @BindView(R.id.comment_user_name_tv)
        TextView name;
        @BindView(R.id.comment_body_tv)
        TextView body;
        @BindView(R.id.comment_create_at_tv)
        TextView time;
        @BindView(R.id.comment_comment_tv)
        TextView comment;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
