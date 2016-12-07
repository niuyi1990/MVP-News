package com.niuyi.mvp_news.mvp.presenter;

import com.niuyi.mvp_news.bean.SportsNewsBean;
import com.niuyi.mvp_news.mvp.contract.SportsContract;
import com.niuyi.mvp_news.mvp.model.SportsModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * 作者：${牛毅} on 2016/12/7 10:10
 * 邮箱：niuyi19900923@hotmail.com
 */
public class SportsPresenter extends SportsContract.Presenter {

    public SportsPresenter(SportsContract.View view) {
        mView = view;
        mModel = new SportsModel();
    }

    @Override
    public void getSportsData() {

        Subscription subscription = mModel.getSportsData()
                .subscribe(new Subscriber<SportsNewsBean>() {
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
                    public void onNext(SportsNewsBean sportsNewsBean) {
                        mView.onRefreshSucceed(sportsNewsBean.getResult().getData());
                    }
                });

        addSubscribe(subscription);
    }

    @Override
    public void refresh() {
        getSportsData();
    }
}
