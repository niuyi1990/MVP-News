package com.niuyi.mvp_news.mvp.presenter;

import com.niuyi.mvp_news.bean.NewsBean;
import com.niuyi.mvp_news.mvp.contract.NewsContract;
import com.niuyi.mvp_news.mvp.model.NewsModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * 作者：${牛毅} on 2016/11/30 15:24
 * 邮箱：niuyi19900923@hotmail.com
 */
public class NewsPresenter extends NewsContract.Presenter {

    public NewsPresenter(NewsContract.View view) {
        mView = view;
        mModel = new NewsModel();
    }

    @Override
    public void getSocietyNews(String type) {

        Subscription subscription = mModel.getSocietyNews(type)
                .subscribe(new Subscriber<NewsBean>() {
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
                    public void onNext(NewsBean societyNewsBean) {
                        mView.onRefreshSucceed(societyNewsBean.getResult().getData());
                    }
                });

        addSubscribe(subscription);
    }

    @Override
    public void refresh(String type) {
        getSocietyNews(type);
    }

}
