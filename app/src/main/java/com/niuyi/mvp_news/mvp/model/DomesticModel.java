package com.niuyi.mvp_news.mvp.model;

import com.niuyi.mvp_news.bean.DomesticNewsBean;
import com.niuyi.mvp_news.constant.Constant;
import com.niuyi.mvp_news.mvp.api.ApiEngine;
import com.niuyi.mvp_news.mvp.contract.DomesticContract;
import com.niuyi.mvp_news.mvp.rx.RxSchedulers;

import rx.Observable;


/**
 * 作者：${牛毅} on 2016/12/2 10:30
 * 邮箱：niuyi19900923@hotmail.com
 */
public class DomesticModel implements DomesticContract.Model {
    @Override
    public Observable<DomesticNewsBean> getDomesticDate() {
        return ApiEngine
                .getInstance(Constant.NEWS_BASE_URL_TYPE)
                .getApiService()
                .getDomesticNews("guonei", Constant.NEWS_KEY)
                .compose(RxSchedulers.<DomesticNewsBean>switchThread());
    }
}
