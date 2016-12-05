package com.niuyi.mvp_news.mvp.contract;


import com.niuyi.mvp_news.base.BaseModel;
import com.niuyi.mvp_news.base.BasePresenter;
import com.niuyi.mvp_news.base.BaseView;
import com.niuyi.mvp_news.bean.SocietyNewsBean;

/**
 * 作者：${牛毅} on 2016/11/30 15:20
 * 邮箱：niuyi19900923@hotmail.com
 */

public interface SocietyContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(SocietyNewsBean societyNewsBean);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {
        rx.Observable<SocietyNewsBean> getSocietyNews();
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getSocietyNews();

        public abstract void refresh();
    }
}
