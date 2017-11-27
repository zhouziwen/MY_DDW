package com.example.bbacr.ddw.bean;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class ServicePopBean {
    private int imgResId;
    private String name;
    private String content;

    public ServicePopBean(String name) {
        this.name = name;
    }

    public int getImgResId() {

        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
