package com.niuyi.mvp_news.mvp.presenter;

import com.niuyi.mvp_news.bean.DomesticNewsBean;
import com.niuyi.mvp_news.mvp.contract.DomesticContract;
import com.niuyi.mvp_news.mvp.model.DomesticModel;
import com.orhanobut.logger.Logger;

import rx.Subscriber;
import rx.Subscription;

/**
 * 作者：${牛毅} on 2016/12/2 10:30
 * 邮箱：niuyi19900923@hotmail.com
 */

public class DomesticPresenter extends DomesticContract.Presenter {

    public DomesticPresenter(DomesticContract.View view) {
        mView = view;
        mModel = new DomesticModel();
    }

    @Override
    public void getDomesticDate() {

        Subscription subscription = mModel.getDomesticDate()
                .subscribe(new Subscriber<DomesticNewsBean>() {
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
                    public void onNext(DomesticNewsBean domesticNewsBean) {
                        mView.onSucceed(domesticNewsBean);
                        Logger.e("onNext" + domesticNewsBean.getResult().getData().size());
                    }
                });
        addSubscribe(subscription);

    }

    @Override
    public void refresh() {
        getDomesticDate();
    }
}
