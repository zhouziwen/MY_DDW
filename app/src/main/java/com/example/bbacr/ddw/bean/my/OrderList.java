package com.example.bbacr.ddw.bean.my;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class OrderList {


    /**
     * msg : success
     * code : 1
     * totalPage : 1
     * pageNo : 1
     * purchaseOrders : [{"shopOrders":[{"serviceNum":1,"code":"c151510372199231","orderStateValue":1,"payMoney":"7106.00","createTime":"2017-11-11 11:49:59","goodsOrders":[{"goodsImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","unitPrice":"6666.00","serviceNum":1,"code":"i151510372199703","goodsId":3,"num":1,"hasComment":false,"orderState":"创建订单","orderStateValue":1,"payMoney":"7106.00","id":21,"goodsName":"iPhone 81(A1863) 港版","serviceMoney":"500.0"}],"shopName":"总仓店铺","id":18,"hasComment":false,"serviceMoney":500,"orderState":"创建订单"}],"serviceNum":1,"code":"p151510372199135","orderStateValue":1,"payMoney":"7106.00","createTime":"2017-11-11 11:49:59","id":18,"goodsOrderNum":1,"serviceMoney":500,"orderState":"创建订单"},{"shopOrders":[{"serviceNum":2,"code":"c151510365063563","orderStateValue":5,"payMoney":"7166.00","createTime":"2017-11-11 09:51:04","goodsOrders":[{"goodsImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","unitPrice":"6666.00","serviceNum":2,"code":"i151510365063763","goodsId":3,"num":1,"hasComment":false,"orderState":"已发货","orderStateValue":5,"payMoney":"7166.00","id":4,"goodsName":"iPhone 81(A1863) 港版","serviceMoney":"500.0"}],"shopName":"总仓店铺","id":4,"hasComment":false,"serviceMoney":500,"orderState":"已发货"}],"serviceNum":2,"code":"p151510365063475","orderStateValue":3,"payMoney":"7166.00","createTime":"2017-11-11 09:51:03","id":4,"goodsOrderNum":1,"serviceMoney":500,"orderState":"已支付"},{"shopOrders":[{"serviceNum":5,"code":"c151510363874878","orderStateValue":2,"payMoney":"7844.00","createTime":"2017-11-11 09:31:15","goodsOrders":[{"goodsImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","unitPrice":"6666.00","serviceNum":5,"code":"i151510363875302","goodsId":3,"num":1,"hasComment":false,"orderState":"已取消","orderStateValue":2,"payMoney":"7844.00","id":3,"goodsName":"iPhone 81(A1863) 港版","serviceMoney":"1238.0"}],"shopName":"总仓店铺","id":3,"hasComment":false,"serviceMoney":1238,"orderState":"已取消"}],"serviceNum":5,"code":"p151510363874801","orderStateValue":2,"payMoney":"7844.00","createTime":"2017-11-11 09:31:15","id":3,"goodsOrderNum":1,"serviceMoney":1238,"orderState":"已取消"},{"shopOrders":[{"serviceNum":0,"code":"c151510363785166","orderStateValue":1,"payMoney":"6666.00","createTime":"2017-11-11 09:29:45","goodsOrders":[{"goodsImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","unitPrice":"6666.00","serviceNum":0,"code":"i151510363785698","orderStateValue":1,"payMoney":"6666.00","goodsId":1,"num":1,"id":2,"hasComment":false,"goodsName":"iPhone 8(A1863) 国行版","orderState":"创建订单"}],"shopName":"总仓店铺","id":2,"hasComment":false,"serviceMoney":0,"orderState":"创建订单"}],"serviceNum":0,"code":"p151510363785072","orderStateValue":1,"payMoney":"6666.00","createTime":"2017-11-11 09:29:45","id":2,"goodsOrderNum":1,"serviceMoney":0,"orderState":"创建订单"}]
     * recordsTotal : 4
     */

    private String msg;
    private int code;
    private int totalPage;
    private int pageNo;
    private int recordsTotal;
    private List<PurchaseOrdersBean> purchaseOrders;

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

    public List<PurchaseOrdersBean> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(List<PurchaseOrdersBean> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public static class PurchaseOrdersBean {
        /**
         * shopOrders : [{"serviceNum":1,"code":"c151510372199231","orderStateValue":1,"payMoney":"7106.00","createTime":"2017-11-11 11:49:59","goodsOrders":[{"goodsImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","unitPrice":"6666.00","serviceNum":1,"code":"i151510372199703","goodsId":3,"num":1,"hasComment":false,"orderState":"创建订单","orderStateValue":1,"payMoney":"7106.00","id":21,"goodsName":"iPhone 81(A1863) 港版","serviceMoney":"500.0"}],"shopName":"总仓店铺","id":18,"hasComment":false,"serviceMoney":500,"orderState":"创建订单"}]
         * serviceNum : 1
         * code : p151510372199135
         * orderStateValue : 1
         * payMoney : 7106.00
         * createTime : 2017-11-11 11:49:59
         * id : 18
         * goodsOrderNum : 1
         * serviceMoney : 500
         * orderState : 创建订单
         */

        private int serviceNum;
        private String code;
        private int orderStateValue;
        private String payMoney;
        private String createTime;
        private int id;
        private int goodsOrderNum;
        private int serviceMoney;
        private String orderState;
        private List<ShopOrdersBean> shopOrders;

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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGoodsOrderNum() {
            return goodsOrderNum;
        }

        public void setGoodsOrderNum(int goodsOrderNum) {
            this.goodsOrderNum = goodsOrderNum;
        }

        public int getServiceMoney() {
            return serviceMoney;
        }

        public void setServiceMoney(int serviceMoney) {
            this.serviceMoney = serviceMoney;
        }

        public String getOrderState() {
            return orderState;
        }

        public void setOrderState(String orderState) {
            this.orderState = orderState;
        }

        public List<ShopOrdersBean> getShopOrders() {
            return shopOrders;
        }

        public void setShopOrders(List<ShopOrdersBean> shopOrders) {
            this.shopOrders = shopOrders;
        }

        public static class ShopOrdersBean {
            /**
             * serviceNum : 1
             * code : c151510372199231
             * orderStateValue : 1
             * payMoney : 7106.00
             * createTime : 2017-11-11 11:49:59
             * goodsOrders : [{"goodsImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","unitPrice":"6666.00","serviceNum":1,"code":"i151510372199703","goodsId":3,"num":1,"hasComment":false,"orderState":"创建订单","orderStateValue":1,"payMoney":"7106.00","id":21,"goodsName":"iPhone 81(A1863) 港版","serviceMoney":"500.0"}]
             * shopName : 总仓店铺
             * id : 18
             * hasComment : false
             * serviceMoney : 500
             * orderState : 创建订单
             */

            private int serviceNum;
            private String code;
            private int orderStateValue;
            private String payMoney;
            private String createTime;
            private String shopName;
            private int id;
            private boolean hasComment;
            private int serviceMoney;
            private String orderState;
            private List<GoodsOrdersBean> goodsOrders;

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

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
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

            public boolean isHasComment() {
                return hasComment;
            }

            public void setHasComment(boolean hasComment) {
                this.hasComment = hasComment;
            }

            public int getServiceMoney() {
                return serviceMoney;
            }

            public void setServiceMoney(int serviceMoney) {
                this.serviceMoney = serviceMoney;
            }

            public String getOrderState() {
                return orderState;
            }

            public void setOrderState(String orderState) {
                this.orderState = orderState;
            }

            public List<GoodsOrdersBean> getGoodsOrders() {
                return goodsOrders;
            }

            public void setGoodsOrders(List<GoodsOrdersBean> goodsOrders) {
                this.goodsOrders = goodsOrders;
            }

            public static class GoodsOrdersBean {
                /**
                 * goodsImg : http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png
                 * unitPrice : 6666.00
                 * serviceNum : 1
                 * code : i151510372199703
                 * goodsId : 3
                 * num : 1
                 * hasComment : false
                 * orderState : 创建订单
                 * orderStateValue : 1
                 * payMoney : 7106.00
                 * id : 21
                 * goodsName : iPhone 81(A1863) 港版
                 * serviceMoney : 500.0
                 */

                private String goodsImg;
                private String unitPrice;
                private int serviceNum;
                private String code;
                private int goodsId;
                private int num;
                private boolean hasComment;
                private String orderState;
                private int orderStateValue;
                private String payMoney;
                private int id;
                private String goodsName;
                private String serviceMoney;

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
            }
        }
    }
}
