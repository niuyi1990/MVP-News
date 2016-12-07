package com.niuyi.mvp_news.mvp.presenter;

import com.niuyi.mvp_news.bean.FunnyPicBean;
import com.niuyi.mvp_news.mvp.contract.PictureContract;
import com.niuyi.mvp_news.mvp.model.PictureModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * 作者：${牛毅} on 2016/12/7 15:08
 * 邮箱：niuyi19900923@hotmail.com
 */
public class PicturePresenter extends PictureContract.Presenter {

    public PicturePresenter(PictureContract.View view) {
        mView = view;
        mModel = new PictureModel();
    }

    @Override
    public void getFunnyPic(final int page) {

        Subscription subscription = mModel.getFunnyPic(page)
                .subscribe(new Subscriber<FunnyPicBean>() {
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
                    public void onNext(FunnyPicBean funnyPicBean) {
                        if (page == 1) {//下拉刷新
                            mView.onRefreshSucceed(funnyPicBean.getResult().getData());
                        } else {
                            mView.onLoadMoreSucceed(funnyPicBean.getResult().getData());
                        }
                    }
                });

        addSubscribe(subscription);
    }

    @Override
    public void refresh() {
        getFunnyPic(1);
    }

    @Override
    public void loadmore(int page) {
        mModel.setPage(page);
        getFunnyPic(page);
    }
}
