package com.retropoktan.rptrello.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.model.entity.Task;
import com.retropoktan.rptrello.ui.adapter.CommentAdapter;
import com.retropoktan.rptrello.ui.base.BaseActivity;
import com.retropoktan.rptrello.ui.inject.component.DaggerTaskComponent;
import com.retropoktan.rptrello.ui.presenter.TaskDetailPresenter;
import com.retropoktan.rptrello.ui.view.ITaskDetailView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RetroPoktan on 5/22/16.
 */
public class TaskDetailActivity extends BaseActivity implements ITaskDetailView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.toolbar_iv)
    ImageView taskIv;
    @Inject
    TaskDetailPresenter presenter;
    @Inject
    CommentAdapter adapter;

    private Task mTask;

    @Override
    protected int getRootContentView() {
        return R.layout.activity_task_detail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initViews();
        initData();
    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void initViews() {
        setToolbar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setToolbar(Toolbar toolbar) {
        toolbar.setTitle(mTask.getName());
    }

    @Override
    protected void setupComponent() {
        parseIntent();
        DaggerTaskComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(getApplicationComponent())
                .build()
                .inject(this);
    }

    private void parseIntent() {
        if (getIntent() != null) {
            mTask = getIntent().getParcelableExtra(Task.TAG);
        }
    }

    @Override
    protected void addListeners() {
        initToolbarListener();
    }

    private void initToolbarListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNavigationClick();
            }
        });
    }

    private void onNavigationClick() {
        finish();
    }

    @Override
    protected void attachViewForPresenter() {
        presenter.attachView(this);
    }

    @Override
    protected void destroyPresenter() {
        presenter.onDestroy();
        presenter = null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoadingError(CharSequence errMsg) {
        showToast(errMsg);
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


}
