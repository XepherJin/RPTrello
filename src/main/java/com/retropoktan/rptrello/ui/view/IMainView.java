package com.retropoktan.rptrello.ui.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by RetroPoktan on 4/8/16.
 */
public interface IMainView extends IView {
    void setToolbarTitle(CharSequence text);

    void setToolbarTitle(int resId);

    FragmentManager fragmentManager();

    int currentItemId();

    void setCurrentItemId(int itemId);

    Fragment boardsFragment();

    Fragment teamsFragment();

    Fragment cardsFragment();

    void closeDrawer();

    void closeDrawerDelayed(long delayMillis);

    boolean isDrawerOpen();

    boolean moveTaskToBack();

    void goSettings();

    void goAbout();
}
