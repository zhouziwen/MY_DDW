package com.example.bbacr.ddw.bean.car;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class SubmitOrder {

    /**
     * msg : success
     * payMoney : 7106.00
     * code : 1
     * payType : 1
     * pCode : p41510113792046
     * deliveryType : 1
     */

    private String msg;
    private String payMoney;
    private int code;
    private int payType;
    private String pCode;
    private int deliveryType;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getPCode() {
        return pCode;
    }

    public void setPCode(String pCode) {
        this.pCode = pCode;
    }

    public int getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(int deliveryType) {
        this.deliveryType = deliveryType;
    }
}
