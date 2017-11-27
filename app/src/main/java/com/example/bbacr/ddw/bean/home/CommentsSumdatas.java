package com.example.bbacr.ddw.bean.home;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class CommentsSumdatas {

    /**
     * msg : success
     * code : 1
     * datas : [{"commentsProperty":"最新评价","commentsPropertyValue":1,"count":6},{"commentsProperty":"晒图","commentsPropertyValue":2,"count":1},{"commentsProperty":"好评","commentsPropertyValue":3,"count":5},{"commentsProperty":"差评","commentsPropertyValue":4,"count":1},{"commentsProperty":"中评","commentsPropertyValue":5,"count":0}]
     */

    private String msg;
    private int code;
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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * commentsProperty : 最新评价
         * commentsPropertyValue : 1
         * count : 6
         */

        private String commentsProperty;
        private int commentsPropertyValue;
        private int count;

        public String getCommentsProperty() {
            return commentsProperty;
        }

        public void setCommentsProperty(String commentsProperty) {
            this.commentsProperty = commentsProperty;
        }

        public int getCommentsPropertyValue() {
            return commentsPropertyValue;
        }

        public void setCommentsPropertyValue(int commentsPropertyValue) {
            this.commentsPropertyValue = commentsPropertyValue;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
