package com.niuyi.mvp_news.mvp.model;

import com.niuyi.mvp_news.bean.WeiXinBean;
import com.niuyi.mvp_news.constant.Constant;
import com.niuyi.mvp_news.mvp.api.ApiEngine;
import com.niuyi.mvp_news.mvp.contract.WeiXinContract;
import com.niuyi.mvp_news.mvp.rx.RxSchedulers;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/12/20 09:40
 * 邮箱：niuyi19900923@hotmail.com
 */
public class WeiXinModel implements WeiXinContract.Model {

    @Override
    public Observable<WeiXinBean> getWeiXinData(int page) {
        return ApiEngine
                .getInstance(Constant.WEIXIN_BASE_URL_TYPE)
                .getApiService()
                .getWeiXinData(String.valueOf(page), "10", "json", Constant.WEIXIN_KEY)
                .compose(RxSchedulers.<WeiXinBean>switchThread());
    }

}
