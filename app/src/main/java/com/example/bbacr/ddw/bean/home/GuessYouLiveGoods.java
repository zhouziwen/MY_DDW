package com.example.bbacr.ddw.bean.home;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class GuessYouLiveGoods {

    /**
     * msg : success
     * code : 1
     * datas : [{"mainImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","memberPrice":"8500","name":"iPhone 8(A1863) 国行版","id":1},{"mainImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","memberPrice":"5800","name":"iPhone 81(A1863) 港版","id":3}]
     * totalPage : 1
     * recordsTotal : 2
     * pageNow : 1
     */

    private String msg;
    private int code;
    private int totalPage;
    private int recordsTotal;
    private int pageNow;
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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * mainImg : http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png
         * memberPrice : 8500
         * name : iPhone 8(A1863) 国行版
         * id : 1
         */

        private String mainImg;
        private String memberPrice;
        private String name;
        private int id;

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
