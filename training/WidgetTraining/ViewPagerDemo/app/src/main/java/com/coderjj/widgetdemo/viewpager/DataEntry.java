package com.coderjj.widgetdemo.viewpager;

/**
 * Created by 刘杰 on 2019/4/6
 */
public class DataEntry {
    public DataEntry(int imageResId, String desc) {
        this.imageResId = imageResId;
        this.desc = desc;
    }

    private int imageResId;
    private String desc;

    public int getImageResId() {
        return imageResId;
    }

    public String getDesc() {
        return desc;
    }
}
