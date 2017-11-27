package com.example.bbacr.ddw.bean.my;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class PurchaseOrderNum {

    /**
     * createNum : 27
     * commentNum : 2
     * msg : success
     * deliveriedNum : 1
     * paymentNum : 1
     * code : 1
     */

    private int createNum;
    private int commentNum;
    private String msg;
    private int deliveriedNum;
    private int paymentNum;
    private int code;

    public int getCreateNum() {
        return createNum;
    }

    public void setCreateNum(int createNum) {
        this.createNum = createNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getDeliveriedNum() {
        return deliveriedNum;
    }

    public void setDeliveriedNum(int deliveriedNum) {
        this.deliveriedNum = deliveriedNum;
    }

    public int getPaymentNum() {
        return paymentNum;
    }

    public void setPaymentNum(int paymentNum) {
        this.paymentNum = paymentNum;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
