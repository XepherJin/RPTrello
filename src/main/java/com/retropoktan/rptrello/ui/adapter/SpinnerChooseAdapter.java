package com.retropoktan.rptrello.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.retropoktan.rptrello.model.entity.Team;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RetroPoktan on 6/10/16.
 */
public class SpinnerChooseAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private List<Team> teams = new ArrayList<>();

    @Inject
    public SpinnerChooseAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return teams.size();
    }

    @Override
    public Team getItem(int position) {
        return teams.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addAll(List<Team> list) {
        if (list == null) {
            return;
        }
        teams.clear();
        teams.addAll(list);
        notifyDataSetChanged();
    }

    public void clearAll() {
        teams.clear();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.teamName.setText(teams.get(position).getName());
        return convertView;
    }

    static class ViewHolder {

        @BindView(android.R.id.text1)
        TextView teamName;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
