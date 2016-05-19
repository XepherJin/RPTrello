package com.retropoktan.rptrello.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.model.entity.Board;
import com.retropoktan.rptrello.ui.activity.BoardDetailActivity;
import com.retropoktan.rptrello.ui.adapter.BoardAdapter;
import com.retropoktan.rptrello.ui.base.BaseFragment;
import com.retropoktan.rptrello.ui.inject.component.DaggerBoardComponent;
import com.retropoktan.rptrello.ui.inject.module.BoardModule;
import com.retropoktan.rptrello.ui.listener.FragmentListener;
import com.retropoktan.rptrello.ui.presenter.AllBoardsPresenter;
import com.retropoktan.rptrello.ui.view.IAllBoardsView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by RetroPoktan on 4/19/16.
 */
public class AllBoardsFragment extends BaseFragment implements IAllBoardsView {

    public static final int TYPE = 0;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @Inject
    AllBoardsPresenter presenter;
    @Inject
    BoardAdapter adapter;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private FragmentListener listener;

    public static AllBoardsFragment newInstance() {
        Bundle args = new Bundle();
        AllBoardsFragment fragment = new AllBoardsFragment();
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("add boards！ 功能待添加");
            }
        });
        adapter.setOnItemClickListener(new BoardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Board board, int i) {
                presenter.onBoardClick(board, i);
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
        DaggerBoardComponent.builder()
                .activityModule(new ActivityModule(getContext()))
                .applicationComponent(listener.applicationComponent())
                .boardModule(new BoardModule())
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
        return R.layout.layout_ptr_recycler_fab;
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
    public void showContent(List<Board> list) {
        adapter.addAll(list);
    }

    @Override
    public void seeBoardDetail(Board board) {
        Intent intent = new Intent(getActivity(), BoardDetailActivity.class);
        startActivity(intent);
    }
}
