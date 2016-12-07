package com.niuyi.mvp_news.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.bean.FunnyPicBean;

import java.util.List;

/**
 * 作者：${牛毅} on 2016/12/7 15:17
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FunnyPicAdapter extends BaseQuickAdapter<FunnyPicBean.ResultBean.DataBean, BaseViewHolder> {

    public FunnyPicAdapter(List<FunnyPicBean.ResultBean.DataBean> data) {
        super(R.layout.item_funny_pic, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FunnyPicBean.ResultBean.DataBean dataBean) {
        baseViewHolder.setText(R.id.tv_content, dataBean.getContent())
                .setText(R.id.tv_data, dataBean.getUpdatetime());

//        GlideImageLoader.displayImage(mContext, dataBean.getUrl(), (ImageView) baseViewHolder.getView(R.id.iv_pic));

        Glide
                .with(mContext)
                .load(dataBean.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.NONE)//不配置这里gif加载速度很慢
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
//                .placeholder(R.mipmap.zhanwei) // 占位图  设置占位图会导致列表图片显示出问题
//                .error(R.mipmap.shibao) // 加载错误图
                .into((ImageView) baseViewHolder.getView(R.id.iv_pic));

    }
}
