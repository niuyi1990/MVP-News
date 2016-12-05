package com.niuyi.mvp_news.mvp.presenter;

import com.niuyi.mvp_news.bean.TentertainmentNewsBean;
import com.niuyi.mvp_news.mvp.contract.TentertainmentContract;
import com.niuyi.mvp_news.mvp.model.TentertainmentModel;
import com.orhanobut.logger.Logger;

import rx.Subscriber;
import rx.Subscription;

/**
 * 作者：${牛毅} on 2016/12/5 16:08
 * 邮箱：niuyi19900923@hotmail.com
 */

public class TentertainmentPresenter extends TentertainmentContract.Presenter {

    public TentertainmentPresenter(TentertainmentContract.View view) {
        mView = view;
        mModel = new TentertainmentModel();
    }

    @Override
    public void getTentertainmentData() {

        Subscription subscription = mModel.getTentertainmentData()
                .subscribe(new Subscriber<TentertainmentNewsBean>() {
                    @Override
                    public void onStart() {
                        mView.showDialog();
                    }

                    @Override
                    public void onCompleted() {
                        mView.hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        onCompleted();
                        mView.onFail(e.getMessage());
                        Logger.e("e======" + e.getMessage());
                    }

                    @Override
                    public void onNext(TentertainmentNewsBean tentertainmentNewsBean) {
                        mView.onSucceed(tentertainmentNewsBean);
                        Logger.e("tentertainmentNewsBean===" + tentertainmentNewsBean.getResult().getData().size());
                    }
                });

        addSubscribe(subscription);
    }

    @Override
    public void refresh() {
        getTentertainmentData();
    }
}
