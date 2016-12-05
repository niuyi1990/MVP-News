package com.niuyi.mvp_news.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.base.BaseFragment;
import com.niuyi.mvp_news.bean.TopNewsBean;
import com.niuyi.mvp_news.mvp.contract.TopContract;
import com.niuyi.mvp_news.mvp.presenter.TopPresenter;
import com.niuyi.mvp_news.ui.activity.NewsDetailsActivity;
import com.niuyi.mvp_news.ui.adapter.TopNewsAdapter;
import com.niuyi.mvp_news.ui.widght.RecycleViewDivider;
import com.niuyi.mvp_news.ui.widght.RecyclerItemClickSupport;
import com.niuyi.mvp_news.utils.DensityUtils;
import com.niuyi.mvp_news.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 头条
 * 作者：${牛毅} on 2016/11/30 10:48
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FragmentTop extends BaseFragment<TopPresenter> implements TopContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_top_news)
    RecyclerView mRvTopNews;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;

    private ArrayList<TopNewsBean.ResultBean.DataBean> mList = new ArrayList<>();
    private TopNewsAdapter mAdapter;

    public static FragmentTop newInstance() {
        Bundle args = new Bundle();
        FragmentTop fragment = new FragmentTop();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_top;
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
        mPresenter.getTopNews();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected TopPresenter onCreatePresenter() {
        return new TopPresenter(this);
    }

    @Override
    public void showDialog() {
        mSwipeLayout.setRefreshing(true);
    }

    @Override
    public void onSucceed(TopNewsBean topNewsBean) {
        mList.clear();
        mList = topNewsBean.getResult().getData();
        Logger.e("mList===" + mList.size());
        initTopView();
    }

    @Override
    public void onFail(String err) {
        ToastUtil.showToast(getActivity(), "加载失败");
    }

    @Override
    public void hideDialog() {
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    private void initTopView() {
        if (mAdapter == null) {
            mAdapter = new TopNewsAdapter();
            mRvTopNews.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter.setData(mList);
            mRvTopNews.setAdapter(mAdapter);

            RecyclerItemClickSupport.addTo(mRvTopNews).setOnItemClickListener(mOnClick);

            mRvTopNews.addItemDecoration(new RecycleViewDivider(getActivity(), StaggeredGridLayoutManager.VERTICAL,
                    DensityUtils.dp2px(getActivity(), 15), getResources().getColor(R.color.colorAccent)));
        } else {
            mAdapter.setData(mList);
        }
    }

    private RecyclerItemClickSupport.OnItemClickListener mOnClick = new RecyclerItemClickSupport.OnItemClickListener() {
        @Override
        public void onItemClicked(RecyclerView recyclerView, int position, View v) {
            Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
            intent.putExtra("url", mList.get(position).getUrl());
            getActivity().startActivity(intent);
        }
    };

}
