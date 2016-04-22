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
import com.retropoktan.rptrello.model.entity.Task;
import com.retropoktan.rptrello.ui.adapter.TaskAdapter;
import com.retropoktan.rptrello.ui.base.BaseFragment;
import com.retropoktan.rptrello.ui.inject.component.DaggerTaskComponent;
import com.retropoktan.rptrello.ui.listener.FragmentListener;
import com.retropoktan.rptrello.ui.presenter.AllTasksPresenter;
import com.retropoktan.rptrello.ui.view.IAllTasksView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by RetroPoktan on 4/20/16.
 */
public class AllTasksFragment extends BaseFragment implements IAllTasksView {

    public static final int TYPE = 2;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Inject
    AllTasksPresenter presenter;
    @Inject
    TaskAdapter adapter;
    private FragmentListener listener;

    public static AllTasksFragment newInstance() {
        Bundle args = new Bundle();
        AllTasksFragment fragment = new AllTasksFragment();
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
        adapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Task Task, int i) {
                presenter.onTaskClick(Task, i);
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
        DaggerTaskComponent.builder()
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
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadingError(CharSequence errMsg) {
        showToast(errMsg);
    }

    @Override
    public void showContent(List<Task> list) {
        adapter.addAll(list);
    }

    @Override
    public void seeTaskDetail(Task Task) {

    }
}
