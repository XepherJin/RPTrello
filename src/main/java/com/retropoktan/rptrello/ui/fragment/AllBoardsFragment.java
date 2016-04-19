package com.retropoktan.rptrello.ui.fragment;

import android.os.Bundle;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.ui.base.BaseFragment;

/**
 * Created by RetroPoktan on 4/19/16.
 */
public class AllBoardsFragment extends BaseFragment {

    public static final int TYPE = 0;

    public static AllBoardsFragment newInstance() {
        Bundle args = new Bundle();
        AllBoardsFragment fragment = new AllBoardsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }
}
