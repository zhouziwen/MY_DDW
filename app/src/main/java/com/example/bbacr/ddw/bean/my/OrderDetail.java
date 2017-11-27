package com.example.bbacr.ddw.bean.my;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class OrderDetail {


    /**
     * msg : success
     * shopOrder : {"code":"c41510794835229","shopLocation":"东风路政七街","deliveryType":"到店自取","shopName":"总仓店铺","express":"","hasComment":false,"orderState":"创建订单","nowTime":1510794926675,"expressTypeValue":"","shopMan":"王超","orderStateValue":1,"payMoney":"21989.00","payType":"网上支付","createTime":"2017-11-16 09:13:55","goodsOrders":[{"goodsImg":"http://ddw-image.oss-cn-beijing.aliyuncs.com/352193894b4642f0a348b1218efe5ba220171113.png","unitPrice":"10690.00","serviceNum":2,"code":"i41510794835615","goodsId":2,"num":1,"totalMoney":"11290.00","hasComment":false,"orderState":"创建订单","goodsLmie":"","hasLmie":true,"orderStateValue":1,"payMoney":"11290.00","serviceOrders":[{"next":"2次","warrantyPeriod":"12个月","valueAddedServicesKind":"碎屏服务","detailsUrl":"www.baidu.com","price":"300元","name":"两次碎屏保（限时特惠）","id":8,"label":"suiping2","state":"有效","valueAddedServicesType":"按次服务"},{"next":"2次","warrantyPeriod":"12个月","valueAddedServicesKind":"碎屏服务","detailsUrl":"www.baidu.com","price":"300元","name":"两次碎屏保（限时特惠）","id":8,"label":"suiping2","state":"有效","valueAddedServicesType":"按次服务"}],"saveMoney":"0.00","id":49,"goodsName":"Apple iPhone (A1865) 国行版 银色64GB","serviceMoney":"600.0","specificationName":"颜色:64G"},{"goodsImg":"http://ddw-image.oss-cn-beijing.aliyuncs.com/352193894b4642f0a348b1218efe5ba220171113.png","unitPrice":"10699.00","serviceNum":0,"code":"i41510794836383","goodsId":2,"num":1,"totalMoney":"10699.00","hasComment":false,"orderState":"创建订单","goodsLmie":"","hasLmie":true,"orderStateValue":1,"payMoney":"10699.00","serviceOrders":[],"saveMoney":"0.00","id":50,"goodsName":"Apple iPhone (A1865) 国行版 银色64GB","serviceMoney":0,"specificationName":"颜色:256G"}],"payTypeValue":1,"shopPhone":"18037350015","timeDif":86308325,"id":43,"deliveryTypeValue":1,"wayBillNum":""}
     * code : 1
     * deliveryAddress : {"code":"p41510794835129","payMoney":"21989.00","addressDetail":"","city":"","phone":"","purchaseOrderId":43,"userName":"飞龙"}
     */

    private String msg;
    private ShopOrderBean shopOrder;
    private int code;
    private DeliveryAddressBean deliveryAddress;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ShopOrderBean getShopOrder() {
        return shopOrder;
    }

    public void setShopOrder(ShopOrderBean shopOrder) {
        this.shopOrder = shopOrder;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DeliveryAddressBean getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddressBean deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public static class ShopOrderBean {
        /**
         * code : c41510794835229
         * shopLocation : 东风路政七街
         * deliveryType : 到店自取
         * shopName : 总仓店铺
         * express :
         * hasComment : false
         * orderState : 创建订单
         * nowTime : 1510794926675
         * expressTypeValue :
         * shopMan : 王超
         * orderStateValue : 1
         * payMoney : 21989.00
         * payType : 网上支付
         * createTime : 2017-11-16 09:13:55
         * goodsOrders : [{"goodsImg":"http://ddw-image.oss-cn-beijing.aliyuncs.com/352193894b4642f0a348b1218efe5ba220171113.png","unitPrice":"10690.00","serviceNum":2,"code":"i41510794835615","goodsId":2,"num":1,"totalMoney":"11290.00","hasComment":false,"orderState":"创建订单","goodsLmie":"","hasLmie":true,"orderStateValue":1,"payMoney":"11290.00","serviceOrders":[{"next":"2次","warrantyPeriod":"12个月","valueAddedServicesKind":"碎屏服务","detailsUrl":"www.baidu.com","price":"300元","name":"两次碎屏保（限时特惠）","id":8,"label":"suiping2","state":"有效","valueAddedServicesType":"按次服务"},{"next":"2次","warrantyPeriod":"12个月","valueAddedServicesKind":"碎屏服务","detailsUrl":"www.baidu.com","price":"300元","name":"两次碎屏保（限时特惠）","id":8,"label":"suiping2","state":"有效","valueAddedServicesType":"按次服务"}],"saveMoney":"0.00","id":49,"goodsName":"Apple iPhone (A1865) 国行版 银色64GB","serviceMoney":"600.0","specificationName":"颜色:64G"},{"goodsImg":"http://ddw-image.oss-cn-beijing.aliyuncs.com/352193894b4642f0a348b1218efe5ba220171113.png","unitPrice":"10699.00","serviceNum":0,"code":"i41510794836383","goodsId":2,"num":1,"totalMoney":"10699.00","hasComment":false,"orderState":"创建订单","goodsLmie":"","hasLmie":true,"orderStateValue":1,"payMoney":"10699.00","serviceOrders":[],"saveMoney":"0.00","id":50,"goodsName":"Apple iPhone (A1865) 国行版 银色64GB","serviceMoney":0,"specificationName":"颜色:256G"}]
         * payTypeValue : 1
         * shopPhone : 18037350015
         * timeDif : 86308325
         * id : 43
         * deliveryTypeValue : 1
         * wayBillNum :
         */

        private String code;
        private String shopLocation;
        private String deliveryType;
        private String shopName;
        private String express;
        private boolean hasComment;
        private String orderState;
        private long nowTime;
        private String expressTypeValue;
        private String shopMan;
        private int orderStateValue;
        private String payMoney;
        private String payType;
        private String createTime;
        private int payTypeValue;
        private String shopPhone;
        private int timeDif;
        private int id;
        private int deliveryTypeValue;
        private String wayBillNum;
        private List<GoodsOrdersBean> goodsOrders;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getShopLocation() {
            return shopLocation;
        }

        public void setShopLocation(String shopLocation) {
            this.shopLocation = shopLocation;
        }

        public String getDeliveryType() {
            return deliveryType;
        }

        public void setDeliveryType(String deliveryType) {
            this.deliveryType = deliveryType;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getExpress() {
            return express;
        }

        public void setExpress(String express) {
            this.express = express;
        }

        public boolean isHasComment() {
            return hasComment;
        }

        public void setHasComment(boolean hasComment) {
            this.hasComment = hasComment;
        }

        public String getOrderState() {
            return orderState;
        }

        public void setOrderState(String orderState) {
            this.orderState = orderState;
        }

        public long getNowTime() {
            return nowTime;
        }

        public void setNowTime(long nowTime) {
            this.nowTime = nowTime;
        }

        public String getExpressTypeValue() {
            return expressTypeValue;
        }

        public void setExpressTypeValue(String expressTypeValue) {
            this.expressTypeValue = expressTypeValue;
        }

        public String getShopMan() {
            return shopMan;
        }

        public void setShopMan(String shopMan) {
            this.shopMan = shopMan;
        }

        public int getOrderStateValue() {
            return orderStateValue;
        }

        public void setOrderStateValue(int orderStateValue) {
            this.orderStateValue = orderStateValue;
        }

        public String getPayMoney() {
            return payMoney;
        }

        public void setPayMoney(String payMoney) {
            this.payMoney = payMoney;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getPayTypeValue() {
            return payTypeValue;
        }

        public void setPayTypeValue(int payTypeValue) {
            this.payTypeValue = payTypeValue;
        }

        public String getShopPhone() {
            return shopPhone;
        }

        public void setShopPhone(String shopPhone) {
            this.shopPhone = shopPhone;
        }

        public int getTimeDif() {
            return timeDif;
        }

        public void setTimeDif(int timeDif) {
            this.timeDif = timeDif;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDeliveryTypeValue() {
            return deliveryTypeValue;
        }

        public void setDeliveryTypeValue(int deliveryTypeValue) {
            this.deliveryTypeValue = deliveryTypeValue;
        }

        public String getWayBillNum() {
            return wayBillNum;
        }

        public void setWayBillNum(String wayBillNum) {
            this.wayBillNum = wayBillNum;
        }

        public List<GoodsOrdersBean> getGoodsOrders() {
            return goodsOrders;
        }

        public void setGoodsOrders(List<GoodsOrdersBean> goodsOrders) {
            this.goodsOrders = goodsOrders;
        }

        public static class GoodsOrdersBean {
            /**
             * goodsImg : http://ddw-image.oss-cn-beijing.aliyuncs.com/352193894b4642f0a348b1218efe5ba220171113.png
             * unitPrice : 10690.00
             * serviceNum : 2
             * code : i41510794835615
             * goodsId : 2
             * num : 1
             * totalMoney : 11290.00
             * hasComment : false
             * orderState : 创建订单
             * goodsLmie :
             * hasLmie : true
             * orderStateValue : 1
             * payMoney : 11290.00
             * serviceOrders : [{"next":"2次","warrantyPeriod":"12个月","valueAddedServicesKind":"碎屏服务","detailsUrl":"www.baidu.com","price":"300元","name":"两次碎屏保（限时特惠）","id":8,"label":"suiping2","state":"有效","valueAddedServicesType":"按次服务"},{"next":"2次","warrantyPeriod":"12个月","valueAddedServicesKind":"碎屏服务","detailsUrl":"www.baidu.com","price":"300元","name":"两次碎屏保（限时特惠）","id":8,"label":"suiping2","state":"有效","valueAddedServicesType":"按次服务"}]
             * saveMoney : 0.00
             * id : 49
             * goodsName : Apple iPhone (A1865) 国行版 银色64GB
             * serviceMoney : 600.0
             * specificationName : 颜色:64G
             */

            private String goodsImg;
            private String unitPrice;
            private int serviceNum;
            private String code;
            private int goodsId;
            private int num;
            private String totalMoney;
            private boolean hasComment;
            private String orderState;
            private String goodsLmie;
            private boolean hasLmie;
            private int orderStateValue;
            private String payMoney;
            private String saveMoney;
            private int id;
            private String goodsName;
            private String serviceMoney;
            private String specificationName;
            private List<ServiceOrdersBean> serviceOrders;

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

            public int getServiceNum() {
                return serviceNum;
            }

            public void setServiceNum(int serviceNum) {
                this.serviceNum = serviceNum;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getTotalMoney() {
                return totalMoney;
            }

            public void setTotalMoney(String totalMoney) {
                this.totalMoney = totalMoney;
            }

            public boolean isHasComment() {
                return hasComment;
            }

            public void setHasComment(boolean hasComment) {
                this.hasComment = hasComment;
            }

            public String getOrderState() {
                return orderState;
            }

            public void setOrderState(String orderState) {
                this.orderState = orderState;
            }

            public String getGoodsLmie() {
                return goodsLmie;
            }

            public void setGoodsLmie(String goodsLmie) {
                this.goodsLmie = goodsLmie;
            }

            public boolean isHasLmie() {
                return hasLmie;
            }

            public void setHasLmie(boolean hasLmie) {
                this.hasLmie = hasLmie;
            }

            public int getOrderStateValue() {
                return orderStateValue;
            }

            public void setOrderStateValue(int orderStateValue) {
                this.orderStateValue = orderStateValue;
            }

            public String getPayMoney() {
                return payMoney;
            }

            public void setPayMoney(String payMoney) {
                this.payMoney = payMoney;
            }

            public String getSaveMoney() {
                return saveMoney;
            }

            public void setSaveMoney(String saveMoney) {
                this.saveMoney = saveMoney;
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

            public String getServiceMoney() {
                return serviceMoney;
            }

            public void setServiceMoney(String serviceMoney) {
                this.serviceMoney = serviceMoney;
            }

            public String getSpecificationName() {
                return specificationName;
            }

            public void setSpecificationName(String specificationName) {
                this.specificationName = specificationName;
            }

            public List<ServiceOrdersBean> getServiceOrders() {
                return serviceOrders;
            }

            public void setServiceOrders(List<ServiceOrdersBean> serviceOrders) {
                this.serviceOrders = serviceOrders;
            }

            public static class ServiceOrdersBean {
                /**
                 * next : 2次
                 * warrantyPeriod : 12个月
                 * valueAddedServicesKind : 碎屏服务
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

    public static class DeliveryAddressBean {
        /**
         * code : p41510794835129
         * payMoney : 21989.00
         * addressDetail :
         * city :
         * phone :
         * purchaseOrderId : 43
         * userName : 飞龙
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
}
