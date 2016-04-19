package com.retropoktan.rptrello.ui.fragment;

import android.os.Bundle;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.ui.base.BaseFragment;

/**
 * Created by RetroPoktan on 4/19/16.
 */
public class AllTeamsFragment extends BaseFragment {

    public static final int TYPE = 1;

    public static AllTeamsFragment newInstance() {
        Bundle args = new Bundle();
        AllTeamsFragment fragment = new AllTeamsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}
