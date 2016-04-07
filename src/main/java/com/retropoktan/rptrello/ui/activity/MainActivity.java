package com.retropoktan.rptrello.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.ui.base.BaseActivity;
import com.retropoktan.rptrello.ui.view.IMainView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IMainView {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.drawerlayout_main)
    DrawerLayout mDrawer;

    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(
                this, mDrawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
    }

    @Override
    protected int getRootContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                startActivity(new Intent(this, SettingsActivity.class), ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
            } else {
                startActivity(new Intent(this, SettingsActivity.class));
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void setupComponent() {
        // TODO: 2/17/16  
    }

    @Override
    protected void destroyPresenter() {
        // TODO: 2/17/16  
    }

    @Override
    protected void attachViewForPresenter() {
        // TODO: 2/17/16
    }

    @Override
    protected void addListeners() {
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoadingError() {

    }
}
