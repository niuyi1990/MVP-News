package com.niuyi.mvp_news.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.base.BaseFragment;
import com.niuyi.mvp_news.base.BasePresenter;
import com.niuyi.mvp_news.ui.adapter.TabFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：${牛毅} on 2016/12/5 13:08
 * 邮箱：niuyi19900923@hotmail.com
 */

public class FragmentMainTwo extends BaseFragment {

    @BindView(R.id.tab_fun)
    TabLayout mTabFun;
    @BindView(R.id.vp_fun_pager)
    ViewPager mVpFunPager;

    String mTitles[] = new String[]{"笑话", "趣图"};

    public static FragmentMainTwo newInstance() {
        Bundle args = new Bundle();
        FragmentMainTwo fragment = new FragmentMainTwo();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_main_two;
    }

    @Override
    protected void initView(View view) {
        initTab();
    }

    @Override
    protected void setListener(Context mContext) {

    }

    @Override
    protected void toDo(Context mContext) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    private void initTab() {
        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(FragmentFunnyJoke.newInstance());
        fragmentList.add(FragmentFunnyPictures.newInstance());

        TabFragmentPagerAdapter mTabFragmentPagerAdapter = new TabFragmentPagerAdapter(
                getActivity().getSupportFragmentManager(), fragmentList, mTitles);
        mVpFunPager.setAdapter(mTabFragmentPagerAdapter);
        mTabFun.setupWithViewPager(mVpFunPager);
    }
}
