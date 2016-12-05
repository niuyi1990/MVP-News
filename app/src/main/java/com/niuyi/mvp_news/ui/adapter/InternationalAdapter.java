package com.niuyi.mvp_news.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.bean.InternationalNewsBean;
import com.niuyi.mvp_news.utils.GlideImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：${牛毅} on 2016/12/2 11:19
 * 邮箱：niuyi19900923@hotmail.com
 */

public class InternationalAdapter extends RecyclerView.Adapter<InternationalAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<InternationalNewsBean.ResultBean.DataBean> mList;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_top, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InternationalNewsBean.ResultBean.DataBean dataBean = mList.get(position);
        holder.mTvTitle.setText(dataBean.getTitle());
        GlideImageLoader.displayImage(mContext, dataBean.getThumbnail_pic_s03(), holder.mIvPic);
        holder.mTvData.setText(dataBean.getDate());
        holder.mTvSource.setText(dataBean.getAuthor_name());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(ArrayList<InternationalNewsBean.ResultBean.DataBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.iv_pic)
        ImageView mIvPic;
        @BindView(R.id.tv_data)
        TextView mTvData;
        @BindView(R.id.tv_source)
        TextView mTvSource;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
