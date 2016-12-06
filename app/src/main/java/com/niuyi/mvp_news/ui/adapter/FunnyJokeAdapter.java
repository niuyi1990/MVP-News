package com.niuyi.mvp_news.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.bean.FunnyJokeBean;

import java.util.List;

/**
 * 作者：${牛毅} on 2016/12/6 13:53
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FunnyJokeAdapter extends BaseQuickAdapter<FunnyJokeBean.ResultBean.DataBean, BaseViewHolder> {

    public FunnyJokeAdapter(List<FunnyJokeBean.ResultBean.DataBean> data) {
        super(R.layout.item_funny_joke, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FunnyJokeBean.ResultBean.DataBean funnyJokeBean) {
        baseViewHolder.setText(R.id.tv_content, funnyJokeBean.getContent())
                .setText(R.id.tv_data, funnyJokeBean.getUpdatetime())
                .linkify(R.id.tv_data);;
    }
}
