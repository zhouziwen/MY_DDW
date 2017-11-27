package com.example.bbacr.ddw.bean.my;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class OrderComment {

    /**
     * msg : success
     * code : 1
     * datas : {"shopOrderId":"20","goodsList":[{"goodsImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","goodsId":3,"id":23,"goodsName":"iPhone 81(A1863) 港版","specificationName":"规格:中"}]}
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
         * shopOrderId : 20
         * goodsList : [{"goodsImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","goodsId":3,"id":23,"goodsName":"iPhone 81(A1863) 港版","specificationName":"规格:中"}]
         */

        private String shopOrderId;
        private List<GoodsListBean> goodsList;

        public String getShopOrderId() {
            return shopOrderId;
        }

        public void setShopOrderId(String shopOrderId) {
            this.shopOrderId = shopOrderId;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class GoodsListBean {
            /**
             * goodsImg : http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png
             * goodsId : 3
             * id : 23
             * goodsName : iPhone 81(A1863) 港版
             * specificationName : 规格:中
             */

            private String goodsImg;
            private int goodsId;
            private int id;
            private String goodsName;
            private String specificationName;

            public String getGoodsImg() {
                return goodsImg;
            }

            public void setGoodsImg(String goodsImg) {
                this.goodsImg = goodsImg;
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

            public String getSpecificationName() {
                return specificationName;
            }

            public void setSpecificationName(String specificationName) {
                this.specificationName = specificationName;
            }
        }
    }
}
