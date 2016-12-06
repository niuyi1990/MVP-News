package com.niuyi.mvp_news.mvp.presenter;

import com.niuyi.mvp_news.bean.FunnyJokeBean;
import com.niuyi.mvp_news.mvp.contract.JokeContract;
import com.niuyi.mvp_news.mvp.model.JokeModel;
import com.orhanobut.logger.Logger;

import rx.Subscriber;
import rx.Subscription;

/**
 * 作者：${牛毅} on 2016/12/6 11:40
 * 邮箱：niuyi19900923@hotmail.com
 */
public class JokePresenter extends JokeContract.Presenter {


    public JokePresenter(JokeContract.View view) {
        mView = view;
        mModel = new JokeModel();
    }

    @Override
    public void getFunnyJoke() {

        Subscription subscription = mModel.getFunnyJoke()
                .subscribe(new Subscriber<FunnyJokeBean>() {
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
                        mView.onFail(e.getMessage());
                        onCompleted();
                        Logger.e("e===" + e.getMessage());
                    }

                    @Override
                    public void onNext(FunnyJokeBean funnyJokeBean) {
                        mView.onSucceed(funnyJokeBean);
                        Logger.e("funnyJokeBean==" + funnyJokeBean.getResult().getData().size());
                    }
                });

        addSubscribe(subscription);
    }

    @Override
    public void refresh() {
        getFunnyJoke();
    }

    @Override
    public void loadmore() {

    }
}
