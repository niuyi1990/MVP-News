package com.niuyi.mvp_news.mvp.contract;

import com.niuyi.mvp_news.base.BaseModel;
import com.niuyi.mvp_news.base.BasePresenter;
import com.niuyi.mvp_news.base.BaseView;
import com.niuyi.mvp_news.bean.FunnyPicBean;

import java.util.List;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/12/7 15:03
 * 邮箱：niuyi19900923@hotmail.com
 */
public interface PictureContract {

    interface View extends BaseView {

        void showRefreshDialog();

        void hideRefreshDialog();

        void onRefreshSucceed(List<FunnyPicBean.ResultBean.DataBean> list);

        void onRefreshFail(String err);

        void onLoadMoreSucceed(List<FunnyPicBean.ResultBean.DataBean> list);

        void onLoadMoreFail(String err);

    }

    interface Model extends BaseModel {
        Observable<FunnyPicBean> getFunnyPic(int page);

        void setPage(int page);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getFunnyPic(int page);

        public abstract void refresh();

        public abstract void loadmore(int page);
    }
}
