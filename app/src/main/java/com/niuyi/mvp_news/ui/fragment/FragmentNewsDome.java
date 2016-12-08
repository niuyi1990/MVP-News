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
import com.niuyi.mvp_news.bean.DomeNewsBean;
import com.niuyi.mvp_news.mvp.contract.DomeContract;
import com.niuyi.mvp_news.mvp.presenter.DomePresenter;
import com.niuyi.mvp_news.ui.activity.NewsDetailsActivity;
import com.niuyi.mvp_news.ui.adapter.NewsDomeAdapter;
import com.niuyi.mvp_news.ui.widght.RecycleViewDivider;
import com.niuyi.mvp_news.utils.DensityUtils;
import com.niuyi.mvp_news.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 国内
 * 作者：${牛毅} on 2016/11/30 10:48
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FragmentNewsDome extends BaseFragment<DomePresenter> implements DomeContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_domestic_news)
    RecyclerView mRvDomesticNews;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;

    private NewsDomeAdapter mAdapter;

    public static FragmentNewsDome newInstance() {
        Bundle args = new Bundle();
        FragmentNewsDome fragment = new FragmentNewsDome();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_domestic;
    }

    @Override
    protected void initView(View view) {
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_orange_dark);
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
        mPresenter.getDomesticDate();
    }

    @Override
    protected DomePresenter onCreatePresenter() {
        return new DomePresenter(this);
    }

    @Override
    public void showRefreshDialog() {
        mSwipeLayout.setRefreshing(true);
    }

    @Override
    public void onRefreshSucceed(List<DomeNewsBean.ResultBean.DataBean> list) {
        if (mAdapter == null) {
            initDomesticView(list);
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

    private void initDomesticView(final List<DomeNewsBean.ResultBean.DataBean> list) {
        mAdapter = new NewsDomeAdapter(list);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mRvDomesticNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvDomesticNews.setAdapter(mAdapter);

        mRvDomesticNews.addItemDecoration(new RecycleViewDivider(getActivity(), StaggeredGridLayoutManager.VERTICAL,
                DensityUtils.dp2px(getActivity(), 15), getResources().getColor(R.color.colorAccent)));

        mRvDomesticNews.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                intent.putExtra("url", list.get(position).getUrl());
                getActivity().startActivity(intent);
            }
        });
    }

}
