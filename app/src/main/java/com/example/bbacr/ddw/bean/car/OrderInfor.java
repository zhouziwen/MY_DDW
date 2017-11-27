package com.example.bbacr.ddw.bean.car;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class OrderInfor {

    /**
     * goodId : 3
     * num : 4
     * goodSpecificationId : 1
     * addService : 1,2,3,4
     */

    private int goodId;
    private int num;
    private int goodSpecificationId;
    private String addService;

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getGoodSpecificationId() {
        return goodSpecificationId;
    }

    public void setGoodSpecificationId(int goodSpecificationId) {
        this.goodSpecificationId = goodSpecificationId;
    }

    public String getAddService() {
        return addService;
    }

    public void setAddService(String addService) {
        this.addService = addService;
    }
}
