package com.niuyi.mvp_news.mvp.contract;

import com.niuyi.mvp_news.base.BaseModel;
import com.niuyi.mvp_news.base.BasePresenter;
import com.niuyi.mvp_news.base.BaseView;
import com.niuyi.mvp_news.bean.TentertainmentNewsBean;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/12/5 16:02
 * 邮箱：niuyi19900923@hotmail.com
 */

public interface TentertainmentContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(TentertainmentNewsBean tentertainmentNewsBean);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<TentertainmentNewsBean> getTentertainmentData();
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getTentertainmentData();

        public abstract void refresh();
    }
}
