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
import com.niuyi.mvp_news.ui.adapter.MyTabFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：${牛毅} on 2016/12/5 13:08
 * 邮箱：niuyi19900923@hotmail.com
 */

public class FragmentMainOne extends BaseFragment {



    @BindView(R.id.tab_news)
    TabLayout mTabNews;
    @BindView(R.id.vp_news_pager)
    ViewPager mVpNewsPager;

    public static FragmentMainOne newInstance() {
        Bundle args = new Bundle();
        FragmentMainOne fragment = new FragmentMainOne();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_main_one;
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

        fragmentList.add(FragmentTop.newInstance());
        fragmentList.add(FragmentSociety.newInstance());
        fragmentList.add(FragmentDomestic.newInstance());
        fragmentList.add(FragmentInternational.newInstance());
        fragmentList.add(FragmenTentertainment.newInstance());
        fragmentList.add(FragmenSports.newInstance());
        fragmentList.add(FragmenMilitary.newInstance());
        fragmentList.add(FragmenTech.newInstance());
        fragmentList.add(FragmenFinancial.newInstance());
        fragmentList.add(FragmenFashion.newInstance());

        MyTabFragmentPagerAdapter mMyTabFragmentPagerAdapter = new MyTabFragmentPagerAdapter(
                getActivity().getSupportFragmentManager(), fragmentList);
        mVpNewsPager.setAdapter(mMyTabFragmentPagerAdapter);
        mTabNews.setupWithViewPager(mVpNewsPager);
    }
}
