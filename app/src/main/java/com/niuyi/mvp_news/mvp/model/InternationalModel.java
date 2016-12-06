package com.niuyi.mvp_news.mvp.model;


import com.niuyi.mvp_news.bean.InternationalNewsBean;
import com.niuyi.mvp_news.constant.Constant;
import com.niuyi.mvp_news.mvp.api.ApiEngine;
import com.niuyi.mvp_news.mvp.contract.InternationalContract;
import com.niuyi.mvp_news.mvp.rx.RxSchedulers;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/12/2 11:12
 * 邮箱：niuyi19900923@hotmail.com
 */

public class InternationalModel implements InternationalContract.Model {
    @Override
    public Observable<InternationalNewsBean> getInternationData() {
        return ApiEngine
                .getInstance(Constant.NEWS_BASE_URL_TYPE)
                .getApiService()
                .getInternationalNews("guoji", Constant.NEWS_KEY)
                .compose(RxSchedulers.<InternationalNewsBean>switchThread());
    }
}
