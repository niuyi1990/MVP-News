package com.niuyi.mvp_news.mvp.contract;


import com.niuyi.mvp_news.base.BaseModel;
import com.niuyi.mvp_news.base.BasePresenter;
import com.niuyi.mvp_news.base.BaseView;
import com.niuyi.mvp_news.bean.DomeNewsBean;

import java.util.List;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/12/2 10:30
 * 邮箱：niuyi19900923@hotmail.com
 */

public interface DomeContract {

    interface View extends BaseView {

        void showRefreshDialog();

        void onRefreshSucceed(List<DomeNewsBean.ResultBean.DataBean> list);

        void onRefreshFail(String err);

        void hideRefreshDialog();

    }

    interface Model extends BaseModel {
        Observable<DomeNewsBean> getDomesticDate();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getDomesticDate();

        public abstract void refresh();
    }
}
