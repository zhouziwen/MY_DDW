package com.example.bbacr.ddw.bean.my;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class MyCollections {


    /**
     * msg : success
     * code : 1
     * datas : {"totalPage":1,"pageNo":1,"recordsTotal":2,"collectionsList":[{"marketPrice":"6000","mainImage":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","goodsState":true,"goodsId":1,"salesPrice":"8500","name":"iPhone 8(A1863) 国行版 金色 64GB","id":25},{"marketPrice":"6000","mainImage":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","goodsState":true,"goodsId":3,"salesPrice":"5800","name":"huawei 18(A1863) 国行版 绿色 128GB","id":23}]}
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
         * totalPage : 1
         * pageNo : 1
         * recordsTotal : 2
         * collectionsList : [{"marketPrice":"6000","mainImage":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","goodsState":true,"goodsId":1,"salesPrice":"8500","name":"iPhone 8(A1863) 国行版 金色 64GB","id":25},{"marketPrice":"6000","mainImage":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","goodsState":true,"goodsId":3,"salesPrice":"5800","name":"huawei 18(A1863) 国行版 绿色 128GB","id":23}]
         */

        private int totalPage;
        private int pageNo;
        private int recordsTotal;
        private List<CollectionsListBean> collectionsList;

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

        public List<CollectionsListBean> getCollectionsList() {
            return collectionsList;
        }

        public void setCollectionsList(List<CollectionsListBean> collectionsList) {
            this.collectionsList = collectionsList;
        }

        public static class CollectionsListBean {
            /**
             * marketPrice : 6000
             * mainImage : http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png
             * goodsState : true
             * goodsId : 1
             * salesPrice : 8500
             * name : iPhone 8(A1863) 国行版 金色 64GB
             * id : 25
             */

            private String marketPrice;
            private String mainImage;
            private boolean goodsState;
            private int goodsId;
            private String salesPrice;
            private String name;
            private int id;

            public String getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(String marketPrice) {
                this.marketPrice = marketPrice;
            }

            public String getMainImage() {
                return mainImage;
            }

            public void setMainImage(String mainImage) {
                this.mainImage = mainImage;
            }

            public boolean isGoodsState() {
                return goodsState;
            }

            public void setGoodsState(boolean goodsState) {
                this.goodsState = goodsState;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public String getSalesPrice() {
                return salesPrice;
            }

            public void setSalesPrice(String salesPrice) {
                this.salesPrice = salesPrice;
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
        }
    }
}
