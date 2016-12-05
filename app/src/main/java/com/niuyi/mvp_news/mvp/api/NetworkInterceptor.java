package com.niuyi.mvp_news.mvp.api;


import com.niuyi.mvp_news.application.MyApplication;
import com.niuyi.mvp_news.utils.NetworkUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * okhttp配置缓存
 * 作者：${牛毅} on 2016/9/12 10:14
 * 邮箱：niuyi19900923@hotmail.com
 */
public class NetworkInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        //无网络时强制使用缓存
        if (!NetworkUtil.isNetworkConnected(MyApplication.getContext())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        Response response = chain.proceed(request);

        if (NetworkUtil.isNetworkConnected(MyApplication.getContext())) {
            // 有网络时，设置超时为0
            int maxStale = 0;
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxStale)
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build();
        } else {
            // 无网络时，设置超时为3周
            int maxStale = 60 * 60 * 24 * 21;
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }

        return response;
    }

}
