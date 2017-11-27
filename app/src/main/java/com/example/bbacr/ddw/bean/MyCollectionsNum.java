package com.example.bbacr.ddw.bean;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class MyCollectionsNum {

    /**
     * msg : success
     * code : 1
     * datas : {"footMarkNum":2,"collectionNum":1,"Avotorr":"http://ddw-image.oss-cn-beijing.aliyuncs.com/ff13eb0e88974bdb8a37f4e27e3671ed20171025.jpg","count":114,"AccountMoney":"64854.05","RealName":"飞龙"}
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
         * footMarkNum : 2
         * collectionNum : 1
         * Avotorr : http://ddw-image.oss-cn-beijing.aliyuncs.com/ff13eb0e88974bdb8a37f4e27e3671ed20171025.jpg
         * count : 114
         * AccountMoney : 64854.05
         * RealName : 飞龙
         */

        private String footMarkNum;
        private String collectionNum;
        private String Avotorr;
        private int count;
        private String AccountMoney;
        private String RealName;

        public String getFootMarkNum() {
            return footMarkNum;
        }

        public void setFootMarkNum(String footMarkNum) {
            this.footMarkNum = footMarkNum;
        }

        public String getCollectionNum() {
            return collectionNum;
        }

        public void setCollectionNum(String collectionNum) {
            this.collectionNum = collectionNum;
        }

        public String getAvotorr() {
            return Avotorr;
        }

        public void setAvotorr(String Avotorr) {
            this.Avotorr = Avotorr;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getAccountMoney() {
            return AccountMoney;
        }

        public void setAccountMoney(String AccountMoney) {
            this.AccountMoney = AccountMoney;
        }

        public String getRealName() {
            return RealName;
        }

        public void setRealName(String RealName) {
            this.RealName = RealName;
        }
    }
}
