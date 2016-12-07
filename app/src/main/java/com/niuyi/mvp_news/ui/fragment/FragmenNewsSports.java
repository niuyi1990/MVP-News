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
import com.niuyi.mvp_news.bean.SportsNewsBean;
import com.niuyi.mvp_news.mvp.contract.SportsContract;
import com.niuyi.mvp_news.mvp.presenter.SportsPresenter;
import com.niuyi.mvp_news.ui.activity.NewsDetailsActivity;
import com.niuyi.mvp_news.ui.adapter.NewsSportsAdapter;
import com.niuyi.mvp_news.ui.widght.RecycleViewDivider;
import com.niuyi.mvp_news.utils.DensityUtils;
import com.niuyi.mvp_news.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 科技
 * 作者：${牛毅} on 2016/11/30 10:48
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FragmenNewsSports extends BaseFragment<SportsPresenter> implements SportsContract.View,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_sports_news)
    RecyclerView mRvSportsNews;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;
    private NewsSportsAdapter mNewsSportsAdapter;

    public static FragmenNewsSports newInstance() {
        Bundle args = new Bundle();
        FragmenNewsSports fragment = new FragmenNewsSports();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_sports;
    }

    @Override
    protected void initView(View view) {
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_orange_light);
    }

    @Override
    protected void setListener(Context mContext) {
        mSwipeLayout.setOnRefreshListener(this);
    }

    @Override
    protected void toDo(Context mContext) {
        mPresenter.getSportsData();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected SportsPresenter onCreatePresenter() {
        return new SportsPresenter(this);
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
    public void onRefreshSucceed(List<SportsNewsBean.ResultBean.DataBean> list) {
        if (mNewsSportsAdapter == null) {
            initSportsView(list);
        } else {
            mNewsSportsAdapter.setNewData(list);
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

    private void initSportsView(final List<SportsNewsBean.ResultBean.DataBean> list) {
        mNewsSportsAdapter = new NewsSportsAdapter(list);
        mNewsSportsAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mRvSportsNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvSportsNews.setAdapter(mNewsSportsAdapter);

        mRvSportsNews.addItemDecoration(new RecycleViewDivider(getActivity(), StaggeredGridLayoutManager.VERTICAL,
                DensityUtils.dp2px(getActivity(), 15), getResources().getColor(R.color.colorAccent)));

        mRvSportsNews.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                intent.putExtra("url", list.get(position).getUrl());
                getActivity().startActivity(intent);
            }
        });
    }
}
