package com.retropoktan.rptrello.ui.presenter;

import com.retropoktan.rptrello.model.UserModel;
import com.retropoktan.rptrello.ui.presenter.base.BasePresenter;
import com.retropoktan.rptrello.ui.view.IUserRegisterView;

import javax.inject.Inject;

/**
 * Created by RetroPoktan on 2/10/16.
 */
public class UserRegisterPresenter extends BasePresenter<IUserRegisterView> {

    private final UserModel mUserModel;

    @Inject
    public UserRegisterPresenter(UserModel UserModel) {
        mUserModel = UserModel;
    }

    public void getCode() {
        mUserModel.getCode();
    }

}
