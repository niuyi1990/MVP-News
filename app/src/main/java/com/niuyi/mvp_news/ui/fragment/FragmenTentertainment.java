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
import com.niuyi.mvp_news.bean.TentertainmentNewsBean;
import com.niuyi.mvp_news.mvp.contract.TentertainmentContract;
import com.niuyi.mvp_news.mvp.presenter.TentertainmentPresenter;
import com.niuyi.mvp_news.ui.activity.NewsDetailsActivity;
import com.niuyi.mvp_news.ui.adapter.TentertainmentAdapter;
import com.niuyi.mvp_news.ui.widght.RecycleViewDivider;
import com.niuyi.mvp_news.ui.widght.RecyclerItemClickSupport;
import com.niuyi.mvp_news.utils.DensityUtils;
import com.niuyi.mvp_news.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 娱乐
 * 作者：${牛毅} on 2016/11/30 10:48
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FragmenTentertainment extends BaseFragment<TentertainmentPresenter> implements TentertainmentContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_tentertainment_news)
    RecyclerView mRvTentertainmentNews;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;

    private ArrayList<TentertainmentNewsBean.ResultBean.DataBean> mList = new ArrayList<>();
    private TentertainmentAdapter mAdapter;

    public static FragmenTentertainment newInstance() {
        Bundle args = new Bundle();
        FragmenTentertainment fragment = new FragmenTentertainment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_tentertainment;
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
        mPresenter.getTentertainmentData();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected TentertainmentPresenter onCreatePresenter() {
        return new TentertainmentPresenter(this);
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void showDialog() {
        mSwipeLayout.setRefreshing(true);
    }

    @Override
    public void onSucceed(TentertainmentNewsBean tentertainmentNewsBean) {
        mList.clear();
        mList = tentertainmentNewsBean.getResult().getData();
        initTenterView();
    }

    @Override
    public void onFail(String err) {
        ToastUtil.showToast(getActivity(), getString(R.string.loading_fail));
    }

    @Override
    public void hideDialog() {
        mSwipeLayout.setRefreshing(false);
    }

    private void initTenterView() {
        if (mAdapter == null) {
            mAdapter = new TentertainmentAdapter();
            mRvTentertainmentNews.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter.setData(mList);
            mRvTentertainmentNews.setAdapter(mAdapter);

            RecyclerItemClickSupport.addTo(mRvTentertainmentNews).setOnItemClickListener(mOnClick);

            mRvTentertainmentNews.addItemDecoration(new RecycleViewDivider(getActivity(), StaggeredGridLayoutManager.VERTICAL,
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
