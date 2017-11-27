package com.example.bbacr.ddw.adapter.callback;


import com.example.bbacr.ddw.bean.ClassifyBean;
import com.example.bbacr.ddw.bean.my.OrderList;

/**
 * Created by Nianner Wang on 2017/9/27.
 * wytaper495@qq.com
 * 类注释：
 */

public interface IOrderCallBack {
    void back(OrderList.PurchaseOrdersBean.ShopOrdersBean item);
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
    void onCancel2(OrderList.PurchaseOrdersBean.ShopOrdersBean item);
    /**
     * 取消订单
     */
    void onCancel(OrderList.PurchaseOrdersBean item);

    /**
     * 删除订单
     */
    void onDelete(OrderList.PurchaseOrdersBean.ShopOrdersBean item, int position);

    void pay(OrderList.PurchaseOrdersBean item);
    /**
     * @param item
     * 查看物流
     */
    void onLook(OrderList.PurchaseOrdersBean.ShopOrdersBean item);
}
