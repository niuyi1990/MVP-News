package com.niuyi.mvp_news.mvp.contract;


import com.niuyi.mvp_news.base.BaseModel;
import com.niuyi.mvp_news.base.BasePresenter;
import com.niuyi.mvp_news.base.BaseView;
import com.niuyi.mvp_news.bean.NewsBean;

import java.util.List;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/11/30 15:20
 * 邮箱：niuyi19900923@hotmail.com
 */
public interface NewsContract {

    interface View extends BaseView {

        void showRefreshDialog();

        void onRefreshSucceed(List<NewsBean.ResultBean.DataBean> list);

        void onRefreshFail(String err);

        void hideRefreshDialog();

    }

    interface Model extends BaseModel {
        Observable<NewsBean> getSocietyNews(String type);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getSocietyNews(String string);

        public abstract void refresh(String string);
    }
}
