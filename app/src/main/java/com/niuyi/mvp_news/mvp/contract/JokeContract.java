package com.niuyi.mvp_news.mvp.contract;

import com.niuyi.mvp_news.base.BaseModel;
import com.niuyi.mvp_news.base.BasePresenter;
import com.niuyi.mvp_news.base.BaseView;
import com.niuyi.mvp_news.bean.FunnyJokeBean;

import java.util.List;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/12/6 11:30
 * 邮箱：niuyi19900923@hotmail.com
 */

public interface JokeContract {

    interface View extends BaseView {

        void showRefreshDialog();

        void hideRefreshDialog();

        void onRefreshSucceed(List<FunnyJokeBean.ResultBean.DataBean> data);

        void onRefreshFail(String err);

        void onLoadMoreSucceed(List<FunnyJokeBean.ResultBean.DataBean> data);

        void onLoadMoreFail(String err);

    }

    interface Model extends BaseModel {
        Observable<FunnyJokeBean> getFunnyJoke(int page);

        void setPage(int page);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getFunnyJoke(int page);

        public abstract void refresh();

        public abstract void loadmore(int page);
    }
}
