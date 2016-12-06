package com.niuyi.mvp_news.constant;

import android.os.Environment;

/**
 * 静态常量工具类
 * 作者：${牛毅} on 2016/4/8 09:42
 * 邮箱：niuyi19900923@hotmail.com
 */
public class Constant {

    public static final String NEWS_KEY = "c0a832d7e26bc6a5966ea6bf2e0709c7";//新闻
    public static final String FUNNY_KEY = "f1ae70464060d564cd5e8220c39089e8";//段子

    //新闻请求示例：http://v.juhe.cn/toutiao/index?type=top&key=APPKEY
    public static final String NEWS_BASE_URL = "http://v.juhe.cn/toutiao/";//必须以‘/’结尾
    //笑话接口地址：http://japi.juhe.cn/joke/content/text.from?key=您申请的KEY&page=1&pagesize=10
    //趣图接口地址：http://japi.juhe.cn/joke/img/text.from?key=您申请的KEY&page=1&pagesize=10
    public static final String FUNNY_BASE_URL = "http://japi.juhe.cn/joke/";//必须以‘/’结尾

    public static final int NEWS_BASE_URL_TYPE = 1;
    public static final int FUNNY_BASE_URL_TYPE = 2;
//    public static final int NEWS_BASE_URL_TYPE = 1;

    public static final String ROOT = Environment.getExternalStorageDirectory().getPath();
    public static final String SHISHIBAO_PATH = ROOT + "/ShiShiBao/";
    public static final String IMAGES_PATH = SHISHIBAO_PATH + "images/";
    public static final String CACHE_PATH = SHISHIBAO_PATH + "cache/";
}
