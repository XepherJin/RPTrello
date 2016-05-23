package com.retropoktan.rptrello.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.retropoktan.rptrello.ui.fragment.CardFragment;

import java.util.List;

/**
 * Created by RetroPoktan on 5/22/16.
 */
public class CardPagerAdapter extends FragmentPagerAdapter {

    private List<CardFragment> fragments;

    public CardPagerAdapter(FragmentManager fm, List<CardFragment> list) {
        super(fm);
        fragments = list;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
