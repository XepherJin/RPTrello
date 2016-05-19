package com.retropoktan.rptrello.ui.base;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.retropoktan.rptrello.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RetroPoktan on 12/19/15.
 */
public abstract class ToolbarBaseActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    protected CoordinatorLayout main;
    protected View content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = (CoordinatorLayout) findViewById(R.id.main);
        if (setContentView() != 0) {
            content = getLayoutInflater().inflate(setContentView(), null);
            CoordinatorLayout.LayoutParams lp = new CoordinatorLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            // set a ScrollingViewBehavior so that the content would not be hidden behind the appbar.
            lp.setBehavior(new AppBarLayout.ScrollingViewBehavior());
            changeLayoutParams(lp);
            content.setLayoutParams(lp);
        }
        if (content != null) {
            int count = main.getChildCount();
            if (withFAB()) {
                main.addView(content, count - 1);
            } else {
                main.addView(content);
            }
        }
        ButterKnife.bind(this);
        if (toolbar != null) {
            setToolbar(toolbar);
            setSupportActionBar(toolbar);
            if (canBack()) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
        initViews(savedInstanceState);
    }

    protected abstract void initViews(Bundle savedInstanceState);

    protected void changeLayoutParams(CoordinatorLayout.LayoutParams lp) {

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

    protected abstract void setToolbar(Toolbar toolbar);

    protected void onNavigationClick() {

    }

    @Override
    protected int getRootContentView() {
        if (withFAB()) {
            return R.layout.activity_base_with_fab;
        } else {
            return R.layout.activity_base;
        }

    }

    protected abstract int setContentView();

    // default without fab
    protected boolean withFAB() {
        return false;
    }

    protected abstract boolean canBack();
}
