package com.retropoktan.rptrello.ui.view;

import com.retropoktan.rptrello.model.entity.User;

/**
 * Created by RetroPoktan on 12/25/15.
 */
public interface IUserLoginView extends IView {
    String getEmail();

    String getPassword();

    void actionEnabled();

    void actionDisabled();

    void emailError();

    void passwordError();

    void loginSuccess(User user);
}
