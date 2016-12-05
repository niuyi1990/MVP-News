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
import com.niuyi.mvp_news.bean.SocietyNewsBean;
import com.niuyi.mvp_news.mvp.contract.SocietyContract;
import com.niuyi.mvp_news.mvp.presenter.SocietyPresenter;
import com.niuyi.mvp_news.ui.activity.NewsDetailsActivity;
import com.niuyi.mvp_news.ui.adapter.SocietyNewsAdapter;
import com.niuyi.mvp_news.ui.widght.RecycleViewDivider;
import com.niuyi.mvp_news.ui.widght.RecyclerItemClickSupport;
import com.niuyi.mvp_news.utils.DensityUtils;
import com.niuyi.mvp_news.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 社会
 * 作者：${牛毅} on 2016/11/30 10:48
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FragmentSociety extends BaseFragment<SocietyPresenter> implements SocietyContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_top_news)
    RecyclerView mRvTopNews;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;
    private SocietyNewsAdapter mSocietyNewsAdapter;
    private ArrayList<SocietyNewsBean.ResultBean.DataBean> mList = new ArrayList<>();

    public static FragmentSociety newInstance() {
        Bundle args = new Bundle();
        FragmentSociety fragment = new FragmentSociety();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_society;
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
    public void showDialog() {
        mSwipeLayout.setRefreshing(true);
    }

    @Override
    public void onSucceed(SocietyNewsBean societyNewsBean) {
        mList.clear();
        mList = societyNewsBean.getResult().getData();
        Logger.e("mList===" + mList.size());
        initSocietyView();
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

    private void initSocietyView() {
        if (mSocietyNewsAdapter == null) {
            mSocietyNewsAdapter = new SocietyNewsAdapter();
            mRvTopNews.setLayoutManager(new LinearLayoutManager(getActivity()));
            mSocietyNewsAdapter.setData(mList);
            mRvTopNews.setAdapter(mSocietyNewsAdapter);

            RecyclerItemClickSupport.addTo(mRvTopNews).setOnItemClickListener(mOnClick);

            mRvTopNews.addItemDecoration(new RecycleViewDivider(getActivity(), StaggeredGridLayoutManager.VERTICAL,
                    DensityUtils.dp2px(getActivity(), 15), getResources().getColor(R.color.colorAccent)));
        } else {
            mSocietyNewsAdapter.setData(mList);
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
