package com.example.bbacr.ddw.bean.my;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class GetUserHeadData {

    /**
     * msg : success
     * code : 1
     * datas : {"footMarkNum":0,"collectionNum":0,"Avotorr":"http://image.ddmzl.com/06d2bbcbfaf145d38bf59b23b460847920170930.jpg","count":18,"AccountMoney":"19905.00","RealName":"15738961968"}
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
         * footMarkNum : 0
         * collectionNum : 0
         * Avotorr : http://image.ddmzl.com/06d2bbcbfaf145d38bf59b23b460847920170930.jpg
         * count : 18
         * AccountMoney : 19905.00
         * RealName : 15738961968
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
