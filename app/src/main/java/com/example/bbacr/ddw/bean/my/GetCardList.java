package com.example.bbacr.ddw.bean.my;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class GetCardList {

    /**
     * msg : success
     * code : 1
     * datas : {"cardList":[{"cardNum":"62365987456123654897","bankId":5,"bank":"农业银行","isDefault":true,"accountName":"李文亚","kaihuhang":"郑州","cardNumShort":"4897","id":229,"state":true,"bankPicture":"http://ddm-image.oss-cn-beijing.aliyuncs.com/%E4%B8%AD%E5%9B%BD%E5%86%9C%E4%B8%9A%E9%93%B6%E8%A1%8C%402x.png","userKey":"93328a05-aee0-434a-a65d-9dba06845dd4","bankImg":"http://ddm-image.oss-cn-beijing.aliyuncs.com/bank_img/5.png"}],"totalPage":1,"recordsTotal":1,"accountMoney":"1158830.05"}
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
         * cardList : [{"cardNum":"62365987456123654897","bankId":5,"bank":"农业银行","isDefault":true,"accountName":"李文亚","kaihuhang":"郑州","cardNumShort":"4897","id":229,"state":true,"bankPicture":"http://ddm-image.oss-cn-beijing.aliyuncs.com/%E4%B8%AD%E5%9B%BD%E5%86%9C%E4%B8%9A%E9%93%B6%E8%A1%8C%402x.png","userKey":"93328a05-aee0-434a-a65d-9dba06845dd4","bankImg":"http://ddm-image.oss-cn-beijing.aliyuncs.com/bank_img/5.png"}]
         * totalPage : 1
         * recordsTotal : 1
         * accountMoney : 1158830.05
         */

        private int totalPage;
        private int recordsTotal;
        private String accountMoney;
        private List<CardListBean> cardList;

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

        public String getAccountMoney() {
            return accountMoney;
        }

        public void setAccountMoney(String accountMoney) {
            this.accountMoney = accountMoney;
        }

        public List<CardListBean> getCardList() {
            return cardList;
        }

        public void setCardList(List<CardListBean> cardList) {
            this.cardList = cardList;
        }

        public static class CardListBean {
            /**
             * cardNum : 62365987456123654897
             * bankId : 5
             * bank : 农业银行
             * isDefault : true
             * accountName : 李文亚
             * kaihuhang : 郑州
             * cardNumShort : 4897
             * id : 229
             * state : true
             * bankPicture : http://ddm-image.oss-cn-beijing.aliyuncs.com/%E4%B8%AD%E5%9B%BD%E5%86%9C%E4%B8%9A%E9%93%B6%E8%A1%8C%402x.png
             * userKey : 93328a05-aee0-434a-a65d-9dba06845dd4
             * bankImg : http://ddm-image.oss-cn-beijing.aliyuncs.com/bank_img/5.png
             */

            private String cardNum;
            private int bankId;
            private String bank;
            private boolean isDefault;
            private String accountName;
            private String kaihuhang;
            private String cardNumShort;
            private int id;
            private boolean state;
            private String bankPicture;
            private String userKey;
            private String bankImg;

            public String getCardNum() {
                return cardNum;
            }

            public void setCardNum(String cardNum) {
                this.cardNum = cardNum;
            }

            public int getBankId() {
                return bankId;
            }

            public void setBankId(int bankId) {
                this.bankId = bankId;
            }

            public String getBank() {
                return bank;
            }

            public void setBank(String bank) {
                this.bank = bank;
            }

            public boolean isIsDefault() {
                return isDefault;
            }

            public void setIsDefault(boolean isDefault) {
                this.isDefault = isDefault;
            }

            public String getAccountName() {
                return accountName;
            }

            public void setAccountName(String accountName) {
                this.accountName = accountName;
            }

            public String getKaihuhang() {
                return kaihuhang;
            }

            public void setKaihuhang(String kaihuhang) {
                this.kaihuhang = kaihuhang;
            }

            public String getCardNumShort() {
                return cardNumShort;
            }

            public void setCardNumShort(String cardNumShort) {
                this.cardNumShort = cardNumShort;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public boolean isState() {
                return state;
            }

            public void setState(boolean state) {
                this.state = state;
            }

            public String getBankPicture() {
                return bankPicture;
            }

            public void setBankPicture(String bankPicture) {
                this.bankPicture = bankPicture;
            }

            public String getUserKey() {
                return userKey;
            }

            public void setUserKey(String userKey) {
                this.userKey = userKey;
            }

            public String getBankImg() {
                return bankImg;
            }

            public void setBankImg(String bankImg) {
                this.bankImg = bankImg;
            }
        }
    }
}
