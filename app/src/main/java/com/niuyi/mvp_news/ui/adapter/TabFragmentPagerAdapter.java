package com.niuyi.mvp_news.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;

    private String[] mTitles;

    public TabFragmentPagerAdapter(FragmentManager supportFragmentManager, List<Fragment> list, String[] titles) {
        super(supportFragmentManager);
        mFragmentList = list;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

}
