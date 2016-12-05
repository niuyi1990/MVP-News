package com.niuyi.mvp_news.mvp.contract;


import com.niuyi.mvp_news.base.BaseModel;
import com.niuyi.mvp_news.base.BasePresenter;
import com.niuyi.mvp_news.base.BaseView;
import com.niuyi.mvp_news.bean.InternationalNewsBean;

import rx.Observable;


/**
 * 作者：${牛毅} on 2016/12/2 11:07
 * 邮箱：niuyi19900923@hotmail.com
 */

public interface InternationalContract {

     interface View extends BaseView {

        void showDialog();

        void onSucceed(InternationalNewsBean domesticNewsBean);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<InternationalNewsBean> getInternationData();
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getInternationData();

        public abstract void refresh();
    }
}
