package com.retropoktan.rptrello.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by RetroPoktan on 12/13/15.
 */
public abstract class BaseFragment extends Fragment {

    protected View root;
    private Unbinder mUnbinder;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        parseArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(getLayoutId(), container, false);
        }
        if (root.getParent() != null) {
            ViewGroup parent = (ViewGroup) root.getParent();
            parent.removeView(root);
        }
        mUnbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (getContext() != null) {
            setupComponent();
        }
        initViews(view);
        addListeners();
        super.onViewCreated(view, savedInstanceState);
    }

    protected void addListeners() {

    }

    protected abstract void initViews(View view);

    protected void setupComponent() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyPresenter();
    }

    protected void destroyPresenter() {

    }

    protected abstract int getLayoutId();

    protected void showToast(CharSequence text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(int resId) {
        Toast.makeText(getActivity(), resId, Toast.LENGTH_SHORT).show();
    }

    protected void parseArguments() {

    }

    protected Bundle getActivityTransitionAnimBundle() {
        return ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity()).toBundle();
    }

}
