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
import com.niuyi.mvp_news.bean.DomesticNewsBean;
import com.niuyi.mvp_news.mvp.contract.DomesticContract;
import com.niuyi.mvp_news.mvp.presenter.DomesticPresenter;
import com.niuyi.mvp_news.ui.activity.NewsDetailsActivity;
import com.niuyi.mvp_news.ui.adapter.DomesticAdapter;
import com.niuyi.mvp_news.ui.widght.RecycleViewDivider;
import com.niuyi.mvp_news.ui.widght.RecyclerItemClickSupport;
import com.niuyi.mvp_news.utils.DensityUtils;
import com.niuyi.mvp_news.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 国内
 * 作者：${牛毅} on 2016/11/30 10:48
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FragmentDomestic extends BaseFragment<DomesticPresenter> implements DomesticContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_domestic_news)
    RecyclerView mRvDomesticNews;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;
    private ArrayList<DomesticNewsBean.ResultBean.DataBean> mList = new ArrayList<>();
    private DomesticAdapter mAdapter;

    public static FragmentDomestic newInstance() {
        Bundle args = new Bundle();
        FragmentDomestic fragment = new FragmentDomestic();
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
        mPresenter.getDomesticDate();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected DomesticPresenter onCreatePresenter() {
        return new DomesticPresenter(this);
    }

    @Override
    public void showDialog() {
        mSwipeLayout.setRefreshing(true);
    }

    @Override
    public void onSucceed(DomesticNewsBean domesticNewsBean) {
        mList.clear();
        mList = domesticNewsBean.getResult().getData();
        initDomesticView();
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

    private void initDomesticView() {
        if (mAdapter == null) {
            mAdapter = new DomesticAdapter();
            mRvDomesticNews.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter.setData(mList);
            mRvDomesticNews.setAdapter(mAdapter);

            RecyclerItemClickSupport.addTo(mRvDomesticNews).setOnItemClickListener(mOnClick);

            mRvDomesticNews.addItemDecoration(new RecycleViewDivider(getActivity(), StaggeredGridLayoutManager.VERTICAL,
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
