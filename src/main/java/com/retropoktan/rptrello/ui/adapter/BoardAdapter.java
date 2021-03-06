package com.retropoktan.rptrello.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
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
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RetroPoktan on 4/4/16.
 */
public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private List<Board> boards = new ArrayList<>();

    @Inject
    public BoardAdapter(Context context) {
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
        Board board = boards.get(position);
        if (board != null) {
            holder.name.setText(board.getName());
            holder.desc.setText(board.getDescription());
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            holder.pic.setImageDrawable(new ColorDrawable(color));
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    Board board = boards.get(holder.getLayoutPosition());
                    if (board != null) {
                        onItemClickListener.onItemClick(board, holder.getLayoutPosition());
                    }
                }
            }
        });
    }

    public void addAll(List<Board> list) {
        if (list == null) {
            return;
        }
        boards.clear();
        boards.addAll(list);
        notifyDataSetChanged();
    }

    public void clearAll() {
        boards.clear();
    }

    @Override
    public int getItemCount() {
        return boards.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Board board, int i);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv_board)
        CardView cardView;
        @BindView(R.id.cv_board_name)
        TextView name;
        @BindView(R.id.cv_board_description)
        TextView desc;
        @BindView(R.id.iv_board)
        BezelImageView pic;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
