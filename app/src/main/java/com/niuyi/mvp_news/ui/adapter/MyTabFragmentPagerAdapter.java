package com.niuyi.mvp_news.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.niuyi.mvp_news.constant.Constant;

import java.util.List;


public class MyTabFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;

    public MyTabFragmentPagerAdapter(FragmentManager supportFragmentManager, List<Fragment> list) {
        super(supportFragmentManager);
        mFragmentList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return Constant.mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constant.mTitles[position];
    }

}
