package com.niuyi.mvp_news.mvp.api;

import com.niuyi.mvp_news.bean.DomesticNewsBean;
import com.niuyi.mvp_news.bean.InternationalNewsBean;
import com.niuyi.mvp_news.bean.SocietyNewsBean;
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
    Observable<DomesticNewsBean> getDomesticNews(@Query("type") String type, @Query("key") String key);

    @GET("index")
    Observable<InternationalNewsBean> getInternationalNews(@Query("type") String type, @Query("key") String key);

//    @GET("social/")
//    Observable<SocietyNewsBean> getSocietyNews(@Query("page") String page, @Query("num") String num, @Query("key") String key);

}
