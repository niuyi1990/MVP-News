package com.niuyi.mvp_news.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.base.BaseFragment;
import com.niuyi.mvp_news.bean.FunnyJokeBean;
import com.niuyi.mvp_news.mvp.contract.JokeContract;
import com.niuyi.mvp_news.mvp.presenter.JokePresenter;
import com.niuyi.mvp_news.utils.ToastUtil;

import butterknife.BindView;

/**
 * 作者：${牛毅} on 2016/12/6 11:19
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FragmentFunnyJoke extends BaseFragment<JokePresenter> implements JokeContract.View,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_funny_joke)
    RecyclerView mRvFunnyJoke;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;

    public static FragmentFunnyJoke newInstance() {
        Bundle args = new Bundle();
        FragmentFunnyJoke fragment = new FragmentFunnyJoke();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_funny_joke;
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
        mPresenter.getFunnyJoke();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected JokePresenter onCreatePresenter() {
        return new JokePresenter(this);
    }

    @Override
    public void showDialog() {
        mSwipeLayout.setRefreshing(true);
    }

    @Override
    public void onSucceed(FunnyJokeBean funnyJokeBean) {

    }

    @Override
    public void onFail(String err) {
        ToastUtil.showToast(getActivity(), getString(R.string.loading_fail));
    }

    @Override
    public void hideDialog() {
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }
}
