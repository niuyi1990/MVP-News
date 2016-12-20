package com.niuyi.mvp_news.mvp.api;

import com.niuyi.mvp_news.bean.FunnyJokeBean;
import com.niuyi.mvp_news.bean.FunnyPicBean;
import com.niuyi.mvp_news.bean.NewsBean;
import com.niuyi.mvp_news.bean.WeiXinBean;

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
    Observable<NewsBean> getNews(@Query("type") String type, @Query("key") String key);

    @GET("content/text.from")
    Observable<FunnyJokeBean> getFunnyJoke(@Query("page") String page, @Query("pagesize") String pagesize, @Query("key") String key);

    @GET("img/text.from")
    Observable<FunnyPicBean> getFunnyPicture(@Query("page") String page, @Query("pagesize") String pagesize, @Query("key") String key);

    @GET("query")
    Observable<WeiXinBean> getWeiXinData(@Query("pno") String pno, @Query("ps") String ps, @Query("dtype") String dtype, @Query("key") String key);
}
