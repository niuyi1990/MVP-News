package com.niuyi.mvp_news.mvp.presenter;

import com.niuyi.mvp_news.bean.TopNewsBean;
import com.niuyi.mvp_news.mvp.contract.TopContract;
import com.niuyi.mvp_news.mvp.model.TopModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * 作者：${牛毅} on 2016/11/30 10:04
 * 邮箱：niuyi19900923@hotmail.com
 */
public class TopPresenter extends TopContract.Presenter {

    public TopPresenter(TopContract.View view) {
        mView = view;
        mModel = new TopModel();
    }

    @Override
    public void getTopNews() {

        Subscription subscription = mModel.getTopNews()
                .subscribe(new Subscriber<TopNewsBean>() {
                    @Override
                    public void onStart() {
                        mView.showRefreshDialog();
                    }

                    @Override
                    public void onCompleted() {
                        mView.hideRefreshDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onRefreshFail(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(TopNewsBean topNewsBean) {
                        mView.onRefreshSucceed(topNewsBean.getResult().getData());
                    }
                });

        addSubscribe(subscription);

    }

    @Override
    public void refresh() {
        getTopNews();
    }
}
