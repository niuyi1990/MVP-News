package com.niuyi.mvp_news.constant;

import android.os.Environment;

/**
 * 静态常量工具类
 * 作者：${牛毅} on 2016/4/8 09:42
 * 邮箱：niuyi19900923@hotmail.com
 */
public class Constant {

    public static final String urlKey = "c0a832d7e26bc6a5966ea6bf2e0709c7";

    //请求示例：http://v.juhe.cn/toutiao/index?type=top&key=APPKEY
    public static final String BASE_URL = "http://v.juhe.cn/toutiao/";

    public static final String ROOT = Environment.getExternalStorageDirectory().getPath();
    public static final String SHISHIBAO_PATH = ROOT + "/ShiShiBao/";
    public static final String IMAGES_PATH = SHISHIBAO_PATH + "images/";
    public static final String CACHE_PATH = SHISHIBAO_PATH + "cache/";

}
