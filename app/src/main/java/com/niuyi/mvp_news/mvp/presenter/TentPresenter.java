package com.niuyi.mvp_news.mvp.presenter;

import com.niuyi.mvp_news.bean.TentNewsBean;
import com.niuyi.mvp_news.mvp.contract.TentContract;
import com.niuyi.mvp_news.mvp.model.TentModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * 作者：${牛毅} on 2016/12/5 16:08
 * 邮箱：niuyi19900923@hotmail.com
 */

public class TentPresenter extends TentContract.Presenter {

    public TentPresenter(TentContract.View view) {
        mView = view;
        mModel = new TentModel();
    }

    @Override
    public void getTentertainmentData() {

        Subscription subscription = mModel.getTentertainmentData()
                .subscribe(new Subscriber<TentNewsBean>() {
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
                        onCompleted();
                        mView.onRefreshFail(e.getMessage());
                    }

                    @Override
                    public void onNext(TentNewsBean tentertainmentNewsBean) {
                        mView.onRefreshSucceed(tentertainmentNewsBean.getResult().getData());
                    }
                });

        addSubscribe(subscription);
    }

    @Override
    public void refresh() {
        getTentertainmentData();
    }
}
