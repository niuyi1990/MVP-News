package com.niuyi.mvp_news.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.base.BaseFragment;
import com.niuyi.mvp_news.base.BasePresenter;

/**
 * 科技
 * 作者：${牛毅} on 2016/11/30 10:48
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FragmenSports extends BaseFragment {


    public static FragmenSports newInstance() {
        Bundle args = new Bundle();
        FragmenSports fragment = new FragmenSports();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_sports;
    }

    @Override
    protected void initView(View view) {

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
}
