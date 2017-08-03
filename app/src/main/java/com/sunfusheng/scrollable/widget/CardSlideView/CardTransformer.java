package com.sunfusheng.scrollable.widget.CardSlideView;

import android.support.v4.view.ViewPager;
import android.view.View;

class CardTransformer implements ViewPager.PageTransformer {

    private int mMaxTranslateOffsetX;
    private ViewPager mViewPager;

    CardTransformer(int maxOffset) {
        mMaxTranslateOffsetX = maxOffset;
    }


    @Override
    public void transformPage(View page, float position) {
        if (mViewPager == null) {
            mViewPager = (ViewPager) page.getParent();
        }

        int leftInScreen = page.getLeft() - mViewPager.getScrollX();
        int centerXInViewPager = leftInScreen + page.getMeasuredWidth() / 2;
        int offsetX = centerXInViewPager - mViewPager.getMeasuredWidth() / 2;
        float offsetRate = (float) offsetX * 0.38f / mViewPager.getMeasuredWidth();
        float scaleFactor = 1 - Math.abs(offsetRate);

        if (scaleFactor > 0) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setTranslationX(-mMaxTranslateOffsetX * offsetRate);
        }
    }
}
