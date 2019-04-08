package com.coderjj.widgetdemo.viewpager;

/**
 * Created by coderjj on 2019/4/6
 *
 * 该类接受一个泛型，但是必须得是ViewPagerHolder的子类，一个方法createViewHolder，返回ViewHolder实例
 */
public interface ViewPagerHolderCreator<VH extends ViewPagerHolder> {
    /**
     * 创建ViewHolder
     * @return
     */
    VH createViewHolder();
}
