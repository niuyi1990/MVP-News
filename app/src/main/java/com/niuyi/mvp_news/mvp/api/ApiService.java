package com.niuyi.mvp_news.mvp.api;

import com.niuyi.mvp_news.bean.DomeNewsBean;
import com.niuyi.mvp_news.bean.FunnyJokeBean;
import com.niuyi.mvp_news.bean.InteNewsBean;
import com.niuyi.mvp_news.bean.SocietyNewsBean;
import com.niuyi.mvp_news.bean.SportsNewsBean;
import com.niuyi.mvp_news.bean.TentNewsBean;
import com.niuyi.mvp_news.bean.TopNewsBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 接口管理
 * 作者：${牛毅} on 2016/11/17 17:27
 * 邮箱：niuyi19900923@hotmail.com
 */
public interface ApiService {

    @GET("index")
    Observable<TopNewsBean> getTopNews(@Query("type") String type, @Query("key") String key);

    @GET("index")
    Observable<SocietyNewsBean> getSocietyNews(@Query("type") String type, @Query("key") String key);

    @GET("index")
    Observable<DomeNewsBean> getDomeNews(@Query("type") String type, @Query("key") String key);

    @GET("index")
    Observable<InteNewsBean> getInteNews(@Query("type") String type, @Query("key") String key);

    @GET("index")
    Observable<TentNewsBean> getTentNews(@Query("type") String type, @Query("key") String key);

    @GET("index")
    Observable<SportsNewsBean> getSportsNews(@Query("type") String type, @Query("key") String key);

    @GET("content/text.from")
    Observable<FunnyJokeBean> getFunnyJoke(@Query("page") String page, @Query("pagesize") String pagesize, @Query("key") String key);

}
