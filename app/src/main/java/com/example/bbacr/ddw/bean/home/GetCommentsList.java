package com.example.bbacr.ddw.bean.home;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class GetCommentsList {

    /**
     * msg : success
     * code : 1
     * datas : [{"imgUrl":"","replyMessage":"谢谢","createTime":"2017-10-01","id":116087,"gradedValue":5,"userName":"飞龙","propertiesName":"","content":"测试店铺","avotorr":"http://ddw-image.oss-cn-beijing.aliyuncs.com/ff13eb0e88974bdb8a37f4e27e3671ed20171025.jpg"},{"imgUrl":"","replyMessage":"","createTime":"2017-10-01","id":116086,"gradedValue":5,"userName":"飞龙","propertiesName":"","content":"测试2","avotorr":"http://ddw-image.oss-cn-beijing.aliyuncs.com/ff13eb0e88974bdb8a37f4e27e3671ed20171025.jpg"},{"imgUrl":"","replyMessage":"","createTime":"2017-10-01","id":116085,"gradedValue":5,"userName":"飞龙","propertiesName":"","content":"测试1","avotorr":"http://ddw-image.oss-cn-beijing.aliyuncs.com/ff13eb0e88974bdb8a37f4e27e3671ed20171025.jpg"},{"imgUrl":"","replyMessage":"谢谢","createTime":"2017-09-28","id":116084,"gradedValue":4,"userName":"ziwen","propertiesName":"","content":"hao1","avotorr":"http://image.ddmzl.com/8b6cb89c674f48019442a83adc991a2320170901.jpg"},{"imgUrl":"","replyMessage":"谢谢","createTime":"2017-09-28","id":116083,"gradedValue":4,"userName":"ziwen","propertiesName":"","content":"nr2","avotorr":"http://image.ddmzl.com/8b6cb89c674f48019442a83adc991a2320170901.jpg"},{"imgUrl":"","replyMessage":"","createTime":"2017-09-28","id":116082,"gradedValue":1,"userName":"ziwen","propertiesName":"","content":"差评！","avotorr":"http://image.ddmzl.com/8b6cb89c674f48019442a83adc991a2320170901.jpg"}]
     * totalPage : 1
     * recordsTotal : 6
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
         * imgUrl :
         * replyMessage : 谢谢
         * createTime : 2017-10-01
         * id : 116087
         * gradedValue : 5
         * userName : 飞龙
         * propertiesName :
         * content : 测试店铺
         * avotorr : http://ddw-image.oss-cn-beijing.aliyuncs.com/ff13eb0e88974bdb8a37f4e27e3671ed20171025.jpg
         */

        private String imgUrl;
        private String replyMessage;
        private String createTime;
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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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
}
