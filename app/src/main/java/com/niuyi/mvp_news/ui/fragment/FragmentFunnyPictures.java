package com.niuyi.mvp_news.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.base.BaseFragment;
import com.niuyi.mvp_news.base.BasePresenter;

/**
 * 作者：${牛毅} on 2016/12/6 11:19
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FragmentFunnyPictures extends BaseFragment {

    public static FragmentFunnyPictures newInstance() {
        Bundle args = new Bundle();
        FragmentFunnyPictures fragment = new FragmentFunnyPictures();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_funny_pictures;
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
