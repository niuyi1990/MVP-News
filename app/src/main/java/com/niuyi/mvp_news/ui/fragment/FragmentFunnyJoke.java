package com.niuyi.mvp_news.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.base.BaseFragment;
import com.niuyi.mvp_news.bean.FunnyJokeBean;
import com.niuyi.mvp_news.mvp.contract.JokeContract;
import com.niuyi.mvp_news.mvp.presenter.JokePresenter;
import com.niuyi.mvp_news.ui.adapter.FunnyJokeAdapter;
import com.niuyi.mvp_news.ui.widght.CustomLoadMoreView;
import com.niuyi.mvp_news.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：${牛毅} on 2016/12/6 11:19
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FragmentFunnyJoke extends BaseFragment<JokePresenter> implements JokeContract.View,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_funny_joke)
    RecyclerView mRvFunnyJoke;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;
    @BindView(R.id.iv_top)
    ImageView mIvTop;

    private int page = 2;//上拉加载从当前二页开始

    private List<FunnyJokeBean.ResultBean.DataBean> mList = new ArrayList<>();
    private FunnyJokeAdapter mAdapter;

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
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_dark);
    }

    @Override
    protected void setListener(Context mContext) {
        mSwipeLayout.setOnRefreshListener(this);
    }

    @Override
    protected void toDo(Context mContext) {
        mPresenter.refresh();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected JokePresenter onCreatePresenter() {
        return new JokePresenter(this);
    }

    @Override
    public void showRefreshDialog() {
        mSwipeLayout.setRefreshing(true);
    }

    @Override
    public void onRefreshSucceed(List<FunnyJokeBean.ResultBean.DataBean> data) {
        mIvTop.setVisibility(View.VISIBLE);
        page = 2;//下来刷新成功，初始化为即将加载第二页
        mList = data;
        if (mAdapter == null) {
            initFunnyJokeView();
        } else {
            mAdapter.setNewData(data);
        }
    }

    @Override
    public void onRefreshFail(String err) {
        mIvTop.setVisibility(View.GONE);
        ToastUtil.showToast(getActivity(), getString(R.string.loading_fail));
    }

    @Override
    public void onLoadMoreSucceed(List<FunnyJokeBean.ResultBean.DataBean> data) {
        mAdapter.addData(data);
        mAdapter.loadMoreComplete();
        page++;
    }

    @Override
    public void onLoadMoreFail(String err) {
        mAdapter.loadMoreFail();
        ToastUtil.showToast(getActivity(), getString(R.string.loading_fail));
    }

    @Override
    public void hideRefreshDialog() {
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadmore(page);
    }

    @OnClick(R.id.iv_top)
    public void onClick() {//快速滑倒顶部
        mRvFunnyJoke.smoothScrollToPosition(0);
    }

    private void initFunnyJokeView() {
        mAdapter = new FunnyJokeAdapter(mList);
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());//自定义上拉加载布局
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
//        mAdapter.setAutoLoadMoreSize(3);//距离底布多少item预加载
        mRvFunnyJoke.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvFunnyJoke.setAdapter(mAdapter);
    }
}
