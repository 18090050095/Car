package com.coderjj.customview.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.coderjj.customview.utils.MeasureUtil;

/**
 * Created by Administrator on 2019/4/23.
 */

public class ChangeCircleView extends View implements Runnable {
    private Paint mPaint;//画笔
    private Context mContext;

    private int radiu;//圆环半径
    public ChangeCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
    }

    /**
     * 初始化画笔
     * 该方法不要在onDraw中执行，onDraw会被频繁的调用，new对象将会浪费内存
     */
    private void initPaint() {
        //实例化画笔并打开抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /**
         * 设置画笔样式为描边：
         * 1.Paint.Style.STROKE:描边
         * 2.Paint.Style.FILL_AND_STROKE:描边并填充
         * 3.Paint.Style.FILL:填充
         */
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.LTGRAY);
        /**
         * 设置描边的粗细，单位px
         * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
         */
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制圆环
        canvas.drawCircle(MeasureUtil.getScreenSize((Activity) mContext)[0]/2,
                MeasureUtil.getScreenSize((Activity) mContext)[1]/2,radiu,mPaint);
    }

    @Override
    public void run() {
        while(true){
            try {
                if(radiu <= 200){
                    radiu += 10;

                    /**
                     * 工作线程中更新UI会报错使用下面的方法
                     */
//                    invalidate();
                    postInvalidate();
                }else {
                    radiu = 0;
                }

                //每执行一次暂停40毫秒
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
