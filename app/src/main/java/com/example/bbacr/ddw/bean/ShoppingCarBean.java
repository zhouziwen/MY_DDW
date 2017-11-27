package com.example.bbacr.ddw.bean;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class ShoppingCarBean  {
    private String mShoppingName;
    private String mShoppingSpecification;
    private String mShoppingPrice;
    private int mCount;

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public String getShoppingName() {
        return mShoppingName;
    }

    public void setShoppingName(String shoppingName) {
        mShoppingName = shoppingName;
    }

    public String getShoppingSpecification() {
        return mShoppingSpecification;
    }

    public void setShoppingSpecification(String shoppingSpecification) {
        mShoppingSpecification = shoppingSpecification;
    }

    public String getShoppingPrice() {
        return mShoppingPrice;
    }

    public void setShoppingPrice(String shoppingPrice) {
        mShoppingPrice = shoppingPrice;
    }

    public ShoppingCarBean(String shoppingName, String shoppingSpecification, String shoppingPrice, int count) {
        mShoppingName = shoppingName;
        mShoppingSpecification = shoppingSpecification;
        mShoppingPrice = shoppingPrice;
        mCount = count;
    }
}
