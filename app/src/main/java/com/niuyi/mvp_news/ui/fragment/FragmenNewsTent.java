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
import com.niuyi.mvp_news.bean.TentNewsBean;
import com.niuyi.mvp_news.mvp.contract.TentContract;
import com.niuyi.mvp_news.mvp.presenter.TentPresenter;
import com.niuyi.mvp_news.ui.activity.NewsDetailsActivity;
import com.niuyi.mvp_news.ui.adapter.NewsTentAdapter;
import com.niuyi.mvp_news.ui.widght.RecycleViewDivider;
import com.niuyi.mvp_news.utils.DensityUtils;
import com.niuyi.mvp_news.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 娱乐
 * 作者：${牛毅} on 2016/11/30 10:48
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FragmenNewsTent extends BaseFragment<TentPresenter> implements TentContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_tentertainment_news)
    RecyclerView mRvTentertainmentNews;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;

    private NewsTentAdapter mAdapter;

    public static FragmenNewsTent newInstance() {
        Bundle args = new Bundle();
        FragmenNewsTent fragment = new FragmenNewsTent();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_tentertainment;
    }

    @Override
    protected void initView(View view) {
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_purple);
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
        mPresenter.getTentertainmentData();
    }

    @Override
    protected TentPresenter onCreatePresenter() {
        return new TentPresenter(this);
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void showRefreshDialog() {
        mSwipeLayout.setRefreshing(true);
    }

    @Override
    public void onRefreshSucceed(List<TentNewsBean.ResultBean.DataBean> list) {
        if (mAdapter == null) {
            initTenterView(list);
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

    private void initTenterView(final List<TentNewsBean.ResultBean.DataBean> list) {
        mAdapter = new NewsTentAdapter(list);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mRvTentertainmentNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvTentertainmentNews.setAdapter(mAdapter);

        mRvTentertainmentNews.addItemDecoration(new RecycleViewDivider(getActivity(), StaggeredGridLayoutManager.VERTICAL,
                DensityUtils.dp2px(getActivity(), 15), getResources().getColor(R.color.colorAccent)));

        mRvTentertainmentNews.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                intent.putExtra("url", list.get(position).getUrl());
                getActivity().startActivity(intent);
            }
        });
    }

}
