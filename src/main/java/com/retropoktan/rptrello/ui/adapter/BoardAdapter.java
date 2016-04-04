package com.retropoktan.rptrello.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.retropoktan.rptrello.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by RetroPoktan on 4/4/16.
 */
public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("asdasdasdasdasdas", v.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cv_board)
        CardView cardView;
        @Bind(R.id.test)
        TextView test;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
