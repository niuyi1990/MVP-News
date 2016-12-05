package com.niuyi.mvp_news.mvp.presenter;

import com.niuyi.mvp_news.bean.InternationalNewsBean;
import com.niuyi.mvp_news.mvp.contract.InternationalContract;
import com.niuyi.mvp_news.mvp.model.InternationalModel;
import com.orhanobut.logger.Logger;

import rx.Subscriber;
import rx.Subscription;

/**
 * 作者：${牛毅} on 2016/12/2 11:11
 * 邮箱：niuyi19900923@hotmail.com
 */
public class InternationalPresenter extends InternationalContract.Presenter {

    public InternationalPresenter(InternationalContract.View view) {
        mView = view;
        mModel = new InternationalModel();
    }

    @Override
    public void getInternationData() {

        Subscription subscription = mModel.getInternationData()
                .subscribe(new Subscriber<InternationalNewsBean>() {
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
                        Logger.e("onError" + e.getMessage());
                    }

                    @Override
                    public void onNext(InternationalNewsBean internationalNewsBean) {
                        mView.onSucceed(internationalNewsBean);
                        Logger.e("InternationalNewsBean====" + internationalNewsBean.getResult().getData().size());
                    }
                });
        addSubscribe(subscription);

    }

    @Override
    public void refresh() {
        getInternationData();
    }

}
