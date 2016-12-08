package com.niuyi.mvp_news.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.base.BaseFragment;
import com.niuyi.mvp_news.bean.TopNewsBean;
import com.niuyi.mvp_news.mvp.contract.TopContract;
import com.niuyi.mvp_news.mvp.presenter.TopPresenter;
import com.niuyi.mvp_news.ui.activity.NewsDetailsActivity;
import com.niuyi.mvp_news.ui.adapter.NewsTopAdapter;
import com.niuyi.mvp_news.ui.widght.RecycleViewDivider;
import com.niuyi.mvp_news.utils.DensityUtils;
import com.niuyi.mvp_news.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 头条
 * 作者：${牛毅} on 2016/11/30 10:48
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FragmentNewsTop extends BaseFragment<TopPresenter> implements TopContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_top_news)
    RecyclerView mRvTopNews;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;

    private NewsTopAdapter mAdapter;

    public static FragmentNewsTop newInstance() {
        Bundle args = new Bundle();
        FragmentNewsTop fragment = new FragmentNewsTop();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_top;
    }

    @Override
    protected void initView(View view) {
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_red_light);
    }

    @Override
    protected void setListener(Context mContext) {
        mSwipeLayout.setOnRefreshListener(this);
    }

    @Override
    protected void toDo(Context mContext) {

    }

    @Override
    protected void lazyLoadData() {
        mPresenter.getTopNews();
    }

    @Override
    protected TopPresenter onCreatePresenter() {
        return new TopPresenter(this);
    }

    @Override
    public void showRefreshDialog() {
        mSwipeLayout.setRefreshing(true);
    }

    @Override
    public void onRefreshSucceed(List<TopNewsBean.ResultBean.DataBean> list) {
        if (mAdapter == null) {
            initTopView(list);
        } else {
            mAdapter.setNewData(list);
        }
    }

    @Override
    public void onRefreshFail(String err) {
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

    private void initTopView(final List<TopNewsBean.ResultBean.DataBean> list) {
        mAdapter = new NewsTopAdapter(list);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mRvTopNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvTopNews.setAdapter(mAdapter);

        mRvTopNews.addItemDecoration(new RecycleViewDivider(getActivity(), StaggeredGridLayoutManager.VERTICAL,
                DensityUtils.dp2px(getActivity(), 15), getResources().getColor(R.color.colorAccent)));

        mRvTopNews.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                intent.putExtra("url", list.get(position).getUrl());
                getActivity().startActivity(intent);
            }
        });
    }

}
