package com.example.bbacr.ddw.adapter.lrecycle;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class RefundList {

    /**
     * msg : success
     * code : 1
     * totalPage : 1
     * pageNo : 1
     * refundList : [{"refundTypeValue":1,"goodsOrderId":23,"goodsImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","refundState":"","refundType":"退款退货","code":"R41510383498180","shopName":"总仓店铺","id":2,"goodsName":"iPhone 81(A1863) 港版","specificationName":"规格:中"},{"refundTypeValue":1,"goodsOrderId":20,"goodsImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","refundState":"退款申请","refundType":"退款退货","refundStateValue":1,"code":"R41510382044996","shopName":"总仓店铺","id":1,"goodsName":"iPhone 8(A1863) 国行版","specificationName":"颜色:白,版本:32G"}]
     * recordsTotal : 2
     */

    private String msg;
    private int code;
    private int totalPage;
    private int pageNo;
    private int recordsTotal;
    private List<RefundListBean> refundList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public List<RefundListBean> getRefundList() {
        return refundList;
    }

    public void setRefundList(List<RefundListBean> refundList) {
        this.refundList = refundList;
    }

    public static class RefundListBean {
        /**
         * refundTypeValue : 1
         * goodsOrderId : 23
         * goodsImg : http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png
         * refundState :
         * refundType : 退款退货
         * code : R41510383498180
         * shopName : 总仓店铺
         * id : 2
         * goodsName : iPhone 81(A1863) 港版
         * specificationName : 规格:中
         * refundStateValue : 1
         */

        private int refundTypeValue;
        private int goodsOrderId;
        private String goodsImg;
        private String refundState;
        private String refundType;
        private String code;
        private String shopName;
        private int id;
        private String goodsName;
        private String specificationName;
        private int refundStateValue;

        public int getRefundTypeValue() {
            return refundTypeValue;
        }

        public void setRefundTypeValue(int refundTypeValue) {
            this.refundTypeValue = refundTypeValue;
        }

        public int getGoodsOrderId() {
            return goodsOrderId;
        }

        public void setGoodsOrderId(int goodsOrderId) {
            this.goodsOrderId = goodsOrderId;
        }

        public String getGoodsImg() {
            return goodsImg;
        }

        public void setGoodsImg(String goodsImg) {
            this.goodsImg = goodsImg;
        }

        public String getRefundState() {
            return refundState;
        }

        public void setRefundState(String refundState) {
            this.refundState = refundState;
        }

        public String getRefundType() {
            return refundType;
        }

        public void setRefundType(String refundType) {
            this.refundType = refundType;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getSpecificationName() {
            return specificationName;
        }

        public void setSpecificationName(String specificationName) {
            this.specificationName = specificationName;
        }

        public int getRefundStateValue() {
            return refundStateValue;
        }

        public void setRefundStateValue(int refundStateValue) {
            this.refundStateValue = refundStateValue;
        }
    }
}
