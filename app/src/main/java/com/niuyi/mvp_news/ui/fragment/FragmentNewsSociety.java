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
import com.niuyi.mvp_news.bean.SocietyNewsBean;
import com.niuyi.mvp_news.mvp.contract.SocietyContract;
import com.niuyi.mvp_news.mvp.presenter.SocietyPresenter;
import com.niuyi.mvp_news.ui.activity.NewsDetailsActivity;
import com.niuyi.mvp_news.ui.adapter.NewsSocietyAdapter;
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
public class FragmentNewsSociety extends BaseFragment<SocietyPresenter> implements SocietyContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_top_news)
    RecyclerView mRvTopNews;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;

    private NewsSocietyAdapter mNewsSocietyAdapter;

    public static FragmentNewsSociety newInstance() {
        Bundle args = new Bundle();
        FragmentNewsSociety fragment = new FragmentNewsSociety();
        fragment.setArguments(args);
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
        mPresenter.getSocietyNews();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected SocietyPresenter onCreatePresenter() {
        return new SocietyPresenter(this);
    }

    @Override
    public void showRefreshDialog() {
        mSwipeLayout.setRefreshing(true);
    }

    @Override
    public void onRefreshSucceed(List<SocietyNewsBean.ResultBean.DataBean> list) {
        if (mNewsSocietyAdapter == null) {
            initSocietyView(list);
        } else {
            mNewsSocietyAdapter.setNewData(list);
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

    private void initSocietyView(final List<SocietyNewsBean.ResultBean.DataBean> list) {
        mNewsSocietyAdapter = new NewsSocietyAdapter(list);
        mNewsSocietyAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mRvTopNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvTopNews.setAdapter(mNewsSocietyAdapter);

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
