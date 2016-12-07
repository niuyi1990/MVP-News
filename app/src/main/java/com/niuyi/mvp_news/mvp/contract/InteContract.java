package com.niuyi.mvp_news.mvp.contract;


import com.niuyi.mvp_news.base.BaseModel;
import com.niuyi.mvp_news.base.BasePresenter;
import com.niuyi.mvp_news.base.BaseView;
import com.niuyi.mvp_news.bean.InteNewsBean;

import java.util.List;

import rx.Observable;


/**
 * 作者：${牛毅} on 2016/12/2 11:07
 * 邮箱：niuyi19900923@hotmail.com
 */

public interface InteContract {

    interface View extends BaseView {

        void showRefreshDialog();

        void onRefreshSucceed(List<InteNewsBean.ResultBean.DataBean> list);

        void onRefreshFail(String err);

        void hideRefreshDialog();

    }

    interface Model extends BaseModel {
        Observable<InteNewsBean> getInternationData();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getInternationData();

        public abstract void refresh();
    }
}
