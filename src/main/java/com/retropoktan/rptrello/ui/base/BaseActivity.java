package com.retropoktan.rptrello.ui.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.retropoktan.rptrello.TrelloApplication;
import com.retropoktan.rptrello.inject.component.ApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getRootContentView());
        setupComponent();
        attachViewForPresenter();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        addListeners();

    }

    protected void addListeners() {

    }

    protected void setupComponent() {

    }

    protected Bundle getActivityTransitionAnimBundle() {
        return ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle();
    }

    protected void attachViewForPresenter() {

    }

    protected abstract int getRootContentView();

    protected void showToast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    protected ApplicationComponent getApplicationComponent() {
        return TrelloApplication.getInstance().getApplicationComponent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyPresenter();
    }

    protected void destroyPresenter() {

    }
}
