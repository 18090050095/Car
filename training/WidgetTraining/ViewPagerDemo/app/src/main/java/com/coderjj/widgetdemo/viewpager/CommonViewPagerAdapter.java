package com.coderjj.widgetdemo.viewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.coderjj.widgetdemo.R;

/**
 * Created by coderjj on 2019/4/6
 */
public class CommonViewPagerAdapter extends PagerAdapter {
    public static final String TAG = "CommonViewPagerAdapter";
    private List mDatas;
    private ViewPagerHolderCreator mCreator;//ViewHolder生成器

    public CommonViewPagerAdapter(List datas, ViewPagerHolderCreator creator) {
        mDatas = datas;
        mCreator = creator;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    /**
     * 预加载，首次进入默认加载两次
     * @param container 当前的ViewPager对象
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //重点就在这儿了，不再是把布局写死，而是用接口提供的布局
        // 也不在这里绑定数据，数据绑定交给Api调用者。
        View view = getView(position, null, container);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**
     * 获取viewPager 页面展示View
     *
     * @param position
     * @param view
     * @param container 当前的ViewPager对象
     * @return
     */
    private View getView(int position, View view, ViewGroup container) {
        Log.d(TAG, "getView: ");

        ViewPagerHolder holder = null;
        if (view == null) {
            //创建Holder
            holder = mCreator.createViewHolder();
            view = holder.createView(container.getContext());
            view.setTag(R.id.common_view_pager_item_tag, holder);
        } else {
            holder = (ViewPagerHolder) view.getTag(R.id.common_view_pager_item_tag);
        }
        if (holder != null && mDatas != null && mDatas.size() > 0) {
            // 数据绑定
            holder.onBind(container.getContext(), position, mDatas.get(position));
        }
        return view;
    }
}
