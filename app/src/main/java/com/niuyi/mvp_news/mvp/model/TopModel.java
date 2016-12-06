package com.niuyi.mvp_news.mvp.model;


import com.niuyi.mvp_news.bean.TopNewsBean;
import com.niuyi.mvp_news.constant.Constant;
import com.niuyi.mvp_news.mvp.api.ApiEngine;
import com.niuyi.mvp_news.mvp.contract.TopContract;
import com.niuyi.mvp_news.mvp.rx.RxSchedulers;

import rx.Observable;


/**
 * 作者：${牛毅} on 2016/11/30 10:04
 * 邮箱：niuyi19900923@hotmail.com
 */

public class TopModel implements TopContract.Model {

    @Override
    public Observable<TopNewsBean> getTopNews() {
        return ApiEngine
                .getInstance(Constant.NEWS_BASE_URL_TYPE)
                .getApiService()
                .getTopNews("top", Constant.NEWS_KEY)
                .compose(RxSchedulers.<TopNewsBean>switchThread());
    }

}
