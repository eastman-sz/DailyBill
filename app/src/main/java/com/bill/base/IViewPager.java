package com.bill.base;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by E on 2018/2/8.
 */
public class IViewPager extends ViewPager {

    public IViewPager(Context context) {
        super(context);
    }

    public IViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
