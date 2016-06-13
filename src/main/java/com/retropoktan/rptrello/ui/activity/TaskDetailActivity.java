package com.retropoktan.rptrello.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.model.entity.Comment;
import com.retropoktan.rptrello.model.entity.Task;
import com.retropoktan.rptrello.ui.adapter.CommentAdapter;
import com.retropoktan.rptrello.ui.base.BaseActivity;
import com.retropoktan.rptrello.ui.fragment.BottomSheetFragment;
import com.retropoktan.rptrello.ui.inject.component.DaggerTaskComponent;
import com.retropoktan.rptrello.ui.presenter.TaskDetailPresenter;
import com.retropoktan.rptrello.ui.view.ITaskDetailView;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RetroPoktan on 5/22/16.
 */
public class TaskDetailActivity extends BaseActivity implements ITaskDetailView {

    static String[] urls;

    static {
        urls = new String[]{
                "http://img4.imgtn.bdimg.com/it/u=1107768246,4186178720&fm=21&gp=0.jpg",
                "http://s3.sinaimg.cn/mw690/e02cf81fgx6CqdCOZOid2&690",
                "http://img3.imgtn.bdimg.com/it/u=3624730890,1664365807&fm=21&gp=0.jpg"
        };
    }

    @BindView(R.id.main)
    CoordinatorLayout main;
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
        if (mTask != null) {
            presenter.getTaskComments(mTask.getId());
        }
    }

    private void initViews() {
        setToolbar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadTaskDetail();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_more:
                presenter.showMoreMenu();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void addListeners() {
        initToolbarListener();
        fab.setOnClickListener(new View.OnClickListener() {

            private boolean flag;

            @Override
            public void onClick(View v) {
                if (!flag) {
                    presenter.likeTask();
                    flag = true;
                } else {
                    presenter.dislikeTask();
                    flag = false;
                }
            }
        });
        adapter.setOnClickListeners(new CommentAdapter.OnClickListeners() {
            @Override
            public void onItemClick(Comment comment, int i) {

            }

            @Override
            public void onSendComment(long taskId, CharSequence txt) {
                presenter.sendComment(taskId, txt);
            }
        });
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
        Snackbar.make(main, errMsg, Snackbar.LENGTH_SHORT).show();
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
    public void loadTaskDetail() {
        if (mTask == null) {
            return;
        }
        adapter.addTaskDetailHeader(mTask);
        Random random = new Random();

        presenter.loadTaskPicUrl(urls[random.nextInt(3)], taskIv);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showContent(List<Comment> list) {
        adapter.addAllComments(list);
    }

    @Override
    public void showOperationResult(CharSequence result) {
        Snackbar.make(main, result, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setFABLike() {
        fab.setImageResource(R.drawable.ic_star);
    }

    @Override
    public void setFABDislike() {
        fab.setImageResource(R.drawable.ic_star_border);
    }

    @Override
    public void showBottomSheet() {
        BottomSheetFragment bottomSheetFragment = BottomSheetFragment.newInstance(R.layout.layout_sheet_task_detail);
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

    @Override
    public void addNewComment(Comment comment) {
        adapter.addNewComment(comment);
    }

    @Override
    public void clearText() {
        adapter.clearCommentEdit();
    }
}
