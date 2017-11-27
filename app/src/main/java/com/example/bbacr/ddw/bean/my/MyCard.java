package com.example.bbacr.ddw.bean.my;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class MyCard {

    /**
     * msg : success
     * code : 1
     * datas : {"data":[{"cardsState":"未使用","img":"../phoneImg/index_coupon_icon.png","endUseTime":"2017-12-10","goodsId":3,"validDay":30,"cardsType":"商品券","cardsTypeValue":3,"goodsCategoryId":"","money":"60","goodsCategoryName":"","name":"购物券","id":13,"beginTime":"2017-11-10","goodsName":"iPhone 81(A1863) 港版"},{"cardsState":"未使用","img":"../phoneImg/index_coupon_icon.png","endUseTime":"2017-12-01","goodsId":1,"validDay":33,"cardsType":"分类券","cardsTypeValue":4,"goodsCategoryId":115,"money":"99","goodsCategoryName":"苹果类","name":"测试卡券","id":3,"beginTime":"2017-10-26","goodsName":"iPhone 8(A1863) 国行版"}],"totalPage":1,"pageNo":"1","recordsTotal":2}
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
         * data : [{"cardsState":"未使用","img":"../phoneImg/index_coupon_icon.png","endUseTime":"2017-12-10","goodsId":3,"validDay":30,"cardsType":"商品券","cardsTypeValue":3,"goodsCategoryId":"","money":"60","goodsCategoryName":"","name":"购物券","id":13,"beginTime":"2017-11-10","goodsName":"iPhone 81(A1863) 港版"},{"cardsState":"未使用","img":"../phoneImg/index_coupon_icon.png","endUseTime":"2017-12-01","goodsId":1,"validDay":33,"cardsType":"分类券","cardsTypeValue":4,"goodsCategoryId":115,"money":"99","goodsCategoryName":"苹果类","name":"测试卡券","id":3,"beginTime":"2017-10-26","goodsName":"iPhone 8(A1863) 国行版"}]
         * totalPage : 1
         * pageNo : 1
         * recordsTotal : 2
         */

        private int totalPage;
        private String pageNo;
        private int recordsTotal;
        private List<DataBean> data;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public String getPageNo() {
            return pageNo;
        }

        public void setPageNo(String pageNo) {
            this.pageNo = pageNo;
        }

        public int getRecordsTotal() {
            return recordsTotal;
        }

        public void setRecordsTotal(int recordsTotal) {
            this.recordsTotal = recordsTotal;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * cardsState : 未使用
             * img : ../phoneImg/index_coupon_icon.png
             * endUseTime : 2017-12-10
             * goodsId : 3
             * validDay : 30
             * cardsType : 商品券
             * cardsTypeValue : 3
             * goodsCategoryId :
             * money : 60
             * goodsCategoryName :
             * name : 购物券
             * id : 13
             * beginTime : 2017-11-10
             * goodsName : iPhone 81(A1863) 港版
             */

            private String cardsState;
            private String img;
            private String endUseTime;
            private String goodsId;
            private String validDay;
            private String cardsType;
            private String cardsTypeValue;
            private String goodsCategoryId;
            private String money;
            private String goodsCategoryName;
            private String name;
            private int id;
            private String beginTime;
            private String goodsName;

            public String getCardsState() {
                return cardsState;
            }

            public void setCardsState(String cardsState) {
                this.cardsState = cardsState;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getEndUseTime() {
                return endUseTime;
            }

            public void setEndUseTime(String endUseTime) {
                this.endUseTime = endUseTime;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getValidDay() {
                return validDay;
            }

            public void setValidDay(String validDay) {
                this.validDay = validDay;
            }

            public String getCardsType() {
                return cardsType;
            }

            public void setCardsType(String cardsType) {
                this.cardsType = cardsType;
            }

            public String getCardsTypeValue() {
                return cardsTypeValue;
            }

            public void setCardsTypeValue(String cardsTypeValue) {
                this.cardsTypeValue = cardsTypeValue;
            }

            public String getGoodsCategoryId() {
                return goodsCategoryId;
            }

            public void setGoodsCategoryId(String goodsCategoryId) {
                this.goodsCategoryId = goodsCategoryId;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getGoodsCategoryName() {
                return goodsCategoryName;
            }

            public void setGoodsCategoryName(String goodsCategoryName) {
                this.goodsCategoryName = goodsCategoryName;
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

            public String getBeginTime() {
                return beginTime;
            }

            public void setBeginTime(String beginTime) {
                this.beginTime = beginTime;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }
        }
    }
}
