package com.retropoktan.rptrello.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.ui.base.BaseFragment;
import com.retropoktan.rptrello.ui.fragment.WelcomeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RetroPoktan on 1/2/16.
 */
public class WelcomePagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    public WelcomePagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(WelcomeFragment.newInstance(
                R.string.Welcome_title_first,
                R.string.Welcome_content_first,
                R.drawable.welcome_hello_trello));
        fragments.add(WelcomeFragment.newInstance(
                R.string.Welcome_title_second,
                R.string.Welcome_content_second,
                R.drawable.welcome_get_organized));
        fragments.add(WelcomeFragment.newInstance(
                R.string.Welcome_title_third,
                R.string.Welcome_content_third,
                R.drawable.welcome_add_details));
        fragments.add(WelcomeFragment.newInstance(
                R.string.Welcome_title_forth,
                R.string.Welcome_content_forth,
                R.drawable.welcome_team_up));
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
