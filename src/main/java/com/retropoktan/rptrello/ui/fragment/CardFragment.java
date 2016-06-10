package com.retropoktan.rptrello.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.model.entity.Card;
import com.retropoktan.rptrello.model.entity.Task;
import com.retropoktan.rptrello.ui.activity.TaskDetailActivity;
import com.retropoktan.rptrello.ui.adapter.TaskAdapter;
import com.retropoktan.rptrello.ui.base.BaseFragment;
import com.retropoktan.rptrello.ui.inject.component.DaggerTaskComponent;
import com.retropoktan.rptrello.ui.inject.module.TaskModule;
import com.retropoktan.rptrello.ui.listener.FragmentListener;
import com.retropoktan.rptrello.ui.presenter.CardPresenter;
import com.retropoktan.rptrello.ui.view.ICardView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by RetroPoktan on 5/22/16.
 */
public class CardFragment extends BaseFragment implements ICardView {

    public static final String TAG = CardFragment.class.getSimpleName();
    private static final String ARG_CARD = "card";

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @Inject
    CardPresenter presenter;
    @Inject
    TaskAdapter adapter;
    private Card mCard;
    private FragmentListener listener;

    public static CardFragment newInstance(Card card) {
        Bundle args = new Bundle();
        CardFragment fragment = new CardFragment();
        args.putParcelable(ARG_CARD, card);
        fragment.setArguments(args);
        return fragment;
    }

    public CharSequence getTitle() {
        return mCard == null ? null : mCard.getName();
    }

    @Override
    protected void parseArguments() {
        Bundle bundle = getArguments();
        mCard = bundle.getParcelable(ARG_CARD);
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
            public void onItemClick(Task task, int i) {
                presenter.onTaskClick(task, i);
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
                .taskModule(new TaskModule())
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
    public void showContent(List<Task> list) {
        adapter.addAll(list);
    }

    @Override
    public void seeTaskDetail(Task task) {
        Intent intent = new Intent(getActivity(), TaskDetailActivity.class);
        intent.putExtra(Task.TAG, task);
        startActivity(intent, getActivityTransitionAnimBundle());
    }

    @Override
    public long getCardId() {
        return mCard == null ? -1L : mCard.getId();
    }
}
