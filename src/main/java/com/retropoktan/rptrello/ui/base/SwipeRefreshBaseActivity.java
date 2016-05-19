package com.retropoktan.rptrello.ui.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.retropoktan.rptrello.R;

import butterknife.BindView;

/**
 * Created by RetroPoktan on 4/4/16.
 */
public abstract class SwipeRefreshBaseActivity extends ToolbarBaseActivity {

    protected boolean isRefreshing;
    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;

    public boolean isRefreshing() {
        return isRefreshing;
    }

    @Override
    protected int setContentView() {
        return R.layout.layout_ptr_recycler;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onSwipeRefresh();
            }
        });
    }

    protected abstract void onSwipeRefresh();

}