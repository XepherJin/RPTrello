package com.retropoktan.rptrello.ui.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.retropoktan.rptrello.ui.adapter.BoardAdapter;
import com.retropoktan.rptrello.ui.base.SwipeRefreshBaseActivity;

/**
 * Created by RetroPoktan on 4/4/16.
 */
public class SettingsActivity extends SwipeRefreshBaseActivity {

    @Override
    protected void onSwipeRefresh() {

    }

    @Override
    protected void setToolbar(Toolbar toolbar) {

    }

    @Override
    protected void initViews() {
        super.initViews();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BoardAdapter(this));
    }

    @Override
    protected boolean withFAB() {
        return true;
    }

    @Override
    protected boolean canBack() {
        return true;
    }
}
