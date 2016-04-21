package com.retropoktan.rptrello.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.ui.base.BaseFragment;

/**
 * Created by RetroPoktan on 4/20/16.
 */
public class AllCardsFragment extends BaseFragment {

    public static final int TYPE = 2;

    public static AllCardsFragment newInstance() {
        Bundle args = new Bundle();
        AllCardsFragment fragment = new AllCardsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}
