package com.retropoktan.rptrello.ui.view;

/**
 * Created by RetroPoktan on 12/25/15.
 */
public interface IUserLoginView extends IView {
    String getPhoneNum();

    String getPassword();

    void actionEnabled();

    void actionDisabled();

    void phoneNumError();

    void passwordError();

    void loginSuccess();
}
