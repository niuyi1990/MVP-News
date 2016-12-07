package com.niuyi.mvp_news.mvp.presenter;

import com.niuyi.mvp_news.bean.InteNewsBean;
import com.niuyi.mvp_news.mvp.contract.InteContract;
import com.niuyi.mvp_news.mvp.model.InteModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * 作者：${牛毅} on 2016/12/2 11:11
 * 邮箱：niuyi19900923@hotmail.com
 */
public class IntePresenter extends InteContract.Presenter {

    public IntePresenter(InteContract.View view) {
        mView = view;
        mModel = new InteModel();
    }

    @Override
    public void getInternationData() {

        Subscription subscription = mModel.getInternationData()
                .subscribe(new Subscriber<InteNewsBean>() {
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
                    public void onNext(InteNewsBean internationalNewsBean) {
                        mView.onRefreshSucceed(internationalNewsBean.getResult().getData());
                    }
                });
        addSubscribe(subscription);

    }

    @Override
    public void refresh() {
        getInternationData();
    }

}
