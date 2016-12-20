package com.niuyi.mvp_news.mvp.contract;

import com.niuyi.mvp_news.base.BaseModel;
import com.niuyi.mvp_news.base.BasePresenter;
import com.niuyi.mvp_news.base.BaseView;
import com.niuyi.mvp_news.bean.WeiXinBean;

import java.util.List;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/12/20 09:34
 * 邮箱：niuyi19900923@hotmail.com
 */
public interface WeiXinContract {

    interface View extends BaseView {

        void showRefreshDialog();

        void hideRefreshDialog();

        void onRefreshSucceed(List<WeiXinBean.ResultBean.ListBean> list);

        void onRefreshFail(String err);

        void onLoadMoreSucceed(List<WeiXinBean.ResultBean.ListBean> list);

        void onLoadMoreFail(String err);

    }

    interface Model extends BaseModel {
        Observable<WeiXinBean> getWeiXinData(int page);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getWeiXinData();

        public abstract void refresh();

        public abstract void loadmore();
    }

}
