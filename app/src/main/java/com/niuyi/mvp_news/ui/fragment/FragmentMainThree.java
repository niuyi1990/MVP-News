package com.niuyi.mvp_news.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.base.BaseFragment;
import com.niuyi.mvp_news.bean.WeiXinBean;
import com.niuyi.mvp_news.mvp.contract.WeiXinContract;
import com.niuyi.mvp_news.mvp.presenter.WeiXinPresenter;
import com.niuyi.mvp_news.ui.activity.WeiXinDetailActivity;
import com.niuyi.mvp_news.ui.adapter.WeiXinAdapter;
import com.niuyi.mvp_news.ui.widght.CustomLoadMoreView;
import com.niuyi.mvp_news.ui.widght.SpacesItemDecoration;
import com.niuyi.mvp_news.utils.DensityUtils;
import com.niuyi.mvp_news.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：${牛毅} on 2016/12/5 13:08
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FragmentMainThree extends BaseFragment<WeiXinPresenter> implements WeiXinContract.View,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_weixin)
    RecyclerView mRvWeixin;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;

    private WeiXinAdapter mAdapter;

    public static FragmentMainThree newInstance() {
        Bundle args = new Bundle();
        FragmentMainThree fragment = new FragmentMainThree();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_main_three;
    }

    @Override
    protected void initView(View view) {
        mSwipeLayout.setColorSchemeResources(R.color.colorPrimary);
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
        mPresenter.refresh();
    }

    @Override
    protected WeiXinPresenter onCreatePresenter() {
        return new WeiXinPresenter(this);
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
    public void hideRefreshDialog() {
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onRefreshSucceed(List<WeiXinBean.ResultBean.ListBean> list) {
        if (mAdapter == null) {
            initWeiXinView(list);
        } else {
            mAdapter.setNewData(list);
        }
    }

    @Override
    public void onRefreshFail(String err) {
        ToastUtil.showToast(getActivity(), getString(R.string.loading_fail));
    }

    @Override
    public void onLoadMoreSucceed(List<WeiXinBean.ResultBean.ListBean> list) {
        mAdapter.addData(list);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadMoreFail(String err) {
        mAdapter.loadMoreFail();
        ToastUtil.showToast(getActivity(), getString(R.string.loading_fail));
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadmore();
    }

    private void initWeiXinView(final List<WeiXinBean.ResultBean.ListBean> list) {
        mAdapter = new WeiXinAdapter(list);
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());//自定义上拉加载布局
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
//        mAdapter.setAutoLoadMoreSize(3);//距离底布多少item预加载
        mRvWeixin.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRvWeixin.setAdapter(mAdapter);

        mRvWeixin.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 15f)));

        mRvWeixin.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), WeiXinDetailActivity.class);
                intent.putExtra("weixin_url", list.get(position).getUrl());
                startActivity(intent);
            }
        });
    }
}
