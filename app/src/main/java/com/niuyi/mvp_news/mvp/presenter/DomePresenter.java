package com.niuyi.mvp_news.mvp.presenter;

import com.niuyi.mvp_news.bean.DomeNewsBean;
import com.niuyi.mvp_news.mvp.contract.DomeContract;
import com.niuyi.mvp_news.mvp.model.DomeModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * 作者：${牛毅} on 2016/12/2 10:30
 * 邮箱：niuyi19900923@hotmail.com
 */

public class DomePresenter extends DomeContract.Presenter {

    public DomePresenter(DomeContract.View view) {
        mView = view;
        mModel = new DomeModel();
    }

    @Override
    public void getDomesticDate() {

        Subscription subscription = mModel.getDomesticDate()
                .subscribe(new Subscriber<DomeNewsBean>() {
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
                    public void onNext(DomeNewsBean domesticNewsBean) {
                        mView.onRefreshSucceed(domesticNewsBean.getResult().getData());
                    }
                });
        addSubscribe(subscription);

    }

    @Override
    public void refresh() {
        getDomesticDate();
    }
}
