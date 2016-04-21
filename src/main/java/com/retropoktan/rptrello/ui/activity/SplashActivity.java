package com.retropoktan.rptrello.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityOptionsCompat;
import android.widget.ImageView;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by RetroPoktan on 12/28/15.
 */
public class SplashActivity extends BaseActivity {

    private static final int ENTER_CODE = 0x01;
    private static final int EXIT_CODE = 0x02;
    private static final int DELAY_TIME = 2000;
    private static final int HIDDEN_TIME = 2000;

    @Bind(R.id.iv_logo_big)
    ImageView logo;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ENTER_CODE:
                    if (isUserExist()) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        break;
                    }
                    Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                SplashActivity.this, logo, getResources().getString(R.string.app_name));
                        startActivity(intent, options.toBundle());
                    } else {
                        startActivity(intent);
                    }
                    break;
                case EXIT_CODE:
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mHandler.sendEmptyMessageDelayed(ENTER_CODE, DELAY_TIME);
        mHandler.sendEmptyMessageDelayed(EXIT_CODE, DELAY_TIME + HIDDEN_TIME);
    }

    @Override
    protected int getRootContentView() {
        return R.layout.activity_splash;
    }

    @Override
    public void onBackPressed() {

    }

    private boolean isUserExist() {
        return getApplicationComponent().getDataManager().getUser().isAvailable();
    }
}
