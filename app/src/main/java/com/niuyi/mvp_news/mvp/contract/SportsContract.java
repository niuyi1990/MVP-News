package com.niuyi.mvp_news.mvp.contract;

import com.niuyi.mvp_news.base.BaseModel;
import com.niuyi.mvp_news.base.BasePresenter;
import com.niuyi.mvp_news.base.BaseView;
import com.niuyi.mvp_news.bean.SportsNewsBean;

import java.util.List;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/12/7 10:04
 * 邮箱：niuyi19900923@hotmail.com
 */
public interface SportsContract {

    interface View extends BaseView {

        void showRefreshDialog();

        void onRefreshSucceed(List<SportsNewsBean.ResultBean.DataBean> list);

        void onRefreshFail(String err);

        void hideRefreshDialog();

    }

    interface Model extends BaseModel {
        Observable<SportsNewsBean> getSportsData();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getSportsData();

        public abstract void refresh();
    }
}
