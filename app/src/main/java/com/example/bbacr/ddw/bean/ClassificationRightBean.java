package com.example.bbacr.ddw.bean;

/**
 * Created by WangYeTong on 2017/9/11.
 * Email:wytaper495@qq.com
 */

public class ClassificationRightBean {
    private String mName;
    private int mIcon;

    public ClassificationRightBean(String name) {
        mName = name;
    }

    public ClassificationRightBean(String mName, int mIcon) {
        this.mName = mName;
        this.mIcon = mIcon;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmIcon() {
        return mIcon;
    }

    public void setmIcon(int mIcon) {
        this.mIcon = mIcon;
    }
}
