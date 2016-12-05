package com.niuyi.mvp_news.ui.widght;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 不能左右滑动的viewpager
 * 作者：${牛毅} on 2016/7/27 15:28
 * 邮箱：niuyi19900923@hotmail.com
 */
public class NoScrollViewPager extends ViewPager {

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //表示事件是否拦截，返回false表示不拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    //重写onTouchEvent事件，什么都不用做
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

}
