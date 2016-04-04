package com.retropoktan.rptrello.ui.activity;

import android.support.v7.widget.Toolbar;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.ui.base.ToolbarBaseActivity;

/**
 * Created by RetroPoktan on 4/4/16.
 */
public class SettingsActivity extends ToolbarBaseActivity {

    @Override
    protected void initViews() {

    }

    @Override
    protected void setToolbar(Toolbar toolbar) {

    }

    @Override
    protected void onNavigationClick() {
        finish();
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected boolean withFAB() {
        return true;
    }

    @Override
    protected boolean canBack() {
        return true;
    }
}
