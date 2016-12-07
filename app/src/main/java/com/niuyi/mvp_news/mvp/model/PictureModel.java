package com.niuyi.mvp_news.mvp.model;

import com.niuyi.mvp_news.bean.FunnyPicBean;
import com.niuyi.mvp_news.constant.Constant;
import com.niuyi.mvp_news.mvp.api.ApiEngine;
import com.niuyi.mvp_news.mvp.contract.PictureContract;
import com.niuyi.mvp_news.mvp.rx.RxSchedulers;

import rx.Observable;

/**
 * 作者：${牛毅} on 2016/12/7 15:06
 * 邮箱：niuyi19900923@hotmail.com
 */
public class PictureModel implements PictureContract.Model {

    @Override
    public Observable<FunnyPicBean> getFunnyPic(int page) {
        return ApiEngine.getInstance(Constant.FUNNY_BASE_URL_TYPE)
                .getApiService()
                .getFunnyPicture(String.valueOf(page), "10", Constant.FUNNY_KEY)
                .compose(RxSchedulers.<FunnyPicBean>switchThread());
    }

    @Override
    public void setPage(int page) {
        getFunnyPic(page);
    }
}
