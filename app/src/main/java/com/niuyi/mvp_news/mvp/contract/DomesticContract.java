package com.niuyi.mvp_news.mvp.contract;


import com.niuyi.mvp_news.base.BaseModel;
import com.niuyi.mvp_news.base.BasePresenter;
import com.niuyi.mvp_news.base.BaseView;
import com.niuyi.mvp_news.bean.DomesticNewsBean;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/12/2 10:30
 * 邮箱：niuyi19900923@hotmail.com
 */

public interface DomesticContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(DomesticNewsBean domesticNewsBean);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<DomesticNewsBean> getDomesticDate();
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getDomesticDate();

        public abstract void refresh();
    }
}
