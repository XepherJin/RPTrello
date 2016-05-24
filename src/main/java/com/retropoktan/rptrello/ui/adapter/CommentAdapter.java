package com.retropoktan.rptrello.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.model.entity.Board;
import com.retropoktan.rptrello.widget.BezelImageView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RetroPoktan on 5/24/16.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private List<Board> boards = new ArrayList<>();

    @Inject
    public CommentAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        onItemClickListener = l;
    }

    public interface OnItemClickListener {
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
}
