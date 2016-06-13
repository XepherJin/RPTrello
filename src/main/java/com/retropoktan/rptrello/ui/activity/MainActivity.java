package com.retropoktan.rptrello.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.inject.component.ApplicationComponent;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.inject.module.ClientApiModule;
import com.retropoktan.rptrello.model.entity.User;
import com.retropoktan.rptrello.ui.base.BaseActivity;
import com.retropoktan.rptrello.ui.fragment.AllBoardsFragment;
import com.retropoktan.rptrello.ui.fragment.AllTasksFragment;
import com.retropoktan.rptrello.ui.fragment.AllTeamsFragment;
import com.retropoktan.rptrello.ui.fragment.SimpleDialogFragment;
import com.retropoktan.rptrello.ui.inject.component.DaggerUserComponent;
import com.retropoktan.rptrello.ui.inject.module.UserModule;
import com.retropoktan.rptrello.ui.listener.FragmentListener;
import com.retropoktan.rptrello.ui.presenter.MainActivityPresenter;
import com.retropoktan.rptrello.ui.view.IMainView;
import com.retropoktan.rptrello.widget.BezelImageView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IMainView, FragmentListener, SimpleDialogFragment.ISimpleDialogFragmentListener {

    private static final String FRAGMENT_INDEX = "FRAGMENT_INDEX";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawerlayout_main)
    DrawerLayout mDrawer;
    @BindView(R.id.nav_view_main)
    NavigationView mNavigationView;
    @BindString(R.string.loading)
    String loading;
    @BindString(R.string.error_network_unavailable)
    String networkUnavailable;
    ProgressDialog mProgressDialog;
    @Inject
    MainActivityPresenter presenter;
    private BezelImageView mAvatar;
    private TextView mUserName;
    private TextView mUserEmail;
    private Button mLogout;
    private ActionBarDrawerToggle toggle;
    private int currentItem = -1;
    private AllBoardsFragment boardsFragment;
    private AllTeamsFragment teamsFragment;
    private AllTasksFragment cardsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initViews(savedInstanceState);
    }

    private void initViews(Bundle savedInstanceState) {
        initHeaderView();
        presenter.initUser();
        if (savedInstanceState != null && savedInstanceState.containsKey(FRAGMENT_INDEX)) {
            presenter.switchFragment(savedInstanceState.getInt(FRAGMENT_INDEX, AllBoardsFragment.TYPE));
            return;
        }
        presenter.switchFragment(AllBoardsFragment.TYPE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(
                this, mDrawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
    }

    private void initHeaderView() {
        View header = mNavigationView.getHeaderView(0);
        mAvatar = ButterKnife.findById(header, R.id.drawer_avatar_iv);
        mUserEmail = ButterKnife.findById(header, R.id.drawer_user_account_tv);
        mUserName = ButterKnife.findById(header, R.id.drawer_user_name_tv);
        mLogout = ButterKnife.findById(header, R.id.drawer_user_logout);
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
        DaggerUserComponent.builder()
                .activityModule(new ActivityModule(this))
                .userModule(new UserModule())
                .applicationComponent(getApplicationComponent())
                .build()
                .inject(this);
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
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDialogFragment dialogFragment = SimpleDialogFragment.newInstance("提示", "确认注销？");
                dialogFragment.show(getSupportFragmentManager(), dialogFragment.getTag());
            }
        });
    }

    @Override
    public void showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
        }
        mProgressDialog.setMessage(loading);
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showLoadingError(CharSequence errMsg) {
        showToast(errMsg);
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
            cardsFragment = AllTasksFragment.newInstance();
        }
        return cardsFragment;
    }

    @Override
    public void closeDrawer() {
        mDrawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void closeDrawerDelayed(long delayMillis) {
        mDrawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                mDrawer.closeDrawer(GravityCompat.START);
            }
        }, delayMillis);
    }

    @Override
    public boolean isDrawerOpen() {
        return mDrawer.isDrawerOpen(GravityCompat.START);
    }

    @Override
    public boolean moveTaskToBack() {
        return moveTaskToBack(true);
    }

    @Override
    public void goSettings() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    public void goAbout() {

    }

    @Override
    public void setUserView(User user) {
        Picasso.with(this).load(ClientApiModule.BASE + user.getAvatar()).placeholder(new ColorDrawable(Color.GRAY)).into(mAvatar);
        mUserName.setText(user.getNick());
        mUserEmail.setText(user.getEmail());
    }

    @Override
    public void gotoLogin() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        ActivityCompat.startActivity(this, intent, getActivityTransitionAnimBundle());
        finish();
    }

    @Override
    public ApplicationComponent applicationComponent() {
        return getApplicationComponent();
    }

    @Override
    public void onDialogCancel() {

    }

    @Override
    public void onDialogButtonClick() {
        presenter.logout();
    }
}
