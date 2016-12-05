package com.niuyi.mvp_news.constant;

import android.os.Environment;

/**
 * 静态常量工具类
 * 作者：${牛毅} on 2016/4/8 09:42
 * 邮箱：niuyi19900923@hotmail.com
 */
public class Constant {

    /**
     * 天行数据urlKey = cdab8d200199018ac02cf9e3b3971f14;
     * <p>
     * 社会新闻：http://api.tianapi.com/social/?key=APIKEY&num=10-----------------------
     * 国内新闻：http://api.tianapi.com/guonei/?key=APIKEY&num=10-----------------------
     * 国际新闻：http://api.tianapi.com/world/?key=APIKEY&num=10------------------------
     * 娱乐花边：http://api.tianapi.com/huabian/?key=APIKEY&num=10----------------------
     * 体育新闻：http://api.tianapi.com/tiyu/?key=APIKEY&num=10-------------------------
     * NBA新闻：http://api.tianapi.com/nba/?key=APIKEY&num=10
     * 足球新闻：http://api.tianapi.com/football/?key=APIKEY&num=10
     * 科技新闻：http://api.tianapi.com/keji/?key=APIKEY&num=10-------------------------
     * 移动互联：http://api.tianapi.com/mobile/?key=APIKEY&num=10
     * 创业新闻：http://api.tianapi.com/startup/?key=APIKEY&num=10
     * VR虚拟现实：http://api.tianapi.com/vr/?key=APIKEY&num=10
     * IT资讯：http://api.tianapi.com/it/?key=APIKEY&num=10
     * 健康资讯：http://api.tianapi.com/health/?key=APIKEY&num=10
     * 苹果新闻：http://api.tianapi.com/apple/?key=APIKEY&num=10
     * 使用帮助：默认返回10条数据，非必填参数请按需传递。
     * 更新周期：2小时/次
     */

    public static final String urlKey = "c0a832d7e26bc6a5966ea6bf2e0709c7";
    public static final String jiSuUrlKey = "3b50738100410119";

    //请求示例：http://v.juhe.cn/toutiao/index?type=top&key=APPKEY
    public static final String BASE_URL = "http://v.juhe.cn/toutiao/";
    public static final String JISU_BASE_URL = "http://api.jisuapi.com/news/";

    public static final String ROOT = Environment.getExternalStorageDirectory().getPath();
    public static final String SHISHIBAO_PATH = ROOT + "/ShiShiBao/";
    public static final String IMAGES_PATH = SHISHIBAO_PATH + "images/";
    public static final String CACHE_PATH = SHISHIBAO_PATH + "cache/";

}
