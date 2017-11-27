package com.example.bbacr.ddw.adapter.callback;

import com.example.bbacr.ddw.bean.my.OrderList;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public interface IOrderTwoCallBack {
    /**
     * 确认收货
     */
    void onReceive(OrderList.PurchaseOrdersBean.ShopOrdersBean item);

    /**
     * 评价
     */
    void onEvaluate(OrderList.PurchaseOrdersBean.ShopOrdersBean item);

    /**
     * 取消订单
     */
    void onCancel(OrderList.PurchaseOrdersBean.ShopOrdersBean item);

    /**
     * @param item
     * 查看物流
     */
    void onLook(OrderList.PurchaseOrdersBean.ShopOrdersBean item);

    void onClick(OrderList.PurchaseOrdersBean.ShopOrdersBean item);

}
