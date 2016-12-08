package com.niuyi.mvp_news.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.base.BaseFragment;
import com.niuyi.mvp_news.bean.FunnyPicBean;
import com.niuyi.mvp_news.mvp.contract.PictureContract;
import com.niuyi.mvp_news.mvp.presenter.PicturePresenter;
import com.niuyi.mvp_news.ui.adapter.FunnyPicAdapter;
import com.niuyi.mvp_news.ui.widght.CustomLoadMoreView;
import com.niuyi.mvp_news.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：${牛毅} on 2016/12/6 11:19
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FragmentFunnyPictures extends BaseFragment<PicturePresenter> implements PictureContract.View,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_funny_picture)
    RecyclerView mRvFunnyPicture;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;
    @BindView(R.id.iv_top)
    ImageView mIvTop;

    private FunnyPicAdapter mFunnyPicAdapter;

    private int page = 2;//上拉加载从第二页开始

    private List<FunnyPicBean.ResultBean.DataBean> mList = new ArrayList<>();

    public static FragmentFunnyPictures newInstance() {
        Bundle args = new Bundle();
        FragmentFunnyPictures fragment = new FragmentFunnyPictures();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_funny_pictures;
    }

    @Override
    protected void initView(View view) {
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_red_dark);
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
    protected PicturePresenter onCreatePresenter() {
        return new PicturePresenter(this);
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @OnClick(R.id.iv_top)
    public void onClick() {
        mRvFunnyPicture.smoothScrollToPosition(0);
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
    public void onRefreshSucceed(List<FunnyPicBean.ResultBean.DataBean> list) {
        mIvTop.setVisibility(View.VISIBLE);
        page = 2;
        mList = list;
        if (mFunnyPicAdapter == null) {
            initPicView();
        } else {
            mFunnyPicAdapter.setNewData(mList);
        }
    }

    @Override
    public void onRefreshFail(String err) {
        mIvTop.setVisibility(View.GONE);
        ToastUtil.showToast(getActivity(), getString(R.string.loading_fail));
    }

    @Override
    public void onLoadMoreSucceed(List<FunnyPicBean.ResultBean.DataBean> list) {
        mFunnyPicAdapter.addData(list);
        mFunnyPicAdapter.loadMoreComplete();
        page++;
    }

    @Override
    public void onLoadMoreFail(String err) {
        mFunnyPicAdapter.loadMoreFail();
        ToastUtil.showToast(getActivity(), getString(R.string.loading_fail));
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadmore(page);
    }

    private void initPicView() {
        mFunnyPicAdapter = new FunnyPicAdapter(mList);
        mFunnyPicAdapter.setOnLoadMoreListener(this);
        mFunnyPicAdapter.setLoadMoreView(new CustomLoadMoreView());//自定义上拉加载布局
        mFunnyPicAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
//        mFunnyPicAdapter.setAutoLoadMoreSize(3);//距离底布多少item预加载
        mRvFunnyPicture.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvFunnyPicture.setAdapter(mFunnyPicAdapter);
    }
}
