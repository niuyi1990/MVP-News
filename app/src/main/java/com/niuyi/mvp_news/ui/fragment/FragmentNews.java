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
import com.niuyi.mvp_news.bean.NewsBean;
import com.niuyi.mvp_news.mvp.contract.NewsContract;
import com.niuyi.mvp_news.mvp.presenter.NewsPresenter;
import com.niuyi.mvp_news.ui.activity.NewsDetailsActivity;
import com.niuyi.mvp_news.ui.adapter.NewsAdapter;
import com.niuyi.mvp_news.ui.widght.RecycleViewDivider;
import com.niuyi.mvp_news.utils.DensityUtils;
import com.niuyi.mvp_news.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 社会
 * 作者：${牛毅} on 2016/11/30 10:48
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FragmentNews extends BaseFragment<NewsPresenter> implements NewsContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_top_news)
    RecyclerView mRvTopNews;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;

    public static final String ARGUMENT = "type";

    private NewsAdapter mNewsAdapter;

    public static FragmentNews newInstance(String args) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, args);
        FragmentNews fragment = new FragmentNews();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_society;
    }

    @Override
    protected void initView(View view) {
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright);
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
        mPresenter.getSocietyNews(getArguments().getString(ARGUMENT));
    }

    @Override
    protected NewsPresenter onCreatePresenter() {
        return new NewsPresenter(this);
    }

    @Override
    public void showRefreshDialog() {
        mSwipeLayout.setRefreshing(true);
    }

    @Override
    public void onRefreshSucceed(List<NewsBean.ResultBean.DataBean> list) {
        if (mNewsAdapter == null) {
            initSocietyView(list);
        } else {
            mNewsAdapter.setNewData(list);
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
        mPresenter.refresh(getArguments().getString(ARGUMENT));
    }

    private void initSocietyView(final List<NewsBean.ResultBean.DataBean> list) {
        mNewsAdapter = new NewsAdapter(list);
        mNewsAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mRvTopNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvTopNews.setAdapter(mNewsAdapter);

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
