package com.niuyi.mvp_news.mvp.contract;

import com.niuyi.mvp_news.base.BaseModel;
import com.niuyi.mvp_news.base.BasePresenter;
import com.niuyi.mvp_news.base.BaseView;
import com.niuyi.mvp_news.bean.TopNewsBean;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/11/30 10:04
 * 邮箱：niuyi19900923@hotmail.com
 */

public interface TopContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(TopNewsBean topNewsBean);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<TopNewsBean> getTopNews();
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getTopNews();

        public abstract void refresh();
    }
}
