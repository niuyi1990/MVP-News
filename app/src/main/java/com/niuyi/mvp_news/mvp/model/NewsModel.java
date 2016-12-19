package com.niuyi.mvp_news.mvp.model;


import com.niuyi.mvp_news.bean.NewsBean;
import com.niuyi.mvp_news.constant.Constant;
import com.niuyi.mvp_news.mvp.api.ApiEngine;
import com.niuyi.mvp_news.mvp.contract.NewsContract;
import com.niuyi.mvp_news.mvp.rx.RxSchedulers;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/11/30 15:24
 * 邮箱：niuyi19900923@hotmail.com
 */
public class NewsModel implements NewsContract.Model {
    @Override
    public Observable<NewsBean> getSocietyNews(String type) {
        return ApiEngine
                .getInstance(Constant.NEWS_BASE_URL_TYPE)
                .getApiService()
                .getNews(type, Constant.NEWS_KEY)
                .compose(RxSchedulers.<NewsBean>switchThread());
    }
}
