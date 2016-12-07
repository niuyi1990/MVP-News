package com.niuyi.mvp_news.mvp.model;

import com.niuyi.mvp_news.bean.SportsNewsBean;
import com.niuyi.mvp_news.constant.Constant;
import com.niuyi.mvp_news.mvp.api.ApiEngine;
import com.niuyi.mvp_news.mvp.contract.SportsContract;
import com.niuyi.mvp_news.mvp.rx.RxSchedulers;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/12/7 10:08
 * 邮箱：niuyi19900923@hotmail.com
 */
public class SportsModel implements SportsContract.Model {

    @Override
    public Observable<SportsNewsBean> getSportsData() {
        return ApiEngine
                .getInstance(Constant.NEWS_BASE_URL_TYPE)
                .getApiService()
                .getSportsNews("tiyu", Constant.NEWS_KEY)
                .compose(RxSchedulers.<SportsNewsBean>switchThread());
    }

}
