package com.niuyi.mvp_news.mvp.model;

import com.niuyi.mvp_news.bean.DomeNewsBean;
import com.niuyi.mvp_news.constant.Constant;
import com.niuyi.mvp_news.mvp.api.ApiEngine;
import com.niuyi.mvp_news.mvp.contract.DomeContract;
import com.niuyi.mvp_news.mvp.rx.RxSchedulers;

import rx.Observable;


/**
 * 作者：${牛毅} on 2016/12/2 10:30
 * 邮箱：niuyi19900923@hotmail.com
 */
public class DomeModel implements DomeContract.Model {
    @Override
    public Observable<DomeNewsBean> getDomesticDate() {
        return ApiEngine
                .getInstance(Constant.NEWS_BASE_URL_TYPE)
                .getApiService()
                .getDomeNews("guonei", Constant.NEWS_KEY)
                .compose(RxSchedulers.<DomeNewsBean>switchThread());
    }
}
