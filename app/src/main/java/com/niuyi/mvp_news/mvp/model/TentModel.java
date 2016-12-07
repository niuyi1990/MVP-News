package com.niuyi.mvp_news.mvp.model;

import com.niuyi.mvp_news.bean.TentNewsBean;
import com.niuyi.mvp_news.constant.Constant;
import com.niuyi.mvp_news.mvp.api.ApiEngine;
import com.niuyi.mvp_news.mvp.contract.TentContract;
import com.niuyi.mvp_news.mvp.rx.RxSchedulers;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/12/5 16:06
 * 邮箱：niuyi19900923@hotmail.com
 */

public class TentModel implements TentContract.Model {
    @Override
    public Observable<TentNewsBean> getTentertainmentData() {
        return ApiEngine
                .getInstance(Constant.NEWS_BASE_URL_TYPE)
                .getApiService()
                .getTentNews("yule", Constant.NEWS_KEY)
                .compose(RxSchedulers.<TentNewsBean>switchThread());
    }
}
