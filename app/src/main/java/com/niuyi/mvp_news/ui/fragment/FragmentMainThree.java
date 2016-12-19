package com.niuyi.mvp_news.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.base.BaseFragment;
import com.niuyi.mvp_news.base.BasePresenter;

import butterknife.BindView;

/**
 * 作者：${牛毅} on 2016/12/5 13:08
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FragmentMainThree extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_weixin)
    RecyclerView mRvWeixin;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;

    public static FragmentMainThree newInstance() {
        Bundle args = new Bundle();
        FragmentMainThree fragment = new FragmentMainThree();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_main_three;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void setListener(Context mContext) {
        mSwipeLayout.setOnRefreshListener(this);
    }

    @Override
    protected void toDo(Context mContext) {

    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    public void onRefresh() {

    }

}
