package com.example.bbacr.ddw.bean.car;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class GetUserShoppingCart {

    /**
     * msg : success
     * code : 1
     * datas : [{"specificationId":10,"mainImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","memberPrice":"6666.00","goodsId":3,"cartid":203,"count":1,"isSelect":true,"goodsState":true,"goodSpecificationName":"规格:大","goodsCode":"DDM932801","goodsName":"iPhone 81(A1863) 港版","valueAddedServicesList":[{"price":"500","name":"两年延保服务","isSelect":true,"id":4},{"price":"400","name":"三年延保","isSelect":false,"id":9},{"price":"38","name":"yjiu","isSelect":false,"id":6},{"price":"300","name":"两次碎屏保（限时特惠）","isSelect":false,"id":8}],"collectionsId":20},{"specificationId":11,"mainImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","memberPrice":"6666.00","goodsId":3,"cartid":199,"count":1,"isSelect":false,"goodsState":true,"goodSpecificationName":"规格:中","goodsCode":"DDM932801","goodsName":"iPhone 81(A1863) 港版","valueAddedServicesList":[{"price":"500","name":"两年延保服务","isSelect":false,"id":4},{"price":"400","name":"三年延保","isSelect":false,"id":9},{"price":"38","name":"yjiu","isSelect":false,"id":6},{"price":"300","name":"两次碎屏保（限时特惠）","isSelect":false,"id":8}],"collectionsId":20},{"specificationId":11,"mainImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","memberPrice":"6666.00","goodsId":3,"cartid":198,"count":1,"isSelect":false,"goodsState":true,"goodSpecificationName":"规格:中","goodsCode":"DDM932801","goodsName":"iPhone 81(A1863) 港版","valueAddedServicesList":[{"price":"500","name":"两年延保服务","isSelect":false,"id":4},{"price":"400","name":"三年延保","isSelect":true,"id":9},{"price":"38","name":"yjiu","isSelect":false,"id":6},{"price":"300","name":"两次碎屏保（限时特惠）","isSelect":false,"id":8}],"collectionsId":20},{"specificationId":12,"mainImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","memberPrice":"6666.00","goodsId":3,"cartid":197,"count":1,"isSelect":false,"goodsState":true,"goodSpecificationName":"规格:小","goodsCode":"DDM932801","goodsName":"iPhone 81(A1863) 港版","valueAddedServicesList":[{"price":"500","name":"两年延保服务","isSelect":false,"id":4},{"price":"400","name":"三年延保","isSelect":false,"id":9},{"price":"38","name":"yjiu","isSelect":false,"id":6},{"price":"300","name":"两次碎屏保（限时特惠）","isSelect":false,"id":8}],"collectionsId":20},{"specificationId":12,"mainImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","memberPrice":"6666.00","goodsId":3,"cartid":196,"count":1,"isSelect":false,"goodsState":true,"goodSpecificationName":"规格:小","goodsCode":"DDM932801","goodsName":"iPhone 81(A1863) 港版","valueAddedServicesList":[{"price":"500","name":"两年延保服务","isSelect":true,"id":4},{"price":"400","name":"三年延保","isSelect":false,"id":9},{"price":"38","name":"yjiu","isSelect":false,"id":6},{"price":"300","name":"两次碎屏保（限时特惠）","isSelect":false,"id":8}],"collectionsId":20},{"specificationId":2,"mainImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","memberPrice":"6666.00","goodsId":1,"cartid":195,"count":1,"isSelect":true,"goodsState":true,"goodSpecificationName":"颜色:黄,版本:258G","goodsCode":"DDM641903","goodsName":"iPhone 8(A1863) 国行版","valueAddedServicesList":[],"collectionsId":""},{"specificationId":1,"mainImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","memberPrice":"6666.00","goodsId":1,"cartid":193,"count":2,"isSelect":true,"goodsState":true,"goodSpecificationName":"颜色:白,版本:32G","goodsCode":"DDM641903","goodsName":"iPhone 8(A1863) 国行版","valueAddedServicesList":[],"collectionsId":""}]
     * totalMoney : 27164.00
     * rowCount : 3
     */

    private String msg;
    private int code;
    private String totalMoney;
    private int rowCount;
    private List<DatasBean> datas;

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

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * specificationId : 10
         * mainImg : http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png
         * memberPrice : 6666.00
         * goodsId : 3
         * cartid : 203
         * count : 1
         * isSelect : true
         * goodsState : true
         * goodSpecificationName : 规格:大
         * goodsCode : DDM932801
         * goodsName : iPhone 81(A1863) 港版
         * valueAddedServicesList : [{"price":"500","name":"两年延保服务","isSelect":true,"id":4},{"price":"400","name":"三年延保","isSelect":false,"id":9},{"price":"38","name":"yjiu","isSelect":false,"id":6},{"price":"300","name":"两次碎屏保（限时特惠）","isSelect":false,"id":8}]
         * collectionsId : 20
         */

        private int specificationId;
        private String mainImg;
        private String memberPrice;
        private int goodsId;
        private int cartid;
        private int count;
        private boolean isSelect;
        private boolean goodsState;
        private String goodSpecificationName;
        private String goodsCode;
        private String goodsName;
        private String collectionsId;
        private List<ValueAddedServicesListBean> valueAddedServicesList;

        public int getSpecificationId() {
            return specificationId;
        }

        public void setSpecificationId(int specificationId) {
            this.specificationId = specificationId;
        }

        public String getMainImg() {
            return mainImg;
        }

        public void setMainImg(String mainImg) {
            this.mainImg = mainImg;
        }

        public String getMemberPrice() {
            return memberPrice;
        }

        public void setMemberPrice(String memberPrice) {
            this.memberPrice = memberPrice;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getCartid() {
            return cartid;
        }

        public void setCartid(int cartid) {
            this.cartid = cartid;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public boolean isIsSelect() {
            return isSelect;
        }

        public void setIsSelect(boolean isSelect) {
            this.isSelect = isSelect;
        }

        public boolean isGoodsState() {
            return goodsState;
        }

        public void setGoodsState(boolean goodsState) {
            this.goodsState = goodsState;
        }

        public String getGoodSpecificationName() {
            return goodSpecificationName;
        }

        public void setGoodSpecificationName(String goodSpecificationName) {
            this.goodSpecificationName = goodSpecificationName;
        }

        public String getGoodsCode() {
            return goodsCode;
        }

        public void setGoodsCode(String goodsCode) {
            this.goodsCode = goodsCode;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getCollectionsId() {
            return collectionsId;
        }

        public void setCollectionsId(String collectionsId) {
            this.collectionsId = collectionsId;
        }

        public List<ValueAddedServicesListBean> getValueAddedServicesList() {
            return valueAddedServicesList;
        }

        public void setValueAddedServicesList(List<ValueAddedServicesListBean> valueAddedServicesList) {
            this.valueAddedServicesList = valueAddedServicesList;
        }

        public static class ValueAddedServicesListBean {
            /**
             * price : 500
             * name : 两年延保服务
             * isSelect : true
             * id : 4
             */

            private String price;
            private String name;
            private boolean isSelect;
            private int id;

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

            public boolean isIsSelect() {
                return isSelect;
            }

            public void setIsSelect(boolean isSelect) {
                this.isSelect = isSelect;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
