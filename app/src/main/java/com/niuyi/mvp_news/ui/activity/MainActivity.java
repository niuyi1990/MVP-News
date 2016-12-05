package com.niuyi.mvp_news.ui.activity;

import android.os.Bundle;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;

import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.ui.adapter.MyFragmentPagerAdapter;
import com.niuyi.mvp_news.ui.fragment.FragmentMainOne;
import com.niuyi.mvp_news.ui.fragment.FragmentMainThree;
import com.niuyi.mvp_news.ui.fragment.FragmentMainTwo;
import com.niuyi.mvp_news.ui.widght.NoScrollViewPager;
import com.niuyi.mvp_news.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.niuyi.mvp_news.R.id.bottom_navigation;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener {

    @BindView(R.id.vp_main)
    NoScrollViewPager mVpMain;
    @BindView(bottom_navigation)
    BottomNavigationView mBottomNavigation;

    MenuItem prevMenuItem;

    private long lastBackKeyDownTick = 0;
    public static final long MAX_DOUBLE_BACK_DURATION = 2000;//间隔2秒返回退出

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);  //隐藏掉系统原先的导航栏
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mBottomNavigation.setOnNavigationItemSelectedListener(this);
        mVpMain.setOnPageChangeListener(this);

        initViewPager();
    }

    private void initViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentMainOne.newInstance());
        fragments.add(FragmentMainTwo.newInstance());
        fragments.add(FragmentMainThree.newInstance());
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mVpMain.setAdapter(myFragmentPagerAdapter);

        mVpMain.setOffscreenPageLimit(2);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_call:
                mVpMain.setCurrentItem(0);
                break;
            case R.id.item_mail:
                mVpMain.setCurrentItem(1);
                break;
            case R.id.item_person:
                mVpMain.setCurrentItem(2);
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (prevMenuItem != null) {
            prevMenuItem.setChecked(false);
        } else {
            mBottomNavigation.getMenu().getItem(0).setChecked(false);
        }
        mBottomNavigation.getMenu().getItem(position).setChecked(true);
        prevMenuItem = mBottomNavigation.getMenu().getItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {
        long currentTick = System.currentTimeMillis();
        if (currentTick - lastBackKeyDownTick > MAX_DOUBLE_BACK_DURATION) {
            ToastUtil.showToast(this, "再按一次退出应用程序");
            lastBackKeyDownTick = currentTick;
        } else {
            finish();
            Process.killProcess(Process.myPid());
        }
    }
}
