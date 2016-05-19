package com.retropoktan.rptrello.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.model.entity.Team;
import com.retropoktan.rptrello.ui.adapter.TeamAdapter;
import com.retropoktan.rptrello.ui.base.BaseFragment;
import com.retropoktan.rptrello.ui.inject.component.DaggerTeamComponent;
import com.retropoktan.rptrello.ui.listener.FragmentListener;
import com.retropoktan.rptrello.ui.presenter.AllTeamsPresenter;
import com.retropoktan.rptrello.ui.view.IAllTeamsView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by RetroPoktan on 4/19/16.
 */
public class AllTeamsFragment extends BaseFragment implements IAllTeamsView {

    public static final int TYPE = 1;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @Inject
    AllTeamsPresenter presenter;
    @Inject
    TeamAdapter adapter;
    private FragmentListener listener;

    public static AllTeamsFragment newInstance() {
        Bundle args = new Bundle();
        AllTeamsFragment fragment = new AllTeamsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (FragmentListener) context;
    }

    @Override
    protected void initViews(View view) {
        presenter.attachView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        presenter.refresh(true);
    }

    @Override
    protected void addListeners() {
        adapter.setOnItemClickListener(new TeamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Team Team, int i) {
                presenter.onTeamClick(Team, i);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh(true);
            }
        });
    }

    @Override
    protected void setupComponent() {
        DaggerTeamComponent.builder()
                .activityModule(new ActivityModule(getContext()))
                .applicationComponent(listener.applicationComponent())
                .build()
                .inject(this);
    }

    @Override
    protected void destroyPresenter() {
        if (presenter != null) {
            presenter.onDestroy();
            presenter = null;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_ptr_recycler;
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void hideEmpty() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showLoadingError(CharSequence errMsg) {
        showToast(errMsg);
    }

    @Override
    public void showContent(List<Team> list) {
        adapter.addAll(list);
    }

    @Override
    public void seeTeamDetail(Team team) {

    }
}
