package com.niuyi.mvp_news.mvp.model;

import com.niuyi.mvp_news.bean.TentertainmentNewsBean;
import com.niuyi.mvp_news.constant.Constant;
import com.niuyi.mvp_news.mvp.api.ApiEngine;
import com.niuyi.mvp_news.mvp.contract.TentertainmentContract;
import com.niuyi.mvp_news.mvp.rx.RxSchedulers;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/12/5 16:06
 * 邮箱：niuyi19900923@hotmail.com
 */

public class TentertainmentModel implements TentertainmentContract.Model {
    @Override
    public Observable<TentertainmentNewsBean> getTentertainmentData() {
        return ApiEngine
                .getInstance()
                .getApiService()
                .getTentertainmentNews("yule", Constant.urlKey)
                .compose(RxSchedulers.<TentertainmentNewsBean>switchThread());
    }
}
