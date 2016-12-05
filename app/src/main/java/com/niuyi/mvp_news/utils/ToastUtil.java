package com.niuyi.mvp_news.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.niuyi.mvp_news.R;


/**
 * 作者：${牛毅} on 2016/4/20 16:15
 * 邮箱：niuyi19900923@hotmail.com
 */
public class ToastUtil {

    public static Toast mToast;

    public ToastUtil() {

    }

    public static void showToast(Context context, String msg) {
        View view = null;
        TextView tv_toast = null;
        if (mToast == null) {
            mToast = new Toast(context);
            mToast.setGravity(Gravity.TOP, 0, 200);
            mToast.setDuration(Toast.LENGTH_SHORT);
            view = LinearLayout.inflate(context, R.layout.toast_layout, null);
            tv_toast = (TextView) view.findViewById(R.id.tv_toast);
            tv_toast.setText(msg);
        } else {
            view = LinearLayout.inflate(context, R.layout.toast_layout, null);
            tv_toast = (TextView) view.findViewById(R.id.tv_toast);
            tv_toast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }

        if (view != null) {
            mToast.setView(view);
        }
        if (mToast != null) {
            mToast.show();
        }

    }
}
