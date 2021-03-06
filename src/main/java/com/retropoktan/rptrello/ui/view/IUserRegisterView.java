package com.retropoktan.rptrello.ui.view;

/**
 * Created by RetroPoktan on 2/9/16.
 */
public interface IUserRegisterView extends IUserLoginView {
    String getUserName();
    void userNameError();
    String getCode();
    void getCodeEnabled();
    void getCodeDisabled();

    void invalidateSecond(long second);

    void resetCodeBtn();

    void showGetCodeError(String msg);
}
