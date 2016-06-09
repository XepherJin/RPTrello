package com.retropoktan.rptrello.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Spinner;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.model.entity.Board;
import com.retropoktan.rptrello.ui.base.ToolbarBaseActivity;
import com.retropoktan.rptrello.ui.inject.component.DaggerBoardComponent;
import com.retropoktan.rptrello.ui.inject.module.BoardModule;
import com.retropoktan.rptrello.ui.presenter.BoardCreatePresenter;
import com.retropoktan.rptrello.ui.view.IBoardCreateView;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnTextChanged;

/**
 * Created by RetroPoktan on 6/9/16.
 */
public class BoardCreateActivity extends ToolbarBaseActivity implements IBoardCreateView {

    @BindView(R.id.et_board_create_layout)
    TextInputLayout name_layout;
    @BindView(R.id.et_board_description_layout)
    TextInputLayout description_layout;
    @BindView(R.id.spinner_board_create)
    Spinner spinner;
    @BindString(R.string.loading)
    String loading;
    @BindString(R.string.error_network_unavailable)
    String networkUnavailable;
    ProgressDialog mProgressDialog;
    @Inject
    BoardCreatePresenter presenter;

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void setToolbar(Toolbar toolbar) {
        toolbar.setTitle("Create Board");
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_board_create;
    }

    @Override
    protected boolean canBack() {
        return true;
    }

    @Override
    protected void onNavigationClick() {
        finish();
    }

    @OnTextChanged(R.id.et_board_create)
    void onNameChanged(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            actionDisabled();
        } else {
            actionEnabled();
        }
    }

    @Override
    protected void setupComponent() {
        DaggerBoardComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(getApplicationComponent())
                .boardModule(new BoardModule())
                .build()
                .inject(this);
    }

    @Override
    protected void attachViewForPresenter() {
        presenter.attachView(this);
    }

    @Override
    protected void destroyPresenter() {
        presenter.onDestroy();
        presenter = null;
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
        Snackbar.make(main, errMsg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void actionEnabled() {

    }

    @Override
    public void actionDisabled() {

    }

    @Override
    public String getBoardName() {
        return name_layout.getEditText().toString();
    }

    @Override
    public String getDescription() {
        return description_layout.getEditText().getText().toString();
    }

    @Override
    public long getTeamId() {
        return 0;
    }

    @Override
    public void createSuccess(Board board) {
        showToast("Create Success!");
    }
}
