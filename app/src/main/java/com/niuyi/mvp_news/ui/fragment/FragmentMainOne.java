package com.niuyi.mvp_news.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.base.BaseFragment;
import com.niuyi.mvp_news.base.BasePresenter;
import com.niuyi.mvp_news.ui.adapter.TabFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：${牛毅} on 2016/12/5 13:08
 * 邮箱：niuyi19900923@hotmail.com
 */

public class FragmentMainOne extends BaseFragment {

    @BindView(R.id.tab_news)
    TabLayout mTabNews;
    @BindView(R.id.vp_news_pager)
    ViewPager mVpNewsPager;
    @BindView(R.id.rl_more)
    RelativeLayout mRlMore;

    String mTitles[] = new String[]{"头条", "社会", "国内", "国外", "娱乐", "体育", "军事", "科技", "财经", "时尚"};

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
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @OnClick(R.id.rl_more)
    public void onClick() {
        showPop();
    }

    private void initTab() {
        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(FragmentNewsTop.newInstance());
        fragmentList.add(FragmentNewsSociety.newInstance());
        fragmentList.add(FragmentNewsDome.newInstance());
        fragmentList.add(FragmentNewsInte.newInstance());
        fragmentList.add(FragmenNewsTent.newInstance());
        fragmentList.add(FragmenNewsSports.newInstance());
        fragmentList.add(FragmenNewsMilitary.newInstance());
        fragmentList.add(FragmenNewsTech.newInstance());
        fragmentList.add(FragmenNewsFinancial.newInstance());
        fragmentList.add(FragmenNewsFashion.newInstance());

        TabFragmentPagerAdapter mTabFragmentPagerAdapter = new TabFragmentPagerAdapter(
                getActivity().getSupportFragmentManager(), fragmentList, mTitles);
        mVpNewsPager.setAdapter(mTabFragmentPagerAdapter);
        mTabNews.setupWithViewPager(mVpNewsPager);
    }

    private void showPop() {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_news, null);

        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        GridView gv_pop = (GridView) contentView.findViewById(R.id.gv_pop);

        gv_pop.setAdapter(new PopAdapter());
        gv_pop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mVpNewsPager.setCurrentItem(position);
                popupWindow.dismiss();
            }
        });

        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_pop_news));// 此处为popwindow 设置背景，同事做到点击外部区域，popwindow消失

        popupWindow.setOutsideTouchable(true); //设置非PopupWindow区域可触摸
        popupWindow.setFocusable(true); //设置PopupWindow可获得焦点

        popupWindow.showAsDropDown(mTabNews);// 设置好参数之后再show
    }

    class PopAdapter extends BaseAdapter {

        @Override
        public int getCount() {//这里先写死
            return mTitles.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pop_news, parent, false);
            }

            new ViewHolder(convertView).mTextview.setText(mTitles[position]);
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.textview)
            TextView mTextview;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
