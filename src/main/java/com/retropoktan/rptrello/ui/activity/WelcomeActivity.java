package com.retropoktan.rptrello.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.ui.adapter.WelcomePagerAdapter;
import com.retropoktan.rptrello.widget.CircleIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RetroPoktan on 12/28/15.
 */
public class WelcomeActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 0x01;

    @Bind(R.id.btn_welcome_login)
    Button btn_login;
    @Bind(R.id.btn_welcome_signup)
    Button btn_signup;
    @Bind(R.id.vp_welcome)
    ViewPager mViewPager;
    @Bind(R.id.vp_indicator_welcome)
    CircleIndicator mIndicator;

    private WelcomePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mPagerAdapter = new WelcomePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mIndicator.setViewPager(mViewPager);
    }

    @OnClick(R.id.btn_welcome_login)
    void goLogin() {
        startActivityForResult(new Intent(WelcomeActivity.this, LoginActivity.class), REQUEST_CODE);
    }

    @OnClick(R.id.btn_welcome_signup)
    void goSignup() {
        startActivityForResult(new Intent(WelcomeActivity.this, RegisterActivity.class), REQUEST_CODE);
    }

    @Override
    public void onBackPressed() {
        // avoid finishAfterTransition!!!!!!
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        finish();
    }

}
