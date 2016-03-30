package com.retropoktan.rptrello.ui.presenter;

import com.retropoktan.rptrello.model.UserUsecase;
import com.retropoktan.rptrello.ui.view.IUserRegisterView;

import javax.inject.Inject;

/**
 * Created by RetroPoktan on 2/10/16.
 */
public class UserRegisterPresenter extends BasePresenter<IUserRegisterView> {

    private final UserUsecase mUserUsecase;

    @Inject
    public UserRegisterPresenter(UserUsecase userUsecase) {
        mUserUsecase = userUsecase;
    }

}
