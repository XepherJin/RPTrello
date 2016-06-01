package com.retropoktan.rptrello.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.inject.component.ApplicationComponent;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.model.entity.Board;
import com.retropoktan.rptrello.model.entity.Card;
import com.retropoktan.rptrello.ui.adapter.CardPagerAdapter;
import com.retropoktan.rptrello.ui.base.BaseActivity;
import com.retropoktan.rptrello.ui.fragment.CardFragment;
import com.retropoktan.rptrello.ui.inject.component.DaggerCardComponent;
import com.retropoktan.rptrello.ui.inject.module.CardModule;
import com.retropoktan.rptrello.ui.listener.FragmentListener;
import com.retropoktan.rptrello.ui.presenter.BoardDetailPresenter;
import com.retropoktan.rptrello.ui.view.IBoardDetailView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RetroPoktan on 2/24/16.
 */
public class BoardDetailActivity extends BaseActivity implements IBoardDetailView, FragmentListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @Inject
    BoardDetailPresenter presenter;

    private Board mBoard;

    @Override
    protected int getRootContentView() {
        return R.layout.activity_board_detail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initViews();
        initData();
    }

    private void parseIntent() {
        if (getIntent() != null) {
            mBoard = getIntent().getParcelableExtra(Board.TAG);
        }
    }

    private void initViews() {
        setToolbar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setToolbar(Toolbar toolbar) {
        if (mBoard != null) {
            toolbar.setTitle(mBoard.getName());
        }
    }

    private void initData() {
        presenter.getBoardDetail();
    }

    @Override
    protected void setupComponent() {
        parseIntent();
        DaggerCardComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(getApplicationComponent())
                .cardModule(mBoard == null ? new CardModule() : new CardModule(mBoard.getId()))
                .build()
                .inject(this);
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
    public void showEmpty() {

    }

    @Override
    public void setTabs(List<Card> list) {
        List<CardFragment> fragments = new ArrayList<>();
        for (Card c : list) {
            fragments.add(CardFragment.newInstance(c));
        }
        viewPager.setAdapter(new CardPagerAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public ApplicationComponent applicationComponent() {
        return getApplicationComponent();
    }
}
