package com.niuyi.mvp_news.mvp.contract;

import com.niuyi.mvp_news.base.BaseModel;
import com.niuyi.mvp_news.base.BasePresenter;
import com.niuyi.mvp_news.base.BaseView;
import com.niuyi.mvp_news.bean.TentNewsBean;

import java.util.List;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/12/5 16:02
 * 邮箱：niuyi19900923@hotmail.com
 */
public interface TentContract {

    interface View extends BaseView {

        void showRefreshDialog();

        void onRefreshSucceed(List<TentNewsBean.ResultBean.DataBean> list);

        void onRefreshFail(String err);

        void hideRefreshDialog();

    }

    interface Model extends BaseModel {
        Observable<TentNewsBean> getTentertainmentData();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getTentertainmentData();

        public abstract void refresh();
    }
}
