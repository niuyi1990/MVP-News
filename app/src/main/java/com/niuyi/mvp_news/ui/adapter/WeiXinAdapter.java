package com.niuyi.mvp_news.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.bean.WeiXinBean;
import com.niuyi.mvp_news.utils.GlideImageLoader;

import java.util.List;

/**
 * 作者：${牛毅} on 2016/12/20 10:01
 * 邮箱：niuyi19900923@hotmail.com
 */
public class WeiXinAdapter extends BaseQuickAdapter<WeiXinBean.ResultBean.ListBean, BaseViewHolder> {

    public WeiXinAdapter(List<WeiXinBean.ResultBean.ListBean> data) {
        super(R.layout.item_weixin, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, WeiXinBean.ResultBean.ListBean listBean) {
        baseViewHolder.setText(R.id.tv_title, listBean.getTitle())
                .setText(R.id.tv_source, listBean.getSource());

        GlideImageLoader.displayImage(mContext, listBean.getFirstImg(), (ImageView) baseViewHolder.getView(R.id.iv_pic));
    }
}
