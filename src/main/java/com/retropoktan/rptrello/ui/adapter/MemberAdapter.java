package com.retropoktan.rptrello.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.model.entity.Member;
import com.retropoktan.rptrello.widget.BezelImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RetroPoktan on 6/12/16.
 */
public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {

    private final Context mContext;
    private List<Member> members = new ArrayList<>();

    public MemberAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_member, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Member member = members.get(holder.getLayoutPosition());
        if (member != null) {
            Picasso.with(mContext).load(member.getAvatar()).placeholder(new ColorDrawable(Color.GRAY)).into(holder.avatar);
        }
    }

    public void addAll(List<Member> list) {
        if (list == null) {
            return;
        }
        members.clear();
        members.addAll(list);
        notifyDataSetChanged();
    }

    public void clearAll() {
        members.clear();
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_member)
        BezelImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
