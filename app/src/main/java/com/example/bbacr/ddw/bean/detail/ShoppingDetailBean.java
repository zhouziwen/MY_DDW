package com.example.bbacr.ddw.bean.detail;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class ShoppingDetailBean {

    /**
     * msg : success
     * code : 1
     * datas : {"commentsList":[{"imgUrl":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","replyMessage":"","id":116088,"gradedValue":3,"userName":"ziwen","propertiesName":"","content":"1122334455","avotorr":"http://image.ddmzl.com/8b6cb89c674f48019442a83adc991a2320170901.jpg"},{"imgUrl":"","replyMessage":"谢谢","id":116087,"gradedValue":5,"userName":"飞龙","propertiesName":"","content":"测试店铺","avotorr":"http://ddw-image.oss-cn-beijing.aliyuncs.com/ff13eb0e88974bdb8a37f4e27e3671ed20171025.jpg"}],"mainImg":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","marketPrice":"6000","code":"DDM932801","memberPrice":"5800","goodsService":[{"imgUrl":"http://ddm-image.oss-cn-beijing.aliyuncs.com/DdmObligation/5.png","name":"快递运输","details":"叮当网包邮服务"},{"imgUrl":"http://ddm-image.oss-cn-beijing.aliyuncs.com/DdmObligation/4.png","name":"分期付款","details":"可以分期付款"},{"imgUrl":"http://ddm-image.oss-cn-beijing.aliyuncs.com/DdmObligation/1.png","name":"以旧换新","details":"可以依旧换新"},{"imgUrl":"http://ddm-image.oss-cn-beijing.aliyuncs.com/DdmObligation/5.png","name":"叮当发货","details":"叮当网包邮服务"}],"isValueAddedServices":true,"detailsImg":"rwerwe","abbreviation":"huawei 188","goodsExplain":"【充足现货，爆款热销】双面全玻璃，无线充电，全新A11高性能芯片","isSpecification":false,"commentsCount":7,"name":"huawei 18(A1863) 国行版 绿色 128GB","id":3,"state":true,"isCollections":false}
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
         * commentsList : [{"imgUrl":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","replyMessage":"","id":116088,"gradedValue":3,"userName":"ziwen","propertiesName":"","content":"1122334455","avotorr":"http://image.ddmzl.com/8b6cb89c674f48019442a83adc991a2320170901.jpg"},{"imgUrl":"","replyMessage":"谢谢","id":116087,"gradedValue":5,"userName":"飞龙","propertiesName":"","content":"测试店铺","avotorr":"http://ddw-image.oss-cn-beijing.aliyuncs.com/ff13eb0e88974bdb8a37f4e27e3671ed20171025.jpg"}]
         * mainImg : http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png
         * marketPrice : 6000
         * code : DDM932801
         * memberPrice : 5800
         * goodsService : [{"imgUrl":"http://ddm-image.oss-cn-beijing.aliyuncs.com/DdmObligation/5.png","name":"快递运输","details":"叮当网包邮服务"},{"imgUrl":"http://ddm-image.oss-cn-beijing.aliyuncs.com/DdmObligation/4.png","name":"分期付款","details":"可以分期付款"},{"imgUrl":"http://ddm-image.oss-cn-beijing.aliyuncs.com/DdmObligation/1.png","name":"以旧换新","details":"可以依旧换新"},{"imgUrl":"http://ddm-image.oss-cn-beijing.aliyuncs.com/DdmObligation/5.png","name":"叮当发货","details":"叮当网包邮服务"}]
         * isValueAddedServices : true
         * detailsImg : rwerwe
         * abbreviation : huawei 188
         * goodsExplain : 【充足现货，爆款热销】双面全玻璃，无线充电，全新A11高性能芯片
         * isSpecification : false
         * commentsCount : 7
         * name : huawei 18(A1863) 国行版 绿色 128GB
         * id : 3
         * state : true
         * isCollections : false
         */

        private String mainImg;
        private String marketPrice;
        private String code;
        private String memberPrice;
        private boolean isValueAddedServices;
        private String detailsImg;
        private String abbreviation;
        private String goodsExplain;
        private boolean isSpecification;
        private int commentsCount;
        private String name;
        private int id;

        public String getCollectionsId() {
            return collectionsId;
        }

        public void setCollectionsId(String collectionsId) {
            this.collectionsId = collectionsId;
        }

        private boolean state;
        private String collectionsId;
        private boolean isCollections;
        private List<CommentsListBean> commentsList;
        private List<GoodsServiceBean> goodsService;

        public String getMainImg() {
            return mainImg;
        }

        public void setMainImg(String mainImg) {
            this.mainImg = mainImg;
        }

        public String getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(String marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMemberPrice() {
            return memberPrice;
        }

        public void setMemberPrice(String memberPrice) {
            this.memberPrice = memberPrice;
        }

        public boolean isIsValueAddedServices() {
            return isValueAddedServices;
        }

        public void setIsValueAddedServices(boolean isValueAddedServices) {
            this.isValueAddedServices = isValueAddedServices;
        }

        public String getDetailsImg() {
            return detailsImg;
        }

        public void setDetailsImg(String detailsImg) {
            this.detailsImg = detailsImg;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public String getGoodsExplain() {
            return goodsExplain;
        }

        public void setGoodsExplain(String goodsExplain) {
            this.goodsExplain = goodsExplain;
        }

        public boolean isIsSpecification() {
            return isSpecification;
        }

        public void setIsSpecification(boolean isSpecification) {
            this.isSpecification = isSpecification;
        }

        public int getCommentsCount() {
            return commentsCount;
        }

        public void setCommentsCount(int commentsCount) {
            this.commentsCount = commentsCount;
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

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public boolean isIsCollections() {
            return isCollections;
        }

        public void setIsCollections(boolean isCollections) {
            this.isCollections = isCollections;
        }

        public List<CommentsListBean> getCommentsList() {
            return commentsList;
        }

        public void setCommentsList(List<CommentsListBean> commentsList) {
            this.commentsList = commentsList;
        }

        public List<GoodsServiceBean> getGoodsService() {
            return goodsService;
        }

        public void setGoodsService(List<GoodsServiceBean> goodsService) {
            this.goodsService = goodsService;
        }

        public static class CommentsListBean {
            /**
             * imgUrl : http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png
             * replyMessage :
             * id : 116088
             * gradedValue : 3
             * userName : ziwen
             * propertiesName :
             * content : 1122334455
             * avotorr : http://image.ddmzl.com/8b6cb89c674f48019442a83adc991a2320170901.jpg
             */

            private String imgUrl;
            private String replyMessage;
            private int id;
            private int gradedValue;
            private String userName;
            private String propertiesName;
            private String content;
            private String avotorr;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getReplyMessage() {
                return replyMessage;
            }

            public void setReplyMessage(String replyMessage) {
                this.replyMessage = replyMessage;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getGradedValue() {
                return gradedValue;
            }

            public void setGradedValue(int gradedValue) {
                this.gradedValue = gradedValue;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getPropertiesName() {
                return propertiesName;
            }

            public void setPropertiesName(String propertiesName) {
                this.propertiesName = propertiesName;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAvotorr() {
                return avotorr;
            }

            public void setAvotorr(String avotorr) {
                this.avotorr = avotorr;
            }
        }

        public static class GoodsServiceBean {
            /**
             * imgUrl : http://ddm-image.oss-cn-beijing.aliyuncs.com/DdmObligation/5.png
             * name : 快递运输
             * details : 叮当网包邮服务
             */

            private String imgUrl;
            private String name;
            private String details;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDetails() {
                return details;
            }

            public void setDetails(String details) {
                this.details = details;
            }
        }
    }
}
