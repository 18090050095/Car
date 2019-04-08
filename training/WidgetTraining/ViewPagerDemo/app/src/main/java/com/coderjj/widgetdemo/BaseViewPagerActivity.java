package com.coderjj.widgetdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderjj.widgetdemo.viewpager.CommonViewPager;
import com.coderjj.widgetdemo.viewpager.DataEntry;
import com.coderjj.widgetdemo.viewpager.ViewPagerHolder;
import com.coderjj.widgetdemo.viewpager.ViewPagerHolderCreator;

import java.util.ArrayList;
import java.util.List;

public class BaseViewPagerActivity extends AppCompatActivity {

    private CommonViewPager mCommonViewPager;
    public static final String TAG = "BaseViewPagerActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_view_pager);
        Log.d(TAG, "onCreate: ");
        initView();
    }

    private void initView() {
        Log.d(TAG, "initView: ");
        mCommonViewPager = findViewById(R.id.activity_common_view_pager);
        // 设置数据
        mCommonViewPager.setPages(mockData(), new ViewPagerHolderCreator<ViewImageHolder>() {
            @Override
            public ViewImageHolder createViewHolder() {
                // 返回ViewPagerHolder
                return new ViewImageHolder();
            }
        });
    }

    private List<DataEntry> mockData() {
        Log.d(TAG, "mockData: ");
        ArrayList mArrayList = new ArrayList();
        mArrayList.add(new DataEntry(R.drawable.welcomimg1,"1"));
        mArrayList.add(new DataEntry(R.drawable.welcomimg2,"2"));
        mArrayList.add(new DataEntry(R.drawable.welcomimg3,"3"));
        mArrayList.add(new DataEntry(R.drawable.welcomimg4,"4"));
        return mArrayList;
    }


    /**
     * 提供ViewPager展示的ViewHolder
     * <P>用于提供布局和绑定数据</P>
     */
    public static class ViewImageHolder implements ViewPagerHolder<DataEntry> {
        private ImageView mImageView;
        private TextView mTextView;
        @Override
        public View createView(Context context) {
            // 返回ViewPager 页面展示的布局
            View view = LayoutInflater.from(context).inflate(R.layout.view_pager_item,null);
            mImageView = view.findViewById(R.id.viewPager_item_image);
            mTextView = view.findViewById(R.id.item_desc);
            return view;
        }

        @Override
        public void onBind(Context context, int position, DataEntry data) {
            // 数据绑定
            // 自己绑定数据，灵活度很大
            mImageView.setImageResource(data.getImageResId());
            mTextView.setText(data.getDesc());
        }
    }
}
