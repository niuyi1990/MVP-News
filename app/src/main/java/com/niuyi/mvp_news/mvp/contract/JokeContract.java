package com.niuyi.mvp_news.mvp.contract;

import com.niuyi.mvp_news.base.BaseModel;
import com.niuyi.mvp_news.base.BasePresenter;
import com.niuyi.mvp_news.base.BaseView;
import com.niuyi.mvp_news.bean.FunnyJokeBean;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/12/6 11:30
 * 邮箱：niuyi19900923@hotmail.com
 */

public interface JokeContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(FunnyJokeBean funnyJokeBean);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<FunnyJokeBean> getFunnyJoke();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getFunnyJoke();

        public abstract void refresh();

        public abstract void loadmore();
    }
}
