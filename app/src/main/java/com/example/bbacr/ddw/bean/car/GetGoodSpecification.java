package com.example.bbacr.ddw.bean.car;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class GetGoodSpecification {

    /**
     * msg : success
     * code : 1
     * datas : {"propertiesId":"2:1,3:4","image":"http://gw.alicdn.com/bao/uploaded/i4/TB1mSlAegMPMeJjy1Xdc89srXXa_1136x1136Q75.jpg_.webp","maketPirce":"8888.00","goodName":"iPhone 8(A1863) 国行版 金色 64GB","memberPrice":"6666.00","createTime":"2017-09-17 21:29:21","stages":[{"specificationId":1,"createUserId":1,"createTime":"Mon Oct 23 16:39:40 CST 2017","instalmentAmount":"315.20","periods":10,"id":2,"state":1,"prepayment":"1700.00"},{"specificationId":1,"createUserId":1,"createTime":"Fri Oct 20 00:22:00 CST 2017","instalmentAmount":"325.23","periods":12,"id":1,"state":1,"prepayment":"1600.12"}],"ddPrice":"0.00","createUserName":"助利商城","id":1,"propertiesName":"颜色:白,版本:32G","goodState":"上架"}
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
         * propertiesId : 2:1,3:4
         * image : http://gw.alicdn.com/bao/uploaded/i4/TB1mSlAegMPMeJjy1Xdc89srXXa_1136x1136Q75.jpg_.webp
         * maketPirce : 8888.00
         * goodName : iPhone 8(A1863) 国行版 金色 64GB
         * memberPrice : 6666.00
         * createTime : 2017-09-17 21:29:21
         * stages : [{"specificationId":1,"createUserId":1,"createTime":"Mon Oct 23 16:39:40 CST 2017","instalmentAmount":"315.20","periods":10,"id":2,"state":1,"prepayment":"1700.00"},{"specificationId":1,"createUserId":1,"createTime":"Fri Oct 20 00:22:00 CST 2017","instalmentAmount":"325.23","periods":12,"id":1,"state":1,"prepayment":"1600.12"}]
         * ddPrice : 0.00
         * createUserName : 助利商城
         * id : 1
         * propertiesName : 颜色:白,版本:32G
         * goodState : 上架
         */

        private String propertiesId;
        private String image;
        private String maketPirce;
        private String goodName;
        private String memberPrice;
        private String createTime;
        private String ddPrice;
        private String createUserName;
        private int id;
        private String propertiesName;
        private String goodState;
        private List<StagesBean> stages;

        public String getPropertiesId() {
            return propertiesId;
        }

        public void setPropertiesId(String propertiesId) {
            this.propertiesId = propertiesId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getMaketPirce() {
            return maketPirce;
        }

        public void setMaketPirce(String maketPirce) {
            this.maketPirce = maketPirce;
        }

        public String getGoodName() {
            return goodName;
        }

        public void setGoodName(String goodName) {
            this.goodName = goodName;
        }

        public String getMemberPrice() {
            return memberPrice;
        }

        public void setMemberPrice(String memberPrice) {
            this.memberPrice = memberPrice;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDdPrice() {
            return ddPrice;
        }

        public void setDdPrice(String ddPrice) {
            this.ddPrice = ddPrice;
        }

        public String getCreateUserName() {
            return createUserName;
        }

        public void setCreateUserName(String createUserName) {
            this.createUserName = createUserName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPropertiesName() {
            return propertiesName;
        }

        public void setPropertiesName(String propertiesName) {
            this.propertiesName = propertiesName;
        }

        public String getGoodState() {
            return goodState;
        }

        public void setGoodState(String goodState) {
            this.goodState = goodState;
        }

        public List<StagesBean> getStages() {
            return stages;
        }

        public void setStages(List<StagesBean> stages) {
            this.stages = stages;
        }

        public static class StagesBean {
            /**
             * specificationId : 1
             * createUserId : 1
             * createTime : Mon Oct 23 16:39:40 CST 2017
             * instalmentAmount : 315.20
             * periods : 10
             * id : 2
             * state : 1
             * prepayment : 1700.00
             */

            private int specificationId;
            private int createUserId;
            private String createTime;
            private String instalmentAmount;
            private int periods;
            private int id;
            private int state;
            private String prepayment;

            public int getSpecificationId() {
                return specificationId;
            }

            public void setSpecificationId(int specificationId) {
                this.specificationId = specificationId;
            }

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getInstalmentAmount() {
                return instalmentAmount;
            }

            public void setInstalmentAmount(String instalmentAmount) {
                this.instalmentAmount = instalmentAmount;
            }

            public int getPeriods() {
                return periods;
            }

            public void setPeriods(int periods) {
                this.periods = periods;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getPrepayment() {
                return prepayment;
            }

            public void setPrepayment(String prepayment) {
                this.prepayment = prepayment;
            }
        }
    }
}
