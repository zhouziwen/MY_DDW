package com.example.bbacr.ddw.adapter.callback;

import com.example.bbacr.ddw.bean.car.GetUserShoppingCart;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public interface ICarCallBack {
    void ClickTure(int i);
    void ClickFalse(int i);
    void ClickBomTure(boolean isIsSelect,int carId,GetUserShoppingCart.DatasBean item,int pos);
    void ClickBomFalse(boolean isIsSelect,int carId,GetUserShoppingCart.DatasBean item,int pos);
    void Count(GetUserShoppingCart.DatasBean item);
    void delete(GetUserShoppingCart.DatasBean item,int pos);
    void collect(GetUserShoppingCart.DatasBean item);
    void specification(GetUserShoppingCart.DatasBean item,int pos);
}
