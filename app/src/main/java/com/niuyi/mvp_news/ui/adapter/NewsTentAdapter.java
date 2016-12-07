package com.niuyi.mvp_news.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.bean.TentNewsBean;
import com.niuyi.mvp_news.utils.GlideImageLoader;

import java.util.List;

/**
 * 作者：${牛毅} on 2016/12/5 16:18
 * 邮箱：niuyi19900923@hotmail.com
 */
public class NewsTentAdapter extends BaseQuickAdapter<TentNewsBean.ResultBean.DataBean, BaseViewHolder> {

    public NewsTentAdapter(List<TentNewsBean.ResultBean.DataBean> data) {
        super(R.layout.item_top, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TentNewsBean.ResultBean.DataBean dataBean) {
        baseViewHolder.setText(R.id.tv_title, dataBean.getTitle())
                .setText(R.id.tv_data, dataBean.getDate())
                .setText(R.id.tv_source, dataBean.getAuthor_name());

        GlideImageLoader.displayImage(mContext, dataBean.getThumbnail_pic_s03(), (ImageView) baseViewHolder.getView(R.id.iv_pic));
    }

}
