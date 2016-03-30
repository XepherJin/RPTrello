package com.retropoktan.rptrello.utils;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by RetroPoktan on 2/15/16.
 */
public class FragmentUtil {

    private FragmentUtil() {

    }

    public static void showDialogFragment(FragmentManager fm,
                                          DialogFragment fragment, String tag) {
        dismissDialogFragment(fm, tag);
        fragment.show(fm, tag);
    }

    public static void dismissDialogFragment(FragmentManager fm, String tag) {
        DialogFragment fragment = findFragment(fm, tag);
        if (fragment != null) {
            fragment.dismissAllowingStateLoss();
        }
    }

    public static <T extends Fragment> T findFragment(FragmentManager fm, String tag) {
        return (T) fm.findFragmentByTag(tag);
    }

    public static <T extends Fragment> T findFragment(FragmentManager fm, int id) {
        return (T) fm.findFragmentById(id);
    }
}
