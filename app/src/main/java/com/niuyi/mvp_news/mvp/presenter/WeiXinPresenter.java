package com.niuyi.mvp_news.mvp.presenter;

import com.niuyi.mvp_news.bean.WeiXinBean;
import com.niuyi.mvp_news.mvp.contract.WeiXinContract;
import com.niuyi.mvp_news.mvp.model.WeiXinModel;
import com.orhanobut.logger.Logger;

import rx.Subscriber;
import rx.Subscription;

/**
 * 作者：${牛毅} on 2016/12/20 09:42
 * 邮箱：niuyi19900923@hotmail.com
 */

public class WeiXinPresenter extends WeiXinContract.Presenter {

    private int mPage = 1;//当前加载第几页

    public WeiXinPresenter(WeiXinContract.View view) {
        mView = view;
        mModel = new WeiXinModel();
    }

    @Override
    public void getWeiXinData() {
        Logger.e("mPage====" + mPage);
        Subscription subscription = mModel.getWeiXinData(mPage)
                .subscribe(new Subscriber<WeiXinBean>() {
                    @Override
                    public void onStart() {
                        if (mPage == 1) mView.showRefreshDialog();//显示下拉刷新
                    }

                    @Override
                    public void onCompleted() {
                        if (mPage == 2) mView.hideRefreshDialog();//隐藏下拉刷新
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mPage == 1) {//下拉刷新
                            mView.onRefreshFail(e.getMessage());
                        } else {//加载更多
                            mView.onLoadMoreFail(e.getMessage());
                        }
                        onCompleted();
                    }

                    @Override
                    public void onNext(WeiXinBean weiXinBean) {
                        if (mPage == 1) {//下拉刷新成功
                            mPage = 2;//下拉刷新成功后，初始化为第二页，加载更多从第二页开始
                            mView.onRefreshSucceed(weiXinBean.getResult().getList());
                        } else {//加载更多
                            mPage++;
                            mView.onLoadMoreSucceed(weiXinBean.getResult().getList());
                        }
                    }
                });

        addSubscribe(subscription);
    }

    @Override
    public void refresh() {
        mPage = 1;//下拉刷新，初始化为加载第一页
        getWeiXinData();
    }

    @Override
    public void loadmore() {
        getWeiXinData();
    }
}
