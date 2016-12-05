package com.niuyi.mvp_news.mvp.api;

import com.niuyi.mvp_news.application.MyApplication;
import com.niuyi.mvp_news.constant.Constant;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 封装retrofit引擎
 */
public class ApiEngine {

    private volatile static ApiEngine apiEngine;
    private Retrofit retrofit;

    private ApiEngine() {
        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(MyApplication.mLogLevel);

        //缓存
        int size = 1024 * 1024 * 100;
        File cacheFile = new File(Constant.CACHE_PATH, "OkHttpCache");
        Cache cache = new Cache(cacheFile, size);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addNetworkInterceptor(new NetworkInterceptor())
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static ApiEngine getInstance() {
        if (apiEngine == null) {
            synchronized (ApiEngine.class) {
                if (apiEngine == null) {
                    apiEngine = new ApiEngine();
                }
            }
        }
        return apiEngine;
    }

    public ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }
}