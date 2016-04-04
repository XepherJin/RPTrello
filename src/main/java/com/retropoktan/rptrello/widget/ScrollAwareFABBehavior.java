package com.retropoktan.rptrello.widget;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

/**
 * Created by RetroPoktan on 2/22/16.
 */
public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {

    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private boolean isInAnimation;
    private FABAnimatorListener mViewPropertyAnimatorListener;
    private Animation animIn, animOut;

    public ScrollAwareFABBehavior(Context context, AttributeSet attributeSet) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (isInAnimation) return;
        if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
            animate(child, false);
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            animate(child, true);
        }
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }

    private void animate(final FloatingActionButton fab, boolean isIn) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (mViewPropertyAnimatorListener == null) {
                mViewPropertyAnimatorListener = new FABAnimatorListener() {

                    public boolean isIn;

                    public void setAnimatorIn(boolean isIn) {
                        this.isIn = isIn;
                    }

                    @Override
                    public void onAnimationStart(View view) {
                        isInAnimation = true;
                        if (isIn) {
                            view.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        isInAnimation = false;
                        if (!isIn) {
                            view.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onAnimationCancel(View view) {
                        isInAnimation = false;
                    }
                };
            }
            mViewPropertyAnimatorListener.setAnimatorIn(isIn);
            ViewCompat.animate(fab)
                    .scaleX(isIn ? 1.0f : 0.0f)
                    .scaleY(isIn ? 1.0f : 0.0f)
                    .setInterpolator(INTERPOLATOR)
                    .withLayer()
                    .setListener(mViewPropertyAnimatorListener)
                    .start();
        } else {
            if (animIn == null) {
                animIn = AnimationUtils.loadAnimation(
                        fab.getContext(), android.support.design.R.anim.design_fab_in);
                animIn.setDuration(200L);
                animIn.setInterpolator(INTERPOLATOR);
                animIn.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        isInAnimation = true;
                        fab.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        isInAnimation = false;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
            if (animOut == null) {
                animOut = AnimationUtils.loadAnimation(
                        fab.getContext(), android.support.design.R.anim.design_fab_out);
                animOut.setDuration(200L);
                animOut.setInterpolator(INTERPOLATOR);
                animOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        isInAnimation = true;
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        isInAnimation = false;
                        fab.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
            fab.startAnimation(isIn ? animIn : animOut);
        }
    }

    private interface FABAnimatorListener extends ViewPropertyAnimatorListener {
        void setAnimatorIn(boolean isIn);
    }
}

