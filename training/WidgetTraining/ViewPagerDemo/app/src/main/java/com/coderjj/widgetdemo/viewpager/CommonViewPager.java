package com.coderjj.widgetdemo.viewpager;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.coderjj.widgetdemo.R;
import com.zhouwei.indicatorview.CircleIndicatorView;

import java.util.List;

/**
 * Created by 刘杰 on 2019/4/6
 */
public class CommonViewPager<T> extends RelativeLayout {
    private ViewPager mViewPager;
    private CommonViewPagerAdapter mAdapter;
    private CircleIndicatorView mCircleIndicatorView;
    public CommonViewPager(@NonNull Context context) {
        super(context);
        init();
    }

    public CommonViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CommonViewPager(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CommonViewPager(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.common_view_pager_layout,this,true);
        mViewPager = (ViewPager) view.findViewById(R.id.common_view_pager);
        mCircleIndicatorView = (CircleIndicatorView) view.findViewById(R.id.common_view_pager_indicator_view);
    }

    /**
     * 设置数据
     * @param data
     * @param creator
     */
    public void setPages(List<T> data, ViewPagerHolderCreator creator){
        mAdapter = new CommonViewPagerAdapter(data,creator);
        mViewPager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mCircleIndicatorView.setUpWithViewPager(mViewPager);
    }

    /**
     * 设置当前页面
     * @param currentItem
     */
    public void setCurrentItem(int currentItem){
        mViewPager.setCurrentItem(currentItem);
    }

    public int getCurrentItem(){
        return mViewPager.getCurrentItem();
    }

    /**
     * 设置当前页面的前后limit页被加载
     * @param limit 为0或1都表示缓存前后一页
     */
    public void setOffscreenPageLimit(int limit){
        mViewPager.setOffscreenPageLimit(limit);
    }

    /**
     * 设置切换动画，see {@link ViewPager#setPageTransformer(boolean, ViewPager.PageTransformer)}
     * @param reverseDrawingOrder
     * @param transformer
     */
    public void setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer){
        mViewPager.setPageTransformer(reverseDrawingOrder,transformer);
    }

    /**
     * see {@link ViewPager#setPageTransformer(boolean, ViewPager.PageTransformer)}
     * @param reverseDrawingOrder
     * @param transformer
     * @param pageLayerType
     */
    public void setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer,
                                   int pageLayerType) {
        mViewPager.setPageTransformer(reverseDrawingOrder,transformer,pageLayerType);
    }

    /**
     * see {@link ViewPager#addOnPageChangeListener(ViewPager.OnPageChangeListener)}
     * @param listener
     */
    public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener){
        mViewPager.addOnPageChangeListener(listener);
    }

    /**
     * 设置是否显示Indicator
     * @param visible
     */
    private void setIndicatorVisible(boolean visible){
        if(visible){
            mCircleIndicatorView.setVisibility(VISIBLE);
        }else{
            mCircleIndicatorView.setVisibility(GONE);
        }

    }

    public ViewPager getViewPager() {
        return mViewPager;
    }
}
