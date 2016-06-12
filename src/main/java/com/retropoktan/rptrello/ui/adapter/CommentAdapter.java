package com.retropoktan.rptrello.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.model.entity.Comment;
import com.retropoktan.rptrello.model.entity.Task;
import com.retropoktan.rptrello.widget.BezelImageView;
import com.retropoktan.rptrello.widget.FullyGridLayoutManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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

    private final Context mContext;
    private OnClickListeners onItemClickListener;

    private List<Comment> comments = new ArrayList<>();
    private Task mTask;
    private CharSequence commentTxt;

    @Inject
    public CommentAdapter(Context context) {
        mContext = context;
    }

    private static int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return position - 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_task_detail, parent, false);
            return new HeaderViewHolder(view, mContext);
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            if (mTask != null) {
                ((HeaderViewHolder) holder).description.setText(mTask.getDescription());
                ((HeaderViewHolder) holder).memberAdapter.addAll(mTask.getWorker());
            }
            commentTxt = ((HeaderViewHolder) holder).addComment.getText();
        } else if (holder instanceof ViewHolder) {
            Comment comment = comments.get(getRealPosition(holder));
            Picasso.with(mContext).load(comment.getMember().getAvatar()).placeholder(new ColorDrawable(Color.GRAY)).into(((ViewHolder) holder).avatar);
            ((ViewHolder) holder).name.setText(comment.getMember().getNick());
            ((ViewHolder) holder).body.setText(comment.getComment());
            ((ViewHolder) holder).time.setText(comment.getCreateTime());
        }
    }

    public CharSequence getComment() {
        return commentTxt;
    }

    public void addAllComments(List<Comment> list) {
        if (list == null) {
            return;
        }
        comments.clear();
        comments.addAll(list);
        notifyDataSetChanged();
    }

    public void clearAllComments() {
        comments.clear();
    }

    public void addTaskDetailHeader(Task task) {
        mTask = task;
    }

    public void updateTaskDetailHeader(Task task) {
        addTaskDetailHeader(task);
        notifyItemChanged(0);
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
        return comments.size() + 1;
    }

    public void setOnClickListeners(OnClickListeners l) {
        onItemClickListener = l;
    }

    public interface OnClickListeners {
        void onItemClick(Comment comment, int i);
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

        @BindView(R.id.tv_task_detail)
        TextView description;
        @BindView(R.id.rv_member)
        RecyclerView member;
        @BindView(R.id.btn_show_task_detail)
        Button showDetail;
        @BindView(R.id.et_add_comment)
        EditText addComment;
        MemberAdapter memberAdapter;

        public HeaderViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            memberAdapter = new MemberAdapter(context);
            // disable nest scroll event
            member.setNestedScrollingEnabled(false);

            member.requestDisallowInterceptTouchEvent(false);
            member.setItemAnimator(new DefaultItemAnimator());
            member.setLayoutManager(new FullyGridLayoutManager(context, 6));
            member.setAdapter(memberAdapter);
        }
    }
}
