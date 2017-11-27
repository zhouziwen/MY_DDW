package com.example.bbacr.ddw.bean.my;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */
public class SearchCards {

    /**
     * msg : success
     * code : 1
     * datas : {"data":[{"img":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","money":"60","name":"iPhone 81(A1863) 港版类","cardType":"商品券","validDay":30,"id":6},{"img":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","money":"60","name":"iPhone 81(A1863) 港版类","cardType":"商品券","validDay":30,"id":5},{"img":"http://img2.ch999img.com/pic/category/201704120956590.jpg","money":"99","name":"苹果类","cardType":"分类券","validDay":33,"id":3},{"img":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","money":"10","name":"iPhone 81(A1863) 港版类","cardType":"商品券","validDay":30,"id":2},{"img":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","money":"30","name":"iPhone 81(A1863) 港版类","cardType":"商品券","validDay":30,"id":1}],"totalPage":1,"pageNo":"1","recordsTotal":5}
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
         * data : [{"img":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","money":"60","name":"iPhone 81(A1863) 港版类","cardType":"商品券","validDay":30,"id":6},{"img":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","money":"60","name":"iPhone 81(A1863) 港版类","cardType":"商品券","validDay":30,"id":5},{"img":"http://img2.ch999img.com/pic/category/201704120956590.jpg","money":"99","name":"苹果类","cardType":"分类券","validDay":33,"id":3},{"img":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","money":"10","name":"iPhone 81(A1863) 港版类","cardType":"商品券","validDay":30,"id":2},{"img":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","money":"30","name":"iPhone 81(A1863) 港版类","cardType":"商品券","validDay":30,"id":1}]
         * totalPage : 1
         * pageNo : 1
         * recordsTotal : 5
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
             * img : http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png
             * money : 60
             * name : iPhone 81(A1863) 港版类
             * cardType : 商品券
             * validDay : 30
             * id : 6
             */

            private String img;
            private String money;
            private String name;
            private String cardType;
            private int validDay;
            private int id;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCardType() {
                return cardType;
            }

            public void setCardType(String cardType) {
                this.cardType = cardType;
            }

            public int getValidDay() {
                return validDay;
            }

            public void setValidDay(int validDay) {
                this.validDay = validDay;
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
