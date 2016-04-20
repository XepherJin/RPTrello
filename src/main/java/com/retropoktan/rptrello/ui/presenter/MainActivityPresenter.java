package com.retropoktan.rptrello.ui.presenter;

import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.ui.fragment.AllBoardsFragment;
import com.retropoktan.rptrello.ui.fragment.AllCardsFragment;
import com.retropoktan.rptrello.ui.fragment.AllTeamsFragment;
import com.retropoktan.rptrello.ui.presenter.base.BasePresenter;
import com.retropoktan.rptrello.ui.view.IMainView;

/**
 * Created by RetroPoktan on 12/25/15.
 */
public class MainActivityPresenter extends BasePresenter<IMainView> {

    public void switchFragment(MenuItem menuItem) {
        int itemId = parseItemId(menuItem.getItemId());
        switchFragment(itemId);
    }

    public void switchFragment(int itemId) {
        if (mView.currentItemId() == itemId) {
            mView.closeDrawer();
            return;
        }
        mView.setCurrentItemId(itemId);
        FragmentTransaction ft = mView.fragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        switch (itemId) {
            case AllBoardsFragment.TYPE:
                ft.replace(R.id.content_main, mView.boardsFragment());
                mView.setToolbarTitle(R.string.Boards_title);
                break;
            case AllTeamsFragment.TYPE:
                ft.replace(R.id.content_main, mView.teamsFragment());
                mView.setToolbarTitle(R.string.Teams_title);
                break;
            case AllCardsFragment.TYPE:
                ft.replace(R.id.content_main, mView.cardsFragment());
                mView.setToolbarTitle(R.string.All_cards_title);
            default:
                break;
        }
        mView.closeDrawer();
        ft.commitAllowingStateLoss();
    }

    private int parseItemId(int menuItemid) {
        switch (menuItemid) {
            case R.id.nav_boards:
                return AllBoardsFragment.TYPE;
            case R.id.nav_teams:
                return AllTeamsFragment.TYPE;
            case R.id.nav_cards:
                return AllCardsFragment.TYPE;
            default:
                return -1;
        }
    }

    public boolean onBackPressed() {
        if (mView.isDrawerOpen()) {
            mView.closeDrawer();
            return true;
        }
        return mView.moveTaskToBack();
    }

}
