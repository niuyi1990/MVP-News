package com.niuyi.mvp_news.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.niuyi.mvp_news.R;

/**
 * 作者：${牛毅} on 2016/11/30 14:08
 * 邮箱：niuyi19900923@hotmail.com
 */

public class GlideImageLoader {

    public static void displayImage(Context context, Object path, ImageView imageView) {
        Glide
                .with(context)
                .load(path)
                .placeholder(R.mipmap.zhanwei) // 占位图
                .error(R.mipmap.shibao) // 加载错误图
                .into(imageView);
    }
}
