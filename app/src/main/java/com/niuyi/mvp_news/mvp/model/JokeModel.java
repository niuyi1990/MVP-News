package com.niuyi.mvp_news.mvp.model;

import com.niuyi.mvp_news.bean.FunnyJokeBean;
import com.niuyi.mvp_news.constant.Constant;
import com.niuyi.mvp_news.mvp.api.ApiEngine;
import com.niuyi.mvp_news.mvp.contract.JokeContract;
import com.niuyi.mvp_news.mvp.rx.RxSchedulers;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/12/6 11:37
 * 邮箱：niuyi19900923@hotmail.com
 */
public class JokeModel implements JokeContract.Model {

    @Override
    public Observable<FunnyJokeBean> getFunnyJoke(int page) {
        return ApiEngine
                .getInstance(Constant.FUNNY_BASE_URL_TYPE)
                .getApiService()
                .getFunnyJoke(String.valueOf(page), "10", Constant.FUNNY_KEY)
                .compose(RxSchedulers.<FunnyJokeBean>switchThread());
    }

    @Override
    public void setPage(int page) {
        getFunnyJoke(page);
    }

}
