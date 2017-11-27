package com.example.bbacr.ddw.bean;

/**
 * Created by Administrator on 2017/7/25.
 *
 */

public class ClassifcationBean implements BaseItemBean {

    public ClassifcationBean(String classifcationName) {
        this.classifcationName = classifcationName;
    }

    public ClassifcationBean() {
    }

    private String classifcationName;
    private int classifcationImg;
    private int itemType = 0;

    public String getClassifcationName() {
        return classifcationName;
    }

    public void setClassifcationName(String classifcationName) {
        this.classifcationName = classifcationName;
    }

    public int getClassifcationImg() {
        return classifcationImg;
    }

    public void setClassifcationImg(int classifcationImg) {
        this.classifcationImg = classifcationImg;
    }



    @Override
    public int getItemType() {
        return itemType;
    }
}
