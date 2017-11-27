package com.example.bbacr.ddw.bean;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */
public class MyFootMark {

    /**
     * msg : success
     * code : 1
     * datas : {"totalPage":1,"pageNo":1,"footMarkList":[{"marketPrice":"8888","mainImage":"http://image.ddmzl.com/a186cdf8714948b8b230f505e9100ebb20170821.png,http://image.ddmzl.com/a186cdf8714948b8b230f505e9100ebb20170821.png,http://image.ddmzl.com/a186cdf8714948b8b230f505e9100ebb20170821.png","goodsState":"上架","goodsId":2,"salesPrice":"0","name":"苹果7","goodsStateValue":1,"id":2},{"marketPrice":"8888","mainImage":"http://image.ddmzl.com/9b4c9c36b14b460091289d2222bb102b20170918.png","goodsState":"上架","goodsId":1,"salesPrice":"0","name":"苹果7","goodsStateValue":1,"id":1}],"recordsTotal":2}
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
         * footMarkList : [{"marketPrice":"8888","mainImage":"http://image.ddmzl.com/a186cdf8714948b8b230f505e9100ebb20170821.png,http://image.ddmzl.com/a186cdf8714948b8b230f505e9100ebb20170821.png,http://image.ddmzl.com/a186cdf8714948b8b230f505e9100ebb20170821.png","goodsState":"上架","goodsId":2,"salesPrice":"0","name":"苹果7","goodsStateValue":1,"id":2},{"marketPrice":"8888","mainImage":"http://image.ddmzl.com/9b4c9c36b14b460091289d2222bb102b20170918.png","goodsState":"上架","goodsId":1,"salesPrice":"0","name":"苹果7","goodsStateValue":1,"id":1}]
         * recordsTotal : 2
         */

        private int totalPage;
        private int pageNo;
        private int recordsTotal;
        private List<FootMarkListBean> footMarkList;

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

        public List<FootMarkListBean> getFootMarkList() {
            return footMarkList;
        }

        public void setFootMarkList(List<FootMarkListBean> footMarkList) {
            this.footMarkList = footMarkList;
        }

        public static class FootMarkListBean {
            /**
             * marketPrice : 8888
             * mainImage : http://image.ddmzl.com/a186cdf8714948b8b230f505e9100ebb20170821.png,http://image.ddmzl.com/a186cdf8714948b8b230f505e9100ebb20170821.png,http://image.ddmzl.com/a186cdf8714948b8b230f505e9100ebb20170821.png
             * goodsState : 上架
             * goodsId : 2
             * salesPrice : 0
             * name : 苹果7
             * goodsStateValue : 1
             * id : 2
             */

            private String marketPrice;
            private String mainImage;
            private String goodsState;
            private int goodsId;
            private String salesPrice;
            private String name;
            private int goodsStateValue;
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

            public String getGoodsState() {
                return goodsState;
            }

            public void setGoodsState(String goodsState) {
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

            public int getGoodsStateValue() {
                return goodsStateValue;
            }

            public void setGoodsStateValue(int goodsStateValue) {
                this.goodsStateValue = goodsStateValue;
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
