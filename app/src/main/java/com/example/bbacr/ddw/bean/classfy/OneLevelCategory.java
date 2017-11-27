package com.example.bbacr.ddw.bean.classfy;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class OneLevelCategory {

    /**
     * msg : success
     * code : 1
     * datas : [{"imgUrl":"http://ddw-image.oss-cn-beijing.aliyuncs.com/category.jpg","name":"手机通讯","linkUrl":"#","id":1},{"imgUrl":"http://ddw-image.oss-cn-beijing.aliyuncs.com/category.jpg","name":"手机配件","linkUrl":"#","id":59},{"imgUrl":"http://ddw-image.oss-cn-beijing.aliyuncs.com/category.jpg","name":"电脑办公","linkUrl":"#","id":2},{"imgUrl":"http://ddw-image.oss-cn-beijing.aliyuncs.com/category.jpg","name":"智能穿戴","linkUrl":"#","id":3},{"imgUrl":"http://ddw-image.oss-cn-beijing.aliyuncs.com/category.jpg","name":"数码影音","linkUrl":"#","id":4},{"imgUrl":"http://ddw-image.oss-cn-beijing.aliyuncs.com/category.jpg","name":"智能家居","linkUrl":"#","id":5},{"imgUrl":"http://ddw-image.oss-cn-beijing.aliyuncs.com/category.jpg","name":"智能出行","linkUrl":"#","id":6},{"imgUrl":"http://ddw-image.oss-cn-beijing.aliyuncs.com/category.jpg","name":"娱乐竞技","linkUrl":"#","id":7},{"imgUrl":"http://ddw-image.oss-cn-beijing.aliyuncs.com/category.jpg","name":"智能健康","linkUrl":"#","id":8}]
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
         * imgUrl : http://ddw-image.oss-cn-beijing.aliyuncs.com/category.jpg
         * name : 手机通讯
         * linkUrl : #
         * id : 1
         */

        private String imgUrl;
        private String name;
        private String linkUrl;
        private int id;

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

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
