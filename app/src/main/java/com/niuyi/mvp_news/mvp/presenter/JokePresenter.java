package com.niuyi.mvp_news.mvp.presenter;

import com.niuyi.mvp_news.bean.FunnyJokeBean;
import com.niuyi.mvp_news.mvp.contract.JokeContract;
import com.niuyi.mvp_news.mvp.model.JokeModel;

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
    public void getFunnyJoke(final int page) {

        Subscription subscription = mModel.getFunnyJoke(page)
                .subscribe(new Subscriber<FunnyJokeBean>() {
                    @Override
                    public void onStart() {
                        if (page == 1) mView.showRefreshDialog();//下拉刷新
                    }

                    @Override
                    public void onCompleted() {
                        if (page == 1) mView.hideRefreshDialog();//下拉刷新
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (page == 1) {//下拉刷新
                            mView.onRefreshFail(e.getMessage());
                        } else {
                            mView.onLoadMoreFail(e.getMessage());
                        }
                        onCompleted();
                    }

                    @Override
                    public void onNext(FunnyJokeBean funnyJokeBean) {
                        if (page == 1) {//下拉刷新
                            mView.onRefreshSucceed(funnyJokeBean.getResult().getData());
                        } else {
                            mView.onLoadMoreSucceed(funnyJokeBean.getResult().getData());
                        }
                    }
                });

        addSubscribe(subscription);
    }

    @Override
    public void refresh() {
        getFunnyJoke(1);//下来刷新加载第一页
    }

    @Override
    public void loadmore(int page) {
        mModel.setPage(page);
        getFunnyJoke(page);
    }
}
