package com.retropoktan.rptrello.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.model.entity.CheckItem;
import com.retropoktan.rptrello.model.entity.Task;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RetroPoktan on 4/22/16.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private List<Task> tasks = new ArrayList<>();

    @Inject
    public TaskAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        onItemClickListener = l;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Task task = tasks.get(position);
        if (task != null) {
            holder.name.setText(task.getName());
            holder.desc.setText(task.getDescription());
            String deadline = task.getDeadline();
            if (TextUtils.isEmpty(deadline)) {
                deadline = "到期时间：2016-04-21";
            }
            holder.deadline.setText(deadline);
            if (task.getCheckItemList() != null) {
                StringBuffer sb = new StringBuffer();
                holder.checkItems.setVisibility(View.VISIBLE);
                for (CheckItem checkItem : task.getCheckItemList()) {
                    sb.append(checkItem.getName());
                    sb.append("\n");
                }
                sb.delete(sb.length() - 1, sb.length());
                holder.checkItems.setText(sb.toString());
            } else {
                holder.checkItems.setVisibility(View.GONE);
            }
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    Task task = tasks.get(holder.getLayoutPosition());
                    if (task != null) {
                        onItemClickListener.onItemClick(task, holder.getLayoutPosition());
                    }
                }
            }
        });
    }

    public void addAll(List<Task> list) {
        if (list == null) {
            return;
        }
        tasks.clear();
        tasks.addAll(list);
        notifyDataSetChanged();
    }

    public void clearAll() {
        tasks.clear();
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Task task, int i);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv_task)
        CardView cardView;
        @BindView(R.id.cv_task_name)
        TextView name;
        @BindView(R.id.cv_task_description)
        TextView desc;
        @BindView(R.id.cv_task_deadline)
        TextView deadline;
        @BindView(R.id.cv_task_check_items)
        TextView checkItems;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
