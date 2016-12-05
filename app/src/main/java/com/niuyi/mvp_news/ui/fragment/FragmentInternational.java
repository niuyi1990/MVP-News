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
import com.niuyi.mvp_news.bean.InternationalNewsBean;
import com.niuyi.mvp_news.mvp.contract.InternationalContract;
import com.niuyi.mvp_news.mvp.presenter.InternationalPresenter;
import com.niuyi.mvp_news.ui.activity.NewsDetailsActivity;
import com.niuyi.mvp_news.ui.adapter.InternationalAdapter;
import com.niuyi.mvp_news.ui.widght.RecycleViewDivider;
import com.niuyi.mvp_news.ui.widght.RecyclerItemClickSupport;
import com.niuyi.mvp_news.utils.DensityUtils;
import com.niuyi.mvp_news.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 国际
 * 作者：${牛毅} on 2016/11/30 10:48
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FragmentInternational extends BaseFragment<InternationalPresenter> implements InternationalContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_international_news)
    RecyclerView mRvInternationalNews;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;
    private ArrayList<InternationalNewsBean.ResultBean.DataBean> mList = new ArrayList<>();
    private InternationalAdapter mAdapter;

    public static FragmentInternational newInstance() {
        Bundle args = new Bundle();
        FragmentInternational fragment = new FragmentInternational();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_international;
    }

    @Override
    protected void initView(View view) {
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_green_dark);
    }

    @Override
    protected void setListener(Context mContext) {
        mSwipeLayout.setOnRefreshListener(this);
    }

    @Override
    protected void toDo(Context mContext) {
        mPresenter.getInternationData();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected InternationalPresenter onCreatePresenter() {
        return new InternationalPresenter(this);
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
    public void onSucceed(InternationalNewsBean internationalNewsBean) {
        mList.clear();
        mList = internationalNewsBean.getResult().getData();
        initInterView();
    }

    @Override
    public void onFail(String err) {
        ToastUtil.showToast(getActivity(), "加载失败");
    }

    @Override
    public void hideDialog() {
        mSwipeLayout.setRefreshing(false);
    }

    private void initInterView() {
        if (mAdapter == null) {
            mAdapter = new InternationalAdapter();
            mRvInternationalNews.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter.setData(mList);
            mRvInternationalNews.setAdapter(mAdapter);

            RecyclerItemClickSupport.addTo(mRvInternationalNews).setOnItemClickListener(mOnClick);

            mRvInternationalNews.addItemDecoration(new RecycleViewDivider(getActivity(), StaggeredGridLayoutManager.VERTICAL,
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
