package com.example.bbacr.ddw.bean.my;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class RefundDetail {


    /**
     * msg : success
     * code : 1
     * datas : {"address":{"code":"p41510382565822","payMoney":"6966.00","addressDetail":"jiandong259","city":"HongKong","phone":"18838998708","purchaseOrderId":20,"userName":"Andy"},"refundLog":{"refundTypeValue":1,"refundState":"","refundType":"退款退货","code":"R41510383498180","refundExplain":"bad","handleTime":"2017-11-11 14:58:18","createTime":"2017-11-11 14:58:18","refundReason":"我不想要了","num":1,"refundMoney":"5000.00","id":2,"refundReasonValue":1},"goods":{"goodsImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","unitPrice":"6666.00","code":"i41510382566126","orderStateValue":10,"goodsId":3,"id":23,"goodsName":"iPhone 81(A1863) 港版","goodsNum":1,"specificationName":"规格:中","orderState":"确认收货"},"serviceOrder":[{"next":"2次","warrantyPeriod":"12个月","valueAddedServicesKind":"进水服务","detailsUrl":"www.baidu.com","price":"300元","name":"两次碎屏保（限时特惠）","id":8,"label":"suiping2","state":"有效","valueAddedServicesType":"按次服务"}]}
     */

    private String msg;
    private int code;
    private DatasBean datas;

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

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * address : {"code":"p41510382565822","payMoney":"6966.00","addressDetail":"jiandong259","city":"HongKong","phone":"18838998708","purchaseOrderId":20,"userName":"Andy"}
         * refundLog : {"refundTypeValue":1,"refundState":"","refundType":"退款退货","code":"R41510383498180","refundExplain":"bad","handleTime":"2017-11-11 14:58:18","createTime":"2017-11-11 14:58:18","refundReason":"我不想要了","num":1,"refundMoney":"5000.00","id":2,"refundReasonValue":1}
         * goods : {"goodsImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","unitPrice":"6666.00","code":"i41510382566126","orderStateValue":10,"goodsId":3,"id":23,"goodsName":"iPhone 81(A1863) 港版","goodsNum":1,"specificationName":"规格:中","orderState":"确认收货"}
         * serviceOrder : [{"next":"2次","warrantyPeriod":"12个月","valueAddedServicesKind":"进水服务","detailsUrl":"www.baidu.com","price":"300元","name":"两次碎屏保（限时特惠）","id":8,"label":"suiping2","state":"有效","valueAddedServicesType":"按次服务"}]
         */

        private AddressBean address;
        private RefundLogBean refundLog;
        private GoodsBean goods;
        private List<ServiceOrderBean> serviceOrder;

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public RefundLogBean getRefundLog() {
            return refundLog;
        }

        public void setRefundLog(RefundLogBean refundLog) {
            this.refundLog = refundLog;
        }

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public List<ServiceOrderBean> getServiceOrder() {
            return serviceOrder;
        }

        public void setServiceOrder(List<ServiceOrderBean> serviceOrder) {
            this.serviceOrder = serviceOrder;
        }

        public static class AddressBean {
            /**
             * code : p41510382565822
             * payMoney : 6966.00
             * addressDetail : jiandong259
             * city : HongKong
             * phone : 18838998708
             * purchaseOrderId : 20
             * userName : Andy
             */

            private String code;
            private String payMoney;
            private String addressDetail;
            private String city;
            private String phone;
            private int purchaseOrderId;
            private String userName;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getPayMoney() {
                return payMoney;
            }

            public void setPayMoney(String payMoney) {
                this.payMoney = payMoney;
            }

            public String getAddressDetail() {
                return addressDetail;
            }

            public void setAddressDetail(String addressDetail) {
                this.addressDetail = addressDetail;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getPurchaseOrderId() {
                return purchaseOrderId;
            }

            public void setPurchaseOrderId(int purchaseOrderId) {
                this.purchaseOrderId = purchaseOrderId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }

        public static class RefundLogBean {
            /**
             * refundTypeValue : 1
             * refundState :
             * refundType : 退款退货
             * code : R41510383498180
             * refundExplain : bad
             * handleTime : 2017-11-11 14:58:18
             * createTime : 2017-11-11 14:58:18
             * refundReason : 我不想要了
             * num : 1
             * refundMoney : 5000.00
             * id : 2
             * refundReasonValue : 1
             */

            private int refundTypeValue;
            private String refundState;
            private String refundType;
            private String code;
            private String refundExplain;
            private String handleTime;
            private String createTime;
            private String refundReason;
            private int num;
            private String refundMoney;
            private int id;
            private int refundReasonValue;

            public int getRefundTypeValue() {
                return refundTypeValue;
            }

            public void setRefundTypeValue(int refundTypeValue) {
                this.refundTypeValue = refundTypeValue;
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

            public String getRefundExplain() {
                return refundExplain;
            }

            public void setRefundExplain(String refundExplain) {
                this.refundExplain = refundExplain;
            }

            public String getHandleTime() {
                return handleTime;
            }

            public void setHandleTime(String handleTime) {
                this.handleTime = handleTime;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getRefundReason() {
                return refundReason;
            }

            public void setRefundReason(String refundReason) {
                this.refundReason = refundReason;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getRefundMoney() {
                return refundMoney;
            }

            public void setRefundMoney(String refundMoney) {
                this.refundMoney = refundMoney;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getRefundReasonValue() {
                return refundReasonValue;
            }

            public void setRefundReasonValue(int refundReasonValue) {
                this.refundReasonValue = refundReasonValue;
            }
        }

        public static class GoodsBean {
            /**
             * goodsImg : http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png
             * unitPrice : 6666.00
             * code : i41510382566126
             * orderStateValue : 10
             * goodsId : 3
             * id : 23
             * goodsName : iPhone 81(A1863) 港版
             * goodsNum : 1
             * specificationName : 规格:中
             * orderState : 确认收货
             */

            private String goodsImg;
            private String unitPrice;
            private String code;
            private int orderStateValue;
            private int goodsId;
            private int id;
            private String goodsName;
            private int goodsNum;
            private String specificationName;
            private String orderState;

            public String getGoodsImg() {
                return goodsImg;
            }

            public void setGoodsImg(String goodsImg) {
                this.goodsImg = goodsImg;
            }

            public String getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(String unitPrice) {
                this.unitPrice = unitPrice;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getOrderStateValue() {
                return orderStateValue;
            }

            public void setOrderStateValue(int orderStateValue) {
                this.orderStateValue = orderStateValue;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
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

            public int getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(int goodsNum) {
                this.goodsNum = goodsNum;
            }

            public String getSpecificationName() {
                return specificationName;
            }

            public void setSpecificationName(String specificationName) {
                this.specificationName = specificationName;
            }

            public String getOrderState() {
                return orderState;
            }

            public void setOrderState(String orderState) {
                this.orderState = orderState;
            }
        }

        public static class ServiceOrderBean {
            /**
             * next : 2次
             * warrantyPeriod : 12个月
             * valueAddedServicesKind : 进水服务
             * detailsUrl : www.baidu.com
             * price : 300元
             * name : 两次碎屏保（限时特惠）
             * id : 8
             * label : suiping2
             * state : 有效
             * valueAddedServicesType : 按次服务
             */

            private String next;
            private String warrantyPeriod;
            private String valueAddedServicesKind;
            private String detailsUrl;
            private String price;
            private String name;
            private int id;
            private String label;
            private String state;
            private String valueAddedServicesType;

            public String getNext() {
                return next;
            }

            public void setNext(String next) {
                this.next = next;
            }

            public String getWarrantyPeriod() {
                return warrantyPeriod;
            }

            public void setWarrantyPeriod(String warrantyPeriod) {
                this.warrantyPeriod = warrantyPeriod;
            }

            public String getValueAddedServicesKind() {
                return valueAddedServicesKind;
            }

            public void setValueAddedServicesKind(String valueAddedServicesKind) {
                this.valueAddedServicesKind = valueAddedServicesKind;
            }

            public String getDetailsUrl() {
                return detailsUrl;
            }

            public void setDetailsUrl(String detailsUrl) {
                this.detailsUrl = detailsUrl;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getValueAddedServicesType() {
                return valueAddedServicesType;
            }

            public void setValueAddedServicesType(String valueAddedServicesType) {
                this.valueAddedServicesType = valueAddedServicesType;
            }
        }
    }
}
