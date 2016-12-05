package com.niuyi.mvp_news.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Fragment基类
 * 作者：${牛毅} on 2016/04/07 10:10
 * 邮箱：niuyi19900923@hotmail.com
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    private View mContextView = null;//当前Fragment渲染的视图View

    protected boolean isVisible;//页面是否可见

    protected P mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (onCreatePresenter() != null) {
            mPresenter = onCreatePresenter();
        }
        if (mContextView == null) mContextView = inflater.inflate(bindLayout(), container, false);
        ButterKnife.bind(this, mContextView);
        initView(mContextView);
        toDo(getActivity());//业务处理
        setListener(getActivity());//设置监听
        return mContextView;
    }

    /**
     * 绑定渲染视图的布局文件
     *
     * @return 布局文件资源id
     */
    protected abstract int bindLayout();

    /**
     * 初始化控件
     */
    protected abstract void initView(final View view);

    /**
     * 设置监听
     */
    protected abstract void setListener(Context mContext);

    /**
     * 业务处理操作（onCreateView方法中调用）
     *
     * @param mContext 当前Activity对象
     */
    protected abstract void toDo(Context mContext);

    /**
     * 页面可见并且控件初始化完毕时，加载设置数据
     */
    protected abstract void loadData();

    /**
     * 页面不可见 ，失去焦点，相当于Fragment的onPause
     */
    protected void onInvisible() {

    }

    /**
     * 页面可见 ，获得焦点，相当于Fragment的onResume
     */
    protected void onVisible() {
        loadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }

    /**
     * 创建Presenter
     *
     * @return
     */
    protected abstract P onCreatePresenter();


}
