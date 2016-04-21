package com.retropoktan.rptrello.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.model.entity.Team;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by RetroPoktan on 4/21/16.
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private List<Team> teams = new ArrayList<>();

    @Inject
    public TeamAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        onItemClickListener = l;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_board, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Team Team = teams.get(position);
        if (Team != null) {
            holder.name.setText(Team.getName());
            holder.desc.setText(Team.getDescription());
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    Team team = teams.get(holder.getLayoutPosition());
                    if (team != null) {
                        onItemClickListener.onItemClick(team, holder.getLayoutPosition());
                    }
                }
            }
        });
    }

    public void addAll(List<Team> list) {
        teams.clear();
        teams.addAll(list);
        notifyDataSetChanged();
    }

    public void clearAll() {
        teams.clear();
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Team team, int i);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cv_board)
        CardView cardView;
        @Bind(R.id.cv_board_name)
        TextView name;
        @Bind(R.id.cv_board_description)
        TextView desc;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
