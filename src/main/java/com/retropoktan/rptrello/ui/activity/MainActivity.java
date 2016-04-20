package com.retropoktan.rptrello.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.ui.base.BaseActivity;
import com.retropoktan.rptrello.ui.fragment.AllBoardsFragment;
import com.retropoktan.rptrello.ui.fragment.AllCardsFragment;
import com.retropoktan.rptrello.ui.fragment.AllTeamsFragment;
import com.retropoktan.rptrello.ui.presenter.MainActivityPresenter;
import com.retropoktan.rptrello.ui.view.IMainView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IMainView {

    private static final String FRAGMENT_INDEX = "FRAGMENT_INDEX";

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.drawerlayout_main)
    DrawerLayout mDrawer;
    @Bind(R.id.nav_view_main)
    NavigationView mNavigationView;

    private ActionBarDrawerToggle toggle;

    private int currentItem = -1;
    private AllBoardsFragment boardsFragment;
    private AllTeamsFragment teamsFragment;
    private AllCardsFragment cardsFragment;

    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initViews(savedInstanceState);
    }

    private void initViews(Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(
                this, mDrawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        if (savedInstanceState != null && savedInstanceState.containsKey(FRAGMENT_INDEX)) {
            presenter.switchFragment(savedInstanceState.getInt(FRAGMENT_INDEX, AllBoardsFragment.TYPE));
            return;
        }
        presenter.switchFragment(AllBoardsFragment.TYPE);
    }

    @Override
    protected int getRootContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if (presenter.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(FRAGMENT_INDEX, currentItem);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void setupComponent() {
        presenter = new MainActivityPresenter();
    }

    @Override
    protected void destroyPresenter() {
        presenter.onDestroy();
        presenter = null;
    }

    @Override
    protected void attachViewForPresenter() {
        presenter.attachView(this);
    }

    @Override
    protected void addListeners() {
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                presenter.switchFragment(item);
                return true;
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoadingError(CharSequence errMsg) {

    }

    @Override
    public void setToolbarTitle(CharSequence text) {
        if (mToolbar != null) {
            mToolbar.setTitle(text);
        }
    }

    @Override
    public void setToolbarTitle(int resId) {
        setToolbarTitle(getResources().getString(resId));
    }

    @Override
    public FragmentManager fragmentManager() {
        return getSupportFragmentManager();
    }

    @Override
    public int currentItemId() {
        return currentItem;
    }

    @Override
    public void setCurrentItemId(int itemId) {
        currentItem = itemId;
    }

    @Override
    public Fragment boardsFragment() {
        if (boardsFragment == null) {
            boardsFragment = AllBoardsFragment.newInstance();
        }
        return boardsFragment;
    }

    @Override
    public Fragment teamsFragment() {
        if (teamsFragment == null) {
            teamsFragment = AllTeamsFragment.newInstance();
        }
        return teamsFragment;
    }

    @Override
    public Fragment cardsFragment() {
        if (cardsFragment == null) {
            cardsFragment = AllCardsFragment.newInstance();
        }
        return cardsFragment;
    }

    @Override
    public void closeDrawer() {
        mDrawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean isDrawerOpen() {
        return mDrawer.isDrawerOpen(GravityCompat.START);
    }

    @Override
    public boolean moveTaskToBack() {
        return moveTaskToBack(true);
    }

}
