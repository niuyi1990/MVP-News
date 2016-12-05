package com.niuyi.mvp_news.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by HongJay on 2016/8/11.
 */
public class MyTabFragmentPagerAdapter extends FragmentPagerAdapter {

    private String mTitles[] = new String[]{"头条", "社会", "国内", "国外", "娱乐", "体育", "军事", "科技", "财经", "时尚"};
    private List<Fragment> mFragmentList;

    public MyTabFragmentPagerAdapter(FragmentManager supportFragmentManager,List<Fragment> list) {
        super(supportFragmentManager);
        mFragmentList = list;
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
