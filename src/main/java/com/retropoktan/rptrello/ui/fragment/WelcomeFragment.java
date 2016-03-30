package com.retropoktan.rptrello.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.ui.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by RetroPoktan on 1/2/16.
 */
public class WelcomeFragment extends BaseFragment {

    public static final String TAG = WelcomeFragment.class.getSimpleName();
    private static final String ARG_TITLE = "title";
    private static final String ARG_IMAGE = "image";
    private static final String ARG_COTENT = "content";
    @Bind(R.id.iv_fragment_welcome)
    ImageView iv;
    @Bind(R.id.tv_fragment_welcome_title)
    TextView tv_title;
    @Bind(R.id.tv_fragment_welcome_content)
    TextView tv_content;
    private int imageResId, titleResId, contentResId;
    private Drawable mDrawable;

    public static WelcomeFragment newInstance(int titleResId, int contentResId, int imageResId) {

        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE, imageResId);
        args.putInt(ARG_TITLE, titleResId);
        args.putInt(ARG_COTENT, contentResId);
        WelcomeFragment fragment = new WelcomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViews() {
        if (mDrawable == null) {
            mDrawable = ContextCompat.getDrawable(getActivity(), imageResId);
        }
        iv.setImageDrawable(mDrawable);
        tv_title.setText(titleResId);
        tv_content.setText(contentResId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_welcome;
    }

    @Override
    protected void parseArguments() {
        Bundle bundle = getArguments();
        imageResId = bundle.getInt(ARG_IMAGE);
        titleResId = bundle.getInt(ARG_TITLE);
        contentResId = bundle.getInt(ARG_COTENT);
    }
}
