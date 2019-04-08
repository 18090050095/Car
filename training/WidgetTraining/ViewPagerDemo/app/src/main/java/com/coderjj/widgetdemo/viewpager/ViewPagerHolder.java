package com.coderjj.widgetdemo.viewpager;

import android.content.Context;
import android.view.View;

/**
 * created by coderjj on 19/4/6
 *
 * @param <T>
 */
public interface ViewPagerHolder<T> {

    /**
     * 创建View 提供给Adapter布局
     * @param context
     * @return
     */
    View createView(Context context);

    /**
     * 绑定参数
     * @param context
     * @param position
     * @param data 这是绑定数据要用的实体类
     */
    void onBind(Context context, int position, T data);
}
