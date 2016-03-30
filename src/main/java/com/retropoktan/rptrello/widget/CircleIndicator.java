package com.retropoktan.rptrello.widget;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.utils.DisplayUtil;

import static android.support.v4.view.ViewPager.OnPageChangeListener;

/**
 * recoded from CircleIndicator by ongakuer (https://github.com/ongakuer/CircleIndicator).
 *
 * @author RetroPoktan
 */
public class CircleIndicator extends LinearLayout {

    private static final int DEFAULT_INDICATOR_WIDTH = 5;
    private ViewPager mViewPager;
    private int mIndicatorWidth = -1;
    private int mIndicatorHeight = -1;
    private int mIndicatorMargin = -1;
    private int mAnimatorInResId, mAnimatorOutResId;
    private Animator mAnimatorIn, mAnimatorOut;
    // immediate result
    private Animator mImmediateAnimatorIn, mImmediateAnimatorOut;
    private int mDrawableSelected, mDrawableUnselected;

    private DataSetObserver mDataSetObserver;
    private int mLastPos = -1;

    private final OnPageChangeListener mPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (mViewPager.getAdapter() == null || mViewPager.getAdapter().getCount() <= 0) {
                return;
            }
            if (mAnimatorIn.isRunning()) mAnimatorIn.end();
            if (mAnimatorOut.isRunning()) mAnimatorOut.end();
            if (mLastPos >= 0) {
                View lastView = getChildAt(mLastPos);
                lastView.setBackgroundResource(mDrawableUnselected);
                mAnimatorOut.setTarget(lastView);
                mAnimatorOut.start();
            }
            View curView = getChildAt(position);
            curView.setBackgroundResource(mDrawableSelected);
            mAnimatorIn.setTarget(curView);
            mAnimatorIn.start();
            mLastPos = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public CircleIndicator(Context context) {
        this(context, null);
    }

    public CircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleIndicator);
            mIndicatorWidth = array.getDimensionPixelSize(R.styleable.CircleIndicator_indicatorWidth, mIndicatorWidth);
            mIndicatorHeight = array.getDimensionPixelSize(R.styleable.CircleIndicator_indicatorHeight, mIndicatorHeight);
            mIndicatorMargin = array.getDimensionPixelSize(R.styleable.CircleIndicator_indicatorMargin, mIndicatorMargin);
            mDrawableSelected = array.getResourceId(R.styleable.CircleIndicator_indicatorDrawableSelected, mDrawableSelected);
            mDrawableUnselected = array.getResourceId(R.styleable.CircleIndicator_indicatorDrawableUnselected, mDrawableUnselected);
            mAnimatorInResId = array.getResourceId(R.styleable.CircleIndicator_animatorIn, R.animator.scale_with_alpha);
            mAnimatorOutResId = array.getResourceId(R.styleable.CircleIndicator_animatorOut, mAnimatorOutResId);
            array.recycle();
        }
        setIndicatorConfiguration(context);
    }

    private void setIndicatorConfiguration(Context context) {
        mIndicatorWidth = (mIndicatorWidth < 0) ? DisplayUtil.dip2px(context, DEFAULT_INDICATOR_WIDTH) : mIndicatorWidth;
        mIndicatorHeight = (mIndicatorHeight < 0) ? DisplayUtil.dip2px(context, DEFAULT_INDICATOR_WIDTH) : mIndicatorHeight;
        mIndicatorMargin = (mIndicatorMargin < 0) ? DisplayUtil.dip2px(context, DEFAULT_INDICATOR_WIDTH) : mIndicatorMargin;
        mDrawableSelected =
                (mDrawableSelected == 0) ? R.drawable.circle_indicator_white_radius : mDrawableSelected;
        mDrawableUnselected =
                (mDrawableUnselected == 0) ? R.drawable.circle_indicator_white_radius : mDrawableUnselected;
        mAnimatorInResId = (mAnimatorInResId == 0) ? R.animator.scale_with_alpha : mAnimatorInResId;

        mAnimatorIn = createAnimatorIn(context);
        mAnimatorOut = createAnimatorOut(context);
        mImmediateAnimatorIn = createAnimatorIn(context);
        mImmediateAnimatorIn.setDuration(0);
        mImmediateAnimatorOut = createAnimatorOut(context);
        mImmediateAnimatorOut.setDuration(0);

        mDataSetObserver = new DataSetObserver() {
            @Override
            public void onChanged() {
                int newCount = mViewPager.getAdapter().getCount();
                int curCount = getChildCount();
                if (newCount == curCount) {
                    return;
                } else if (mLastPos < newCount) {
                    mLastPos = mViewPager.getCurrentItem();
                } else {
                    mLastPos = -1;
                }
                createIndicators();
            }
        };
    }

    private Animator createAnimatorIn(Context context) {
        return AnimatorInflater.loadAnimator(context, mAnimatorInResId);
    }

    private Animator createAnimatorOut(Context context) {
        Animator out;
        if (mAnimatorOutResId == 0) {
            out = AnimatorInflater.loadAnimator(context, mAnimatorInResId);
            out.setInterpolator(new ReverseInterpolator());
        } else {
            out = AnimatorInflater.loadAnimator(context, mAnimatorOutResId);
        }
        return out;
    }


    public void setViewPager(ViewPager viewPager) {
        if (mViewPager == viewPager) {
            return;
        }
        if (viewPager != null && viewPager.getAdapter() != null) {
            mViewPager = viewPager;
            createIndicators();
            mViewPager.removeOnPageChangeListener(mPageChangeListener);
            mViewPager.addOnPageChangeListener(mPageChangeListener);
            mViewPager.getAdapter().registerDataSetObserver(mDataSetObserver);
            mLastPos = mViewPager.getCurrentItem();
        }
    }

    private void createIndicators() {
        removeAllViews();
        int count = mViewPager.getAdapter().getCount();
        if (count <= 0) {
            return;
        }
        int curItem = mViewPager.getCurrentItem();
        for (int i = 0; i < count; i++) {
            if (curItem != i) {
                addIndicator(mDrawableUnselected, mImmediateAnimatorOut);
            } else {
                addIndicator(mDrawableSelected, mImmediateAnimatorIn);
            }
        }
    }

    private void addIndicator(int drawableId, Animator immediateAnimator) {
        if (immediateAnimator.isRunning()) immediateAnimator.end();
        View indicator = new View(getContext());
        indicator.setBackgroundResource(drawableId);
        addView(indicator, mIndicatorWidth, mIndicatorHeight);
        LayoutParams lp = (LayoutParams) indicator.getLayoutParams();
        lp.leftMargin = mIndicatorMargin;
        lp.rightMargin = mIndicatorMargin;
        indicator.setLayoutParams(lp);
        immediateAnimator.setTarget(indicator);
        immediateAnimator.start();
    }

    public void addOnPageChangeListener(OnPageChangeListener l) {
        if (mViewPager == null) {
            throw new NullPointerException("can not find viewpager, set viewpager first!");
        }
        mViewPager.removeOnPageChangeListener(l);
        mViewPager.addOnPageChangeListener(l);
    }

    private static class ReverseInterpolator implements Interpolator {
        @Override
        public float getInterpolation(float input) {
            return Math.abs(1.0f - input);
        }
    }

}
